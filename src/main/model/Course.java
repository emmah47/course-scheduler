package model;

import org.json.JSONObject;
import persistence.Writable;


// represents a UBC course. Each course has a course ID (ex. "CPSC 210") and a description.
public class Course implements Writable {
    private String courseID;
    private String description;

    // EFFECTS: Constructs a new course with a courseID and description
    public Course(String courseID, String description) {
        this.courseID = courseID;
        this.description = description;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // This code is based on the JsonSerializationDemo example provided for phase2
    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("courseID", courseID);
        json.put("description", description);
        return json;
    }

}

