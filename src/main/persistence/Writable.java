package persistence;

import org.json.JSONObject;

// This class is based on the JsonSerializationDemo example provided for phase2
// Classes that can be written to file as data must implement this interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJsonObject();
}
