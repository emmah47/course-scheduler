package model.util;

import model.Schedule;
import model.Weight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {


    @Test
    void scheduleAndCalculateScore() {
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