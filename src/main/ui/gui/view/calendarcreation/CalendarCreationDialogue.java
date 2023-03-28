package ui.gui.view.calendarcreation;

import ui.SchedulerApp;

import javax.swing.*;


public class CalendarCreationDialogue extends JDialog {
    private SchedulerApp app;
    private JPanel rootPanel;

    public CalendarCreationDialogue(JFrame frame, String title, SchedulerApp app) {
        super(frame, true);
        this.app = app;
        this.setTitle(title);
        rootPanel = new CalendarCreationPanel(this.app, this);
        this.add(rootPanel);


    }

}
