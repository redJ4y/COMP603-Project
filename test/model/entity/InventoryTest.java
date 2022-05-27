package model.entity;

import java.util.List;
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
public class InventoryTest {

    public InventoryTest() {
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
     * Test of equipOrConsume method, of class Inventory.
     */
    @Test
    public void testEquipOrConsume() {
        System.out.println("equipOrConsume");
        int index = 0;
        EntityStats targetStats = null;
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.equipOrConsume(index, targetStats);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Inventory.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        Inventory instance = new Inventory();
        Item expResult = null;
        Item result = instance.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class Inventory.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int index = 0;
        Inventory instance = new Inventory();
        instance.remove(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class Inventory.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        Item item = null;
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.addItem(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEquippedWeapon method, of class Inventory.
     */
    @Test
    public void testSetEquippedWeapon() {
        System.out.println("setEquippedWeapon");
        int index = 0;
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.setEquippedWeapon(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEquippedArmor method, of class Inventory.
     */
    @Test
    public void testSetEquippedArmor() {
        System.out.println("setEquippedArmor");
        int index = 0;
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.setEquippedArmor(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquippedWeapon method, of class Inventory.
     */
    @Test
    public void testGetEquippedWeapon() {
        System.out.println("getEquippedWeapon");
        Inventory instance = new Inventory();
        Weapon expResult = null;
        Weapon result = instance.getEquippedWeapon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquippedArmor method, of class Inventory.
     */
    @Test
    public void testGetEquippedArmor() {
        System.out.println("getEquippedArmor");
        Inventory instance = new Inventory();
        Armor expResult = null;
        Armor result = instance.getEquippedArmor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquippedWeaponIndex method, of class Inventory.
     */
    @Test
    public void testGetEquippedWeaponIndex() {
        System.out.println("getEquippedWeaponIndex");
        Inventory instance = new Inventory();
        int expResult = 0;
        int result = instance.getEquippedWeaponIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquippedArmorIndex method, of class Inventory.
     */
    @Test
    public void testGetEquippedArmorIndex() {
        System.out.println("getEquippedArmorIndex");
        Inventory instance = new Inventory();
        int expResult = 0;
        int result = instance.getEquippedArmorIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class Inventory.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        Inventory instance = new Inventory();
        List<Item> expResult = null;
        List<Item> result = instance.getItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasSpace method, of class Inventory.
     */
    @Test
    public void testHasSpace() {
        System.out.println("hasSpace");
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.hasSpace();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class Inventory.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Inventory instance = new Inventory();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
