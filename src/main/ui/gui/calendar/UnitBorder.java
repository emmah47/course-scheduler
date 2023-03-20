package ui.gui.calendar;

import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

// A class for making a custom border that is dashed on only one side
public class UnitBorder extends MatteBorder {

    public static final int DASH_LENGTH = 3;

    // EFFECTS: constructs a UnitBorder
    public UnitBorder(int top, int left, int bottom, int right, Color matteColor) {
        super(top, left, bottom, right, matteColor);
    }

    // EFFECTS: makes the border's top side dashed
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets insets = getBorderInsets(c);
        Color oldColor = g.getColor();
        g.translate(x, y);

        BufferedImage bufferedImage = new BufferedImage(2 * DASH_LENGTH, insets.top, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d2 = bufferedImage.createGraphics();
        g2d2.setColor(color);
        g2d2.fillRect(0, 0, DASH_LENGTH, insets.top);
        g2d2.setColor(Color.WHITE);
        g2d2.fillRect(DASH_LENGTH, 0, DASH_LENGTH, insets.top);
        Rectangle r = new Rectangle(0, 0, 2 * DASH_LENGTH, insets.top);

        Graphics2D g2d = (Graphics2D) g;
        Paint oldPaint = g2d.getPaint();
        g2d.setPaint(new TexturePaint(bufferedImage, r));



        Rectangle topBorderRect = new Rectangle(0, 0, width - insets.right, insets.top);
        g2d.fill(topBorderRect);
        g2d.setPaint(oldPaint);
        g.setColor(color);
//        g.fillRect(0, 0, width - insets.right, insets.top);
        g.fillRect(0, insets.top, insets.left, height - insets.top);
        g.fillRect(insets.left, height - insets.bottom, width - insets.left, insets.bottom);
        g.fillRect(width - insets.right, 0, insets.right, height - insets.bottom);


        g.translate(-x, -y);
        g.setColor(oldColor);
    }
}
