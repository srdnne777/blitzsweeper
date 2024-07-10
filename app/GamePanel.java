package app;

import sweeper.SweeperBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    
    JButton menuReturn = new JButton();
    public JButton restartGame = new JButton();
    public JLabel mineCount = new JLabel();
    public JLabel flagsPlaced = new JLabel();
    public JLabel gameFinishLabel = new JLabel();
    AppFrame parent;
    
    public GamePanel(AppFrame parent, int x, int y, int width, int height) {
        this.parent = parent;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setVisible(true);
        this.setBounds(0, 0, 600, 600);
        
        // Properties for return button
        menuReturn.setText("Return to Menu");
        Font playFont = menuReturn.getFont();
        menuReturn.setFont(new Font(playFont.getName(), Font.BOLD, 18));
        menuReturn.setBounds(10, 530, 160, 24);
        menuReturn.addActionListener(this);
        menuReturn.setFocusPainted(false);
        menuReturn.setBackground(new Color(200,200,200));
        menuReturn.setHorizontalAlignment(SwingConstants.CENTER);
        menuReturn.setVerticalAlignment(SwingConstants.CENTER);
        menuReturn.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 3));

        // Properties for restart game button
        restartGame.setText("Restart game");
        Font restartFont = restartGame.getFont();
        restartGame.setFont(new Font(playFont.getName(), Font.BOLD, 18));
        restartGame.setBounds(414, 530, 160, 24);
        restartGame.addActionListener(this);
        restartGame.setFocusPainted(false);
        restartGame.setBackground(new Color(200,200,200));
        restartGame.setVisible(false);
        restartGame.setHorizontalAlignment(SwingConstants.CENTER);
        restartGame.setVerticalAlignment(SwingConstants.CENTER);
        restartGame.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 3));

        // Properties for mine count label
        mineCount.setText("Mine count: ");
        Font mcFont = mineCount.getFont();
        mineCount.setFont(new Font(mcFont.getName(), mcFont.getStyle(), 24));
        mineCount.setBounds(6, -130, 300, 300);

        // Properties for flags placed label
        flagsPlaced.setText("Flags placed: 0");
        Font fpFont = flagsPlaced.getFont();
        flagsPlaced.setFont(new Font(fpFont.getName(), fpFont.getStyle(), 24));
        flagsPlaced.setBounds(380, -130, 300, 300);

        // Properties for mine count label
        gameFinishLabel.setText("");

        this.add(menuReturn);
        this.add(restartGame);
        this.add(mineCount);
        this.add(flagsPlaced);
        this.add(gameFinishLabel);
    }

    // Method from implementing ActionListener to listen for events (mainly from button sources)
    @Override
    public void actionPerformed(ActionEvent e) {

        // On menu click return delete the created minesweeper board
        if (e.getSource() == menuReturn) {
            this.setVisible(false);
            flagsPlaced.setText("Flags placed: 0");
            gameFinishLabel.setText("");
            parent.menuPanel.setVisible(true);
            this.remove(parent.sweeperBoard);
            parent.sweeperBoard = null;
        }

        // On restart game button click when it appears
        if (e.getSource() == restartGame) {
            flagsPlaced.setText("Flags placed: 0");
            gameFinishLabel.setText("");
            this.remove(parent.sweeperBoard);
            restartGame.setVisible(false);
            parent.sweeperBoard = null;
            parent.sweeperBoard = new SweeperBoard(parent, parent.gamePanel, parent.difficulty);
            parent.gamePanel.add(parent.sweeperBoard);
        }
    }
}
