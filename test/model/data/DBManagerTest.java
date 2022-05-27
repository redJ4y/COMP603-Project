package model.data;

import java.sql.Connection;
import java.util.List;
import model.entity.Item;
import model.entity.Player;
import model.map.Enemy;
import model.map.GameMap;
import model.map.Merchant;
import model.map.PassiveEvent;
import model.map.Scene;
import model.map.Trap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jared
 */
public class DBManagerTest {

    public DBManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class DBManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DBManager instance = new DBManager();
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkGameSave method, of class DBManager.
     */
    @Test
    public void testCheckGameSave() {
        System.out.println("checkGameSave");
        String username = "";
        DBManager instance = new DBManager();
        boolean expResult = false;
        boolean result = instance.checkGameSave(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveGame method, of class DBManager.
     */
    @Test
    public void testSaveGame() {
        System.out.println("saveGame");
        GameMap gameMap = null;
        Player player = null;
        DBManager instance = new DBManager();
        instance.saveGame(gameMap, player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteGameSave method, of class DBManager.
     */
    @Test
    public void testDeleteGameSave() {
        System.out.println("deleteGameSave");
        DBManager instance = new DBManager();
        instance.deleteGameSave();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExistingMap method, of class DBManager.
     */
    @Test
    public void testGetExistingMap() {
        System.out.println("getExistingMap");
        DBManager instance = new DBManager();
        GameMap expResult = null;
        GameMap result = instance.getExistingMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExistingPlayer method, of class DBManager.
     */
    @Test
    public void testGetExistingPlayer() {
        System.out.println("getExistingPlayer");
        DBManager instance = new DBManager();
        Player expResult = null;
        Player result = instance.getExistingPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeData method, of class DBManager.
     */
    @Test
    public void testInitializeData() {
        System.out.println("initializeData");
        DBManager instance = new DBManager();
        instance.initializeData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItems method, of class DBManager.
     */
    @Test
    public void testGetAllItems() {
        System.out.println("getAllItems");
        DBManager instance = new DBManager();
        List<Item> expResult = null;
        List<Item> result = instance.getAllItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEnemies method, of class DBManager.
     */
    @Test
    public void testGetAllEnemies() {
        System.out.println("getAllEnemies");
        DBManager instance = new DBManager();
        List<Enemy> expResult = null;
        List<Enemy> result = instance.getAllEnemies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMerchants method, of class DBManager.
     */
    @Test
    public void testGetAllMerchants() {
        System.out.println("getAllMerchants");
        DBManager instance = new DBManager();
        List<Merchant> expResult = null;
        List<Merchant> result = instance.getAllMerchants();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPassiveEvents method, of class DBManager.
     */
    @Test
    public void testGetAllPassiveEvents() {
        System.out.println("getAllPassiveEvents");
        DBManager instance = new DBManager();
        List<PassiveEvent> expResult = null;
        List<PassiveEvent> result = instance.getAllPassiveEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTraps method, of class DBManager.
     */
    @Test
    public void testGetAllTraps() {
        System.out.println("getAllTraps");
        DBManager instance = new DBManager();
        List<Trap> expResult = null;
        List<Trap> result = instance.getAllTraps();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllScenes method, of class DBManager.
     */
    @Test
    public void testGetAllScenes() {
        System.out.println("getAllScenes");
        DBManager instance = new DBManager();
        List<Scene> expResult = null;
        List<Scene> result = instance.getAllScenes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
