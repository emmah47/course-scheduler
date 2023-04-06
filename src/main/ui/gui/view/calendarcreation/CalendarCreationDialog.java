package ui.gui.view.calendarcreation;

import ui.SchedulerApp;

import javax.swing.*;

// This class represents a calendar creation dialogue, which is a window that pops up for creating new calendars
public class CalendarCreationDialog extends JDialog {
    private SchedulerApp app;
    private JPanel rootPanel;

    // EFFECTS: constructs a new CalendarCreationDialogue
    public CalendarCreationDialog(JFrame frame, String title, SchedulerApp app) {
        super(frame, true);
        this.app = app;
        this.setTitle(title);
        rootPanel = new CalendarCreationPanel(this.app, this);
        this.add(rootPanel);


    }

}
