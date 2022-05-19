package View;

// @author Jared Scholz
import Controller.GameDriver;
import Model.Entity.Item;
import Model.Entity.Player;
import Model.Map.Merchant;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewManager extends JPanel {

    private GameDriver gameDriver;

    // private PregameMenu pregameMenu;
    // Game Area panels:
    private GameplayView gameplayView;
    // private MerchantView merchantView;
    // private LootView lootView;
    // Player Area panels:
    // private MapView mapView;
    // private InventoryView inventoryView;
    // private StatsView statsView;

    public ViewManager(GameDriver gameDriver) {
        super(new BorderLayout());
        this.gameDriver = gameDriver;

        // pregameMenu = new PregameMenu(this);
        // pregameMenu.setVisible(false);
        gameplayView = new GameplayView(this);
        // merchantView = new MerchantView(this);
        // merchantView.setVisible(false);
        // lootView = new LootView(this);
        // lootView.setVisible(false);
        // mapView = new MapView(this);
        // mapView.setVisible(false);
        // inventoryView = new InventoryView(this);
        // inventoryView.setVisible(false);
        // statsView = new StatsView(this);
        // statsView.setVisible(false);

        // super.add(pregameMenu, BorderLayout.CENTER);
        gameplayView.setVisible(true);
        super.add(gameplayView, BorderLayout.WEST);
        // super.add(merchantView, BorderLayout.WEST);
        // super.add(lootView, BorderLayout.WEST);
        // super.add(mapView, BorderLayout.EAST);
        // super.add(inventoryView, BorderLayout.EAST);
        // super.add(statsView, BorderLayout.EAST);
    }

    public void initDisplay() {
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    /* ----- End methods to be called by GameDriver ----- */
    private void setGameArea(GameAreaOptions panel) {

    }

    /* ----- Methods to be called by JPanels below ----- */
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
    /* ----- End methods to be called by JPanels ----- */
}
