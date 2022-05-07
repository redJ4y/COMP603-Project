package FileIO;

// @author Jared Scholz
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String USER_NAME = "rpg_game";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:GameData_Ebd; create=true";
    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("ERROR: COULD NOT CONNECT TO DATABASE");
            System.out.println(ex.getMessage());
        }
    }
}
