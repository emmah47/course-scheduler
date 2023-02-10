package model.util;

import model.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRealDataTest {

    CourseRealData courseRealData;

    @BeforeEach
    void setup() {
        courseRealData = new CourseRealData();
    }

    @Test
    void getRootSectionsTest() {
        List<Section> cpsc110Sections = courseRealData.getRootSections("CPSC 110", 1);
        assertEquals(4, cpsc110Sections.size());
        assertEquals("CPSC 110", cpsc110Sections.get(0).getCourseID());
        assertEquals("CPSC 110", cpsc110Sections.get(1).getCourseID());
    }

    @Test
    void getSectionTest() {
        assertEquals("CPSC 110 101", courseRealData.getSection("CPSC 110 101").getSectionID());
    }


    @Test
    void getSectionsTest() {
        assertEquals(new ArrayList<>(), courseRealData.getSections(new ArrayList<>()));

        List<Section> expectedSections = Arrays.asList(courseRealData.getSection("CPSC 110 101"),
                courseRealData.getSection("CPSC 110 102"));
        assertEquals(expectedSections, courseRealData.getSections(Arrays.asList("CPSC 110 101", "CPSC 110 102")));
    }
}