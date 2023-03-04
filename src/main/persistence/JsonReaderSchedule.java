package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Schedule;
import model.Weight;
import model.util.CourseRealData;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderSchedule {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderSchedule(String source) {
        this.source = source;
    }

    // EFFECTS: reads Schedule from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Schedule> read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseSchedules(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the schedules and returns them as a list
    private List<Schedule> parseSchedules(JSONArray jsonArray) {
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject scheduleJsonObj = jsonArray.getJSONObject(i);
            schedules.add(parseSchedule(scheduleJsonObj));
        }
        return schedules;
    }


    // EFFECTS: parses Schedule from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int term = jsonObject.getInt("term");
        Schedule s = new Schedule(name, term,
                new Weight(1, 1, 1, 1), new CourseRealData());
        addCourses(s, jsonObject);
        addSections(s, jsonObject);
        addWeight(s, jsonObject);
        s.calculateScore();
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses courses from file and adds them to the schedule
    private void addCourses(Schedule s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object item : jsonArray) {
            String courseID = item.toString();
            s.addCoursesByID(courseID);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses sectionIDs from file and adds them to the schedule
    private void addSections(Schedule s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sectionIDs");
        for (Object item : jsonArray) {
            String courseID = item.toString();
            s.addSectionID(courseID);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses weight from file and adds them to the schedule
    private void addWeight(Schedule s, JSONObject jsonObject) {
        JSONObject weight = jsonObject.getJSONObject("weight");
        s.setWeight(new Weight((Integer) weight.get("compactWeight"), (Integer) weight.get("balanceWeight"),
                (Integer) weight.get("preferredStartTime"),
                (Integer) weight.get("preferredEndTime")));
    }

}