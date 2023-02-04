package ui;


import model.Schedule;
import model.Weight;
import model.util.CourseTestData;
import model.util.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Schedule mySchedule = new Schedule(
                "my schedule",
                Arrays.asList("CPSC 110","CPSC 121"),
                new ArrayList<>(),
                new Weight(1,2,3,4),
                new CourseTestData());

        List<Schedule> result = Scheduler.scheduleCourses(mySchedule);
        for (Schedule s : result) {
            System.out.println(s.toString());
        }

    }
}
