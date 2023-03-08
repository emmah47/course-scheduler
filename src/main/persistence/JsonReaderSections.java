package persistence;

import model.Section;
import model.SectionType;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// This class is based on the JsonSerializationDemo example provided for phase2
// Represents a reader that reads a schedule from JSON data stored in file
public class JsonReaderSections {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderSections(String source) {
        this.source = source;
    }

    // EFFECTS: reads sections from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Section> readSections() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseSections(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses sections from JSONArray and returns it
    private List<Section> parseSections(JSONArray jsonArray) {
        List<Section> sections = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject sectionJsonObj = jsonArray.getJSONObject(i);
            sections.add(parseSection(sectionJsonObj));
        }
        return sections;
    }

    // EFFECTS: parses a section from JSON object and returns it
    private Section parseSection(JSONObject json) {
        Section section = new Section(json.getString("sectionID"),
                json.getString("courseID"),
                SectionType.valueOf(json.getString("sectionType")),
                json.getString("startTime"),
                json.getString("endTime"),
                parseWeekDays((JSONArray) json.get("weekdays")),
                json.getInt("term"),
                parseAntiRequisiteIDs(json.getJSONArray("antiRequisiteIDs")));
        return section;
    }

    // EFFECTS: parses weekdays from a JSONArray and returns it
    private List<String> parseWeekDays(JSONArray json) {
        List<String> weekdays = new ArrayList<>();
        for (Object item : json) {
            String weekday = item.toString();
            weekdays.add(weekday);
        }
        return weekdays;
    }

    // EFFECTS: parses antirequisiteIDs from a JSONArray and returns it
    private List<List<String>> parseAntiRequisiteIDs(JSONArray json) {
        List<List<String>> antirequisiteIDs = new ArrayList<>();
        for (Object item : json) {
            List<String> weekday = parseAntiRequisiteList((JSONArray) item);
            antirequisiteIDs.add(weekday);
        }
        return antirequisiteIDs;
    }

    // EFFECTS: parses antirequisites from a JSONArray and returns it
    private List<String> parseAntiRequisiteList(JSONArray json) {
        List<String> antirequisites = new ArrayList<>();
        for (Object item : json) {
            String antirequisite = item.toString();
            antirequisites.add(antirequisite);
        }
        return antirequisites;
    }
}