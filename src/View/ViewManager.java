package View;

// @author Jared Scholz
import Controller.GameDriver;
import Model.Entity.Item;
import Model.Entity.Player;
import Model.Map.Merchant;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ViewManager extends JPanel {

    private GameDriver gameDriver;

    private JPanel gameArea; // to use CardLayout
    private JTabbedPane playerArea;

    private PregameMenuView pregameMenu;
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

        pregameMenu = new PregameMenuView(this);
        super.add(pregameMenu, BorderLayout.CENTER);

        initializePanels();
    }

    public void display() {
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setPreferredSize(new Dimension(800, 488));
        frame.setMinimumSize(new Dimension(800, 488));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializePanels() {
        // set up the two sides (gameArea and playerArea):
        gameArea = new JPanel(new CardLayout());
        playerArea = new JTabbedPane();
        playerArea.setBorder(new EmptyBorder(0, 6, 12, 6));
        gameArea.setVisible(false); // hidden until pregame menu is complete
        playerArea.setVisible(false);
        super.add(gameArea, BorderLayout.WEST);
        super.add(playerArea, BorderLayout.EAST);

        // initialize gameArea:
        gameplayView = new GameplayView(this);
        gameArea.add(gameplayView, GameAreaOptions.GAMEPLAY.name());

        // initialize playerArea:
        int scaleMode = Image.SCALE_SMOOTH; // set the scale mode for icon scaling
        inventoryView = new InventoryView(this);
        ImageIcon inventoryIcon = new ImageIcon(new ImageIcon("icons/inventory.png").getImage().getScaledInstance(20, 20, scaleMode));
        playerArea.addTab("Inventory", inventoryIcon, inventoryView);
        mapView = new MapView();
        ImageIcon mapIcon = new ImageIcon(new ImageIcon("icons/map.png").getImage().getScaledInstance(20, 20, scaleMode));
        playerArea.addTab("Map", mapIcon, mapView);
        statsView = new StatsView();
        ImageIcon statsIcon = new ImageIcon(new ImageIcon("icons/stats.png").getImage().getScaledInstance(20, 20, scaleMode));
        playerArea.addTab("Stats", statsIcon, statsView);
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
        // updates inventory, travel map, stats
    }

    public void displayText(String text) {
        // display text in the gameplayView
    }

    public void enableGameplayButtons(List<GameplayButtons> buttons) {
        // enables buttons (disables all buttons not in the array)
    }

    public void setMerchant(Merchant merchant, int coins) {
        setGameArea(GameAreaOptions.MERCHANT); // to be undone when finished
    }

    public void setLoot(Item loot, String enemyName) {
        setGameArea(GameAreaOptions.LOOT); // to be undone when finished
    }

    /* ----- End methods to be called by GameDriver ----- 
    *
    *
    *  ----- Methods to be called by view components below ----- */
    public void gameplayButtonPressed(GameplayButtons button) { // used by gameplay panel

    }

    public void purchaseItem(Item selection) { // used by merchant panel

    }

    public void collectLoot() { // used by loot panel

    }

    public void leavePressed(GameAreaOptions source) { // used by merchant and loot panels

    }

    public void equipOrConsumePressed(int index) { // used by inventory panel

    }

    public void dropPressed(int index) { // used by inventory panel

    }

    public void usernameSubmitted(String username) { // used by pregame menu
        // username is already validated
        hidePregameMenu(); // switch to the game view
        // TODO: give the username to the driver
    }
    /* ----- End methods to be called by view components ----- */
}
