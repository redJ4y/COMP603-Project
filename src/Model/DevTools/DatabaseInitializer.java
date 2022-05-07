package Model.DevTools;

// @author Jared Scholz
import Model.Data.DBManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer { // this class transfers data from the old .txt files to the new database

    public static void main(String[] args) {
        OldDataKeeper dataKeeper = new OldDataKeeper();
        dataKeeper.initializeData();

        DBManager database = new DBManager();
        Connection connection = database.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE ARMOR (NAME VARCHAR(512), DESCRIPT VARCHAR(512), RARITY INT, PROT INT, AGIL INT, DUR INT)");
            statement.executeUpdate("CREATE TABLE POTIONS (NAME VARCHAR(512), DESCRIPT VARCHAR(512), RARITY INT, STAT VARCHAR(16), MOD INT)");
            statement.executeUpdate("CREATE TABLE WEAPONS (NAME VARCHAR(512), DESCRIPT VARCHAR(512), RARITY INT, DMG VARCHAR(16), AP INT, DUR INT");
            statement.executeUpdate("CREATE TABLE MERCHANTS (NAME VARCHAR(512), DESCRIPT VARCHAR(512), INVRARITY INT)");
            statement.executeUpdate("CREATE TABLE PASSIVEEVENTS (DESCRIPT VARCHAR(512), HP INT)");
            statement.executeUpdate("CREATE TABLE TRAPS (DESCRIPT VARCHAR(512), STAT VARCHAR(16), MOD INT)");
            statement.executeUpdate("CREATE TABLE ENEMIES (NAME VARCHAR(512), DESCRIPT VARCHAR(512), TYPE VARCHAR(32), RARITY INT, DMG VARCHAR(16), HP INT, APM INT, PROT INT, AGIL INT");
            statement.executeUpdate("CREATE TABLE SCENES (VIEW VARCHAR(512), DESCRIPT VARCHAR(512), ENEM VARCHAR(32), "
                    + "TRAP VARCHAR(8), TRAPDESCRIPT VARCHAR(512), TRAPSTAT VARCHAR(16), TRAPMOD INT, "
                    + "MERCH VARCHAR(8), MERCHNAME VARCHAR(512), MERCHDESCRIPT VARCHAR(512), MERCHINVRARITY INT, "
                    + "PE VARCHAR(8), PEDESCRIPT VARCHAR(512), PEHP INT)");

            // TODO: FILL TABLES
        } catch (SQLException ex) {
            System.out.println("DATA TRANSFER FAILED");
            System.out.println(ex.getMessage());
        }
    }
}
