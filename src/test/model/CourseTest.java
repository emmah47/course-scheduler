package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    public void testCourseConstructor() {
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

}