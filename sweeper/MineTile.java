package sweeper;

import javax.swing.*;
import java.awt.*;

public class MineTile extends JButton {

    // Rows and columns for sweeper
    int r, c;

    public MineTile(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
