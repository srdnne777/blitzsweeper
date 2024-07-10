package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel implements ActionListener {

    JLabel label = new JLabel();
    JLabel imageLabel = new JLabel();
    JLabel desc = new JLabel();
    JButton back = new JButton();
    AppFrame parent;
    private final ImageIcon image;

    public HelpPanel(AppFrame parent, int x, int y, int width, int height) {
        this.parent = parent;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setVisible(true);
        image = new ImageIcon(getClass().getResource("funny_emoji.png"));

        // Properties of title label
        label.setText("Help");
        Font titleFont = label.getFont();
        label.setFont(new Font(titleFont.getName(), Font.ITALIC | Font.BOLD, 50));
        label.setForeground(Color.BLACK);
        label.setBounds(240, 5, width, 50);

        // Properties of description label
        desc.setText("<html>This is good old minesweeper with the same old rules.<br><br>" +
                "Left click to dig up a mine under a tile, and right click to flag a potential<br>" +
                "mine under a tile.<br><br>" +
                "You can adjust the difficulty in the main menu. The difficulties change<br>" +
                "the tile amount, and amount of mines to flag.<br><br>" +
                "Slightly more difficult due to pure randomization!</html>");
        Font descFont = desc.getFont();
        desc.setFont(new Font(descFont.getName(), descFont.getStyle(), 16));
        desc.setPreferredSize(new Dimension(width, height));
        desc.setBounds(17, -120, desc.getPreferredSize().width, desc.getPreferredSize().height);

        // Properties of back button
        back.setText("<<");
        Font playFont = back.getFont();
        back.setFont(new Font(playFont.getName(), Font.BOLD, 30));
        back.setBounds(10, 8, 70, 30);
        back.addActionListener(this);
        back.setFocusPainted(false);
        back.setBackground(new Color(200,200,200));
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setVerticalAlignment(SwingConstants.CENTER);
        back.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 3));

        // Properties of image label
        imageLabel.setIcon(new ImageIcon(image.getImage().getScaledInstance(500, 240, Image.SCALE_SMOOTH)));
        imageLabel.setBounds(160,100,600,600);

        this.add(label);
        this.add(desc);
        this.add(back);
        this.add(imageLabel);
    }

    // Method from implementing ActionListener to listen for events (mainly from button sources)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.setVisible(false);
            parent.menuPanel.setVisible(true);
        }
    }
}
