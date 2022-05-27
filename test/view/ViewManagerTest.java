package view;

import java.util.List;
import model.entity.Item;
import model.entity.Player;
import model.map.Merchant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TODO: test task queue (add some and look at count, add some delays and make
 * sure it waits)
 *
 * @author jared
 */
public class ViewManagerTest {

    public ViewManagerTest() {
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
     * Test of stopTaskRunner method, of class ViewManager.
     */
    @Test
    public void testStopTaskRunner() {
        System.out.println("stopTaskRunner");
        ViewManager instance = null;
        instance.stopTaskRunner();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumTasks method, of class ViewManager.
     */
    @Test
    public void testGetNumTasks() {
        System.out.println("getNumTasks");
        ViewManager instance = null;
        int expResult = 0;
        int result = instance.getNumTasks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of display method, of class ViewManager.
     */
    @Test
    public void testDisplay() {
        System.out.println("display");
        ViewManager instance = null;
        instance.display();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePlayerInfo method, of class ViewManager.
     */
    @Test
    public void testUpdatePlayerInfo() {
        System.out.println("updatePlayerInfo");
        Player player = null;
        ViewManager instance = null;
        instance.updatePlayerInfo(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePlayerInfoDirectly method, of class ViewManager.
     */
    @Test
    public void testUpdatePlayerInfoDirectly() {
        System.out.println("updatePlayerInfoDirectly");
        Player player = null;
        ViewManager instance = null;
        instance.updatePlayerInfoDirectly(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disablePlayerInfoDirectly method, of class ViewManager.
     */
    @Test
    public void testDisablePlayerInfoDirectly() {
        System.out.println("disablePlayerInfoDirectly");
        ViewManager instance = null;
        instance.disablePlayerInfoDirectly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayTextLine method, of class ViewManager.
     */
    @Test
    public void testDisplayTextLine_String() {
        System.out.println("displayTextLine");
        String text = "";
        ViewManager instance = null;
        instance.displayTextLine(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayTextLine method, of class ViewManager.
     */
    @Test
    public void testDisplayTextLine_0args() {
        System.out.println("displayTextLine");
        ViewManager instance = null;
        instance.displayTextLine();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayText method, of class ViewManager.
     */
    @Test
    public void testDisplayText() {
        System.out.println("displayText");
        String text = "";
        ViewManager instance = null;
        instance.displayText(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableGameplayButtons method, of class ViewManager.
     */
    @Test
    public void testEnableGameplayButtons() {
        System.out.println("enableGameplayButtons");
        List<GameplayButtons> buttons = null;
        ViewManager instance = null;
        instance.enableGameplayButtons(buttons);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableGameplayButton method, of class ViewManager.
     */
    @Test
    public void testEnableGameplayButton() {
        System.out.println("enableGameplayButton");
        GameplayButtons button = null;
        ViewManager instance = null;
        instance.enableGameplayButton(button);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMerchant method, of class ViewManager.
     */
    @Test
    public void testSetMerchant() {
        System.out.println("setMerchant");
        Merchant merchant = null;
        int coins = 0;
        boolean invFull = false;
        ViewManager instance = null;
        instance.setMerchant(merchant, coins, invFull);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoot method, of class ViewManager.
     */
    @Test
    public void testSetLoot() {
        System.out.println("setLoot");
        Item loot = null;
        int numCoins = 0;
        boolean invFull = false;
        ViewManager instance = null;
        instance.setLoot(loot, numCoins, invFull);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDelay method, of class ViewManager.
     */
    @Test
    public void testAddDelay() {
        System.out.println("addDelay");
        int ms = 0;
        ViewManager instance = null;
        instance.addDelay(ms);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gameplayButtonPressed method, of class ViewManager.
     */
    @Test
    public void testGameplayButtonPressed() {
        System.out.println("gameplayButtonPressed");
        GameplayButtons button = null;
        ViewManager instance = null;
        instance.gameplayButtonPressed(button);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of purchaseItem method, of class ViewManager.
     */
    @Test
    public void testPurchaseItem() {
        System.out.println("purchaseItem");
        int index = 0;
        ViewManager instance = null;
        instance.purchaseItem(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of collectLoot method, of class ViewManager.
     */
    @Test
    public void testCollectLoot() {
        System.out.println("collectLoot");
        ViewManager instance = null;
        instance.collectLoot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leavePressed method, of class ViewManager.
     */
    @Test
    public void testLeavePressed() {
        System.out.println("leavePressed");
        GameAreaOptions source = null;
        ViewManager instance = null;
        instance.leavePressed(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equipPressed method, of class ViewManager.
     */
    @Test
    public void testEquipPressed() {
        System.out.println("equipPressed");
        int index = 0;
        ViewManager instance = null;
        instance.equipPressed(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consumePressed method, of class ViewManager.
     */
    @Test
    public void testConsumePressed() {
        System.out.println("consumePressed");
        int index = 0;
        ViewManager instance = null;
        instance.consumePressed(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropPressed method, of class ViewManager.
     */
    @Test
    public void testDropPressed() {
        System.out.println("dropPressed");
        int index = 0;
        ViewManager instance = null;
        instance.dropPressed(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usernameSubmitted method, of class ViewManager.
     */
    @Test
    public void testUsernameSubmitted() {
        System.out.println("usernameSubmitted");
        String username = "";
        ViewManager instance = null;
        instance.usernameSubmitted(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
