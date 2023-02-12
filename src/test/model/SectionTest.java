package model;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {

    @Test
    void testHasConflictWith() {
        Section s1 = new Section("section1",
                "course1",
                SectionType.LABORATORY,
                "01:00",
                "02:00",
                Arrays.asList("Mon", "Tue"),
                1,
                null);
        Section s2 = new Section("section2",
                "course2",
                SectionType.LABORATORY,
                "01:00",
                "02:00",
                Arrays.asList("Mon", "Tue"),
                1,
                null);
        assertTrue(s1.hasConflictWith(s2));

        s2.setWeekDays(Arrays.asList("Wed"));
        assertFalse(s1.hasConflictWith(s2));

        s2.setWeekDays(Arrays.asList("Mon","Wed"));
        assertTrue(s1.hasConflictWith(s2));

        s2.setStartTime("02:00");
        s2.setEndTime("03:00");
        assertFalse(s1.hasConflictWith(s2));

        s2.setStartTime("00:00");
        s2.setEndTime("03:00");
        assertTrue(s1.hasConflictWith(s2));

        s2.setStartTime("01:01");
        s2.setEndTime("01:59");
        assertTrue(s1.hasConflictWith(s2));

        s2.setStartTime("00:01");
        s2.setEndTime("00:59");
        assertFalse(s1.hasConflictWith(s2));

        s2.setStartTime("01:30");
        s2.setEndTime("02:59");
        assertTrue(s1.hasConflictWith(s2));

    }

    @Test
    void testGetterSetter(){
        Section s1 = new Section(null,
                null,
                SectionType.LABORATORY,
                "00:00",
                "00:00",
                null,
                1,
                null);
        String sectionId = "sectionId";
        List<List<String>> antiRequisiteIDs = new ArrayList<>();
        List<String> weekDays = new ArrayList<>();
        int newTerm = 2;
        SectionType newSectionType = SectionType.LECTURE;
        String startTime = "00:00";
        String endTime = "00:05";
        String courseId = "courseId";
        s1.setWeekDays(weekDays);
        s1.setSectionID(sectionId);
        s1.setAntiRequisiteIDs(antiRequisiteIDs);
        s1.setTerm(newTerm);
        s1.setSectionType(newSectionType);

        s1.setCourseID(courseId);
        s1.setStartTime(startTime);
        s1.setEndTime(endTime);
        assertEquals(startTime, s1.getStartTime());
        assertEquals(endTime, s1.getEndTime());
        assertEquals(courseId, s1.getCourseID());
        assertEquals(sectionId, s1.getSectionID());
        assertEquals(0, s1.getStartTimeInMinutes());
        assertEquals(5, s1.getEndTimeInMinutes());
        assertEquals(weekDays, s1.getWeekDays());
        assertEquals(antiRequisiteIDs, s1.getAntiRequisiteIDs());
        assertEquals(newSectionType, s1.getSectionType());
        assertEquals(newTerm, s1.getTerm());

    }
}