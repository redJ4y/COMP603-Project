package Controller;

/*
GameDriver contains the main method and runs the game.
This driver is extra long because it is acting as both the controller and view (console user interface).
When I separate them for part 2, the controller will become much more concise.
Separating the view from the controller at this stage would only add unnecessary complexity.
 */
// @author Jared Scholz
import Model.Data.DBManager;
import Model.Entity.Inventory;
import Model.Entity.Item;
import Model.Map.Enemy;
import Model.Map.Event;
import Model.Map.GameMap;
import Model.Map.Merchant;
import Model.Map.PassiveEvent;
import Model.Map.Scene;
import Model.Map.Trap;
import Model.Entity.Player;
import Model.Entity.Potion;
import Model.Entity.StatType;
import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

public class GameDriver {

    public final static int MAP_SIZE = 17; // should never be changed when using a saved game

    private final DBManager dataKeeper;
    private GameMap gameMap;
    private Player player;

    private final Scanner scanner;
    private String ANSI_GREEN = ""; // "\u001B[32m" if enabled
    private String ANSI_RED = ""; // "\u001B[31m" if enabled
    private String ANSI_RESET = ""; // "\u001B[0m" if enabled

    public GameDriver() {
        dataKeeper = new DBManager();
        scanner = new Scanner(System.in);

        String username = checkForGameSave();
        // if no game save was found, the input name is returned to make a new game with
        // if null is returned, gameMap and player have already been initialized
        if (username != null) {
            // initialize new game:
            gameMap = new GameMap(MAP_SIZE, dataKeeper); // generate random map
            player = new Player(username, MAP_SIZE / 2, MAP_SIZE / 2); // generate new player (center of map)
        }

        enableTextColors(); // give the option to enable text colors
    }

    /* Looks for a game save - if there isn't, return input name */
    private String checkForGameSave() {
        System.out.println("-=- Pre-Game Menu -=-");
        System.out.println("Enter a username (one word):");
        String username = "*invalid*";
        while (!username.matches("^[A-za-z0-9]{1,255}$")) { // don't allow special characters, limit 255
            System.out.print("> ");
            username = scanner.nextLine();
            username = username.contains(" ") ? username.split(" ")[0] : username; // extract first word

            if (!username.matches("^[A-za-z0-9]{1,255}$")) {
                System.out.println("Username must not contain special characters. Try again:");
            }
        } // this ensures that the username is a valid option (valid in file name)

        if (dataKeeper.checkGameSave(username)) {
            // a game save with this username exists
            System.out.println("User found, loading saved game...\n");
            gameMap = dataKeeper.getExistingMap();
            player = dataKeeper.getExistingPlayer();
            return null;
        }
        System.out.println("Generating new game...\n");
        return username; // if no game save was found, the input name is returned to make a new game with
    }

    /* Allow the user to change prompt text color */
    private void enableTextColors() {
        System.out.println("Would you like prompt text to appear green? (Y/N):");
        if (scanYesNo()) {
            ANSI_GREEN = "\u001B[32m";
            ANSI_RED = "\u001B[31m";
            ANSI_RESET = "\u001B[0m";
        }
        System.out.println();
    }

    public void runGame() {
        System.out.println("-=- Game Description -=-");
        System.out.println("Explore a randomly generated map.");
        System.out.println("You may return to places you've already been.");
        System.out.println("Fight enemies and buy from merchants.");
        System.out.println("Upgrade your gear and bolster your stats.");
        System.out.println("You may equip a single weapon + armor piece.");
        System.out.println("Remember to use your potions!");
        System.out.println();
        System.out.println("-=- Stats Overview -=-");
        System.out.println("HP [Health] - When it reaches 0 you die.");
        System.out.println("DM [Damage Modifier] - Increases damage dealt.");
        System.out.println("APM [Armor Piercing Modifier] - Mitigates enemy armor.");
        System.out.println("PROT [Protection] - Decreases damage taken.");
        System.out.println("AGIL [Agility] - Increases chance of dodging attacks.");
        System.out.println();
        System.out.println("-=-");
        System.out.println("REMEMBER: You may enter Q at any time to save and quit.");
        System.out.println("-=-");
        System.out.println();
        while (true) { // exits when quitGame() called or player dies
            showMenu();
        }
    }

