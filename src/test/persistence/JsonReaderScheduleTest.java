package persistence;

import model.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderScheduleTest {
    String source = "./data/testSchedule.json";
    JsonReaderSchedule jrs;

    @BeforeEach
    void setup() {
        jrs = new JsonReaderSchedule(source);
    }

    @Test
    void jsonReaderScheduleConstructorTest() {
        assertEquals(source, this.source);
    }

    @Test
    void readTest() {
        try {
            List<Schedule> schedules = jrs.read();
            Schedule s1 = schedules.get(0);
            Schedule s2 = schedules.get(1);
            assertEquals(2, schedules.size());
            assertEquals("demo schedule", s1.getName());
            assertEquals("demo schedule2", s2.getName());
            assertEquals(2, s1.getCourseIDs().size());
            assertEquals(7, s1.getSectionIDs().size());
        } catch (IOException e) {
            fail();
        }
    }
}