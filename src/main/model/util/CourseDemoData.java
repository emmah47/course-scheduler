package model.util;

import model.Section;

import java.util.List;

public class CourseDemoData implements CourseData {
    @Override
    public List<Section> getRootSections(String courseID) {
        return null;
    }

    @Override
    public Section getSection(String sectionID) {
        return null;
    }

    @Override
    public List<Section> getSectionIDs(List<String> sectionsIDs) {
        return null;
    }
}
