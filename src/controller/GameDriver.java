package controller;

/*
GameDriver contains the main method and runs the game.
This driver is extra long because it is acting as both the controller and view (console user interface).
When I separate them for part 2, the controller will become much more concise.
Separating the view from the controller at this stage would only add unnecessary complexity.
 */
// @author Jared Scholz
import model.data.DBManager;
import model.entity.Inventory;
import model.entity.Item;
import model.map.Enemy;
import model.map.Event;
import model.map.GameMap;
import model.map.Merchant;
import model.map.PassiveEvent;
import model.map.Scene;
import model.map.Trap;
import model.entity.Player;
import model.entity.Potion;
import model.entity.StatType;
import view.ViewManager;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.UIManager;
import view.GameplayButtons;

public class GameDriver {

    public final static int MAP_SIZE = 17; // should not be changed

    private final DBManager dataKeeper;
    private final ViewManager viewManager;
    private GameMap gameMap;
    private Player player;

    /* Context variables - remember values between actions */
    private Point lookToPosition;

    public GameDriver() {
        dataKeeper = new DBManager();
        viewManager = new ViewManager(this);
        gameMap = null; // set by checkForGameSave()
        player = null; // set by checkForGameSave()
    }

    public void runGame() {
        viewManager.display(); // prompts the pregame menu first
        // prepare gameplay view:
        viewManager.displayTextLine("You awake in a strange place.");
        viewManager.displayTextLine("You feel ready for an adventure...");
        viewManager.enableGameplayButton(GameplayButtons.ADVENTURE);
    }

    /* Looks for a game save and initializes game data variables */
    public void checkForGameSave(String username) {
        if (dataKeeper.checkGameSave(username)) {
            // a game save with this username exists...
            gameMap = dataKeeper.getExistingMap();
            player = dataKeeper.getExistingPlayer();
        } else {
            // a game save with this username does not exist...
            gameMap = new GameMap(MAP_SIZE, dataKeeper); // generate random map
            player = new Player(username, MAP_SIZE / 2, MAP_SIZE / 2); // generate new player
        }
        viewManager.updatePlayerInfo(player);
    }

    public void adventure() {
        viewManager.displayTextLine("Pick a direction to look...");
        viewManager.enableGameplayButtons(Arrays.asList(GameplayButtons.N, GameplayButtons.E, GameplayButtons.S, GameplayButtons.W));
    }

    public void look(Direction lookDirection) {
        viewManager.displayTextLine("You pull out your spyglass and look into the distant " + lookDirection + "...");
        lookToPosition = lookDirection.getChange(player.getPosition());
        // DRAMATIC PAUSE
        if (isValidPosition(lookToPosition)) {
            viewManager.displayTextLine(gameMap.getScene(lookToPosition).getView());
            viewManager.displayTextLine("Venture " + lookDirection + "?");
            viewManager.enableGameplayButtons(Arrays.asList(GameplayButtons.YES, GameplayButtons.NO));
        } else {
            viewManager.displayTextLine("You see a mighty cliff cascading downwards into the violent sea below.");
            viewManager.displayTextLine("You cannot venture there.");
            viewManager.enableGameplayButtons(Arrays.asList(GameplayButtons.N, GameplayButtons.E, GameplayButtons.S, GameplayButtons.W));
        }
    }

    public void goDirection() {
        player.move(lookToPosition);
        player.getTravelMap().setVisited(player.getPosition()); // record movement when player enters new tile
        viewManager.updatePlayerInfo(player);
        // encounterNewScene(lookDirection.toString());
    }

    public void pickNewDirection() {
        viewManager.displayTextLine("Maybe a different direction would be better.");
        adventure();
    }

    public void attack() {

    }

    public void runAway() {

    }

    public void quitGame() {

    }

    /* Determines the scene, prints generic text, then calls an appropriate encounter scene method */
 /*
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
    }*/

 /* Alternate between the players turn and the enemies turn until one dies */
 /*
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
    }*/

 /* Give the player multiple options in battle (attack, run, inventory) */
 /*
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
     */

 /* Method to be called by Item when broken (let player know of broken item) */
    public static void notifyOfBrokenItem(String itemName) {
        System.out.println("...Your " + itemName + " is almost broken, this is the last good use...");
    }

    /* Give the player a chance to purchase a single item from a merchant */
 /*
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
    }*/

 /*
    private void encounterPassiveEvent(PassiveEvent passiveEvent) {
        System.out.println("You gain " + passiveEvent.getHpBonus() + " [Health].");
        player.getStats().modifyStat(StatType.HP, passiveEvent.getHpBonus());
    }*/

 /*
    private void encounterTrap(Trap trap) {
        String gainOrLoss = trap.getModification() < 0 ? "lose " : "gain ";
        System.out.println("You " + gainOrLoss + Math.abs(trap.getModification()) + " [" + trap.getStat() + "].");
        player.getStats().modifyStat(trap.getStat(), trap.getModification());
        checkForDeath(); // health could go down
    }*/

 /* Ensures that a Point is within the game map */
    private boolean isValidPosition(Point moveTo) {
        return (moveTo.x >= 0 && moveTo.x < MAP_SIZE && moveTo.y >= 0 && moveTo.y < MAP_SIZE);
    }

    /*
    private void printSlowTransition() {
        dramaticPause(250);
        System.out.print(". ");
        dramaticPause(500);
        System.out.print(". ");
        dramaticPause(500);
        System.out.print(".");
        dramaticPause(500);
        System.out.println();
    }*/

 /*
    private void dramaticPause(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            // IGNORE
        }
    }*/

 /* Checks for player death - delete game save on death */
 /*
    private void checkForDeath() {
        if (player.getStats().getHealth() <= 0) {
            printSlowTransition();
            System.out.println("You fall slowly to the ground.");
            printSlowTransition();
            System.out.println("Game over...\n(Progress Lost)");
            dataKeeper.deleteGameSave();
            System.exit(0);
        }
    }*/

 /*
    private void quitGame() {
        System.out.println("Game ended...\n(Progress Saved)");
        dataKeeper.saveGame(gameMap, player); // save game on exit
        System.exit(0);
    }*/
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.out.println("ERROR SETTING LOOK AND FEEL");
            System.out.println("PLEASE ENSURE THAT THE FLATLAF LIBRARY EXISTS");
            System.out.println("THE UI IS DESIGNED ONLY FOR FLATLAF DARK");
        }
        GameDriver driver = new GameDriver();
        driver.runGame();
    }
}
