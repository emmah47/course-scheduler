package ui.gui.calendar;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

// A background block in the Calendar
public class BackgroundBlock extends JPanel {
    private static final Color BORDER_COLOR = new Color(0xe4e4e4);

    // Effects: constructs a BackgroundBlock that is gray on even weekdays and white on odd weekdays. Also has a solid
    //          top line if the line corresponds to an hour, or a dashed line for half hours.
    public BackgroundBlock(boolean isLastRow, boolean isLastCol, boolean isWeekdayEven, boolean dashed) {
        this.setOpaque(true);
        this.setBackground(isWeekdayEven ? new Color(0xf6f6f6) : new Color(0xffffff));
        if (dashed) {
            this.setBorder(new UnitBorder(1, 1, isLastRow ? 1 : 0, isLastCol ? 1 : 0,
                    BORDER_COLOR));
        } else {
            this.setBorder(new MatteBorder(1, 1, isLastRow ? 1 : 0, isLastCol ? 1 : 0, BORDER_COLOR));
        }
    }
}
