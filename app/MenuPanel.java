package app;

import sweeper.SweeperBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    // Create objects
    JLabel label = new JLabel();
    JLabel currentDiff = new JLabel();
    JLabel diffLabel = new JLabel();
    JButton help = new JButton();
    JButton play = new JButton();
    JButton easy = new JButton();
    JButton medium = new JButton();
    JButton hard = new JButton();
    JButton catastrophic = new JButton();
    AppFrame parent;

    // Constructor to create the menu panel
    public MenuPanel(AppFrame parent, int x, int y, int width, int height) {
        this.parent = parent;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setVisible(true);

        // Properties of title label
        label.setText("BlitzSweeper");
        Font titleFont = label.getFont();
        label.setFont(new Font(titleFont.getName(), Font.ITALIC | Font.BOLD, 50));
        label.setBounds(142, 5, width, 50);

        // Properties of current difficulty title label
        diffLabel.setText("Current difficulty:");
        Font diffLFont = diffLabel.getFont();
        diffLabel.setFont(new Font(diffLFont.getName(), Font.BOLD, 20));
        diffLabel.setBounds(320, 110, width, height);

        // Properties of current difficulty label
        currentDiff.setText("Easy");
        Font diffFont = currentDiff.getFont();
        currentDiff.setFont(new Font(diffFont.getName(), Font.ITALIC | Font.BOLD, 40));
        currentDiff.setBounds(100, 150, width, height);
        currentDiff.setVerticalAlignment(SwingConstants.CENTER);
        currentDiff.setHorizontalAlignment(SwingConstants.CENTER);
        currentDiff.setVerticalTextPosition(SwingConstants.CENTER);
        currentDiff.setHorizontalTextPosition(SwingConstants.CENTER);

        // Properties of play button
        play.setText("Play");
        Font playFont = play.getFont();
        play.setFont(new Font(playFont.getName(), Font.BOLD, 30));
        play.setBounds(220, 130, 150, 60);
        play.addActionListener(this);
        play.setFocusPainted(false);
        play.setBackground(new Color(200,200,200));
        play.setVerticalAlignment(SwingConstants.CENTER);
        play.setHorizontalAlignment(SwingConstants.CENTER);
        play.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 3));

        // Properties of help button
        help.setText("Help");
        Font helpFont = help.getFont();
        help.setFont(new Font(helpFont.getName(), Font.BOLD, 30));
        help.setBounds(220, 240, 150, 60);
        help.addActionListener(this);
        help.setFocusPainted(false);
        help.setBackground(new Color(200,200,200));
        help.setVerticalAlignment(SwingConstants.CENTER);
        help.setHorizontalAlignment(SwingConstants.CENTER);
        help.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 3));

        // Properties of easy button
        easy.setText("Easy");
        Font easyFont = easy.getFont();
        easy.setFont(new Font(easyFont.getName(), Font.BOLD, 14));
        easy.setBounds(130, 370, 120, 30);
        easy.addActionListener(this);
        easy.setFocusPainted(false);
        easy.setBackground(new Color(95, 201, 69));
        easy.setVerticalAlignment(SwingConstants.CENTER);
        easy.setHorizontalAlignment(SwingConstants.CENTER);
        easy.setBorder(BorderFactory.createLineBorder(new Color(135, 225, 113), 3));

        // Properties of medium button
        medium.setText("Medium");
        Font mediumFont = medium.getFont();
        medium.setFont(new Font(mediumFont.getName(), Font.BOLD, 14));
        medium.setBounds(130, 410, 120, 30);
        medium.addActionListener(this);
        medium.setFocusPainted(false);
        medium.setBackground(new Color(201, 120, 69));
        medium.setVerticalAlignment(SwingConstants.CENTER);
        medium.setHorizontalAlignment(SwingConstants.CENTER);
        medium.setBorder(BorderFactory.createLineBorder(new Color(227, 155, 110), 3));

        // Properties of hard button
        hard.setText("Hard");
        Font hardFont = hard.getFont();
        hard.setFont(new Font(hardFont.getName(), Font.BOLD, 14));
        hard.setBounds(130, 450, 120, 30);
        hard.addActionListener(this);
        hard.setFocusPainted(false);
        hard.setBackground(new Color(196, 64, 64));
        hard.setVerticalAlignment(SwingConstants.CENTER);
        hard.setHorizontalAlignment(SwingConstants.CENTER);
        hard.setBorder(BorderFactory.createLineBorder(new Color(225, 109, 109), 3));

        // Properties of catastrophic button
        catastrophic.setText("Catastrophic");
        Font catastrophicFont = catastrophic.getFont();
        catastrophic.setFont(new Font(catastrophicFont.getName(), Font.BOLD, 14));
        catastrophic.setBounds(130, 490, 120, 30);
        catastrophic.addActionListener(this);
        catastrophic.setFocusPainted(false);
        catastrophic.setBackground(new Color(68, 0, 79));
        catastrophic.setVerticalAlignment(SwingConstants.CENTER);
        catastrophic.setHorizontalAlignment(SwingConstants.CENTER);
        catastrophic.setForeground(new Color(255, 255,255));
        catastrophic.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        // Add created objects to panel
        this.add(label);
        this.add(diffLabel);
        this.add(currentDiff);
        this.add(play);
        this.add(help);
        this.add(easy);
        this.add(medium);
        this.add(hard);
        this.add(catastrophic);
    }

    // Method from implementing ActionListener to listen for events (mainly from button sources)
    @Override
    public void actionPerformed(ActionEvent e) {

        // On help click show the instructions panel
        if (e.getSource() == help) {
            this.setVisible(false);
            parent.helpPanel.setVisible(true);
        }

        // On play click create fresh minesweeper board
        if (e.getSource() == play) {
            this.setVisible(false);
            parent.gamePanel.setVisible(true);
            parent.sweeperBoard = new SweeperBoard(parent, parent.gamePanel, parent.difficulty);
            parent.gamePanel.add(parent.sweeperBoard);
        }

        // Change the game's difficulty based on which difficulty button is clicked
        if (e.getSource() == easy) {
            this.UpdateDifficulty(1);
        } else if (e.getSource() == medium) {
            this.UpdateDifficulty(2);
        } else if (e.getSource() == hard) {
            this.UpdateDifficulty(3);
        } else if (e.getSource() == catastrophic) {
            this.UpdateDifficulty(4);
        }
    }

    // Method to set the game difficulty and update labels based on difficulty
    protected void UpdateDifficulty(int diffInput) {
        parent.difficulty = diffInput;

        if (diffInput == 1) {
            currentDiff.setText("Easy");
        } else if (diffInput == 2) {
            currentDiff.setText("Medium");
        } else if (diffInput == 3) {
            currentDiff.setText("Hard");
        } else if (diffInput == 4) {
            currentDiff.setText("Catastrophic");
        }

        //System.out.println(parent.difficulty);
    }
}
