package model;


public class Course {
    private String courseID;
    private String description;

    // Constructor for Course
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

