package model.util;

import model.Section;

import java.util.List;

// Array of methods to load sections from data
public interface CourseData {

    // given a course ID, load all root sections
    List<Section> getRootSections(String courseID);

    // given a section ID, load the section
    Section getSection(String sectionID);

    // given list of section IDs, get sections
    List<Section> getSections(List<String> sectionsIDs);
}


// A UBC course with course id, description, and a list of section ids whos type is a prerequisite to
// other section types.
// For example: CPSC 121 rootSectionIDs will hold CPSC 121 lecture ids, because a lecture must be first chosen
// to determine what labs and tutorials are available. If no such section type exists, then all section IDs of
// the course will be added.
