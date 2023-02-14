package model.util;

import model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A testing class for CourseTestData
class CourseTestDataTest {
    CourseTestData courseTestData;

    // EFFECTS: Makes new CourseTestData before every test
    @BeforeEach
    void setup() {
        courseTestData = new CourseTestData();
    }

    // EFFECTS: Test for getting the root sections from a given course ID
    @Test
    void getRootSectionsTest() {
        List<Section> cpsc110Sections = courseTestData.getRootSections("CPSC 110", 1);
        assertEquals(2, cpsc110Sections.size());
        assertEquals("CPSC 110", cpsc110Sections.get(0).getCourseID());
        assertEquals("CPSC 110", cpsc110Sections.get(1).getCourseID());
    }

    // EFFECTS: Test for getting the section from a given section ID
    @Test
    void getSectionTest() {
        assertEquals("CPSC 110 101",
                courseTestData.getSection("CPSC 110 101").getSectionID());
    }

    // EFFECTS: Test for getting a list of sections given a list of section ids
    @Test
    void getSectionsTest() {
        assertEquals(new ArrayList<>(), courseTestData.getSections(new ArrayList<>()));

        List<Section> expectedSections = Arrays.asList(courseTestData.getSection("CPSC 110 101"),
                courseTestData.getSection("CPSC 110 102"));
        assertEquals(expectedSections, courseTestData.getSections(Arrays.asList("CPSC 110 101", "CPSC 110 102")));
    }

    // EFFECTS: Test for getting all the courses
    @Test
    void getAllCourseTest() {
        assertTrue(courseTestData.getAllCourse().size()>0);
    }

    // EFFECTS: Test for getting all the course IDs
    @Test
    void getAllCourseIDsTest() {
        assertTrue(courseTestData.getAllCourseIDs().size()>0);
    }
}