package model.util;

import model.Schedule;
import model.Section;
import model.SectionType;

import java.util.*;

public class CourseTestData implements CourseData {
    private Map<String, Section> data = new LinkedHashMap<>();

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public CourseTestData() {
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
                "10:30", "17:00", Arrays.asList("Tue", "Fri"), 1,
                Arrays.asList(Arrays.asList("CPSC 121 L1A", "CPSC 121 L1B"),
                Arrays.asList("CPSC 121 T1A", "CPSC 121 T1C"))));

        // LABS
        data.put("CPSC 110 L10", new Section("CPSC 110 L10", "CPSC 110", SectionType.LAB,
                "18:00", "21:00",
                Arrays.asList("Wed"), 1, Arrays.asList()));
        data.put("CPSC 110 L11", new Section("CPSC 110 L11", "CPSC 110", SectionType.LAB,
                "8:00", "11:00",
                Arrays.asList("Thu"), 1, Arrays.asList()));
        data.put("CPSC 110 L12", new Section("CPSC 110 L12", "CPSC 110", SectionType.LAB,
                "1:00", "3:00",
                Arrays.asList("Fri"), 1, Arrays.asList()));
        data.put("CPSC 110 L13", new Section("CPSC 110 L13", "CPSC 110", SectionType.LAB,
                "18:00", "21:00",
                Arrays.asList("Mon"), 1, Arrays.asList()));

        data.put("CPSC 121 L1A", new Section("CPSC 121 L1A", "CPSC 121", SectionType.LAB,
                "15:00", "17:00",
                Arrays.asList("Wed"), 1, Arrays.asList()));
        data.put("CPSC 121 L1B", new Section("CPSC 121 L1B", "CPSC 121", SectionType.LAB,
                "13:00", "15:00",
                Arrays.asList("Tue"), 1, Arrays.asList()));
        data.put("CPSC 121 L1C", new Section("CPSC 121 L1C", "CPSC 121", SectionType.LAB,
                "15:00", "17:00",
                Arrays.asList("Tue"), 1, Arrays.asList()));

        // TUTORIALS
        data.put("CPSC 121 T1A", new Section("CPSC 121 T1A", "CPSC 121", SectionType.TUTORIAL,
                "9:00", "10:00",
                Arrays.asList("Mon"), 1, Arrays.asList()));
        data.put("CPSC 121 T1B", new Section("CPSC 121 T1B", "CPSC 121", SectionType.TUTORIAL,
                "16:00", "17:00",
                Arrays.asList("Tue"), 1, Arrays.asList()));
        data.put("CPSC 121 T1C  ", new Section("CPSC 121 T1C", "CPSC 121", SectionType.TUTORIAL,
                "17:00", "18:00",
                Arrays.asList("Mon"), 1, Arrays.asList()));




        // Data used for unit tests only
        // test time conflict
        data.put("Test Section 1", new Section("Test Section 1", "Test Course", SectionType.LECTURE,
                "1:00", "3:00", Arrays.asList("Mon, Wed, Fri"), 1, new ArrayList<>()));
        data.put("Test Section 2", new Section("Test Section 2", "Test Course", SectionType.LECTURE,
                "1:00", "3:00", Arrays.asList("Mon, Wed, Fri"), 1, new ArrayList<>()));


    }

    // gets root sections given a course ID. It will get all lectures sections of a course, but if there are no
    // lectures, it returns all the sections of the course
    @Override
    public List<Section> getRootSections(String courseID) {
        List<Section> result = new ArrayList<>();
        for (Section section : data.values()) {
            if (courseID.equals(section.getCourseID()) && !section.getAntiRequisiteIDs().isEmpty()) {
                result.add(section);
            }
        }
        if (result.isEmpty()) {
            for (Section section : data.values()) {
                if (courseID.equals(section.getCourseID())) {
                    result.add(section);
                }
            }
        }
        return result;
    }


    @Override
    public Section getSection(String sectionID) {
        return data.get(sectionID);
    }


    @Override
    public List<Section> getSectionIDs(List<String> sectionsIDs) {
        List<Section> result = new ArrayList<>();
        for (String sectionID : sectionsIDs) {
            result.add(getSection(sectionID));
        }
        return result;
    }
}
