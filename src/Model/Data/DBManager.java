package Model.Data;

// @author Jared Scholz
import Model.Entity.Armor;
import Model.Entity.Item;
import Model.Entity.Potion;
import Model.Entity.StatType;
import Model.Entity.Weapon;
import Model.Map.Enemy;
import Model.Map.EnemyType;
import Model.Map.Merchant;
import Model.Map.PassiveEvent;
import Model.Map.Scene;
import Model.Map.Trap;
import java.sql.Connection;
import java.sql.DriverManager;
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

    private final List<Item> allItems;
    private final List<Enemy> allEnemies;
    private final List<Merchant> allMerchants;
    private final List<PassiveEvent> allPassiveEvents;
    private final List<Trap> allTraps;
    private final List<Scene> allScenes;

    public DBManager() {
        establishConnection();

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
