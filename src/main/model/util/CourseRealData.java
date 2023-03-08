package model.util;

import model.Course;
import model.Section;
import model.SectionType;
import persistence.JsonReaderCourses;
import persistence.JsonReaderSections;

import java.io.IOException;
import java.util.*;

// Data used for demo purposes. Contains 8 courses with a few sections from each course. Include methods for loading
// the data.
public class CourseRealData implements CourseData {
    private Map<String, Section> sections = new LinkedHashMap<>(); // A hashmap of Sections, with the value being a
    // section, and the key being the corresponding course ID.
    private Map<String, Course> courses = new LinkedHashMap<>(); // A hashmap of course IDs and their description

    // EFFECTS: Creates a new CourseRealData with a populated data field.
    public CourseRealData(String sourceCourses, String sourceSections) {
        JsonReaderCourses readerCourse = new JsonReaderCourses(sourceCourses);
        JsonReaderSections readerSection = new JsonReaderSections(sourceSections);
        try {
            List<Course> courses = readerCourse.readCourses();
            List<Section> sections = readerSection.readSections();
            setCoursesFromList(courses);
            setSectionsFromList(sections);
        } catch (IOException e) {
            setCoursesFromList(new ArrayList<>());
            setSectionsFromList(new ArrayList<>());
        }


    }

    // REQUIRES: given String is a valid course ID
    // EFFECTS: returns root sections given a course ID. It returns all sections of a course that has antirequisits.
    //          If there are none, it will return all lectures of the section.
    @Override
    public List<Section> getRootSections(String courseID, int term) {
        List<Section> result = new ArrayList<>();
        for (Section section : sections.values()) {
            if (courseID.equals(section.getCourseID()) && section.getSectionType() == SectionType.LECTURE
                    && section.getTerm() == term) {
                result.add(section);
            }
        }

        return result;
    }


    // REQUIRES: given string is a valid Section ID.
    // EFFECTS: returns a Section corresponding to the given Section ID.
    @Override
    public Section getSection(String sectionID) {
        Section result = sections.get(sectionID);
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

    // EFFECTS: returns a list of all courses
    @Override
    public List<Course> getAllCourse() {
        return new ArrayList<>(courses.values());
    }

    // EFFECTS: returns a list of all course IDs
    @Override
    public List<String> getAllCourseIDs() {
        return new ArrayList<>(courses.keySet());
    }

    // MODIFIES: this
    // EFFECTS: given a list of courses, maps them to their IDs and sets them
    private void setCoursesFromList(List<Course> courses) {
        for (Course course : courses) {
            this.courses.put(course.getCourseID(), course);
        }
    }

    // MODIFIES: this
    // EFFECTS: given a list of sections, maps them to their IDs and sets them
    private void setSectionsFromList(List<Section> sections) {
        for (Section section : sections) {
            this.sections.put(section.getSectionID(), section);
        }
    }

    // EFFECTS: returns a course given the course ID
    @Override
    public Course getCourseByID(String courseID) {
        return courses.get(courseID);
    }


}

