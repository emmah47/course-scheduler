package persistence;

import model.Weight;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// This code is based on the JsonSerializationDemo example provided for phase2
// A testing class for JsonReaderPreferences
class JsonReaderPreferencesTest {
    String source = "./data/testWeightRead.json";

    @Test
    void JsonReaderPreferencesConstructorTest() {
        JsonReaderPreferences jrp = new JsonReaderPreferences(source);
        assertEquals(source, this.source);
    }

    @Test
    void readNonExistentFileTest() {
        JsonReaderPreferences jrp = new JsonReaderPreferences("./data/illegalFile.json");
        try {
            jrp.read();
            fail("Expected exception to be thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readTest() {
        JsonReaderPreferences jrp = new JsonReaderPreferences(source);
        try {
            Weight weight = jrp.read();
            assertEquals(3, weight.getCompactWeight());
            assertEquals(2, weight.getBalanceWeight());
            assertEquals(600, weight.getPreferredStartTime());
            assertEquals(960, weight.getPreferredEndTime());
        } catch (IOException e) {
            fail();
        }
    }
}


