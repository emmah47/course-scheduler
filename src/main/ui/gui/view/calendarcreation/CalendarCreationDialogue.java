package ui.gui.view.calendarcreation;

import ui.SchedulerApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CalendarCreationDialogue extends JDialog
        implements ActionListener,
        PropertyChangeListener {
    private SchedulerApp app;
    private JPanel rootPanel;

    public CalendarCreationDialogue(JFrame frame, String title, SchedulerApp app) {
        super(frame, true);
        this.app = app;
        this.setTitle(title);
        rootPanel = new CalendarCreationPanel(this.app);
        this.add(rootPanel);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
