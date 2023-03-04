package persistence;

import model.Weight;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderPreferencesTest {
    String source = "./data/testWeight.json";

    @Test
    void JsonReaderPreferencesConstructorTest() {
        JsonReaderPreferences jrp = new JsonReaderPreferences(source);
        assertEquals(source, this.source);
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


