package model;

import model.util.CourseTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A testing class for Schedule
class ScheduleTest {
    private Schedule defaultSchedule;
    private Section section1;
    private Section section2;

    // EFFECTS: Makes a new weight, schedule, and two different sections before each test
    @BeforeEach
    public void setup() {
        Weight weight1 = new Weight(1,1, "8:00",
                "16:00");
        List<String> courseIDs1 = new ArrayList<>(Arrays.asList("CPSC 110", "CPSC 121"));
        List<String> sectionIDs1 =  new ArrayList<>();
        defaultSchedule = new Schedule("s1",1, weight1, new CourseTestData());
        defaultSchedule.addCoursesByIDs(courseIDs1);
        for (String sectionID : sectionIDs1) {
            defaultSchedule.addSectionID(sectionID);
        }
        section1 = new Section("Test Section 1", "Test Course", SectionType.LECTURE,
                "1:00", "3:00", Arrays.asList("Mon, Wed, Fri"), 1, new ArrayList<>());
        section2 = new Section("Test Section 2", "Test Course", SectionType.LECTURE,
                "1:00", "3:00", Arrays.asList("Mon, Wed, Fri"), 1, new ArrayList<>());
    }

    @Test
    void CloneSameValueTest() {
        defaultSchedule.setScore(4);
        Schedule s1Clone = defaultSchedule.makeCopy();
        assertEquals(defaultSchedule.getName(), s1Clone.getName());
        assertEquals(defaultSchedule.getCourseIDs(), s1Clone.getCourseIDs());
        assertEquals(defaultSchedule.getSectionIDs(), s1Clone.getSectionIDs());
        assertEquals(defaultSchedule.getWeight().getBalanceWeight(), s1Clone.getWeight().getBalanceWeight());
        assertEquals(defaultSchedule.getWeight().getCompactWeight(), s1Clone.getWeight().getCompactWeight());
        assertEquals(defaultSchedule.getWeight().getPreferredEndTime(), s1Clone.getWeight().getPreferredEndTime());
        assertEquals(defaultSchedule.getWeight().getPreferredStartTime(), s1Clone.getWeight().getPreferredStartTime());
        assertEquals(defaultSchedule.getScore(), s1Clone.getScore());
    }

    @Test
    void SettersTest() {
        Schedule schedule = new Schedule(
                "testSchedule",
                1,
                new Weight(1, 1,"9:00","15:00" ),
                new CourseTestData());
        String newName = "new name";
        List<String> newCourseIdList = Arrays.asList("courseId1", "courseId2");
        int newTerm = 2;
        schedule.setName(newName);
        schedule.addCoursesByIDs(newCourseIdList);
        schedule.setTerm(newTerm);
        assertEquals(newName, schedule.getName());
        assertEquals(newCourseIdList, schedule.getCourseIDs());
        assertEquals(newTerm, schedule.getTerm());
        assertEquals(0, schedule.getAllAntirequisits().size());

    }

    @Test
    void CloneDifferentObjectTest() {
        defaultSchedule.setScore(10);
        Schedule s1Clone = defaultSchedule.makeCopy();
        assertNotEquals(defaultSchedule, s1Clone);
        s1Clone.addCoursesByIDs(Arrays.asList("courseId1"));
        s1Clone.addSectionID("test");
        Weight weight2 = new Weight(1,2, "8:00",
                "16:00");
        defaultSchedule.setWeight(weight2);

        assertNotEquals(defaultSchedule.getCourseIDs(), s1Clone.getCourseIDs());
        assertNotEquals(defaultSchedule.getSectionIDs(), s1Clone.getSectionIDs());
        assertNotEquals(defaultSchedule.getWeight(), s1Clone.getWeight());

    }

    @Test
    void tryAddSectionTest() {
        Section testS1 = defaultSchedule.getCourseData().getSection("Test Section 1");
        Section testS2 = defaultSchedule.getCourseData().getSection("Test Section 2");
        Section testS3 = defaultSchedule.getCourseData().getSection("Test Section 3");
        ArrayList<String> startingSectionIDs = new ArrayList<>();
        startingSectionIDs.add("Test Section 1");
        for (String sectionID : startingSectionIDs) {
            defaultSchedule.addSectionID(sectionID);
        }
        assertTrue(defaultSchedule.tryAddSection(testS2));
        assertEquals("Test Section 2", defaultSchedule.getSectionIDs().get(1));
        defaultSchedule.getSectionIDs().remove(1);
        assertFalse(defaultSchedule.tryAddSection(testS3));
        assertEquals(1, defaultSchedule.getSectionIDs().size());
    }


    @Test
    void calculateScoreNoOvertimeTest() {
        Weight weight1 = new Weight(1,2, "8:00", "16:00");
        Schedule testSchedule = new Schedule("testSchedule", 2, weight1, new CourseTestData());
        testSchedule.addSectionID("weightTest1");
        testSchedule.addSectionID("weightTest2");
        testSchedule.calculateScore();
        assertEquals(300, testSchedule.getScore());
    }

    @Test
    void calculateScoreWithOvertimeStartTest() {
        Weight weight1 = new Weight(1,1, "13:30", "16:00");
        Schedule testSchedule = new Schedule("testSchedule", 2, weight1, new CourseTestData());
        testSchedule.addSectionID("weightTest1");
        testSchedule.addSectionID("weightTest2");
        testSchedule.calculateScore();
        assertEquals(33.333335876464844, testSchedule.getScore());
    }

    @Test
    void calculateScoreWithOvertimeEndTest() {
        Weight weight1 = new Weight(1,1, "8:00", "14:30");
        Schedule testSchedule = new Schedule("testSchedule", 2, weight1, new CourseTestData());
        testSchedule.addSectionID("weightTest1");
        testSchedule.addSectionID("weightTest2");
        testSchedule.calculateScore();
        assertEquals(33.333335876464844, testSchedule.getScore());
    }

    @Test
    void calculateScoreZeroTest() {
        Weight weight1 = new Weight(0,0, "8:00", "16:00");
        Schedule testSchedule = new Schedule("testSchedule", 2, weight1, new CourseTestData());
        testSchedule.addSectionID("weightTest1");
        testSchedule.addSectionID("weightTest2");
        testSchedule.calculateScore();
        assertEquals(0, testSchedule.getScore());
    }

    @Test
    void getCourseTest() {
        assertEquals(2, defaultSchedule.getCourseIDs().size());
    }

    @Test
    void removeCourseById() {
        defaultSchedule.addSectionID("CPSC 110 101");
        defaultSchedule.addSectionID("CPSC 121 101");
        defaultSchedule.removeCourseById("CPSC 110");
        assertEquals(1, defaultSchedule.getCourseIDs().size());
        assertEquals(1, defaultSchedule.getSectionIDs().size());

        defaultSchedule.removeCourseById("LDKSJFKLDS");
        assertEquals(1, defaultSchedule.getCourseIDs().size());
        assertEquals(1, defaultSchedule.getSectionIDs().size());
    }
}