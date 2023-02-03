package model.util;

import model.Section;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTestDataTest {

    @Test
    void getRootSectionsTest() {
        CourseTestData courseTestData = new CourseTestData();
        List<Section> cpsc110Sections = courseTestData.getRootSections("CPSC 110");
        assertEquals(2, cpsc110Sections.size());
        assertEquals("CPSC 110", cpsc110Sections.get(0).getCourseID());
        assertEquals("CPSC 110", cpsc110Sections.get(1).getCourseID());
    }

    @Test
    void getSectionTest() {
        CourseTestData courseTestData = new CourseTestData();
        assertEquals("CPSC 110 101", courseTestData.getSection("CPSC 110 101").getSectionID());
    }
}