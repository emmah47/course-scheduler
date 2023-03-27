package ui.gui.view;

import model.Schedule;
import ui.SchedulerApp;

import javax.swing.*;
import java.awt.*;

public class ViewCalendarHolderPanel extends JPanel {
    private ViewCalendarPanel viewCalendarPanel;
    private SchedulerApp app;

    public ViewCalendarHolderPanel(Schedule s, SchedulerApp app) {
        this.app = app;
        viewCalendarPanel = new ViewCalendarPanel(s, app);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(viewCalendarPanel, c);
    }

    public void removeViewCalendarPanel() {
        this.remove(viewCalendarPanel);
        this.revalidate();
    }

    public void addViewCalendarPanel(Schedule s) {
        this.viewCalendarPanel = new ViewCalendarPanel(s, app);
        this.revalidate();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(viewCalendarPanel, c);
    }
}
