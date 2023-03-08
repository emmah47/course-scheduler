package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A testing class for Course
class CourseTest {

    // EFFECTS: Tests the constructor of a course
    @Test
    public void CourseConstructorTest() {
        Course testCourse = new Course("test course", "this is a test course");
        assertEquals("test course", testCourse.getCourseID());
        assertEquals("this is a test course", testCourse.getDescription());
        String newId = "new ID";
        testCourse.setCourseID(newId);
        String newDescription = "new description";
        testCourse.setDescription(newDescription);
        assertEquals(newId, testCourse.getCourseID());
        assertEquals(newDescription, testCourse.getDescription());
    }

    @Test
    void toJsonObject() {
        Course testCourse = new Course("test course", "this is a test course");
        JSONObject json = testCourse.toJsonObject();
        assertEquals(json.get("courseID"), "test course");
        assertEquals(json.get("description"), "this is a test course");
    }
}