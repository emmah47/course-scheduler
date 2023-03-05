package persistence;

import model.Schedule;
import model.Weight;
import model.util.CourseTestData;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import ui.CourseSchedulerApp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

// This code is based on the JsonSerializationDemo example provided for phase2
// Tests for JsonWriter
class JsonWriterTest {
    String weightDestination = "./data/testWeightWrite.json";
    String scheduleDestination = "./data/testScheduleWrite.json";

    @Test
    void constructorTest() {
        JsonWriter jwp = new JsonWriter(weightDestination);
    }

    @Test
    void openNonExistentFileTest() {
        JsonWriter jwp = new JsonWriter("./data/\0InvalidFile.json");
        try {
            jwp.open();
            fail("Expected exception to be thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void weightWriteObjectTest() {
        try {
            JsonWriter jwp = new JsonWriter(weightDestination);
            Weight weight = new Weight(1,2,3,4);
            jwp.open();
            jwp.writeJsonObject(weight.toJsonObject());
            jwp.close();

            JsonReaderPreferences jrp = new JsonReaderPreferences(weightDestination);
            Weight savedWeight = jrp.read();
            assertEquals(1, savedWeight.getCompactWeight());
            assertEquals(2, savedWeight.getBalanceWeight());
            assertEquals(3, savedWeight.getPreferredStartTime());
            assertEquals(4, savedWeight.getPreferredEndTime());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void scheduleWriteArrayTest() {
        try {
            JsonWriter jwp = new JsonWriter(scheduleDestination);
            Schedule s = new Schedule("test", 2, new Weight(1,1,1,1),
                    new CourseTestData());
            s.addCoursesByIDs(Arrays.asList("CPSC 110", "CPSC 121"));
            s.addSectionID("CPSC 110 101");
            s.addSectionID("CPSC 110 L1A");
            s.addSectionID("CPSC 121 L1B");
            JSONArray json = new JSONArray();
            jwp.open();
            jwp.writeJsonArray(json.put(s.toJsonObject()));
            jwp.close();

            JsonReaderSchedule jrp = new JsonReaderSchedule(scheduleDestination);
            List<Schedule> savedSchedules = jrp.read();
            assertEquals(1, savedSchedules.size());
            assertEquals(2, savedSchedules.get(0).getCourseIDs().size());
            assertEquals(3, savedSchedules.get(0).getSectionIDs().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}