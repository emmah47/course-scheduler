package persistence;

import model.Course;
import model.Section;
import model.SectionType;
import model.util.CourseData;
import model.util.CourseRealData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// This class is based on the JsonSerializationDemo example provided for phase2
// Represents a reader that reads courses from JSON data stored in file
public class JsonReaderCourses {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderCourses(String source) {
        this.source = source;
    }

    // EFFECTS: reads courses from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Course> readCourses() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseCourses(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses courses from JSONArray and returns it
    private List<Course> parseCourses(JSONArray jsonArray) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject courseJsonObj = jsonArray.getJSONObject(i);
            courses.add(parseCourse(courseJsonObj));
        }
        return courses;
    }

    // EFFECTS: parses a course from JSON object and returns it
    private Course parseCourse(JSONObject json) {
        Course course = new Course(json.getString("courseID"),
                json.getString("description"));
        return course;
    }

}

