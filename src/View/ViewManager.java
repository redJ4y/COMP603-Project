package View;

// @author Jared Scholz
import Controller.GameDriver;
import Model.Entity.Item;
import Model.Entity.Player;
import Model.Map.Merchant;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/*
Notes:
gameArea panels must have a border of 6, 6, 6, 6.
playerArea panels must have no borders (it is included in the JTabbedPane).
 */
public class ViewManager extends JPanel {

    private GameDriver gameDriver;

    private JPanel gameArea; // to have CardLayout
    private JTabbedPane playerArea;

    // private PregameMenu pregameMenu;
    // gameArea panels:
    private GameplayView gameplayView;
    // private MerchantView merchantView;
    // private LootView lootView;
    // playerArea panels:
    private MapView mapView;
    // private InventoryView inventoryView;
    // private StatsView statsView;

    public ViewManager(GameDriver gameDriver) {
        super(new BorderLayout());
        this.gameDriver = gameDriver;
        // set up the two sides - gameArea and playerArea:
        gameArea = new JPanel(new CardLayout());
        playerArea = new JTabbedPane();
        playerArea.setBorder(new EmptyBorder(6, 6, 6, 6));
        super.add(gameArea, BorderLayout.WEST);
        super.add(playerArea, BorderLayout.EAST);
        // initialize gameArea:
        gameplayView = new GameplayView(this);
        gameArea.add(gameplayView, GameAreaOptions.GAMEPLAY.name());
        // initialize playerArea:
        mapView = new MapView(this);
        playerArea.addTab("Map", mapView); // TODO: add tab with icon
    }

    public void initDisplay() {
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    public void setUsername(String username) { // used by pregame menu

    }
    /* ----- End methods to be called by view components ----- */
}
