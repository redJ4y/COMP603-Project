package Model.Data;

// @author Jared Scholz
import Model.Entity.Armor;
import Model.Entity.EntityStats;
import Model.Entity.Inventory;
import Model.Entity.Item;
import Model.Entity.Player;
import Model.Entity.Potion;
import Model.Entity.StatType;
import Model.Entity.TravelMap;
import Model.Entity.Weapon;
import Model.Map.Enemy;
import Model.Map.EnemyType;
import Model.Map.GameMap;
import Model.Map.Merchant;
import Model.Map.PassiveEvent;
import Model.Map.Scene;
import Model.Map.Trap;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String USER_NAME = "rpg_game";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:GameData_Ebd; create=true";
    private Connection connection;

    private String username;
    private final List<Item> allItems;
    private final List<Enemy> allEnemies;
    private final List<Merchant> allMerchants;
    private final List<PassiveEvent> allPassiveEvents;
    private final List<Trap> allTraps;
    private final List<Scene> allScenes;

    public DBManager() {
        establishConnection();

        username = null;
        allItems = new ArrayList<>();
        allEnemies = new ArrayList<>();
        allMerchants = new ArrayList<>();
        allPassiveEvents = new ArrayList<>();
        allTraps = new ArrayList<>();
        allScenes = new ArrayList<>();
    }

    private void establishConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("ERROR: COULD NOT CONNECT TO DATABASE");
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /* Looks in the saves folder for an existing save. */
    // This method provides the DataKeeper with the username and must be called before other game save methods.
    /*
    public boolean checkGameSave(String username) {

    }
     */
    public void saveGame(GameMap gameMap, Player player) {
        username = "test"; // TEMPORARY
        String preparedStatement = "INSERT INTO SAVES VALUES ('" + username + "', ?, ?, ?, ?, ?, 0)";
        try {
            PreparedStatement statement = connection.prepareStatement(preparedStatement);
            for (int i = 1; i <= 5; i++) {
                statement.setObject(i, null);
            }
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR CREATING GAME SAVE");
            System.out.println(ex.getMessage());
        }

        // TODO:
        // finish all of the save and read functions
        // finish checkGameSave through getExistingPlayer to emulate the old DataKeeper
    }

    /*
    public void deleteGameSave() {

    }
     */
    public GameMap getExistingMap() {
        if (username == null) {
            return null; // checkGameSave not yet called (this should never occur)
        }
        return readExistingGameMap();
    }

    /*
    public Player getExistingPlayer() {

    }
     */
    private boolean saveGameMap(GameMap gameMap) {
        String preparedStatement = "UPDATE SAVES SET SERGAMEMAP = ? WHERE USERNAME = '" + username + "'";
        try {
            ByteArrayOutputStream byteArrStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteArrStream);
            objectStream.writeObject(gameMap);
            objectStream.flush();
            byte[] serializedObject = byteArrStream.toByteArray();
            objectStream.close();
            byteArrStream.close();

            PreparedStatement statement = connection.prepareStatement(preparedStatement);
            statement.setObject(1, serializedObject);
            statement.executeUpdate();
            statement.close();
        } catch (IOException | SQLException ex) {
            System.out.println("ERROR SAVING GAME MAP");
            System.out.println("Game save lost...");
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /*
    private boolean saveStats(EntityStats stats) {

    }

    private boolean savePosition(Point position) {

    }

    private boolean saveTravelMap(TravelMap travelMap) {

    }

    private boolean saveInventory(Inventory inventory) {

    }

    private boolean saveCoins(int coins) {

    }
     */
    private GameMap readExistingGameMap() {
        GameMap gameMap = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT SERGAMEMAP FROM SAVES WHERE USERNAME = '" + username + "'");
            rs.next(); // there will be a single result
            byte[] serializedObject = rs.getBytes("SERGAMEMAP");
            rs.close();
            statement.close();

            ByteArrayInputStream byteArrStream = new ByteArrayInputStream(serializedObject);
            ObjectInputStream objectStream = new ObjectInputStream(byteArrStream);
            gameMap = (GameMap) objectStream.readObject();
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            System.out.println("ERROR LOADING SAVED GAME MAP");
            System.out.println("Corrupt game save!");
            System.out.println(ex.getMessage());
            // deleteGameSave();
            System.exit(0);
        }
        return gameMap;
    }

    /*
    private EntityStats readExistingStats() {

    }

    private Point readExistingPosition() {

    }

    private TravelMap readExistingTravelMap() {

    }

    private Inventory readExistingInventory() {

    }

    private int readExistingCoins() {

    }
     */
    public void initializeData() {
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ARMOR");

            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPT");
                int rarity = rs.getInt("RARITY");
                int protection = rs.getInt("PROT");
                int agility = rs.getInt("AGIL");
                int durability = rs.getInt("DUR");
                allItems.add(new Armor(name, description, rarity, protection, agility, durability));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING ARMOR");
            System.out.println(ex.getMessage());
        }
    }

    private void loadPotions() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM POTIONS");

            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPT");
                int rarity = rs.getInt("RARITY");
                StatType stat = StatType.valueOf(rs.getString("STAT"));
                int modification = rs.getInt("MOD");
                allItems.add(new Potion(name, description, rarity, stat, modification));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING POTIONS");
            System.out.println(ex.getMessage());
        }
    }

    private void loadWeapons() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM WEAPONS");

            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPT");
                int rarity = rs.getInt("RARITY");
                String[] damageRange = rs.getString("DMG").split(",", 2);
                int damageMin = Integer.parseInt(damageRange[0]);
                int damageMax = Integer.parseInt(damageRange[1]);
                int armorPiercing = rs.getInt("AP");
                int durability = rs.getInt("DUR");
                allItems.add(new Weapon(name, description, rarity, damageMin, damageMax, armorPiercing, durability));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING WEAPONS");
            System.out.println(ex.getMessage());
        }
    }

    private void loadEnemies() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ENEMIES");

            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPT");
                EnemyType type = EnemyType.valueOf(rs.getString("TYPE"));
                int rarity = rs.getInt("RARITY");
                String[] damageRange = rs.getString("DMG").split(",", 2);
                int damageMin = Integer.parseInt(damageRange[0]);
                int damageMax = Integer.parseInt(damageRange[1]);
                int hp = rs.getInt("HP");
                int apm = rs.getInt("APM");
                int prot = rs.getInt("PROT");
                int agil = rs.getInt("AGIL");
                allEnemies.add(new Enemy(name, description, type, rarity, damageMin, damageMax, hp, apm, prot, agil));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING ENEMIES");
            System.out.println(ex.getMessage());
        }
    }

    private void loadMerchants() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM MERCHANTS");

            while (rs.next()) {
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPT");
                int invrarity = rs.getInt("INVRARITY");
                allMerchants.add(new Merchant(name, description, invrarity));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING MERCHANTS");
            System.out.println(ex.getMessage());
        }
    }

    private void loadPassiveEvents() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PASSIVEEVENTS");

            while (rs.next()) {
                String description = rs.getString("DESCRIPT");
                int hpBonus = rs.getInt("HP");
                allPassiveEvents.add(new PassiveEvent(description, hpBonus));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING PASSIVEEVENTS");
            System.out.println(ex.getMessage());
        }
    }

    private void loadTraps() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM TRAPS");

            while (rs.next()) {
                String description = rs.getString("DESCRIPT");
                StatType stat = StatType.valueOf(rs.getString("STAT"));
                int modification = rs.getInt("MOD");
                allTraps.add(new Trap(description, stat, modification));
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING TRAPS");
            System.out.println(ex.getMessage());
        }
    }

    private void loadScenes() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SCENES");

            while (rs.next()) {
                String view = rs.getString("SCENEVIEW");
                String description = rs.getString("DESCRIPT");
                EnemyType enemyType = EnemyType.valueOf(rs.getString("ENEM"));
                Scene currentScene = new Scene(view, description, enemyType);

                if (Boolean.parseBoolean(rs.getString("TRAP"))) {
                    String trapDescription = rs.getString("TRAPDESCRIPT");
                    StatType trapStat = StatType.valueOf(rs.getString("TRAPSTAT"));
                    int trapModification = rs.getInt("TRAPMOD");
                    currentScene.addTrap(new Trap(trapDescription, trapStat, trapModification));
                }
                if (Boolean.parseBoolean(rs.getString("MERCH"))) {
                    String merchantName = rs.getString("MERCHNAME");
                    String merchantDescription = rs.getString("MERCHDESCRIPT");
                    int merchantInvrarity = rs.getInt("MERCHINVRARITY");
                    currentScene.addMerchant(new Merchant(merchantName, merchantDescription, merchantInvrarity));
                }
                if (Boolean.parseBoolean(rs.getString("PE"))) {
                    String passiveEventDescription = rs.getString("PEDESCRIPT");
                    int passiveEventHpBonus = rs.getInt("PEHP");
                    currentScene.addPassiveEvent(new PassiveEvent(passiveEventDescription, passiveEventHpBonus));
                }
                allScenes.add(currentScene);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("ERROR LOADING SCENES");
            System.out.println(ex.getMessage());
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
