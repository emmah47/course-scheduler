package model.util;

import model.Course;
import model.Section;


import java.util.List;

// Array of methods to load sections from data. Data contains a list of Courses and a list of Sections.
public interface CourseData {

    // given a course ID, load all root sections
    List<Section> getRootSections(String courseID, int term);

    // given a section ID, load the section
    Section getSection(String sectionID);

    // given list of section IDs, gets sections
    List<Section> getSections(List<String> sectionsIDs);

    // gets a course given id
    Course getCourseByID(String courseID);

    // returns a list of all courses
    List<Course> getAllCourse();

    // returns a list of all course IDs
    List<String> getAllCourseIDs();
}


