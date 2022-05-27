package model.map;

import java.util.List;
import java.util.Random;
import java.util.Set;
import model.entity.Item;
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
public class SceneTest {

    public SceneTest() {
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
     * Test of pickEvent method, of class Scene.
     */
    @Test
    public void testPickEvent() {
        System.out.println("pickEvent");
        Scene instance = null;
        Event expResult = null;
        Event result = instance.pickEvent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBattleCompleted method, of class Scene.
     */
    @Test
    public void testSetBattleCompleted() {
        System.out.println("setBattleCompleted");
        Scene instance = null;
        instance.setBattleCompleted();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeBattle method, of class Scene.
     */
    @Test
    public void testInitializeBattle() {
        System.out.println("initializeBattle");
        List<Enemy> allEnemies = null;
        List<Item> allItems = null;
        Random randGen = null;
        Scene instance = null;
        instance.initializeBattle(allEnemies, allItems, randGen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeMerchants method, of class Scene.
     */
    @Test
    public void testInitializeMerchants() {
        System.out.println("initializeMerchants");
        List<Item> allItems = null;
        Random randGen = null;
        Scene instance = null;
        instance.initializeMerchants(allItems, randGen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTrap method, of class Scene.
     */
    @Test
    public void testAddTrap() {
        System.out.println("addTrap");
        Trap trap = null;
        Scene instance = null;
        instance.addTrap(trap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMerchant method, of class Scene.
     */
    @Test
    public void testAddMerchant() {
        System.out.println("addMerchant");
        Merchant merchant = null;
        Scene instance = null;
        instance.addMerchant(merchant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPassiveEvent method, of class Scene.
     */
    @Test
    public void testAddPassiveEvent() {
        System.out.println("addPassiveEvent");
        PassiveEvent passiveEvent = null;
        Scene instance = null;
        instance.addPassiveEvent(passiveEvent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getView method, of class Scene.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        Scene instance = null;
        String expResult = "";
        String result = instance.getView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Scene.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Scene instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnemyType method, of class Scene.
     */
    @Test
    public void testGetEnemyType() {
        System.out.println("getEnemyType");
        Scene instance = null;
        EnemyType expResult = null;
        EnemyType result = instance.getEnemyType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleTraps method, of class Scene.
     */
    @Test
    public void testGetPossibleTraps() {
        System.out.println("getPossibleTraps");
        Scene instance = null;
        Set<Trap> expResult = null;
        Set<Trap> result = instance.getPossibleTraps();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleMerchants method, of class Scene.
     */
    @Test
    public void testGetPossibleMerchants() {
        System.out.println("getPossibleMerchants");
        Scene instance = null;
        Set<Merchant> expResult = null;
        Set<Merchant> result = instance.getPossibleMerchants();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossiblePassiveEvents method, of class Scene.
     */
    @Test
    public void testGetPossiblePassiveEvents() {
        System.out.println("getPossiblePassiveEvents");
        Scene instance = null;
        Set<PassiveEvent> expResult = null;
        Set<PassiveEvent> result = instance.getPossiblePassiveEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Scene.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Scene instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
