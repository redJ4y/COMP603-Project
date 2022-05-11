package Model.Data;

// @author Jared Scholz
import Model.Entity.Item;
import Model.Map.Enemy;
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

    private void loadArmor() {
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM ARMOR");
            while (rs.next()) {
                // TODO: add armor to items list
            }
        } catch (SQLException ex) {
            System.out.println("ERROR GETTING ITEMS");
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
