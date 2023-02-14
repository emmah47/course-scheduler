package model.util;

import model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTestDataTest {
    CourseTestData courseTestData;

    @BeforeEach
    void setup() {
        courseTestData = new CourseTestData();
    }

    @Test
    void getRootSectionsTest() {
        List<Section> cpsc110Sections = courseTestData.getRootSections("CPSC 110", 1);
        assertEquals(2, cpsc110Sections.size());
        assertEquals("CPSC 110", cpsc110Sections.get(0).getCourseID());
        assertEquals("CPSC 110", cpsc110Sections.get(1).getCourseID());
    }


    @Test
    void getSectionTest() {
        assertEquals("CPSC 110 101", courseTestData.getSection("CPSC 110 101").getSectionID());
    }


    @Test
    void getSectionsTest() {
        assertEquals(new ArrayList<>(), courseTestData.getSections(new ArrayList<>()));

        List<Section> expectedSections = Arrays.asList(courseTestData.getSection("CPSC 110 101"),
                courseTestData.getSection("CPSC 110 102"));
        assertEquals(expectedSections, courseTestData.getSections(Arrays.asList("CPSC 110 101", "CPSC 110 102")));
    }

    @Test
    void getAllCourse() {
        assertTrue(courseTestData.getAllCourse().size()>0);
    }

    @Test
    void getAllCourseIDs() {
        assertTrue(courseTestData.getAllCourseIDs().size()>0);
    }
}