    /* Show the main menu that leads to other actions */
    private void showMenu() {
        System.out.println(ANSI_GREEN + "Your options:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + " (I)" + ANSI_RESET + " Access Inventory");
        System.out.println(ANSI_GREEN + " (M)" + ANSI_RESET + " Inspect Map");
        System.out.println(ANSI_GREEN + " (A)" + ANSI_RESET + " Adventure");
        char input = 0;
        while (input != 'i' && input != 'm' && input != 'a') {
            System.out.print(ANSI_GREEN + "> " + ANSI_RESET);
            input = scanner.nextLine().toLowerCase().charAt(0);
            if (input == 'q') {
                quitGame();
            }
            if (input != 'i' && input != 'm' && input != 'a') {
                System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_RESET);
            }
        }
        switch (input) {
            case 'i':
                showInventory(false);
                break;
            case 'm':
                showMap();
                break;
            case 'a':
                lookAndMovePlayer(); // player moves and encounters a scene (or not)
                break;
        }
    }

    /* Allow the player to equip/consume items, drop items, or view stats */
    private void showInventory(boolean inBattle) { // inBattle true: shows an extra message before quitting
        String playerInfo = player.getName() + " | " + player.getStats().getHealth() + " [Health] | " + player.getCoins() + " Coins";
        String underline = ">";
        for (int i = 0; i < Math.max(playerInfo.length() - 2, 33); i++) {
            underline += "-";
        }
        underline += "<";
        System.out.println(playerInfo);
        System.out.println(underline);

        Inventory inventory = player.getInventory();
        Item equippedWeapon = inventory.getEquippedWeapon();
        Item equippedArmor = inventory.getEquippedArmor();
        if (equippedWeapon != null) {
            System.out.print("Equipped Weapon: ");
            System.out.println(equippedWeapon.toInventoryString().substring(4)); // omit type tag
        } else {
            System.out.println("No weapon equipped!");
        }
        System.out.println();
        if (equippedArmor != null) {
            System.out.print("Equipped Armor: ");
            System.out.println(equippedArmor.toInventoryString().substring(4)); // omit type tag
        } else {
            System.out.println("No armor equipped!");
        }
        System.out.println(underline);
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty!");
        } else {
            System.out.println("Inventory (" + inventory.getItems().size() + "/" + inventory.MAX_SIZE + "):");
        }
        int index = 1;
        for (Item current : inventory.getItems()) {
            System.out.println("\n" + (index++) + ": " + current.toInventoryString());
        }
        System.out.println(underline);
        System.out.println("<W> Weapon | <A> Armor | <P> Potion");

