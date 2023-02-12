package model.util;

import model.Course;
import model.Section;
import model.SectionType;

import java.util.*;

// Data used for demo purposes. Contains 8 courses with a few sections from each course. Include methods for loading
// the data.
public class CourseRealData implements CourseData {
    private Map<String, Section> data = new LinkedHashMap<>(); // a hashmap of Sections, with the value being a
    // section, and the key being the corresponding course ID.
    private Map<String, Course> courses = new LinkedHashMap<>(); // Some course IDs and their description

    // EFFECTS: Creates a new CourseRealData with a populated data field.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public CourseRealData() {

        // COURSES:
        courses.put("CPSC 110", new Course("CPSC 110",
                "Fundamental program and computation structures. Introductory programming skills."));
        courses.put("CPSC 121", new Course("CPSC 121",
                "Physical and mathematical structures of computation."));
        courses.put("CPSC 210", new Course("CPSC 210",
                "Design, development, and analysis of robust software components. "));
        courses.put("PHYS 117", new Course("PHYS 117",
                "Dynamics and Waves. Kinematics including curvilinear motion."));
        courses.put("ENGL 110", new Course("ENGL 110",
                "Study of selected examples of literary and cultural expression."));
        courses.put("ENGL 111", new Course("ENGL 111",
                "Study of selected communication genres from a language-based perspective"));
        courses.put("MATH 180", new Course("MATH 180",
                "Topics as for Math 100; intended for students with no previous knowledge of Calculus."));
        courses.put("MATH 101", new Course("MATH 101",
                "The definite integral, integration techniques, applications, modelling, infinite series."));
        courses.put("ASTR 102", new Course("ASTR 102",
                "Modern stellar and extragalactic astronomy."));
        courses.put("BIOL 111", new Course("BIOL 111",
                "Concepts fundamental to biological issues"));



