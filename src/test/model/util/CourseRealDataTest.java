package model.util;

import model.Course;
import model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A testing class for CourseRealData
class CourseRealDataTest {

    CourseRealData courseRealData;

    // EFFECTS: Makes new CourseRealData before every test
    @BeforeEach
    void setup() {
        courseRealData = new CourseRealData();
    }

    // EFFECTS: Test for getting the root sections from a given course ID
    @Test
    void getRootSectionsTest() {
        List<Section> cpsc110Sections = courseRealData.getRootSections("CPSC 110", 1);
        assertEquals(4, cpsc110Sections.size());
        assertEquals("CPSC 110", cpsc110Sections.get(0).getCourseID());
        assertEquals("CPSC 110", cpsc110Sections.get(1).getCourseID());
    }

    // EFFECTS: Test for getting the section from a given section ID
    @Test
    void getSectionTest() {
        assertEquals("CPSC 110 101",
                courseRealData.getSection("CPSC 110 101").getSectionID());
    }

    // EFFECTS: Test for getting a list of sections given a list of section ids
    @Test
    void getSectionsTest() {
        assertEquals(new ArrayList<>(), courseRealData.getSections(new ArrayList<>()));

        List<Section> expectedSections = Arrays.asList(courseRealData.getSection("CPSC 110 101"),
                courseRealData.getSection("CPSC 110 102"));
        assertEquals(expectedSections, courseRealData.getSections(Arrays.asList("CPSC 110 101", "CPSC 110 102")));
    }

    @Test
    void getCourseByIDTest() {
        Course course = courseRealData.getCourseByID("CPSC 110");
        assertEquals("CPSC 110", course.getCourseID());
    }

    // EFFECTS: Test for getting all the courses
    @Test
    void getAllCourseTest() {
        assertTrue(courseRealData.getAllCourse().size()>0);
    }

    // EFFECTS: Test for getting all the course IDs
    @Test
    void getAllCourseIDsTest() {
        assertTrue(courseRealData.getAllCourseIDs().size()>0);
    }
}