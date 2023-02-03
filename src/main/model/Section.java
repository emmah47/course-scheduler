package model;


import model.util.HelperUtil;

import java.util.List;

// Represents all data for a single section.
public class Section {
    private String courseID;
    private String sectionID;
    private SectionType sectionType;
    private String startTime;
    private String endTime;
    private int startMin;
    private int endMin;
    private List<String> weekDays;
    private int term;
    private List<List<String>> antiRequisiteIDs;


    // EFFECTS: Constructor for Section
    public Section(String sectionID,
                   String courseID,
                   SectionType sectionType,
                   String startTime,
                   String endTime,
                   List<String> weekDays,
                   int term,
                   List<List<String>> antiRequisiteIDs) {
        this.sectionID = sectionID;
        this.sectionType = sectionType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startMin = HelperUtil.calculateMinutes(startTime);
        this.endMin = HelperUtil.calculateMinutes(endTime);
        this.weekDays = weekDays;
        this.term = term;
        this.courseID = courseID;
        this.antiRequisiteIDs = antiRequisiteIDs;
    }

    public String getSectionID() {
        return sectionID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays) {
        this.weekDays = weekDays;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getStartTimeInMinutes() {
        return HelperUtil.calculateMinutes(startTime);
    }

    public int getEndTimeInMinutes() {
        return HelperUtil.calculateMinutes(endTime);
    }

    public List<List<String>> getAntiRequisiteIDs() {
        return antiRequisiteIDs;
    }

    public void setAntiRequisiteIDs(List<List<String>> antiRequisiteIDs) {
        this.antiRequisiteIDs = antiRequisiteIDs;
    }

    // checks if two sections overlap (has conflict)
    public boolean hasConflictWith(Section section) {
        if (HelperUtil.hasSameMember(this.weekDays, section.getWeekDays())) {
            return ((this.getStartTimeInMinutes() <= section.getStartTimeInMinutes()
                    && section.getStartTimeInMinutes() < this.getEndTimeInMinutes())
                    || (this.getStartTimeInMinutes() < section.getEndTimeInMinutes()
                    && section.getEndTimeInMinutes() <= this.getEndTimeInMinutes())

                    || (section.getStartTimeInMinutes() <= this.getStartTimeInMinutes()
                    && this.getStartTimeInMinutes() < section.getEndTimeInMinutes())
                    || (section.getStartTimeInMinutes() < this.getEndTimeInMinutes()
                    && this.getEndTimeInMinutes() <= section.getEndTimeInMinutes()));
        } else {
            return false;
        }
    }

}
