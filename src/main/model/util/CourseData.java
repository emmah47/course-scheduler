package model.util;

import model.Section;

import java.util.List;

// Array of methods to load sections from data. Data contains a list of Sections.
public interface CourseData {

    // given a course ID, load all root sections
    List<Section> getRootSections(String courseID, int term);

    // given a section ID, load the section
    Section getSection(String sectionID);

    // given list of section IDs, gets sections
    List<Section> getSections(List<String> sectionsIDs);
}


