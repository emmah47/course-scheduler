package model;

import model.util.CourseData;
import model.util.CourseTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {
    CourseTestData data = new CourseTestData();

    @Test
    void testHasConflictWithNotSameDay() {
        Section s1 = data.getSection("CPSC 110 101");
        Section s2 = data.getSection("CPSC 121 101");
        assertFalse(s1.hasConflictWith(s2));
    }

    @Test
    void testHasConflictWithFalse() {
        Section s1 = data.getSection("CPSC 121 L1B");
        Section s2 = data.getSection("CPSC 121 L1C");
        assertFalse(s1.hasConflictWith(s2));
        assertFalse(s2.hasConflictWith(s1));
    }

    @Test
    void testHasConflictWithTrue() {
        Section s1 = data.getSection("CPSC 121 L1B");
        Section s2 = data.getSection("Test Section 1");
        Section s3 = data.getSection("Test Section 3");
        assertTrue(s1.hasConflictWith(s1));
        assertTrue(s2.hasConflictWith(s3));
    }
}