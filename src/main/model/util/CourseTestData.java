package model.util;

import model.Course;
import model.Section;
import model.SectionType;

import java.util.*;

// Test data for unit tests.
public class CourseTestData implements CourseData {
    private Map<String, Section> data = new LinkedHashMap<>(); // a hashmap of Sections, with the value being a
    // section, and the key being the corresponding course ID.
    private Map<String, Course> courses = new LinkedHashMap<>(); // Some course IDs and their description

    // EFFECTS: Creates a new CourseTestData with a populated data field.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public CourseTestData() {
        // COURSES:
        courses.put("CPSC 110", new Course("CPSC 110", "UBC's introductory cs programming course"));
        courses.put("CPSC 210", new Course("CPSC 210", "UBC's other cs programming course"));


        // LECTURES
        data.put("CPSC 110 101",  new Section("CPSC 110 101", "CPSC 110", SectionType.LECTURE,
                "9:00", "10:30", Arrays.asList("Mon", "Wed", "Fri"), 1,
                Arrays.asList(Arrays.asList("CPSC 110 L10", "CPSC 110 L11"))));
        data.put("CPSC 110 102", new Section("CPSC 110 102", "CPSC 110", SectionType.LECTURE,
                "11:00", "12:30", Arrays.asList("Mon", "Wed", "Fri"), 1,
                Arrays.asList(Arrays.asList("CPSC 110 L12", "CPSC 110 L13"))));

        data.put("CPSC 121 101",  new Section("CPSC 121 101", "CPSC 121", SectionType.LECTURE,
                "13:00", "15:00", Arrays.asList("Tue", "Thu"), 1,
                Arrays.asList(Arrays.asList("CPSC 121 L1A", "CPSC 121 L1B"),
                              Arrays.asList("CPSC 121 T1A", "CPSC 121 T1B"))));
        data.put("CPSC 121 102", new Section("CPSC 121 102", "CPSC 121", SectionType.LECTURE,
                "15:00", "17:00", Arrays.asList("Tue", "Fri"), 1,
                Arrays.asList(Arrays.asList("CPSC 121 L1A", "CPSC 121 L1B"),
                Arrays.asList("CPSC 121 T1A", "CPSC 121 T1C"))));


        // LABS
        data.put("CPSC 110 L10", new Section("CPSC 110 L10", "CPSC 110", SectionType.LABORATORY,
                "18:00", "21:00",
                Arrays.asList("Wed"), 1, Arrays.asList()));
        data.put("CPSC 110 L11", new Section("CPSC 110 L11", "CPSC 110", SectionType.LABORATORY,
                "8:00", "11:00",
                Arrays.asList("Thu"), 1, Arrays.asList()));
        data.put("CPSC 110 L12", new Section("CPSC 110 L12", "CPSC 110", SectionType.LABORATORY,
                "1:00", "3:00",
                Arrays.asList("Fri"), 1, Arrays.asList()));
        data.put("CPSC 110 L13", new Section("CPSC 110 L13", "CPSC 110", SectionType.LABORATORY,
                "18:00", "21:00",
                Arrays.asList("Mon"), 1, Arrays.asList()));

        data.put("CPSC 121 L1A", new Section("CPSC 121 L1A", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00",
                Arrays.asList("Wed"), 1, Arrays.asList()));
        data.put("CPSC 121 L1B", new Section("CPSC 121 L1B", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00",
                Arrays.asList("Tue"), 1, Arrays.asList()));
        data.put("CPSC 121 L1C", new Section("CPSC 121 L1C", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00",
                Arrays.asList("Tue"), 1, Arrays.asList()));


        // TUTORIALS
        data.put("CPSC 121 T1A", new Section("CPSC 121 T1A", "CPSC 121", SectionType.TUTORIAL,
                "9:00", "10:00",
                Arrays.asList("Mon"), 1, Arrays.asList()));
        data.put("CPSC 121 T1B", new Section("CPSC 121 T1B", "CPSC 121", SectionType.TUTORIAL,
                "16:00", "17:00",
                Arrays.asList("Tue"), 1, Arrays.asList()));
        data.put("CPSC 121 T1C", new Section("CPSC 121 T1C", "CPSC 121", SectionType.TUTORIAL,
                "17:00", "18:00",
                Arrays.asList("Mon"), 1, Arrays.asList()));




        // Data used for unit tests only
        // test time conflict
        data.put("Test Section 1", new Section("Test Section 1", "Test Course", SectionType.LECTURE,
                "1:00", "3:00", Arrays.asList("Mon, Wed, Fri"), 1, new ArrayList<>()));
        data.put("Test Section 2", new Section("Test Section 2", "Test Course", SectionType.LECTURE,
                "1:00", "4:00", Arrays.asList("Tues, Thurs"), 1, new ArrayList<>()));
        data.put("Test Section 3", new Section("Test Section 3", "Test Course", SectionType.LECTURE,
                "12:00", "2:00", Arrays.asList("Mon, Wed, Fri"), 1, new ArrayList<>()));


        // test weights
        data.put("weightTest1", new Section("weightTest1", "test course", SectionType.LECTURE,
                "13:00", "14:00", Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri"), 2,
                new ArrayList<>()));
        data.put("weightTest2", new Section("weightTest2", "test course", SectionType.LECTURE,
                "14:00", "15:00", Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri"), 2,
                new ArrayList<>()));

    }

    // REQUIRES: given String is a valid course ID
    // EFFECTS: returns root sections given a course ID. It returns all sections of a course that has antirequisits,
    //          but if there are none, it returns all the sections of the course that have the SectionType LECTURE
    @Override
    public List<Section> getRootSections(String courseID, int term) {
        List<Section> result = new ArrayList<>();
        for (Section section : data.values()) {
            if (courseID.equals(section.getCourseID()) && !section.getAntiRequisiteIDs().isEmpty()
                    && section.getTerm() == term) {
                result.add(section);
            }
        }
        if (result.isEmpty()) {
            for (Section section : data.values()) {
                if (courseID.equals(section.getCourseID()) && section.getSectionType() == SectionType.LECTURE) {
                    result.add(section);
                }
            }
        }
        return result;
    }


    // REQUIRES: given string is a valid Section ID.
    // EFFECTS: returns a Section corresponding to the given Section ID.
    @Override
    public Section getSection(String sectionID) {
        Section result = data.get(sectionID);
        return result;
    }


    // REQUIRES: given list of strings are all valid Section IDs.
    // EFFECTS: returns a list of Sections corresponding to the given section IDs.
    @Override
    public List<Section> getSections(List<String> sectionsIDs) {
        List<Section> result = new ArrayList<>();
        for (String sectionID : sectionsIDs) {
            result.add(getSection(sectionID));
        }
        return result;
    }

    // EFFECTS: returns a list of all course IDs
    public List<String> getAllCourseIDs() {
        ArrayList<String> courseIDs = new ArrayList<>();
        for (String courseID : courses.keySet()) {
            courseIDs.add(courseID);
        }
        return courseIDs;
    }

    // REQUIRES: given course ID is a valid course ID
    // EFFECTS: given a course ID, gets the course description
    public String getCourseDescription(String courseID) {
        return courses.get(courseID).getDescription();
    }
}
