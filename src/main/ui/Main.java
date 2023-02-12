package ui;


import model.Schedule;
import model.Weight;
import model.util.CourseRealData;


import java.util.*;


public class Main {
    public static void main(String[] args) {
        Schedule schedule = new Schedule("name", new ArrayList<>(), new ArrayList<>(), 2,
                new Weight(1, 1, 1, 1),
                new CourseRealData());
        CourseSchedulerApp schedulerApp = new CourseSchedulerApp();
        schedulerApp.runSchedulerApp(schedule);
    }
}
