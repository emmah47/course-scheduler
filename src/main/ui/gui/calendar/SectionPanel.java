package ui.gui.calendar;

import model.Section;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


// A class representing a section panel (the green block on the calendar)
public class SectionPanel extends JPanel implements MouseListener {
    private Section section;
    private JLabel label;
    private JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem menuItemDelete = new JMenuItem("Delete");
    private boolean isReadOnly;

    // EFFECTS: constructs a panel that displays the name, building, and term of the section
    public SectionPanel(Section section, boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        this.setLayout(new GridBagLayout());
        this.section = section;
        this.setBackground(new Color(0xdff0d8));
        setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, new Color(0x808080)));
        label = new JLabel();
        label.setText(String.format("<html><a href=\"link\" target=\"_blank\">" +
                        "<b><span style=\"font-size:10pt\"> %s </span></b></a>" +
                        "<center style=\"font-size:9pt\">%s - %s</center>" +
                        "</html> ",
                section.getSectionID(), section.getStartTime(), section.getEndTime()));
        label.setForeground(new Color(0x0000));
        addMouseListener(this);
        this.add(label);
        menuItemDelete.setText(String.format("Delete Section %s", section.getSectionID()));
        menuItemDelete.setName(section.getSectionID());
    }

    public Section getSection() {
        return section;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!isReadOnly) {
            popupMenu.add(menuItemDelete);
            popupMenu.show(this, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!isReadOnly) {
            setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, new Color(0x1E90FF)));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!isReadOnly) {
            setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, new Color(0x808080)));
        }
    }

    public void addActionListener(ActionListener listener) {
        menuItemDelete.addActionListener(listener);
    }

}

