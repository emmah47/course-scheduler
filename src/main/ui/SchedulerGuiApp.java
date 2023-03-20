package ui;

import ui.gui.calendar.CalendarPanel;

import javax.swing.*;

// A class that represents the course scheduler gui app
public class SchedulerGuiApp extends SchedulerApp {

    // EFFECTS: runs the course scheduler gui app
    @Override
    public void run() {
        JFrame frame = new JFrame("UBC Course Scheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        CalendarPanel calendarPanel = new CalendarPanel(super.savedSchedules.get(0));
        frame.add(calendarPanel);
    }
}
