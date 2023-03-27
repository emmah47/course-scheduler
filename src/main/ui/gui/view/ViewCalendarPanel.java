package ui.gui.view;

import model.Schedule;
import ui.SchedulerApp;
import ui.gui.calendar.CalendarPanel;

import javax.swing.*;
import java.awt.*;

// SOURCE: This code was based on the demo code in the oracle java documentation

public class ViewCalendarPanel extends JPanel {
    private SchedulerApp app;
    private CalendarPanel calendarPanel;
    private CalendarHeader calendarHeader;
    private Schedule schedule;

    ViewCalendarPanel(Schedule schedule, SchedulerApp app) {
        this.app = app;
        this.schedule = schedule;
        this.calendarPanel = new CalendarPanel(schedule);
        this.calendarHeader = new CalendarHeader(schedule);


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Calendar"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        this.add(calendarHeader);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(calendarPanel);
    }

}
