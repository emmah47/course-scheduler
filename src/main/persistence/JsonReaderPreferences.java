package persistence;

import model.Weight;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class is based on the JsonSerializationDemo example provided for phase2
// Represents a reader that reads weights from JSON data stored in file
public class JsonReaderPreferences {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderPreferences(String source) {
        this.source = source;
    }

    // EFFECTS: reads weights from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Weight read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeight(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses weights from JSON object and returns it
    private Weight parseWeight(JSONObject jsonObject) {
        int compactWeight = jsonObject.getInt("compactWeight");
        int balanceWeight = jsonObject.getInt("balanceWeight");
        int preferredStartTime = jsonObject.getInt("preferredStartTime");
        int preferredEndTime = jsonObject.getInt("preferredEndTime");
        Weight weight = new Weight(compactWeight, balanceWeight, preferredStartTime, preferredEndTime);
        return weight;
    }

}