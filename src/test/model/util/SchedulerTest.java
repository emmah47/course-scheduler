package model.util;

import model.Schedule;
import model.Weight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A testing class for Scheduler
class SchedulerTest {

    // EFFECTS: Test for an empty schedule with no courses to be scheduled
    @Test
    void scheduleAndCalculateScoreEmptyIDsTest() {
        Weight weight = new Weight(1, 1, "9:00", "15:00");
        Schedule schedule = new Schedule(
                "test schedule",
                Arrays.asList(),
                new ArrayList<>(),
                2,
                weight,
                new CourseTestData());
        List<Schedule> result = Scheduler.scheduleAndCalculateScore(schedule);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getSectionIDs().isEmpty());
        assertEquals(200, result.get(0).getScore());
    }

    // EFFECTS: Test for scheduling 2 courses
    @Test
    void scheduleAndCalculateScoreTest() {
        Weight weight = new Weight(1, 1, "9:00", "15:00");
        Schedule schedule = new Schedule(
                "test schedule",
                Arrays.asList("CPSC 110", "CPSC 121"),
                new ArrayList<>(),
                1,
                weight,
                new CourseTestData());
        List<Schedule> result = Scheduler.scheduleAndCalculateScore(schedule);
        assertEquals(16, result.size());
        assertTrue(result.get(0).getSortedSections().get("Mon").size() > 0);
    }

}