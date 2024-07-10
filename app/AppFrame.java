package app;

import sweeper.SweeperBoard;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    public int difficulty = 1; // 1 = easy, 2 = medium, 3 = hard, 4 = catastrophic

    MenuPanel menuPanel;
    HelpPanel helpPanel;
    GamePanel gamePanel;
    SweeperBoard sweeperBoard;
    ImageIcon imageIcon = new ImageIcon(getClass().getResource("surprised.png"));

    public AppFrame() {
        // Set properties
        this.difficulty = 1;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600,600);
        this.setVisible(true);
        this.setTitle("BlitzSweeper");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(imageIcon.getImage());

        menuPanel = new MenuPanel(this,0, 10, 600, 590);
        helpPanel = new HelpPanel(this,0, 10, 600, 590);
        gamePanel = new GamePanel(this,0, 10, 600, 590);

        // Set panels
        this.add(menuPanel);
        this.add(helpPanel);
        this.add(gamePanel);
        menuPanel.setVisible(true);
        helpPanel.setVisible(false);
        gamePanel.setVisible(false);
    }
}
