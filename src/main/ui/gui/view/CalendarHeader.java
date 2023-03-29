package ui.gui.view;

import model.Schedule;
import model.util.HelperUtil;

import javax.swing.*;
import java.awt.*;

// The header on top of the displayed saved calendar that contains information about the schedule such as courses,
// weights, etc.
public class CalendarHeader extends JPanel {
    private Schedule schedule;

    // EFFECT: Constructs a new CalendarHeader
    public CalendarHeader(Schedule s) {
        this.schedule = s;
        setBackground(Color.WHITE);


        if (s != null) {

            String htmlTpl = "<html><b>Name: </b> %s <br>"
                    + "<b>Courses:</b> %s &nbsp;&nbsp;"
                    + "<b>Term:</b> %s &nbsp;&nbsp;<br>"
                    + "<b>Compact weight::</b> %s &nbsp;&nbsp;"
                    + "<b>Balance weight:</b> %s &nbsp;&nbsp;"
                    + "<b>Preferred Start Time:</b> %s &nbsp;&nbsp;"
                    + "<b>Preferred End Time:</b> %s &nbsp;&nbsp;"
                    + "</html> ";

            JLabel description = new JLabel(
                    String.format(htmlTpl, schedule.getName(),
                    schedule.getCourseIDs(),
                    schedule.getTerm(),
                    schedule.getWeight().getCompactWeight(),
                    schedule.getWeight().getBalanceWeight(),
                    HelperUtil.minutesToTime(schedule.getWeight().getPreferredStartTime()),
                    HelperUtil.minutesToTime(schedule.getWeight().getPreferredEndTime())));

            this.add(description);
        }
    }


}
