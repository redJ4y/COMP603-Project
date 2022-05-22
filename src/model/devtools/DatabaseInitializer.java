package model.devtools;

/*
THIS CLASS IS USED IN DEVELOPMENT ONLY
This is not used within the game code.
This was used only to initialize the new database.
 */
// @author Jared Scholz
import model.data.DBManager;
import model.entity.Armor;
import model.entity.Item;
import model.entity.Potion;
import model.entity.Weapon;
import model.map.Enemy;
import model.map.Merchant;
import model.map.PassiveEvent;
import model.map.Scene;
import model.map.Trap;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer { // this class transfers data from the old .txt files to the new database

    // DELETE EXISTING TABLES BEFORE RUNNING!
    public static void main(String[] args) {
        OldDataKeeper dataKeeper = new OldDataKeeper();
        dataKeeper.initializeData();

        DBManager database = new DBManager();
        Connection connection = database.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE ARMOR (NAME VARCHAR(512), DESCRIPT VARCHAR(512), RARITY INT, PROT INT, AGIL INT, DUR INT)");
            statement.executeUpdate("CREATE TABLE POTIONS (NAME VARCHAR(512), DESCRIPT VARCHAR(512), RARITY INT, STAT VARCHAR(16), MOD INT)");
            statement.executeUpdate("CREATE TABLE WEAPONS (NAME VARCHAR(512), DESCRIPT VARCHAR(512), RARITY INT, DMG VARCHAR(16), AP INT, DUR INT)");
            statement.executeUpdate("CREATE TABLE MERCHANTS (NAME VARCHAR(512), DESCRIPT VARCHAR(512), INVRARITY INT)");
            statement.executeUpdate("CREATE TABLE PASSIVEEVENTS (DESCRIPT VARCHAR(512), HP INT)");
            statement.executeUpdate("CREATE TABLE TRAPS (DESCRIPT VARCHAR(512), STAT VARCHAR(16), MOD INT)");
            statement.executeUpdate("CREATE TABLE ENEMIES (NAME VARCHAR(512), DESCRIPT VARCHAR(512), TYPE VARCHAR(32), RARITY INT, DMG VARCHAR(16), HP INT, APM INT, PROT INT, AGIL INT)");
            statement.executeUpdate("CREATE TABLE SCENES (SCENEVIEW VARCHAR(512), DESCRIPT VARCHAR(512), ENEM VARCHAR(32), "
                    + "TRAP VARCHAR(8), TRAPDESCRIPT VARCHAR(512), TRAPSTAT VARCHAR(16), TRAPMOD INT, "
                    + "MERCH VARCHAR(8), MERCHNAME VARCHAR(512), MERCHDESCRIPT VARCHAR(512), MERCHINVRARITY INT, "
                    + "PE VARCHAR(8), PEDESCRIPT VARCHAR(512), PEHP INT)");
            statement.executeUpdate("CREATE TABLE SAVES (USERNAME VARCHAR(256), SERGAMEMAP BLOB, SERSTATS BLOB, SERPOSITION BLOB, SERTRAVELMAP BLOB, SERINVENTORY BLOB, COINS INT)");

            for (Item current : dataKeeper.getAllItems()) {
                if (current instanceof Armor) {
                    Armor toAdd = (Armor) current;
                    statement.executeUpdate("INSERT INTO ARMOR VALUES ('" + toAdd.getName().replace("'", "''") + "', '" + toAdd.getDescription().replace("'", "''") + "', " + toAdd.getRarity() + ", " + toAdd.getProtectionBonus() + ", " + toAdd.getAgilityBonus() + ", " + toAdd.getDurability() + ")");
                } else if (current instanceof Potion) {
                    Potion toAdd = (Potion) current;
                    statement.executeUpdate("INSERT INTO POTIONS VALUES ('" + toAdd.getName().replace("'", "''") + "', '" + toAdd.getDescription().replace("'", "''") + "', " + toAdd.getRarity() + ", '" + toAdd.getStat().name() + "', " + toAdd.getModification() + ")");
                } else if (current instanceof Weapon) {
                    Weapon toAdd = (Weapon) current;
                    statement.executeUpdate("INSERT INTO WEAPONS VALUES ('" + toAdd.getName().replace("'", "''") + "', '" + toAdd.getDescription().replace("'", "''") + "', " + toAdd.getRarity() + ", '" + toAdd.getDamageMin() + "," + toAdd.getDamageMax() + "', " + toAdd.getArmorPiercing() + ", " + toAdd.getDurability() + ")");
                }
            }

            for (Merchant current : dataKeeper.getAllMerchants()) {
                statement.executeUpdate("INSERT INTO MERCHANTS VALUES ('" + current.getName().replace("'", "''") + "', '" + current.getDescription().replace("'", "''") + "', " + current.getInvrarity() + ")");
            }

            for (PassiveEvent current : dataKeeper.getAllPassiveEvents()) {
                statement.executeUpdate("INSERT INTO PASSIVEEVENTS VALUES ('" + current.getDescription().replace("'", "''") + "', " + current.getHpBonus() + ")");
            }

            for (Trap current : dataKeeper.getAllTraps()) {
                statement.executeUpdate("INSERT INTO TRAPS VALUES ('" + current.getDescription().replace("'", "''") + "', '" + current.getStat().name() + "', " + current.getModification() + ")");
            }

            for (Enemy current : dataKeeper.getAllEnemies()) {
                statement.executeUpdate("INSERT INTO ENEMIES VALUES ('" + current.getName().replace("'", "''") + "', '" + current.getDescription().replace("'", "''") + "', '" + current.getEnemyType().name() + "', " + current.getRarity() + ", '" + current.getDamageMin() + "," + current.getDamageMax() + "', "
                        + current.getStats().getHealth() + ", " + current.getStats().getArmorPiercingModifier() + ", " + current.getStats().getProtection() + ", " + current.getStats().getAgility() + ")");
            }

            for (Scene current : dataKeeper.getAllScenes()) {
                String insertData = "INSERT INTO SCENES VALUES ('" + current.getView().replace("'", "''") + "', '" + current.getDescription().replace("'", "''") + "', '" + current.getEnemyType().name() + "', ";

                if (!current.getPossibleTraps().isEmpty()) {
                    Trap trap = current.getPossibleTraps().iterator().next();
                    insertData += "'true', '" + trap.getDescription().replace("'", "''") + "', '" + trap.getStat().name() + "', " + trap.getModification() + ", ";
                } else {
                    insertData += "'false', 'null', 'null', 0, ";
                }

                if (!current.getPossibleMerchants().isEmpty()) {
                    Merchant merchant = current.getPossibleMerchants().iterator().next();
                    insertData += "'true', '" + merchant.getName().replace("'", "''") + "', '" + merchant.getDescription().replace("'", "''") + "', " + merchant.getInvrarity() + ", ";
                } else {
                    insertData += "'false', 'null', 'null', 0, ";
                }

                if (!current.getPossiblePassiveEvents().isEmpty()) {
                    PassiveEvent passiveEvent = current.getPossiblePassiveEvents().iterator().next();
                    insertData += "'true', '" + passiveEvent.getDescription().replace("'", "''") + "', " + passiveEvent.getHpBonus() + ")";
                } else {
                    insertData += "'false', 'null', 0)";
                }

                statement.executeUpdate(insertData);
            }

            statement.close();
        } catch (SQLException ex) {
            System.out.println("DATA TRANSFER FAILED");
            System.out.println(ex.getMessage());
        }
    }
}
