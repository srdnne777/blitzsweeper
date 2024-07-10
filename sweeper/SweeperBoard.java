package sweeper;

import app.AppFrame;
import app.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class SweeperBoard extends JPanel {

    int rows, columns, difficulty, tileSize, fontIconSize, mineCount, tilesClicked, flagsPlaced;
    AppFrame parent;
    GamePanel gamePanel;
    MineTile[][] board;
    ArrayList<MineTile> mineList;
    Random random;
    boolean gameOver = false;

    public SweeperBoard(AppFrame parent, GamePanel panel, int diff) {
        this.parent = parent;
        this.gamePanel = panel;
        this.difficulty = diff;
        gameOver = false;
        random = new Random();
        tilesClicked = 0;
        flagsPlaced = 0;

        SetTiles(difficulty);
        board = new MineTile[rows][columns];

        this.setSize(Size(difficulty), Size(difficulty));
        this.setLayout(new GridLayout(rows, columns));
        this.setVisible(true);
        this.setBackground(new Color(100,100,100));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;

                tile.setFocusPainted(false);
                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setForeground(Color.BLACK);
                tile.setOpaque(true);
                Font tileFont = tile.getFont();
                tile.setFont(new Font(tileFont.getName(), tileFont.getStyle(), fontIconSize));
                tile.setBorder(BorderFactory.createLineBorder(new Color(80,80,80), 2));
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver) {
                            return;
                        }

                        MineTile tile = (MineTile) e.getSource();

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (tile.getText() == "") {
                                if (mineList.contains(tile)) {
                                    RevealMines();
                                }
                                else {
                                    CheckMine(tile.r, tile.c);
                                }
                            }
                        }
                        //right click
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (tile.getText() == "" && tile.isEnabled()) {
                                tile.setText("🚩");
                                flagsPlaced += 1;
                                gamePanel.flagsPlaced.setText("Flags placed: " + flagsPlaced);
                            }
                            else if (tile.getText() == "🚩") {
                                tile.setText("");
                                flagsPlaced -= 1;
                                gamePanel.flagsPlaced.setText("Flags placed: " + flagsPlaced);
                            }
                        }
                    }
                });

                this.add(tile);
            }
        }

        SetMines();
    }

    // Method to randomize mine placement on board via ArrayList
    protected void SetMines() {
        mineList = new ArrayList<>();

        int mineLeft = mineCount;
        while (mineLeft > 0) {
            int r = random.nextInt(rows);
            int c = random.nextInt(columns);

            MineTile tile = board[r][c];
            if (!mineList.contains(tile)) {
                mineList.add(tile);
                mineLeft -= 1;
            }
        }
    }

    // Method to reveal a mine when clicked
    protected void RevealMines() {
        for (int i = 0; i < mineList.size(); i++) {
            MineTile tile = mineList.get(i);
            if (tile.getText() == "🚩") {
                tile.setText("✔");
            } else {
                tile.setText("💣");
            }
        }

        gameOver = true;
        gamePanel.gameFinishLabel.setText("Game Over");
        gamePanel.restartGame.setVisible(true);
    }

    // Method to determine if mines are around a clicked tile
    protected void CheckMine(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            return;
        }

        MineTile tile = board[r][c];

        // Don't do anything if tile is already uncovered
        if (!tile.isEnabled()) {
            return;
        }
        tile.setEnabled(false);
        tilesClicked += 1;

        int minesFound = 0;

        // Count mines around a tile
        //top 3
        minesFound += CountMine(r-1, c-1);  //top left
        minesFound += CountMine(r-1, c);    //top
        minesFound += CountMine(r-1, c+1);  //top right

        //left and right
        minesFound += CountMine(r, c-1);    //left
        minesFound += CountMine(r, c+1);    //right

        //bottom 3
        minesFound += CountMine(r+1, c-1);  //bottom left
        minesFound += CountMine(r+1, c);    //bottom
        minesFound += CountMine(r+1, c+1);  //bottom right

        // Set tile to number of mines around
        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        }
        // Clear away the board until a tile that is around a mine is found (recursive)
        else {
            if (tile.getText() == "🚩") {
                flagsPlaced -= 1;
            }
            tile.setText("");

            //top 3
            CheckMine(r-1, c-1);    //top left
            CheckMine(r-1, c);      //top
            CheckMine(r-1, c+1);    //top right

            //left and right
            CheckMine(r, c-1);      //left
            CheckMine(r, c+1);      //right

            //bottom 3
            CheckMine(r+1, c-1);    //bottom left
            CheckMine(r+1, c);      //bottom
            CheckMine(r+1, c+1);    //bottom right
        }

        // If all the tiles are clicked and the mines are either flagged or left alone
        if (tilesClicked == rows * columns - mineList.size()) {
            gameOver = true;
            gamePanel.gameFinishLabel.setText("Game Won");
            gamePanel.restartGame.setVisible(true);
        }
    }

    // Method to count mines around tiles
    protected int CountMine(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            return 0;
        }
        if (mineList.contains(board[r][c])) {
            return 1;
        }
        return 0;
    }

    // Method to set size and position of board and tiles
    protected int Size(int difficulty) {
        if (difficulty == 1) {
            tileSize = 40;
            fontIconSize = 30;
            this.setBounds(130,100, 600, 600);
        } else if (difficulty == 2) {
            tileSize = 36;
            fontIconSize = 24;
            this.setBounds(70,55,600,600);
        } else if (difficulty == 3) {
            tileSize = 26;
            fontIconSize = 18;
            this.setBounds(55,45,600,600);
        } else if (difficulty == 4) {
            tileSize = 18;
            fontIconSize = 12;
            this.setBounds(55,45,600,600);
        }

        return rows * tileSize;
    }

    // Method to set rows and columns for board and position post-game label
    protected void SetTiles(int difficulty) {
        if (difficulty == 1) {
            rows = columns = 8;
            mineCount = 10;
            Font gflFont = gamePanel.gameFinishLabel.getFont();
            gamePanel.gameFinishLabel.setFont(new Font(gflFont.getName(), gflFont.getStyle(), 40));
            gamePanel.gameFinishLabel.setBounds(180, -80, 300, 300);
        } else if (difficulty == 2) {
            rows = columns = 12;
            mineCount = 24;
            Font gflFont = gamePanel.gameFinishLabel.getFont();
            gamePanel.gameFinishLabel.setFont(new Font(gflFont.getName(), gflFont.getStyle(), 26));
            gamePanel.gameFinishLabel.setBounds(212, -120, 300, 300);
        } else if (difficulty == 3) {
            rows = columns = 18;
            mineCount = 36;
            Font gflFont = gamePanel.gameFinishLabel.getFont();
            gamePanel.gameFinishLabel.setFont(new Font(gflFont.getName(), gflFont.getStyle(), 26));
            gamePanel.gameFinishLabel.setBounds(212, -122, 300, 300);
        } else if (difficulty == 4) {
            rows = columns = 26;
            mineCount = 64;
            Font gflFont = gamePanel.gameFinishLabel.getFont();
            gamePanel.gameFinishLabel.setFont(new Font(gflFont.getName(), gflFont.getStyle(), 26));
            gamePanel.gameFinishLabel.setBounds(212, -122, 300, 300);
        }

        gamePanel.mineCount.setText("Mine count: " + mineCount);
    }
}
