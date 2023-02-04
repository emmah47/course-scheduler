package model.util;

import model.Schedule;
import model.Weight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {

    @Test
    void scheduleCoursesTest() {
        Schedule testSchedule = new Schedule("Test Schedule", Arrays.asList("CPSC 110", "CPSC 121"),
                new ArrayList<>(), new Weight(1, 1, 1, 1),
                new CourseTestData());
        assertEquals(18, Scheduler.scheduleCourses(testSchedule).size());
    }


}