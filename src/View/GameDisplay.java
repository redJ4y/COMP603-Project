package View;

// @author Jared Scholz
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameDisplay extends JPanel {

    // declare all of the possible UI elements
    private GameplayView gameplayView;
    
    public GameDisplay() {
        super(new BorderLayout());
        
        gameplayView = new GameplayView();
        gameplayView.setVisible(false);
        
        gameplayView.setVisible(true);
        super.add(gameplayView, BorderLayout.WEST);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GameDisplay());
        frame.pack();
        frame.setSize(768, 488);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