        data.put("PHYS 117 101",  new Section("PHYS 117 101", "PHYS 117", SectionType.LECTURE,
                "11:00", "12:00", Arrays.asList("Mon","Wed","Fri"), 1,
                Arrays.asList(Arrays.asList("PHYS 117 T1A","PHYS 117 T1B","PHYS 117 T1C","PHYS 117 T1D","PHYS 117 T1E",
                        "PHYS 117 T1F","PHYS 117 T1G","PHYS 117 T1N","PHYS 117 T1O"))));
        data.put("PHYS 117 T1A",  new Section("PHYS 117 T1A", "PHYS 117", SectionType.TUTORIAL,
                "10:00", "11:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1B",  new Section("PHYS 117 T1B", "PHYS 117", SectionType.TUTORIAL,
                "14:00", "15:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1C",  new Section("PHYS 117 T1C", "PHYS 117", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1D",  new Section("PHYS 117 T1D", "PHYS 117", SectionType.TUTORIAL,
                "13:00", "14:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1E",  new Section("PHYS 117 T1E", "PHYS 117", SectionType.TUTORIAL,
                "15:30", "16:30", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1F",  new Section("PHYS 117 T1F", "PHYS 117", SectionType.TUTORIAL,
                "13:00", "14:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1G",  new Section("PHYS 117 T1G", "PHYS 117", SectionType.TUTORIAL,
                "16:00", "17:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1N",  new Section("PHYS 117 T1N", "PHYS 117", SectionType.TUTORIAL,
                "15:00", "16:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("PHYS 117 T1O",  new Section("PHYS 117 T1O", "PHYS 117", SectionType.TUTORIAL,
                "10:00", "11:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("PHYS 117 102",  new Section("PHYS 117 102", "PHYS 117", SectionType.LECTURE,
                "15:00", "16:00", Arrays.asList("Mon","Wed","Fri"), 1,
                Arrays.asList(Arrays.asList("PHYS 117 T1A","PHYS 117 T1B","PHYS 117 T1C","PHYS 117 T1D","PHYS 117 T1E",
                        "PHYS 117 T1F","PHYS 117 T1G","PHYS 117 T1N","PHYS 117 T1O"))));
        data.put("PHYS 117 V01",  new Section("PHYS 117 V01", "PHYS 117", SectionType.LECTURE,
                "9:00", "10:30", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList()));
        data.put("PHYS 117 V02",  new Section("PHYS 117 V02", "PHYS 117", SectionType.LECTURE,
                "10:30", "12:00", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList()));
        data.put("MATH 180 101",  new Section("MATH 180 101", "MATH 180", SectionType.LECTURE,
                "16:00", "18:00", Arrays.asList("Thu"), 1,
                Arrays.asList(Arrays.asList("MATH 180 DF1","MATH 180 DF2","MATH 180 DM1","MATH 180 DM2","MATH 180 DT1",
                        "MATH 180 DW1","MATH 180 DW2"))));
        data.put("MATH 180 DF1",  new Section("MATH 180 DF1", "MATH 180", SectionType.DISCUSSION,
                "10:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("MATH 180 DF2",  new Section("MATH 180 DF2", "MATH 180", SectionType.DISCUSSION,
                "12:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("MATH 180 DM1",  new Section("MATH 180 DM1", "MATH 180", SectionType.DISCUSSION,
                "10:00", "12:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("MATH 180 DM2",  new Section("MATH 180 DM2", "MATH 180", SectionType.DISCUSSION,
                "12:00", "14:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("MATH 180 DT1",  new Section("MATH 180 DT1", "MATH 180", SectionType.DISCUSSION,
                "14:00", "16:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("MATH 180 DW1",  new Section("MATH 180 DW1", "MATH 180", SectionType.DISCUSSION,
                "10:00", "12:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("MATH 180 DW2",  new Section("MATH 180 DW2", "MATH 180", SectionType.DISCUSSION,
                "11:00", "13:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 110 101",  new Section("CPSC 110 101", "CPSC 110", SectionType.LECTURE,
                "11:00", "12:30", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList(Arrays.asList("CPSC 110 L12","CPSC 110 L13","CPSC 110 L14","CPSC 110 L15","CPSC 110 L16",
                        "CPSC 110 L19","CPSC 110 L1B","CPSC 110 L1E","CPSC 110 L1H","CPSC 110 L1M"))));
        data.put("CPSC 110 L12",  new Section("CPSC 110 L12", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L13",  new Section("CPSC 110 L13", "CPSC 110", SectionType.LABORATORY,
                "9:30", "12:30", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L14",  new Section("CPSC 110 L14", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L15",  new Section("CPSC 110 L15", "CPSC 110", SectionType.LABORATORY,
                "9:30", "12:30", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L16",  new Section("CPSC 110 L16", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L19",  new Section("CPSC 110 L19", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1A",  new Section("CPSC 110 L1A", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1B",  new Section("CPSC 110 L1B", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1D",  new Section("CPSC 110 L1D", "CPSC 110", SectionType.LABORATORY,
                "9:30", "12:30", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1E",  new Section("CPSC 110 L1E", "CPSC 110", SectionType.LABORATORY,
                "12:30", "15:30", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1G",  new Section("CPSC 110 L1G", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1H",  new Section("CPSC 110 L1H", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1K",  new Section("CPSC 110 L1K", "CPSC 110", SectionType.LABORATORY,
                "9:30", "12:30", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1M",  new Section("CPSC 110 L1M", "CPSC 110", SectionType.LABORATORY,
                "12:30", "15:30", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1N",  new Section("CPSC 110 L1N", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1P",  new Section("CPSC 110 L1P", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1R",  new Section("CPSC 110 L1R", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1S",  new Section("CPSC 110 L1S", "CPSC 110", SectionType.LABORATORY,
                "12:30", "15:30", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1U",  new Section("CPSC 110 L1U", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 110 L1V",  new Section("CPSC 110 L1V", "CPSC 110", SectionType.LABORATORY,
                "12:30", "15:30", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 110 LAE",  new Section("CPSC 110 LAE", "CPSC 110", SectionType.LABORATORY,
                "9:30", "12:30", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 110 102",  new Section("CPSC 110 102", "CPSC 110", SectionType.LECTURE,
                "14:00", "15:30", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList(Arrays.asList("CPSC 110 L12","CPSC 110 L13","CPSC 110 L14","CPSC 110 L15","CPSC 110 L16",
                        "CPSC 110 L19","CPSC 110 L1B","CPSC 110 L1E","CPSC 110 L1H","CPSC 110 L1M"))));
        data.put("CPSC 110 103",  new Section("CPSC 110 103", "CPSC 110", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList(Arrays.asList("CPSC 110 L12","CPSC 110 L13","CPSC 110 L14","CPSC 110 L15","CPSC 110 L16",
                        "CPSC 110 L19","CPSC 110 L1B","CPSC 110 L1E","CPSC 110 L1H","CPSC 110 L1M"))));
        data.put("CPSC 110 104",  new Section("CPSC 110 104", "CPSC 110", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList(Arrays.asList("CPSC 110 L12","CPSC 110 L13","CPSC 110 L14","CPSC 110 L15","CPSC 110 L16",
                        "CPSC 110 L19","CPSC 110 L1B","CPSC 110 L1E","CPSC 110 L1H","CPSC 110 L1M"))));
        data.put("ENGL 111 001",  new Section("ENGL 111 001", "ENGL 111", SectionType.LECTURE,
                "14:00", "15:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList(Arrays.asList("ENGL 111 L05"))));
        data.put("ENGL 111 L05",  new Section("ENGL 111 L05", "ENGL 111", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 111 L06",  new Section("ENGL 111 L06", "ENGL 111", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 111 L07",  new Section("ENGL 111 L07", "ENGL 111", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 111 L08",  new Section("ENGL 111 L08", "ENGL 111", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 111 002",  new Section("ENGL 111 002", "ENGL 111", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList()));
        data.put("BIOL 111 101",  new Section("BIOL 111 101", "BIOL 111", SectionType.LECTURE,
                "8:00", "9:30", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList()));
        data.put("BIOL 111 102",  new Section("BIOL 111 102", "BIOL 111", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 101",  new Section("CPSC 121 101", "CPSC 121", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList(Arrays.asList("CPSC 121 L1A","CPSC 121 L1B","CPSC 121 L1D","CPSC 121 L1E","CPSC 121 L1F",
                        "CPSC 121 L1G","CPSC 121 L1H","CPSC 121 L1J","CPSC 121 L1K","CPSC 121 L1L","CPSC 121 L1M",
                        "CPSC 121 L1N","CPSC 121 L1R","CPSC 121 L1T","CPSC 121 L1U","CPSC 121 L1V","CPSC 121 L1X",
                        "CPSC 121 L1Y","CPSC 121 L1Z"),Arrays.asList("CPSC 121 T1A","CPSC 121 T1B","CPSC 121 T1D",
                        "CPSC 121 T1E","CPSC 121 T1F","CPSC 121 T1G","CPSC 121 T1H","CPSC 121 T1J","CPSC 121 T1L",
                        "CPSC 121 T1M","CPSC 121 T1N","CPSC 121 T2C","CPSC 121 T2E","CPSC 121 T2S","CPSC 121 T2U"))));
        data.put("CPSC 121 L1A",  new Section("CPSC 121 L1A", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1B",  new Section("CPSC 121 L1B", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1D",  new Section("CPSC 121 L1D", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1E",  new Section("CPSC 121 L1E", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1F",  new Section("CPSC 121 L1F", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1G",  new Section("CPSC 121 L1G", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1H",  new Section("CPSC 121 L1H", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1J",  new Section("CPSC 121 L1J", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1K",  new Section("CPSC 121 L1K", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1L",  new Section("CPSC 121 L1L", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1M",  new Section("CPSC 121 L1M", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1N",  new Section("CPSC 121 L1N", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1R",  new Section("CPSC 121 L1R", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1T",  new Section("CPSC 121 L1T", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1U",  new Section("CPSC 121 L1U", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1V",  new Section("CPSC 121 L1V", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1X",  new Section("CPSC 121 L1X", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1Y",  new Section("CPSC 121 L1Y", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 L1Z",  new Section("CPSC 121 L1Z", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1A",  new Section("CPSC 121 T1A", "CPSC 121", SectionType.TUTORIAL,
                "9:00", "10:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1B",  new Section("CPSC 121 T1B", "CPSC 121", SectionType.TUTORIAL,
                "16:00", "17:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1D",  new Section("CPSC 121 T1D", "CPSC 121", SectionType.TUTORIAL,
                "15:00", "16:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1E",  new Section("CPSC 121 T1E", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1F",  new Section("CPSC 121 T1F", "CPSC 121", SectionType.TUTORIAL,
                "12:00", "13:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1G",  new Section("CPSC 121 T1G", "CPSC 121", SectionType.TUTORIAL,
                "16:00", "17:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1H",  new Section("CPSC 121 T1H", "CPSC 121", SectionType.TUTORIAL,
                "14:00", "15:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1J",  new Section("CPSC 121 T1J", "CPSC 121", SectionType.TUTORIAL,
                "15:00", "16:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1L",  new Section("CPSC 121 T1L", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1M",  new Section("CPSC 121 T1M", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 121 T1N",  new Section("CPSC 121 T1N", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 121 102",  new Section("CPSC 121 102", "CPSC 121", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList(Arrays.asList("CPSC 121 L1A","CPSC 121 L1B","CPSC 121 L1D","CPSC 121 L1E","CPSC 121 L1F",
                        "CPSC 121 L1G","CPSC 121 L1H","CPSC 121 L1J","CPSC 121 L1K","CPSC 121 L1L","CPSC 121 L1M",
                        "CPSC 121 L1N","CPSC 121 L1R","CPSC 121 L1T","CPSC 121 L1U","CPSC 121 L1V","CPSC 121 L1X",
                        "CPSC 121 L1Y","CPSC 121 L1Z"),Arrays.asList("CPSC 121 T1A","CPSC 121 T1B","CPSC 121 T1D",
                        "CPSC 121 T1E","CPSC 121 T1F","CPSC 121 T1G","CPSC 121 T1H","CPSC 121 T1J","CPSC 121 T1L",
                        "CPSC 121 T1M","CPSC 121 T1N"))));
        data.put("ENGL 110 001",  new Section("ENGL 110 001", "ENGL 110", SectionType.LECTURE,
                "9:00", "10:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList(Arrays.asList("ENGL 110 LA1"))));
        data.put("ENGL 110 LA1",  new Section("ENGL 110 LA1", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LA2",  new Section("ENGL 110 LA2", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LA3",  new Section("ENGL 110 LA3", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LA4",  new Section("ENGL 110 LA4", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LA5",  new Section("ENGL 110 LA5", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LA6",  new Section("ENGL 110 LA6", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 002",  new Section("ENGL 110 002", "ENGL 110", SectionType.LECTURE,
                "10:00", "11:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList(Arrays.asList("ENGL 110 LB1"))));
        data.put("ENGL 110 LB1",  new Section("ENGL 110 LB1", "ENGL 110", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LB2",  new Section("ENGL 110 LB2", "ENGL 110", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LB3",  new Section("ENGL 110 LB3", "ENGL 110", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LB4",  new Section("ENGL 110 LB4", "ENGL 110", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LB5",  new Section("ENGL 110 LB5", "ENGL 110", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LB6",  new Section("ENGL 110 LB6", "ENGL 110", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 003",  new Section("ENGL 110 003", "ENGL 110", SectionType.LECTURE,
                "11:00", "12:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LC1",  new Section("ENGL 110 LC1", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LC2",  new Section("ENGL 110 LC2", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LC3",  new Section("ENGL 110 LC3", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LC4",  new Section("ENGL 110 LC4", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LC5",  new Section("ENGL 110 LC5", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 004",  new Section("ENGL 110 004", "ENGL 110", SectionType.LECTURE,
                "12:00", "13:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LF1",  new Section("ENGL 110 LF1", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LF2",  new Section("ENGL 110 LF2", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LF3",  new Section("ENGL 110 LF3", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LF4",  new Section("ENGL 110 LF4", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LF5",  new Section("ENGL 110 LF5", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LF6",  new Section("ENGL 110 LF6", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 005",  new Section("ENGL 110 005", "ENGL 110", SectionType.LECTURE,
                "13:00", "14:00", Arrays.asList("Mon","Wed"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LE1",  new Section("ENGL 110 LE1", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LE2",  new Section("ENGL 110 LE2", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LE3",  new Section("ENGL 110 LE3", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LE4",  new Section("ENGL 110 LE4", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LE5",  new Section("ENGL 110 LE5", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 LE6",  new Section("ENGL 110 LE6", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("ENGL 110 006",  new Section("ENGL 110 006", "ENGL 110", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Tue","Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 210 101",  new Section("CPSC 210 101", "CPSC 210", SectionType.LECTURE,
                "11:00", "12:00", Arrays.asList("Mon","Wed","Fri"), 1,
                Arrays.asList(Arrays.asList("CPSC 210 L11","CPSC 210 L12","CPSC 210 L13","CPSC 210 L15",
                        "CPSC 210 L16","CPSC 210 L1A","CPSC 210 L1B","CPSC 210 L1D","CPSC 210 L1E","CPSC 210 L1F",
                        "CPSC 210 L1G","CPSC 210 L1H","CPSC 210 L1J","CPSC 210 L1K","CPSC 210 L1L","CPSC 210 L1M",
                        "CPSC 210 L1P","CPSC 210 L1R","CPSC 210 L1T","CPSC 210 L1Y"))));
        data.put("CPSC 210 L11",  new Section("CPSC 210 L11", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L12",  new Section("CPSC 210 L12", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L13",  new Section("CPSC 210 L13", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L15",  new Section("CPSC 210 L15", "CPSC 210", SectionType.LABORATORY,
                "14:00", "16:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L16",  new Section("CPSC 210 L16", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1A",  new Section("CPSC 210 L1A", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1B",  new Section("CPSC 210 L1B", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1C",  new Section("CPSC 210 L1C", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1D",  new Section("CPSC 210 L1D", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1E",  new Section("CPSC 210 L1E", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1F",  new Section("CPSC 210 L1F", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1G",  new Section("CPSC 210 L1G", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1H",  new Section("CPSC 210 L1H", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1J",  new Section("CPSC 210 L1J", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1K",  new Section("CPSC 210 L1K", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Tue"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1L",  new Section("CPSC 210 L1L", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1M",  new Section("CPSC 210 L1M", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1N",  new Section("CPSC 210 L1N", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1O",  new Section("CPSC 210 L1O", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1P",  new Section("CPSC 210 L1P", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1R",  new Section("CPSC 210 L1R", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Thu"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1S",  new Section("CPSC 210 L1S", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1T",  new Section("CPSC 210 L1T", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Fri"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1X",  new Section("CPSC 210 L1X", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Mon"), 1,
                Arrays.asList()));
        data.put("CPSC 210 L1Y",  new Section("CPSC 210 L1Y", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Wed"), 1,
                Arrays.asList()));
        data.put("CPSC 210 102",  new Section("CPSC 210 102", "CPSC 210", SectionType.LECTURE,
                "13:00", "14:00", Arrays.asList("Mon","Wed","Fri"), 1,
                Arrays.asList(Arrays.asList("CPSC 210 L11","CPSC 210 L12","CPSC 210 L13","CPSC 210 L15",
                        "CPSC 210 L16","CPSC 210 L1A","CPSC 210 L1B","CPSC 210 L1D","CPSC 210 L1E","CPSC 210 L1F",
                        "CPSC 210 L1G","CPSC 210 L1H","CPSC 210 L1J","CPSC 210 L1K","CPSC 210 L1L","CPSC 210 L1M",
                        "CPSC 210 L1P","CPSC 210 L1R","CPSC 210 L1T","CPSC 210 L1Y"))));
        data.put("CPSC 110 201",  new Section("CPSC 110 201", "CPSC 110", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList(Arrays.asList("CPSC 110 L20","CPSC 110 L21","CPSC 110 L23","CPSC 110 L2A",
                        "CPSC 110 L2B","CPSC 110 L2E","CPSC 110 L2H","CPSC 110 L2J","CPSC 110 L2P","CPSC 110 L2S"))));
        data.put("CPSC 110 L20",  new Section("CPSC 110 L20", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L21",  new Section("CPSC 110 L21", "CPSC 110", SectionType.LABORATORY,
                "11:00", "14:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L23",  new Section("CPSC 110 L23", "CPSC 110", SectionType.LABORATORY,
                "11:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2A",  new Section("CPSC 110 L2A", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2B",  new Section("CPSC 110 L2B", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2E",  new Section("CPSC 110 L2E", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2F",  new Section("CPSC 110 L2F", "CPSC 110", SectionType.LABORATORY,
                "11:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2H",  new Section("CPSC 110 L2H", "CPSC 110", SectionType.LABORATORY,
                "14:00", "17:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2J",  new Section("CPSC 110 L2J", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2M",  new Section("CPSC 110 L2M", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2P",  new Section("CPSC 110 L2P", "CPSC 110", SectionType.LABORATORY,
                "14:00", "17:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2S",  new Section("CPSC 110 L2S", "CPSC 110", SectionType.LABORATORY,
                "9:00", "12:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2T",  new Section("CPSC 110 L2T", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2U",  new Section("CPSC 110 L2U", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 110 L2W",  new Section("CPSC 110 L2W", "CPSC 110", SectionType.LABORATORY,
                "12:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 110 203",  new Section("CPSC 110 203", "CPSC 110", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList(Arrays.asList("CPSC 110 L20","CPSC 110 L21","CPSC 110 L23","CPSC 110 L2A",
                        "CPSC 110 L2B","CPSC 110 L2E","CPSC 110 L2H","CPSC 110 L2J","CPSC 110 L2P","CPSC 110 L2S"))));
        data.put("CPSC 110 V01",  new Section("CPSC 110 V01", "CPSC 110", SectionType.LECTURE,
                "11:00", "12:00", Arrays.asList("Mon","Wed","Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 111 003",  new Section("ENGL 111 003", "ENGL 111", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList()));
        data.put("ENGL 111 005",  new Section("ENGL 111 005", "ENGL 111", SectionType.LECTURE,
                "10:00", "11:00", Arrays.asList("Mon","Wed"), 2,
                Arrays.asList(Arrays.asList("ENGL 111 L20"))));
        data.put("ENGL 111 L20",  new Section("ENGL 111 L20", "ENGL 111", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 111 L21",  new Section("ENGL 111 L21", "ENGL 111", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 111 L22",  new Section("ENGL 111 L22", "ENGL 111", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 111 L23",  new Section("ENGL 111 L23", "ENGL 111", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 111 006",  new Section("ENGL 111 006", "ENGL 111", SectionType.LECTURE,
                "14:00", "15:30", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList()));
        data.put("BIOL 111 201",  new Section("BIOL 111 201", "BIOL 111", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 2A1",  new Section("MATH 101 2A1", "MATH 101", SectionType.LECTURE,
                "8:00", "10:00", Arrays.asList("Tue"), 2,
                Arrays.asList(Arrays.asList("MATH 101 A11","MATH 101 A12","MATH 101 A13","MATH 101 A14",
                        "MATH 101 A15","MATH 101 A16","MATH 101 A17","MATH 101 A18"))));
        data.put("MATH 101 A11",  new Section("MATH 101 A11", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A12",  new Section("MATH 101 A12", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A13",  new Section("MATH 101 A13", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A14",  new Section("MATH 101 A14", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A15",  new Section("MATH 101 A15", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A16",  new Section("MATH 101 A16", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A17",  new Section("MATH 101 A17", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 A18",  new Section("MATH 101 A18", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 2A2",  new Section("MATH 101 2A2", "MATH 101", SectionType.LECTURE,
                "10:00", "12:00", Arrays.asList("Tue"), 2,
                Arrays.asList(Arrays.asList("MATH 101 A21","MATH 101 A22","MATH 101 A23","MATH 101 A24",
                        "MATH 101 A25","MATH 101 A26","MATH 101 A27"))));
        data.put("MATH 101 A21",  new Section("MATH 101 A21", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A22",  new Section("MATH 101 A22", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A23",  new Section("MATH 101 A23", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A24",  new Section("MATH 101 A24", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A25",  new Section("MATH 101 A25", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A26",  new Section("MATH 101 A26", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A27",  new Section("MATH 101 A27", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A28",  new Section("MATH 101 A28", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A29",  new Section("MATH 101 A29", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 2A3",  new Section("MATH 101 2A3", "MATH 101", SectionType.LECTURE,
                "12:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList(Arrays.asList("MATH 101 A31","MATH 101 A32","MATH 101 A33","MATH 101 A34",
                        "MATH 101 A35","MATH 101 A36","MATH 101 A37","MATH 101 A39"))));
        data.put("MATH 101 A31",  new Section("MATH 101 A31", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A32",  new Section("MATH 101 A32", "MATH 101", SectionType.DISCUSSION,
                "15:00", "16:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A33",  new Section("MATH 101 A33", "MATH 101", SectionType.DISCUSSION,
                "16:00", "17:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 A34",  new Section("MATH 101 A34", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A35",  new Section("MATH 101 A35", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A36",  new Section("MATH 101 A36", "MATH 101", SectionType.DISCUSSION,
                "15:00", "16:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A37",  new Section("MATH 101 A37", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A39",  new Section("MATH 101 A39", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 2A4",  new Section("MATH 101 2A4", "MATH 101", SectionType.LECTURE,
                "14:00", "16:00", Arrays.asList("Tue"), 2,
                Arrays.asList(Arrays.asList("MATH 101 A41","MATH 101 A42","MATH 101 A43","MATH 101 A44",
                        "MATH 101 A45","MATH 101 A46"))));
        data.put("MATH 101 A41",  new Section("MATH 101 A41", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 A42",  new Section("MATH 101 A42", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A43",  new Section("MATH 101 A43", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A44",  new Section("MATH 101 A44", "MATH 101", SectionType.DISCUSSION,
                "12:30", "13:30", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 A45",  new Section("MATH 101 A45", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 A46",  new Section("MATH 101 A46", "MATH 101", SectionType.DISCUSSION,
                "15:00", "16:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 2AR",  new Section("MATH 101 2AR", "MATH 101", SectionType.LECTURE,
                "10:00", "12:00", Arrays.asList("Tue"), 2,
                Arrays.asList(Arrays.asList("MATH 101 AR2","MATH 101 AR3","MATH 101 AR5","MATH 101 AR6"))));
        data.put("MATH 101 AR2",  new Section("MATH 101 AR2", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 AR3",  new Section("MATH 101 AR3", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 AR5",  new Section("MATH 101 AR5", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 AR6",  new Section("MATH 101 AR6", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 2B1",  new Section("MATH 101 2B1", "MATH 101", SectionType.LECTURE,
                "8:00", "10:00", Arrays.asList("Wed"), 2,
                Arrays.asList(Arrays.asList("MATH 101 B11","MATH 101 B12","MATH 101 B13","MATH 101 B14",
                        "MATH 101 B15","MATH 101 B16","MATH 101 B17","MATH 101 B18"))));
        data.put("MATH 101 B11",  new Section("MATH 101 B11", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 B12",  new Section("MATH 101 B12", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 B13",  new Section("MATH 101 B13", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 B14",  new Section("MATH 101 B14", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 B15",  new Section("MATH 101 B15", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 B16",  new Section("MATH 101 B16", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 B17",  new Section("MATH 101 B17", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 B18",  new Section("MATH 101 B18", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 2B2",  new Section("MATH 101 2B2", "MATH 101", SectionType.LECTURE,
                "12:00", "14:00", Arrays.asList("Wed"), 2,
                Arrays.asList(Arrays.asList("MATH 101 B21","MATH 101 B22","MATH 101 B23","MATH 101 B24",
                        "MATH 101 B25","MATH 101 B26"))));
        data.put("MATH 101 B21",  new Section("MATH 101 B21", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 B22",  new Section("MATH 101 B22", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 B23",  new Section("MATH 101 B23", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 B24",  new Section("MATH 101 B24", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 B25",  new Section("MATH 101 B25", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 B26",  new Section("MATH 101 B26", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 2B3",  new Section("MATH 101 2B3", "MATH 101", SectionType.LECTURE,
                "14:00", "16:00", Arrays.asList("Wed"), 2,
                Arrays.asList(Arrays.asList("MATH 101 B31","MATH 101 B32","MATH 101 B33","MATH 101 B34",
                        "MATH 101 B35","MATH 101 B36"))));
        data.put("MATH 101 B31",  new Section("MATH 101 B31", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 B32",  new Section("MATH 101 B32", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("MATH 101 B33",  new Section("MATH 101 B33", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 B34",  new Section("MATH 101 B34", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 B35",  new Section("MATH 101 B35", "MATH 101", SectionType.DISCUSSION,
                "15:00", "16:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 B36",  new Section("MATH 101 B36", "MATH 101", SectionType.DISCUSSION,
                "16:00", "17:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 2C1",  new Section("MATH 101 2C1", "MATH 101", SectionType.LECTURE,
                "10:00", "12:00", Arrays.asList("Thu"), 2,
                Arrays.asList(Arrays.asList("MATH 101 C11","MATH 101 C12","MATH 101 C13","MATH 101 C14",
                        "MATH 101 C15","MATH 101 C16","MATH 101 C17"))));
        data.put("MATH 101 C11",  new Section("MATH 101 C11", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 C12",  new Section("MATH 101 C12", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C13",  new Section("MATH 101 C13", "MATH 101", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C14",  new Section("MATH 101 C14", "MATH 101", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C15",  new Section("MATH 101 C15", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C16",  new Section("MATH 101 C16", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C17",  new Section("MATH 101 C17", "MATH 101", SectionType.DISCUSSION,
                "16:00", "17:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C18",  new Section("MATH 101 C18", "MATH 101", SectionType.DISCUSSION,
                "16:00", "17:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 2C2",  new Section("MATH 101 2C2", "MATH 101", SectionType.LECTURE,
                "12:00", "14:00", Arrays.asList("Thu"), 2,
                Arrays.asList(Arrays.asList("MATH 101 C21","MATH 101 C22","MATH 101 C23",
                        "MATH 101 C24","MATH 101 C25","MATH 101 C26","MATH 101 C27","MATH 101 C28"))));
        data.put("MATH 101 C21",  new Section("MATH 101 C21", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 C22",  new Section("MATH 101 C22", "MATH 101", SectionType.DISCUSSION,
                "15:00", "16:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 C23",  new Section("MATH 101 C23", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C24",  new Section("MATH 101 C24", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("MATH 101 C25",  new Section("MATH 101 C25", "MATH 101", SectionType.DISCUSSION,
                "8:00", "9:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 C26",  new Section("MATH 101 C26", "MATH 101", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 C27",  new Section("MATH 101 C27", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 C28",  new Section("MATH 101 C28", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 2C3",  new Section("MATH 101 2C3", "MATH 101", SectionType.LECTURE,
                "14:00", "16:00", Arrays.asList("Thu"), 2,
                Arrays.asList(Arrays.asList("MATH 101 C31","MATH 101 C32","MATH 101 C33","MATH 101 C34","MATH 101 C35",
                        "MATH 101 C36"))));
        data.put("MATH 101 C31",  new Section("MATH 101 C31", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 C32",  new Section("MATH 101 C32", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("MATH 101 C33",  new Section("MATH 101 C33", "MATH 101", SectionType.DISCUSSION,
                "10:00", "11:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 C34",  new Section("MATH 101 C34", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("MATH 101 C35",  new Section("MATH 101 C35", "MATH 101", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("MATH 101 C36",  new Section("MATH 101 C36", "MATH 101", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ASTR 102 201",  new Section("ASTR 102 201", "ASTR 102", SectionType.LECTURE,
                "12:00", "13:00", Arrays.asList("Mon","Wed","Fri"), 2,
                Arrays.asList(Arrays.asList("ASTR 102 L2A","ASTR 102 L2B","ASTR 102 L2C","ASTR 102 L2D","ASTR 102 L2E",
                        "ASTR 102 L2F"))));
        data.put("ASTR 102 L2A",  new Section("ASTR 102 L2A", "ASTR 102", SectionType.LABORATORY,
                "12:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("ASTR 102 L2B",  new Section("ASTR 102 L2B", "ASTR 102", SectionType.LABORATORY,
                "10:00", "12:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("ASTR 102 L2C",  new Section("ASTR 102 L2C", "ASTR 102", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("ASTR 102 L2D",  new Section("ASTR 102 L2D", "ASTR 102", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("ASTR 102 L2E",  new Section("ASTR 102 L2E", "ASTR 102", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("ASTR 102 L2F",  new Section("ASTR 102 L2F", "ASTR 102", SectionType.LABORATORY,
                "8:00", "10:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2C",  new Section("CPSC 121 T2C", "CPSC 121", SectionType.TUTORIAL,
                "13:00", "14:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2E",  new Section("CPSC 121 T2E", "CPSC 121", SectionType.TUTORIAL,
                "13:00", "14:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2S",  new Section("CPSC 121 T2S", "CPSC 121", SectionType.TUTORIAL,
                "12:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2U",  new Section("CPSC 121 T2U", "CPSC 121", SectionType.TUTORIAL,
                "12:00", "13:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 201",  new Section("CPSC 121 201", "CPSC 121", SectionType.LECTURE,
                "12:30", "14:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList(Arrays.asList("CPSC 121 L22","CPSC 121 L25","CPSC 121 L26","CPSC 121 L27",
                        "CPSC 121 L28","CPSC 121 L29","CPSC 121 L2C","CPSC 121 L2D","CPSC 121 L2E","CPSC 121 L2F",
                        "CPSC 121 L2G","CPSC 121 L2H","CPSC 121 L2K","CPSC 121 L2L","CPSC 121 L2M","CPSC 121 L2N",
                        "CPSC 121 L2P","CPSC 121 L2R","CPSC 121 L2U","CPSC 121 L2V","CPSC 121 L2Y","CPSC 121 L2Z"),
                        Arrays.asList("CPSC 121 T2A","CPSC 121 T2B","CPSC 121 T2C","CPSC 121 T2D","CPSC 121 T2E",
                                "CPSC 121 T2F","CPSC 121 T2G","CPSC 121 T2H","CPSC 121 T2J","CPSC 121 T2K",
                                "CPSC 121 T2M","CPSC 121 T2N","CPSC 121 T2P","CPSC 121 T2Q","CPSC 121 T2T",
                                "CPSC 121 T2U"))));
        data.put("CPSC 121 L22",  new Section("CPSC 121 L22", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L25",  new Section("CPSC 121 L25", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L26",  new Section("CPSC 121 L26", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L27",  new Section("CPSC 121 L27", "CPSC 121", SectionType.LABORATORY,
                "14:00", "16:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L28",  new Section("CPSC 121 L28", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L29",  new Section("CPSC 121 L29", "CPSC 121", SectionType.LABORATORY,
                "12:00", "14:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2A",  new Section("CPSC 121 L2A", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2C",  new Section("CPSC 121 L2C", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2D",  new Section("CPSC 121 L2D", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2E",  new Section("CPSC 121 L2E", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2F",  new Section("CPSC 121 L2F", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2G",  new Section("CPSC 121 L2G", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2H",  new Section("CPSC 121 L2H", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2J",  new Section("CPSC 121 L2J", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2K",  new Section("CPSC 121 L2K", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2L",  new Section("CPSC 121 L2L", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2M",  new Section("CPSC 121 L2M", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2N",  new Section("CPSC 121 L2N", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2P",  new Section("CPSC 121 L2P", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2Q",  new Section("CPSC 121 L2Q", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2R",  new Section("CPSC 121 L2R", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2U",  new Section("CPSC 121 L2U", "CPSC 121", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2V",  new Section("CPSC 121 L2V", "CPSC 121", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2Y",  new Section("CPSC 121 L2Y", "CPSC 121", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 L2Z",  new Section("CPSC 121 L2Z", "CPSC 121", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2A",  new Section("CPSC 121 T2A", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2B",  new Section("CPSC 121 T2B", "CPSC 121", SectionType.TUTORIAL,
                "13:00", "14:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2D",  new Section("CPSC 121 T2D", "CPSC 121", SectionType.TUTORIAL,
                "12:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2F",  new Section("CPSC 121 T2F", "CPSC 121", SectionType.TUTORIAL,
                "15:00", "16:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2G",  new Section("CPSC 121 T2G", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2H",  new Section("CPSC 121 T2H", "CPSC 121", SectionType.TUTORIAL,
                "15:00", "16:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2J",  new Section("CPSC 121 T2J", "CPSC 121", SectionType.TUTORIAL,
                "9:00", "10:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2K",  new Section("CPSC 121 T2K", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2M",  new Section("CPSC 121 T2M", "CPSC 121", SectionType.TUTORIAL,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2N",  new Section("CPSC 121 T2N", "CPSC 121", SectionType.TUTORIAL,
                "9:00", "10:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2P",  new Section("CPSC 121 T2P", "CPSC 121", SectionType.TUTORIAL,
                "9:00", "10:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2Q",  new Section("CPSC 121 T2Q", "CPSC 121", SectionType.TUTORIAL,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 121 T2T",  new Section("CPSC 121 T2T", "CPSC 121", SectionType.TUTORIAL,
                "10:00", "11:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 121 202",  new Section("CPSC 121 202", "CPSC 121", SectionType.LECTURE,
                "15:30", "17:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList(Arrays.asList("CPSC 121 L22","CPSC 121 L25","CPSC 121 L26","CPSC 121 L27",
                        "CPSC 121 L28","CPSC 121 L29","CPSC 121 L2C","CPSC 121 L2D","CPSC 121 L2E","CPSC 121 L2F",
                        "CPSC 121 L2G","CPSC 121 L2H","CPSC 121 L2K","CPSC 121 L2L","CPSC 121 L2M","CPSC 121 L2N",
                        "CPSC 121 L2P","CPSC 121 L2R","CPSC 121 L2U","CPSC 121 L2V","CPSC 121 L2Y","CPSC 121 L2Z"),
                        Arrays.asList("CPSC 121 T2A","CPSC 121 T2B","CPSC 121 T2C","CPSC 121 T2D","CPSC 121 T2E",
                                "CPSC 121 T2F","CPSC 121 T2G","CPSC 121 T2H","CPSC 121 T2J","CPSC 121 T2K",
                                "CPSC 121 T2M","CPSC 121 T2N","CPSC 121 T2P","CPSC 121 T2Q","CPSC 121 T2T",
                                "CPSC 121 T2U"))));
        data.put("CPSC 121 204",  new Section("CPSC 121 204", "CPSC 121", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList(Arrays.asList("CPSC 121 L22","CPSC 121 L25","CPSC 121 L26","CPSC 121 L27","CPSC 121 L28",
                        "CPSC 121 L29","CPSC 121 L2C","CPSC 121 L2D","CPSC 121 L2E","CPSC 121 L2F","CPSC 121 L2G",
                        "CPSC 121 L2H","CPSC 121 L2K","CPSC 121 L2L","CPSC 121 L2M","CPSC 121 L2N","CPSC 121 L2P",
                        "CPSC 121 L2R","CPSC 121 L2U","CPSC 121 L2V","CPSC 121 L2Y","CPSC 121 L2Z"),
                        Arrays.asList("CPSC 121 T2A","CPSC 121 T2B","CPSC 121 T2C","CPSC 121 T2D",
                                "CPSC 121 T2E","CPSC 121 T2F","CPSC 121 T2G","CPSC 121 T2H","CPSC 121 T2J",
                                "CPSC 121 T2K","CPSC 121 T2M","CPSC 121 T2N","CPSC 121 T2P","CPSC 121 T2Q",
                                "CPSC 121 T2T","CPSC 121 T2U"))));
        data.put("ENGL 110 007",  new Section("ENGL 110 007", "ENGL 110", SectionType.LECTURE,
                "9:00", "10:00", Arrays.asList("Mon","Wed"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LP1",  new Section("ENGL 110 LP1", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LP2",  new Section("ENGL 110 LP2", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LP3",  new Section("ENGL 110 LP3", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LP4",  new Section("ENGL 110 LP4", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LP5",  new Section("ENGL 110 LP5", "ENGL 110", SectionType.DISCUSSION,
                "9:00", "10:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 008",  new Section("ENGL 110 008", "ENGL 110", SectionType.LECTURE,
                "11:00", "12:00", Arrays.asList("Mon","Wed"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LQ1",  new Section("ENGL 110 LQ1", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LQ2",  new Section("ENGL 110 LQ2", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LQ3",  new Section("ENGL 110 LQ3", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LQ4",  new Section("ENGL 110 LQ4", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LQ5",  new Section("ENGL 110 LQ5", "ENGL 110", SectionType.DISCUSSION,
                "11:00", "12:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 009",  new Section("ENGL 110 009", "ENGL 110", SectionType.LECTURE,
                "12:00", "13:00", Arrays.asList("Mon","Wed"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LR1",  new Section("ENGL 110 LR1", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LR2",  new Section("ENGL 110 LR2", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LR3",  new Section("ENGL 110 LR3", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LR4",  new Section("ENGL 110 LR4", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LR5",  new Section("ENGL 110 LR5", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LR6",  new Section("ENGL 110 LR6", "ENGL 110", SectionType.DISCUSSION,
                "12:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 010",  new Section("ENGL 110 010", "ENGL 110", SectionType.LECTURE,
                "13:00", "14:00", Arrays.asList("Mon","Wed"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LS1",  new Section("ENGL 110 LS1", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LS2",  new Section("ENGL 110 LS2", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LS3",  new Section("ENGL 110 LS3", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LS4",  new Section("ENGL 110 LS4", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LS5",  new Section("ENGL 110 LS5", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LS6",  new Section("ENGL 110 LS6", "ENGL 110", SectionType.DISCUSSION,
                "13:00", "14:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 011",  new Section("ENGL 110 011", "ENGL 110", SectionType.LECTURE,
                "14:00", "15:00", Arrays.asList("Mon","Wed"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LT1",  new Section("ENGL 110 LT1", "ENGL 110", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LT2",  new Section("ENGL 110 LT2", "ENGL 110", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LT3",  new Section("ENGL 110 LT3", "ENGL 110", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LT4",  new Section("ENGL 110 LT4", "ENGL 110", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 LT5",  new Section("ENGL 110 LT5", "ENGL 110", SectionType.DISCUSSION,
                "14:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("ENGL 110 012",  new Section("ENGL 110 012", "ENGL 110", SectionType.LECTURE,
                "9:30", "11:00", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList()));
        data.put("ENGL 110 013",  new Section("ENGL 110 013", "ENGL 110", SectionType.LECTURE,
                "11:00", "12:30", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList()));
        data.put("ENGL 110 015",  new Section("ENGL 110 015", "ENGL 110", SectionType.LECTURE,
                "14:00", "15:30", Arrays.asList("Tue","Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 210 201",  new Section("CPSC 210 201", "CPSC 210", SectionType.LECTURE,
                "10:00", "11:00", Arrays.asList("Mon","Wed","Fri"), 2,
                Arrays.asList(Arrays.asList("CPSC 210 L2A","CPSC 210 L2B","CPSC 210 L2D","CPSC 210 L2E","CPSC 210 L2F",
                        "CPSC 210 L2G","CPSC 210 L2H","CPSC 210 L2J","CPSC 210 L2K","CPSC 210 L2M","CPSC 210 L2N",
                        "CPSC 210 L2P","CPSC 210 L2R","CPSC 210 L2T","CPSC 210 L2U","CPSC 210 L2X","CPSC 210 L2Y",
                        "CPSC 210 L2Z"))));
        data.put("CPSC 210 L2A",  new Section("CPSC 210 L2A", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2B",  new Section("CPSC 210 L2B", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2D",  new Section("CPSC 210 L2D", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2E",  new Section("CPSC 210 L2E", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2F",  new Section("CPSC 210 L2F", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2G",  new Section("CPSC 210 L2G", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2H",  new Section("CPSC 210 L2H", "CPSC 210", SectionType.LABORATORY,
                "9:00", "11:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2J",  new Section("CPSC 210 L2J", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2K",  new Section("CPSC 210 L2K", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2L",  new Section("CPSC 210 L2L", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2M",  new Section("CPSC 210 L2M", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2N",  new Section("CPSC 210 L2N", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Tue"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2P",  new Section("CPSC 210 L2P", "CPSC 210", SectionType.LABORATORY,
                "15:00", "17:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2R",  new Section("CPSC 210 L2R", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Wed"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2T",  new Section("CPSC 210 L2T", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2U",  new Section("CPSC 210 L2U", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Mon"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2X",  new Section("CPSC 210 L2X", "CPSC 210", SectionType.LABORATORY,
                "11:00", "13:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2Y",  new Section("CPSC 210 L2Y", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Fri"), 2,
                Arrays.asList()));
        data.put("CPSC 210 L2Z",  new Section("CPSC 210 L2Z", "CPSC 210", SectionType.LABORATORY,
                "13:00", "15:00", Arrays.asList("Thu"), 2,
                Arrays.asList()));
        data.put("CPSC 210 202",  new Section("CPSC 210 202", "CPSC 210", SectionType.LECTURE,
                "13:00", "14:00", Arrays.asList("Mon","Wed","Fri"), 2,
                Arrays.asList(Arrays.asList("CPSC 210 L2A","CPSC 210 L2B","CPSC 210 L2D","CPSC 210 L2E","CPSC 210 L2F",
                        "CPSC 210 L2G","CPSC 210 L2H","CPSC 210 L2J","CPSC 210 L2K","CPSC 210 L2M","CPSC 210 L2N",
                        "CPSC 210 L2P","CPSC 210 L2R","CPSC 210 L2T","CPSC 210 L2U","CPSC 210 L2X","CPSC 210 L2Y",
                        "CPSC 210 L2Z"))));
        data.put("CPSC 210 203",  new Section("CPSC 210 203", "CPSC 210", SectionType.LECTURE,
                "15:00", "16:00", Arrays.asList("Mon","Wed","Fri"), 2,
                Arrays.asList(Arrays.asList("CPSC 210 L2A","CPSC 210 L2B","CPSC 210 L2D","CPSC 210 L2E",
                        "CPSC 210 L2F","CPSC 210 L2G","CPSC 210 L2H","CPSC 210 L2J","CPSC 210 L2K","CPSC 210 L2M",
                        "CPSC 210 L2N","CPSC 210 L2P","CPSC 210 L2R","CPSC 210 L2T","CPSC 210 L2U","CPSC 210 L2X",
                        "CPSC 210 L2Y","CPSC 210 L2Z"))));

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
