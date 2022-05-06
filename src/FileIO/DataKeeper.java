package FileIO;

/*
The DataKeeper is responsible for:
    checking for game saves,
    loading game saves,
    loading all game data,
    and saving games.
 */
// @author Jared Scholz
import Map.Enemy;
import Map.EnemyType;
import Map.Merchant;
import Map.PassiveEvent;
import Map.Scene;
import Map.Trap;
import Entity.Armor;
import Entity.EntityStats;
import Entity.Inventory;
import Entity.Item;
import Entity.Player;
import Entity.Potion;
import Entity.Weapon;
import Entity.StatType;
import Entity.TravelMap;
import Map.GameMap;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataKeeper {

    private String username;
    private final List<Item> allItems;
    private final List<Enemy> allEnemies;
    private final List<Merchant> allMerchants;
    private final List<PassiveEvent> allPassiveEvents;
    private final List<Trap> allTraps;
    private final List<Scene> allScenes;

    public DataKeeper() {
        username = null;
        allItems = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allMerchants = new ArrayList<>();
        allPassiveEvents = new ArrayList<>();
        allTraps = new ArrayList<>();
        allScenes = new ArrayList<>();
    }

    /* Looks in the saves folder for an existing save. */
    // This method provides the DataKeeper with the username and must be called before other game save methods.
    public boolean checkGameSave(String username) {
        this.username = username;
        File currentGameSave = new File("./gamedata/saves/" + username);
        boolean alreadyExists = currentGameSave.exists();
        if (!alreadyExists) {
            initializeData(); // initialize data so that a new map may be created
        }
        return alreadyExists;
    }

    public void saveGame(GameMap gameMap, Player player) {
        File saveLocation = new File("./gamedata/saves/" + username);
        if (!saveLocation.exists()) {
            saveLocation.mkdirs(); // prepare the save location
        }

        boolean saveWorked = saveGameMap(gameMap);
        saveWorked = saveWorked ? saveStats(player.getStats()) : false;
        saveWorked = saveWorked ? savePosition(player.getPosition()) : false;
        saveWorked = saveWorked ? saveTravelMap(player.getTravelMap()) : false;
        saveWorked = saveWorked ? saveInventory(player.getInventory()) : false;
        saveWorked = saveWorked ? saveCoins(player.getCoins()) : false;
        if (!saveWorked) {
            deleteGameSave();
        }
    }

    public void deleteGameSave() {
        File directoryToDelete = new File("./gamedata/saves/" + username);
        // must empty directory before deleting:
        File[] files = directoryToDelete.listFiles();
        if (files != null) {
            for (File current : files) {
                current.delete();
            }
        }
        directoryToDelete.delete();
    }

    public GameMap getExistingMap() {
        if (username == null) {
            return null; // checkGameSave not yet called (this should never occur)
        }
        return readExistingGameMap();
    }

    public Player getExistingPlayer() {
        if (username == null) {
            return null; // checkGameSave not yet called (this should never occur)
        }
        return new Player(username, readExistingStats(), readExistingPosition(), readExistingTravelMap(), readExistingInventory(), readExistingCoins());
    }

    private boolean saveGameMap(GameMap gameMap) {
        ObjectOutputStream outputStream = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/gamemap.ser");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(targetFile));
            outputStream.writeObject(gameMap);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR SAVING GAME MAP");
            System.out.println("Game save lost...");
            if (outputStream != null) try {
                outputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            return false;
        }
        return true;
    }

    private boolean saveStats(EntityStats stats) {
        ObjectOutputStream outputStream = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/stats.ser");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(targetFile));
            outputStream.writeObject(stats);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR SAVING STATS");
            System.out.println("Game save lost...");
            if (outputStream != null) try {
                outputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            return false;
        }
        return true;
    }

    private boolean savePosition(Point position) {
        ObjectOutputStream outputStream = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/position.ser");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(targetFile));
            outputStream.writeObject(position);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR SAVING POSITION");
            System.out.println("Game save lost...");
            if (outputStream != null) try {
                outputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            return false;
        }
        return true;
    }

    private boolean saveTravelMap(TravelMap travelMap) {
        ObjectOutputStream outputStream = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/travelmap.ser");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(targetFile));
            outputStream.writeObject(travelMap);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR SAVING TRAVEL MAP");
            System.out.println("Game save lost...");
            if (outputStream != null) try {
                outputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            return false;
        }
        return true;
    }

    private boolean saveInventory(Inventory inventory) {
        ObjectOutputStream outputStream = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/inventory.ser");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            outputStream = new ObjectOutputStream(new FileOutputStream(targetFile));
            outputStream.writeObject(inventory);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR SAVING INVENTORY");
            System.out.println("Game save lost...");
            if (outputStream != null) try {
                outputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            return false;
        }
        return true;
    }

    private boolean saveCoins(int coins) {
        BufferedWriter writer = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/coins.txt");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(targetFile));
            writer.write(Integer.toString(coins));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR SAVING COINS");
            System.out.println("Game save lost...");
            if (writer != null) try {
                writer.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            return false;
        }
        return true;
    }

    private GameMap readExistingGameMap() {
        ObjectInputStream inputStream = null;
        GameMap gameMap = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/gamemap.ser");
            inputStream = new ObjectInputStream(new FileInputStream(targetFile));
            gameMap = (GameMap) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("ERROR LOADING SAVED GAME MAP");
            System.out.println("Corrupt game save!");
            if (inputStream != null) try {
                inputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            deleteGameSave();
            System.exit(0);
        }
        return gameMap;
    }

    private EntityStats readExistingStats() {
        ObjectInputStream inputStream = null;
        EntityStats stats = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/stats.ser");
            inputStream = new ObjectInputStream(new FileInputStream(targetFile));
            stats = (EntityStats) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("ERROR LOADING SAVED STATS");
            System.out.println("Corrupt game save!");
            if (inputStream != null) try {
                inputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            deleteGameSave();
            System.exit(0);
        }
        return stats;
    }

    private Point readExistingPosition() {
        ObjectInputStream inputStream = null;
        Point position = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/position.ser");
            inputStream = new ObjectInputStream(new FileInputStream(targetFile));
            position = (Point) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("ERROR LOADING SAVED POSITION");
            System.out.println("Corrupt game save!");
            if (inputStream != null) try {
                inputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            deleteGameSave();
            System.exit(0);
        }
        return position;
    }

    private TravelMap readExistingTravelMap() {
        ObjectInputStream inputStream = null;
        TravelMap travelMap = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/travelmap.ser");
            inputStream = new ObjectInputStream(new FileInputStream(targetFile));
            travelMap = (TravelMap) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("ERROR LOADING SAVED TRAVEL MAP");
            System.out.println("Corrupt game save!");
            if (inputStream != null) try {
                inputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            deleteGameSave();
            System.exit(0);
        }
        return travelMap;
    }

    private Inventory readExistingInventory() {
        ObjectInputStream inputStream = null;
        Inventory inventory = null;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/inventory.ser");
            inputStream = new ObjectInputStream(new FileInputStream(targetFile));
            inventory = (Inventory) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("ERROR LOADING SAVED INVENTORY");
            System.out.println("Corrupt game save!");
            if (inputStream != null) try {
                inputStream.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            deleteGameSave();
            System.exit(0);
        }
        return inventory;
    }

    private int readExistingCoins() {
        BufferedReader reader = null;
        int coins = 0;
        try {
            File targetFile = new File("./gamedata/saves/" + username + "/coins.txt");
            reader = new BufferedReader(new FileReader(targetFile));
            coins = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR LOADING SAVED COINS");
            System.out.println("Corrupt game save!");
            if (reader != null) try {
                reader.close(); // make sure file closes so that it can be deleted
            } catch (IOException e2) {
                // IGNORE
            }
            deleteGameSave();
            System.exit(0);
        }
        return coins;
    }

    private void initializeData() {
        loadItems();
        loadEnemies();
        loadMerchants();
        loadPassiveEvents();
        loadTraps();
        loadScenes();
    }

    private void loadItems() {
        loadArmor();
        loadPotions();
        loadWeapons();
    }

    private void loadArmor() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/items/armor.txt"));

            String currentLine = null;
            String name;
            String description;
            int rarity;
            int protection;
            int agility;
            int durability;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new armor:
                    name = reader.readLine().split("==", 2)[1].trim();
                    description = reader.readLine().split("==", 2)[1].trim();
                    rarity = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    protection = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    agility = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    durability = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allItems.add(new Armor(name, description, rarity, protection, agility, durability));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING ARMOR (gamedata/readonly/items/armor.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadPotions() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/items/potions.txt"));

            String currentLine = null;
            String name;
            String description;
            int rarity;
            StatType stat;
            int modification;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new potion:
                    name = reader.readLine().split("==", 2)[1].trim();
                    description = reader.readLine().split("==", 2)[1].trim();
                    rarity = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    stat = StatType.valueOf(reader.readLine().split("==", 2)[1].trim());
                    modification = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allItems.add(new Potion(name, description, rarity, stat, modification));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING POTIONS (gamedata/readonly/items/potions.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadWeapons() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/items/weapons.txt"));

            String currentLine = null;
            String name;
            String description;
            int rarity;
            String[] damageRange;
            int damageMin;
            int damageMax;
            int armorPiercing;
            int durability;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new weapon:
                    name = reader.readLine().split("==", 2)[1].trim();
                    description = reader.readLine().split("==", 2)[1].trim();
                    rarity = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    damageRange = reader.readLine().split("==", 2)[1].split(",", 2);
                    damageMin = Integer.parseInt(damageRange[0]);
                    damageMax = Integer.parseInt(damageRange[1]);
                    armorPiercing = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    durability = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allItems.add(new Weapon(name, description, rarity, damageMin, damageMax, armorPiercing, durability));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING WEAPONS (gamedata/readonly/items/weapons.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadEnemies() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/events/enemies.txt"));

            String currentLine = null;
            String name;
            String description;
            EnemyType type;
            int rarity;
            String[] damageRange;
            int damageMin;
            int damageMax;
            int hp;
            int apm;
            int prot;
            int agil;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new enemy:
                    name = reader.readLine().split("==", 2)[1].trim();
                    description = reader.readLine().split("==", 2)[1].trim();
                    type = EnemyType.valueOf(reader.readLine().split("==", 2)[1].trim());
                    rarity = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    damageRange = reader.readLine().split("==", 2)[1].split(",", 2);
                    damageMin = Integer.parseInt(damageRange[0]);
                    damageMax = Integer.parseInt(damageRange[1]);
                    hp = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    apm = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    prot = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    agil = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allEnemies.add(new Enemy(name, description, type, rarity, damageMin, damageMax, hp, apm, prot, agil));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING ENEMIES (gamedata/readonly/events/enemies.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadMerchants() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/events/generic/merchants.txt"));

            String currentLine = null;
            String name;
            String description;
            int invrarity;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new merchant:
                    name = reader.readLine().split("==", 2)[1].trim();
                    description = reader.readLine().split("==", 2)[1].trim();
                    invrarity = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allMerchants.add(new Merchant(name, description, invrarity));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING MERCHANTS (gamedata/readonly/events/generic/merchants.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadPassiveEvents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/events/generic/passiveevents.txt"));

            String currentLine = null;
            String description;
            int hpBonus;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new passive event:
                    description = reader.readLine().split("==", 2)[1].trim();
                    hpBonus = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allPassiveEvents.add(new PassiveEvent(description, hpBonus));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING PASSIVE EVENTS (gamedata/readonly/events/generic/passiveevents.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadTraps() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/events/generic/traps.txt"));

            String currentLine = null;
            String description;
            StatType stat;
            int modification;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new trap:
                    description = reader.readLine().split("==", 2)[1].trim();
                    stat = StatType.valueOf(reader.readLine().split("==", 2)[1].trim());
                    modification = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                    allTraps.add(new Trap(description, stat, modification));
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING TRAPS (gamedata/readonly/events/generic/traps.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    private void loadScenes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./gamedata/readonly/scenes.txt"));

            String currentLine = null;
            Scene currentScene = null;
            String view;
            String description;
            EnemyType enemyType;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals("new:")) {
                    // bulk read in new scene:
                    view = reader.readLine().split("==", 2)[1].trim();
                    description = reader.readLine().split("==", 2)[1].trim();
                    enemyType = EnemyType.valueOf(reader.readLine().split("==", 2)[1].trim());
                    currentScene = new Scene(view, description, enemyType);
                    // check for custom additions:
                    if (Boolean.parseBoolean(reader.readLine().split("==", 2)[1])) {
                        // bulk read in new trap:
                        String trapDescription = reader.readLine().split("==", 2)[1].trim();
                        StatType trapStat = StatType.valueOf(reader.readLine().split("==", 2)[1].trim());
                        int trapModification = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                        currentScene.addTrap(new Trap(trapDescription, trapStat, trapModification));
                    }
                    if (Boolean.parseBoolean(reader.readLine().split("==", 2)[1])) {
                        // bulk read in new merchant:
                        String merchantName = reader.readLine().split("==", 2)[1].trim();
                        String merchantDescription = reader.readLine().split("==", 2)[1].trim();
                        int merchantInvrarity = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                        currentScene.addMerchant(new Merchant(merchantName, merchantDescription, merchantInvrarity));
                    }
                    if (Boolean.parseBoolean(reader.readLine().split("==", 2)[1])) {
                        // bulk read in new passive event:
                        String passiveEventDescription = reader.readLine().split("==", 2)[1].trim();
                        int passiveEventHpBonus = Integer.parseInt(reader.readLine().split("==", 2)[1]);
                        currentScene.addPassiveEvent(new PassiveEvent(passiveEventDescription, passiveEventHpBonus));
                    }
                    allScenes.add(currentScene);
                }
            }

            reader.close();
        } catch (Exception e) { // catch any and all possible issues
            System.out.println("ERROR LOADING SCENES (gamedata/readonly/scenes.txt)");
            System.out.println("Please ensure that the game files are correctly in the project!");
            System.exit(0);
        }
    }

    public List<Item> getAllItems() {
        return allItems;
    }

    public List<Enemy> getAllEnemies() {
        return allEnemies;
    }

    public List<Merchant> getAllMerchants() {
        return allMerchants;
    }

    public List<PassiveEvent> getAllPassiveEvents() {
        return allPassiveEvents;
    }

    public List<Trap> getAllTraps() {
        return allTraps;
    }

    public List<Scene> getAllScenes() {
        return allScenes;
    }
}