        System.out.println();
        System.out.println(ANSI_GREEN + "-" + ANSI_RESET + " Enter an index to equip/consume that item (e.g. \"1\").");
        System.out.println(ANSI_GREEN + "-" + ANSI_RESET + " Enter D followed by an index to drop that item (e.g. \"D 1\").");
        System.out.println(ANSI_GREEN + "-" + ANSI_RESET + " Enter S to view detailed player stats.");
        System.out.println(ANSI_GREEN + "Enter one of the above, or E to exit:");
        boolean receivedValidInput = false;
        String input = "e";
        while (!receivedValidInput) {
            System.out.print("> " + ANSI_RESET);
            input = scanner.nextLine().trim().toLowerCase();
            if (input.charAt(0) == 'q') {
                if (inBattle) {
                    System.out.println("Quitting during a battle will end the battle!");
                    System.out.println(ANSI_GREEN + "Are you sure you want to quit now? (Y/N):");
                    if (scanYesNo()) {
                        quitGame();
                    }
                } else {
                    quitGame();
                }
            } else if (Character.isDigit(input.charAt(0))) {
                try {
                    int inputIndex = Integer.parseInt(input) - 1;
                    if (!inventory.isEmpty() && inputIndex >= 0 && inputIndex < inventory.getItems().size()) {
                        Item selectedItem = inventory.get(inputIndex);
                        System.out.print(selectedItem.getName());
                        if (inventory.equipOrConsume(inputIndex, player.getStats())) {
                            if (selectedItem instanceof Potion) {
                                System.out.println(" consumed. You feel strange.");
                                potionConsumedDisplay((Potion) selectedItem);
                                checkForDeath(); // health could (but should not) go down
                            } else {
                                System.out.println(" equipped.");
                            }
                        } else {
                            System.out.println(" already equipped!");
                        }
                        receivedValidInput = true;
                    }
                } catch (NumberFormatException e) {
                    // ignore exception and prompt again (invalid input)
                }
            } else if (input.charAt(0) == 'd' && input.contains(" ")) {
                try {
                    int inputIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (!inventory.isEmpty() && inputIndex >= 0 && inputIndex < inventory.getItems().size()) {
                        if (inputIndex == inventory.getEquippedWeaponIndex() || inputIndex == inventory.getEquippedArmorIndex()) {
                            System.out.println(ANSI_GREEN + "This item is equipped. Drop it anyway? (Y/N):");
                            if (!scanYesNo()) {
                                break;
                            }
                        }
                        System.out.println(inventory.get(inputIndex).getName() + " dropped."); // print before change
                        inventory.remove(inputIndex);
                        receivedValidInput = true;
                    }
                } catch (NumberFormatException e) {
                    // ignore exception and prompt again (invalid input)
                }
            } else if (input.charAt(0) == 's') {
                System.out.print(player.getStats().toDisplayString(player.getName(), inventory.getEquippedArmor()));
                receivedValidInput = true;
            } else if (input.charAt(0) == 'e') {
                receivedValidInput = true;
            }

            if (!receivedValidInput) {
                System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_GREEN);
            }
        }

        if (input.charAt(0) != 'e') {
            System.out.println(ANSI_GREEN + "Exit inventory? (Y/N):");
            if (!scanYesNo()) {
                showInventory(inBattle);
            }
        }
    }

    private void potionConsumedDisplay(Potion potion) {
        String gainOrLoss = potion.getModification() < 0 ? "lose " : "gain ";
        System.out.println("You " + gainOrLoss + Math.abs(potion.getModification()) + " [" + potion.getStat() + "].");
    }

    /* Minimal inventory method - shown when inventory is full - allows for item swapping */
    private boolean showInventoryForSwap(Item swapIn) { // allows player to swap out items when inventory is full
        System.out.println("Your inventory is full... You will need to swap something out in order to collect:");
        System.out.println(swapIn.getName());
        Inventory inventory = player.getInventory();
        System.out.println(">---------------------------------<");
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty!"); // this should never occur
        } else {
            System.out.println("Inventory (" + inventory.getItems().size() + "/" + inventory.MAX_SIZE + "):");
        }
        int index = 1;
        for (Item current : inventory.getItems()) {
            System.out.println("\n" + (index++) + ": " + current.toInventoryString());
        }
        System.out.println(">---------------------------------<");
        System.out.println("<W> Weapon | <A> Armor | <P> Potion");

        System.out.println();
        System.out.println(ANSI_GREEN + "-" + ANSI_RESET + " Enter an index to swap out that item (e.g. \"1\").");
        System.out.println(ANSI_GREEN + "Enter an index, or E to exit/cancel:");
        boolean itemSwapped = false;
        boolean receivedValidInput = false;
        String input = "e";
        while (!receivedValidInput) {
            System.out.print("> " + ANSI_RESET);
            input = scanner.nextLine().toLowerCase();
            if (input.charAt(0) == 'q') {
                quitGame();
            } else if (Character.isDigit(input.charAt(0))) {
                try {
                    int inputIndex = Integer.parseInt(input) - 1;
                    if (!inventory.isEmpty() && inputIndex >= 0 && inputIndex < inventory.getItems().size()) {
                        if (inputIndex == inventory.getEquippedWeaponIndex() || inputIndex == inventory.getEquippedArmorIndex()) {
                            System.out.println(ANSI_GREEN + "This item is equipped. Swap it out anyway? (Y/N):");
                            if (!scanYesNo()) {
                                System.out.println("Try again:");
                                itemSwapped = showInventoryForSwap(swapIn);
                                break;
                            }
                        }
                        System.out.println(inventory.get(inputIndex).getName() + " has been replaced with " + swapIn.getName() + ".");
                        inventory.getItems().set(inputIndex, swapIn);
                        itemSwapped = true;
                        receivedValidInput = true;
                    }
                } catch (NumberFormatException e) {
                    // ignore exception and prompt again (invalid input)
                }
            } else if (input.charAt(0) == 'e') {
                receivedValidInput = true;
            }

            if (!receivedValidInput) {
                System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_GREEN);
            }
        }
        return itemSwapped;
    } // returns whether or not item swapIn was added to inventory

    private void showMap() {
        System.out.println("You pull out your tattered map:");
        System.out.print(player.getTravelMap().toDisplayString(player.getPosition()));
    }

    /* Give the player the option to look/move any direction, calls encounterNewScene on movement */
    private void lookAndMovePlayer() {
        Direction lookDirection = promptLookDirection();
        System.out.println("You pull out your spyglass and look into the distant " + lookDirection + "...");
        Point lookToPosition = lookDirection.getChange(player.getPosition());
        dramaticPause(250);
        if (isValidPosition(lookToPosition)) {
            System.out.println(gameMap.getScene(lookToPosition).getView());
            System.out.println(ANSI_GREEN + "Venture " + lookDirection + "? (Y/N):");
            if (scanYesNo()) {
                player.move(lookToPosition);
                player.getTravelMap().setVisited(player.getPosition()); // record movement when player enters new tile
                encounterNewScene(lookDirection.toString());
            } else {
                System.out.println(ANSI_GREEN + "Look a different direction? (Y/N):");
                if (scanYesNo()) {
                    lookAndMovePlayer(); // try again
                }
            }
        } else {
            System.out.println("You see a mighty cliff cascading downwards into the violent sea below.\nYou cannot venture there.");
            System.out.println(ANSI_GREEN + "Look a different direction? (Y/N):");
            if (scanYesNo()) {
                lookAndMovePlayer(); // try again
            }
        }
    }

    /* Determines the scene, prints generic text, then calls an appropriate encounter scene method */
    private void encounterNewScene(String travelDirection) {
        System.out.println("You set off " + travelDirection + "ward.");
        printSlowTransition();
        Scene scene = gameMap.getScene(player.getPosition());
        System.out.println(scene.getDescription());
        printSlowTransition();
        Event event = scene.pickEvent(); // randomly selected event
        switch (event.getType()) {
            case BATTLE:
                System.out.println("Something is here.");
                printSlowTransition();
                System.out.println("Oh no... It's a: " + event.getName());
                dramaticPause(250);
                System.out.println(event.getDescription());
                printSlowTransition();
                encounterBattle((Enemy) event);
                break;
            case MERCHANT:
                System.out.println("Something is here.");
                printSlowTransition();
                System.out.println("Phew... It's a: " + event.getName());
                dramaticPause(250);
                System.out.println(event.getDescription());
                dramaticPause(500);
                encounterMerchant((Merchant) event);
                break;
            case PASSIVE_EVENT:
                System.out.println("There's nothing here. A future visit might uncover something more...");
                dramaticPause(250);
                System.out.println(event.getDescription());
                dramaticPause(250);
                encounterPassiveEvent((PassiveEvent) event);
                break;
            case TRAP:
                System.out.println("An eerie silence engulfs you.");
                printSlowTransition();
                System.out.println(event.getDescription());
                dramaticPause(250);
                encounterTrap((Trap) event);
                break;
        }
    }

    /* Alternate between the players turn and the enemies turn until one dies */
    private void encounterBattle(Enemy enemy) {
        int initialHealth = enemy.getStats().getHealth();
        while (!enemy.isDead() && doPlayerTurn(enemy, initialHealth)) { // run until enemy killed or player runs away
            if (!enemy.isDead()) { // enemy's turn
                printSlowTransition();
                System.out.println("The " + enemy.getName() + " responds with a vicious attack of its own!");
                printSlowTransition();

                int damage = enemy.getAttack(player.getStats(), player.getInventory().getEquippedArmor());
                if (damage < 0) {
                    System.out.println("You dodge the enemy's strike!");
                    damage = 0;
                } else {
                    // print a special message if the enemy does more or less damage than expected:
                    if (damage > (int) Math.ceil(enemy.getDamageMax() * 0.75)) { // within 25% of max damage or above
                        System.out.println("It pierces straight through your armor!");
                    } else if (damage < enemy.getDamageMin()) {
                        System.out.println("It struggles to pierce your armor!");
                    } else {
                        System.out.println("It hurts... ");
                    }
                }
                dramaticPause(500);
                player.getStats().modifyStat(StatType.HP, -1 * damage);
                System.out.println("Damage taken: " + damage + " | You have " + player.getStats().getHealth() + " [Health] left.");
                checkForDeath(); // enemy could kill player
            }
        }

        if (enemy.isDead()) {
            gameMap.getScene(player.getPosition()).setBattleCompleted();
            player.getTravelMap().setDefeated(player.getPosition());

            printSlowTransition();
            System.out.println("The " + enemy.getName() + " falls to the ground...");
            printSlowTransition();
            System.out.println("You search the corpse and find " + player.addCoins(enemy.getRarity()) + " coins!");
            dramaticPause(500);
            System.out.println("And, what's this?");
            printSlowTransition();
            System.out.println("Loot:\n");
            System.out.println(enemy.getLoot().toInventoryString() + "\n");
            System.out.println(ANSI_GREEN + "Collect this item? (Y/N):");
            if (scanYesNo()) {
                if (player.getInventory().hasSpace()) {
                    player.getInventory().addItem(enemy.getLoot());
                } else {
                    showInventoryForSwap(enemy.getLoot());
                }
            }
            System.out.println("...You have conqered another beast!");
        } else { // the player chose to run away
            // give enemy one last chance to attack (33% chance)
            if ((new Random()).nextInt(3) == 0) {
                int damage = enemy.getAttack(player.getStats(), player.getInventory().getEquippedArmor());
                if (damage > 0) { // agility increases chance of escaping without getting hit
                    System.out.println("The " + enemy.getName() + " gets off one last hit as you begin to run!");
                    printSlowTransition();
                    player.getStats().modifyStat(StatType.HP, -1 * damage);
                    System.out.println("Damage taken: " + damage + " | You have " + player.getStats().getHealth() + " [Health] left.");
                    checkForDeath(); // enemy could kill player
                }
            }
            dramaticPause(500);
            System.out.println("Phew... You made it out alive.");
        }
    }

    /* Give the player multiple options in battle (attack, run, inventory) */
    private boolean doPlayerTurn(Enemy enemy, int initialHealth) {
        System.out.println(ANSI_GREEN + "Your options:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + " (A)" + ANSI_RESET + " Attack");
        System.out.println(ANSI_GREEN + " (I)" + ANSI_RESET + " Access Inventory");
        System.out.println(ANSI_GREEN + " (R)" + ANSI_RESET + " Run Away");
        char input = 0;
        while (input != 'a' && input != 'i' && input != 'r') {
            System.out.print(ANSI_GREEN + "> " + ANSI_RESET);
            input = scanner.nextLine().toLowerCase().charAt(0);
            if (input == 'q') {
                System.out.println("Quitting during a battle will end the battle!");
                System.out.println(ANSI_GREEN + "Are you sure you want to quit now? (Y/N):");
                if (scanYesNo()) {
                    quitGame();
                }
            }
            if (input != 'q' && input != 'a' && input != 'i' && input != 'r') {
                System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_RESET);
            }
        }
        switch (input) {
            case 'a':
                int damage = player.getAttack(enemy.getStats());
                if (!player.hasWeapon()) {
                    System.out.println("You punch hard with your bare fist... Perhaps you should equip a weapon.");
                } else {
                    if (damage < 0) { // enemy dodged (damage == -1)
                        System.out.println("You attempt to strike, but the " + enemy.getName() + " dodges.");
                        damage = 0;
                    } else {
                        System.out.println("You strike the " + enemy.getName() + " with your " + player.getInventory().getEquippedWeapon().getName() + "!");
                        printSlowTransition();
                        // print a special message if the weapon does more or less than expected:
                        if (damage > (int) Math.ceil(player.getDamageMax() * 0.75)) { // within 25% of max damage or above
                            System.out.println("Your hit pierces straight through the enemy's protection!");
                        } else if (damage < player.getDamageMin()) {
                            System.out.println("Your hit nearly bounces off of the enemy! It must be well protected...");
                        } else {
                            System.out.println("Your hit connects well.");
                        }
                    }
                }
                dramaticPause(500);
                enemy.getStats().modifyStat(StatType.HP, -1 * damage);
                System.out.println("Damage dealt: " + damage + " | Enemy health: " + (int) (100 * ((double) enemy.getStats().getHealth() / initialHealth)) + "%");
                break;
            case 'i':
                showInventory(true);
                System.out.println("Back to the battle...");
                dramaticPause(500);
                doPlayerTurn(enemy, initialHealth);
                break;
            case 'r':
                System.out.println("You turn and run.");
                printSlowTransition();
                return false;
        }
        return true;
    } // returns false only when the player runs

    /* Method to be called by Item when broken (let player know of broken item) */
    public static void notifyOfBrokenItem(String itemName) {
        System.out.println("...Your " + itemName + " is almost broken, this is the last good use...");
    }

    /* Give the player a chance to purchase a single item from a merchant */
    private void encounterMerchant(Merchant merchant) {
        System.out.println("You check your pockets and find that you have " + player.getCoins() + " coins.");
        dramaticPause(250);
        if (!merchant.hasItems()) {
            System.out.println("...But the merchant has already sold all of their items...\nToo bad.");
        } else {
            System.out.println(merchant.toDisplayString());

            System.out.println(ANSI_GREEN + "-" + ANSI_RESET + " Enter an index to purchase that item (e.g. \"1\").");
            System.out.println(ANSI_GREEN + "Enter an index, I to access your inventory, or E to exit/leave:");
            boolean receivedValidInput = false;
            String input = "e";
            while (!receivedValidInput) {
                System.out.print("> " + ANSI_RESET);
                input = scanner.nextLine().toLowerCase();
                if (input.charAt(0) == 'q') {
                    quitGame();
                } else if (Character.isDigit(input.charAt(0))) {
                    try {
                        int inputIndex = Integer.parseInt(input) - 1;
                        if (inputIndex >= 0 && inputIndex < merchant.inventorySize()) {
                            if (merchant.getPrice(inputIndex) > player.getCoins()) {
                                System.out.println("You cannot afford that item! The merchant seems annoyed by your request...");
                            } else {
                                if (player.getInventory().hasSpace()) {
                                    player.getInventory().addItem(merchant.getItem(inputIndex));
                                    player.removeCoins(merchant.getPrice(inputIndex));
                                    System.out.println("You take your new " + merchant.getItem(inputIndex).getName() + " and hand over some coins.");
                                    merchant.removeItem(inputIndex); // remove purchased item from merchant inventory
                                } else { // players inventory is full
                                    if (showInventoryForSwap(merchant.getItem(inputIndex))) {
                                        player.removeCoins(merchant.getPrice(inputIndex));
                                        System.out.println("You take your new " + merchant.getItem(inputIndex).getName() + " and hand over some coins.");
                                        merchant.removeItem(inputIndex); // remove purchased item from merchant inventory
                                    } else {
                                        System.out.println("The merchant seems annoyed by your indecisiveness...");
                                    }
                                }
                            }
                            receivedValidInput = true;
                        }
                    } catch (NumberFormatException e) {
                        // ignore exception and prompt again (invalid input)
                    }
                } else if (input.charAt(0) == 'i') {
                    showInventory(false);

                    System.out.println("You look up and find that the merchant is still there waiting for you.");
                    dramaticPause(500);
                    System.out.println(merchant.toDisplayString());
                    System.out.println(ANSI_GREEN + "-" + ANSI_RESET + " Enter an index to purchase that item (e.g. \"1\").");
                    System.out.println(ANSI_GREEN + "Enter an index, I to access your inventory, or E to exit:");
                } else if (input.charAt(0) == 'e') {
                    receivedValidInput = true;
                }

                if (input.charAt(0) != 'i' && !receivedValidInput) {
                    System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_GREEN);
                }
            }
        }
        dramaticPause(500);
        System.out.println("The merchant hurriedly packs up and sets off...");
    }

    private void encounterPassiveEvent(PassiveEvent passiveEvent) {
        System.out.println("You gain " + passiveEvent.getHpBonus() + " [Health].");
        player.getStats().modifyStat(StatType.HP, passiveEvent.getHpBonus());
    }

    private void encounterTrap(Trap trap) {
        String gainOrLoss = trap.getModification() < 0 ? "lose " : "gain ";
        System.out.println("You " + gainOrLoss + Math.abs(trap.getModification()) + " [" + trap.getStat() + "].");
        player.getStats().modifyStat(trap.getStat(), trap.getModification());
        checkForDeath(); // health could go down
    }

    /* Validates yes/no input, returns corresponding boolean */
    private boolean scanYesNo() {
        char input = 0;
        while (input != 'y' && input != 'n') {
            System.out.print("> " + ANSI_RESET);
            input = scanner.nextLine().toLowerCase().charAt(0);
            if (input == 'q') {
                quitGame();
            }
            if (input != 'y' && input != 'n') {
                System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_GREEN);
            }
        }
        return input == 'y';
    }

    /* Validates a direction input, returns corresponding Direction (enum) */
    private Direction promptLookDirection() {
        Direction lookDirection = null;
        System.out.println(ANSI_GREEN + "Enter a direction to look (N/S/E/W):");
        while (lookDirection == null) {
            System.out.print("> " + ANSI_RESET);
            char input = scanner.nextLine().toLowerCase().charAt(0);
            if (input == 'q') {
                quitGame();
            }
            lookDirection = Direction.charToDirection(input); // null if invalid
            if (lookDirection == null) {
                System.out.println(ANSI_RED + "Invalid input, try again:" + ANSI_GREEN);
            }
        }
        return lookDirection;
    }

    /* Ensures that a Point is within the game map */
    private boolean isValidPosition(Point moveTo) {
        return (moveTo.x >= 0 && moveTo.x < MAP_SIZE && moveTo.y >= 0 && moveTo.y < MAP_SIZE);
    }

    private void printSlowTransition() {
        dramaticPause(250);
        System.out.print(". ");
        dramaticPause(500);
        System.out.print(". ");
        dramaticPause(500);
        System.out.print(".");
        dramaticPause(500);
        System.out.println();
    }

    private void dramaticPause(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            // IGNORE
        }
    }

    /* Checks for player death - delete game save on death */
    private void checkForDeath() {
        if (player.getStats().getHealth() <= 0) {
            printSlowTransition();
            System.out.println("You fall slowly to the ground.");
            printSlowTransition();
            System.out.println("Game over...\n(Progress Lost)");
            dataKeeper.deleteGameSave();
            System.exit(0);
        }
    }

    private void quitGame() {
        System.out.println("Game ended...\n(Progress Saved)");
        dataKeeper.saveGame(gameMap, player); // save game on exit
        System.exit(0);
    }

    public static void main(String[] args) {
        GameDriver driver = new GameDriver();
        driver.runGame();
    }
}
