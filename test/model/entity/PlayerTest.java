package model.entity;

import java.awt.Point;
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
public class PlayerTest {

    public PlayerTest() {
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
     * Test of getAttack method, of class Player.
     */
    @Test
    public void testGetAttack() {
        System.out.println("getAttack");
        EntityStats enemyStats = null;
        Player instance = null;
        int expResult = 0;
        int result = instance.getAttack(enemyStats);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCoins method, of class Player.
     */
    @Test
    public void testAddCoins() {
        System.out.println("addCoins");
        int defeatedEnemyRarity = 0;
        Player instance = null;
        int expResult = 0;
        int result = instance.addCoins(defeatedEnemyRarity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCoins method, of class Player.
     */
    @Test
    public void testRemoveCoins() {
        System.out.println("removeCoins");
        int amount = 0;
        Player instance = null;
        instance.removeCoins(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class Player.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        Point newPosition = null;
        Player instance = null;
        instance.move(newPosition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasWeapon method, of class Player.
     */
    @Test
    public void testHasWeapon() {
        System.out.println("hasWeapon");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.hasWeapon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDamageMin method, of class Player.
     */
    @Test
    public void testGetDamageMin() {
        System.out.println("getDamageMin");
        Player instance = null;
        int expResult = 0;
        int result = instance.getDamageMin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDamageMax method, of class Player.
     */
    @Test
    public void testGetDamageMax() {
        System.out.println("getDamageMax");
        Player instance = null;
        int expResult = 0;
        int result = instance.getDamageMax();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStats method, of class Player.
     */
    @Test
    public void testGetStats() {
        System.out.println("getStats");
        Player instance = null;
        EntityStats expResult = null;
        EntityStats result = instance.getStats();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Player instance = null;
        Point expResult = null;
        Point result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTravelMap method, of class Player.
     */
    @Test
    public void testGetTravelMap() {
        System.out.println("getTravelMap");
        Player instance = null;
        TravelMap expResult = null;
        TravelMap result = instance.getTravelMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInventory method, of class Player.
     */
    @Test
    public void testGetInventory() {
        System.out.println("getInventory");
        Player instance = null;
        Inventory expResult = null;
        Inventory result = instance.getInventory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoins method, of class Player.
     */
    @Test
    public void testGetCoins() {
        System.out.println("getCoins");
        Player instance = null;
        int expResult = 0;
        int result = instance.getCoins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
