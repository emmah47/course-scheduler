package ui;

import model.Course;
import model.Schedule;
import model.Weight;
import model.util.CourseRealData;
import model.util.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        List<String> courseIDs = Arrays.asList("CPSC 110", "CPSC 121", "CPSC 210");
        Schedule schedule = new Schedule("name", courseIDs, new ArrayList<>(), 2,
                new Weight(1, 1, "8:00", "16:00"),
                new CourseRealData());
        List<Schedule> results = Scheduler.scheduleAndCalculateScore(schedule);
        CourseSchedulerApp app = new CourseSchedulerApp();
        for (int i = 0; i < 3; i++) {
            System.out.println(app.displaySchedule(results.get(i)));
        }
    }
}
