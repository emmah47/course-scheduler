package model;

// represents a UBC course. Each course has a course ID (ex. "CPSC 210") and a description.
public class Course {
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

}

