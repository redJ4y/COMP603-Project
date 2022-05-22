package view;

// @author Jared Scholz
import controller.Direction;
import controller.GameDriver;
import model.entity.Item;
import model.entity.Player;
import model.map.Merchant;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ViewManager extends JPanel {

    private final GameDriver gameDriver;

    private JPanel gameArea; // to use CardLayout
    private JTabbedPane playerArea;

    private final PregameMenuView pregameMenu;
    // gameArea panels:
    private GameplayView gameplayView;
    private MerchantView merchantView;
    private LootView lootView;
    // playerArea panels:
    private MapView mapView;
    private InventoryView inventoryView;
    private StatsView statsView;

    public ViewManager(GameDriver gameDriver) {
        super(new BorderLayout());
        this.gameDriver = gameDriver;
        initializePanels();

        pregameMenu = new PregameMenuView(this);
        super.add(pregameMenu, BorderLayout.CENTER);
    }

    public void display() {
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setMinimumSize(new Dimension(800, 488));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializePanels() {
        // set up the two sides/areas (gameArea and playerArea):
        gameArea = new JPanel(new CardLayout());
        playerArea = new JTabbedPane();
        playerArea.setBorder(new EmptyBorder(0, 6, 12, 6));
        super.add(gameArea, BorderLayout.WEST);
        super.add(playerArea, BorderLayout.EAST);

        // initialize gameArea:
        gameplayView = new GameplayView(this);
        gameArea.add(gameplayView, GameAreaOptions.GAMEPLAY.name());
        merchantView = new MerchantView(this);
        gameArea.add(merchantView, GameAreaOptions.MERCHANT.name());
        lootView = new LootView(this);
        gameArea.add(lootView, GameAreaOptions.LOOT.name());

        // initialize playerArea:
        int scaleMode = Image.SCALE_SMOOTH; // set the scale mode for icon scaling
        inventoryView = new InventoryView(this);
        ImageIcon inventoryIcon = new ImageIcon(new ImageIcon("icons/pinventory.png").getImage().getScaledInstance(20, 20, scaleMode));
        playerArea.addTab("Inventory", inventoryIcon, inventoryView);
        mapView = new MapView();
        ImageIcon mapIcon = new ImageIcon(new ImageIcon("icons/pmap.png").getImage().getScaledInstance(20, 20, scaleMode));
        playerArea.addTab("Map", mapIcon, mapView);
        statsView = new StatsView();
        ImageIcon statsIcon = new ImageIcon(new ImageIcon("icons/pstats.png").getImage().getScaledInstance(20, 20, scaleMode));
        playerArea.addTab("Stats", statsIcon, statsView);

        // hide areas until the pregame menu is complete:
        gameArea.setVisible(false);
        playerArea.setVisible(false);
    }

    private void hidePregameMenu() {
        pregameMenu.setVisible(false);
        super.remove(pregameMenu);
        gameArea.setVisible(true);
        playerArea.setVisible(true);
    }

    private void setGameArea(GameAreaOptions card) {
        ((CardLayout) gameArea.getLayout()).show(gameArea, card.name());
    }

    /* ----- Methods to be called by GameDriver below ----- */
    public void updatePlayerInfo(Player player) {
        inventoryView.updateInventory(player);
        statsView.updateStats(player);
        mapView.updateMap(player.getTravelMap(), player.getPosition());
    }

    /* Printing Methods */
    public void displayTextLine(String text) {
        gameplayView.addText(text + "\n");
    }

    public void displayTextLine() {
        gameplayView.addText("\n");
    }

    public void displayText(String text) {
        gameplayView.addText(text);
    } // End Printing Methods

    public void enableGameplayButtons(List<GameplayButtons> buttons) {
        gameplayView.enableButtons(buttons);
    } // only the selected buttons will be enabled

    public void enableGameplayButton(GameplayButtons button) { // for a single button
        List<GameplayButtons> wrapperList = new ArrayList<>(1);
        wrapperList.add(button);
        gameplayView.enableButtons(wrapperList);
    } // only the selected button will be enabled

    public void setMerchant(Merchant merchant, int coins, boolean invFull) {
        merchantView.prepPanel(merchant, coins, invFull);
        setGameArea(GameAreaOptions.MERCHANT); // to be undone when finished
    }

    public void setLoot(Item loot, int numCoins, boolean invFull) {
        lootView.prepPanel(loot, numCoins, invFull);
        setGameArea(GameAreaOptions.LOOT); // to be undone when finished
    }

    /* ----- End methods to be called by GameDriver ----- 
    *
    *
    *  ----- Methods to be called by view components below ----- */
    public void gameplayButtonPressed(GameplayButtons button) { // used by gameplay panel
        // convert button press into action method call:
        switch (button) {
            case N: // fall through
            case S: // fall through
            case E: // fall through
            case W:
                gameDriver.look(Direction.charToDirection(button.name().toLowerCase().charAt(0)));
                break;
            case YES:
                gameDriver.goDirection();
                break;
            case NO:
                gameDriver.pickNewDirection();
                break;
            case ADVENTURE:
                gameDriver.adventure();
                break;
            case ATTACK:
                gameDriver.attack();
                break;
            case RUN:
                gameDriver.runAway();
                break;
            case QUIT:
                gameDriver.quitGame();
                break;
        }
    }

    public void purchaseItem(int index) { // used by merchant panel
        // index is already validated
    }

    public void collectLoot() { // used by loot panel

    }

    public void leavePressed(GameAreaOptions source) { // used by merchant and loot panels
        setGameArea(GameAreaOptions.GAMEPLAY);

    }

    public void equipOrConsumePressed(int index) { // used by inventory panel
        // index is already validated
    }

    public void dropPressed(int index) { // used by inventory panel
        // index is already validated

        lootView.invNotFull(); // make sure the user can pick up a waiting item
    }

    public void usernameSubmitted(String username) { // used by pregame menu
        // username is already validated
        gameDriver.checkForGameSave(username);
        hidePregameMenu(); // switch to playing the game (info has been updated)
    }
    /* ----- End methods to be called by view components ----- */
}

// TODO: allow printing text with a delay (from the last message). only enable buttons when all text in the queue is printed
