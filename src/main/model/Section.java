package model;


import model.util.HelperUtil;

import java.util.List;

// Represents a single UBC course section, modeled after sections on UBC's browse courses webpage. An example of a
// section would be CPSC 210 101. The section contains a course ID, a section ID, a SectionType, a start time,
// an end time, the days of the week that the section is held on, the term, and a list of antirequisites.
//
// Antirequisits are the sections that depend on this section. For example, if for CPSC 110 101 lecture you have to
// choose one lab from L01, L02, and L03, and for CPSC 110 102 lecture you have to choose one lab from L03 and L04, then
// CPSC 110 101's antirequisites are L01, L02, and L03, and CPSC 110 102's antirequisites are L03 and L04.
public class Section {
    private String courseID;                         // the course ID (ex "CPSC 210")
    private String sectionID;                        // the section ID (ex "CPSC 201 101")
    private SectionType sectionType;                 // the section's type
    private String startTime;                        // the section's start time (in 24hr time)
    private String endTime;                          // the section's end time (in 24hr time)
    private int startMin;                            // the section's start time in minutes
    private int endMin;                              // the section's end time in minutes
    private List<String> weekDays;                   // the week days that this section is on
    private int term;                                // the term this section is in
    private List<List<String>> antiRequisiteIDs;     // a list of antirequisites. They are split by type, where each
    //                                                  inner list contains the antirequisite section IDs of one type.
    //                                                  A Section with both labs and tutorials as antirequities will
    //                                                  have 2 inner lists. A empty list represents no antirequisites.


    // REQUIRES: sectionID is a valid section id, courseID is a valid course id, startTime and endTime are in the form
    // of "hr:mins" (ex. "12:30") and are in 24hr time, the week days inside weekDays are the first three letters of
    // the weekday with the first letter capitalized (ex "Wed"), term is either 1 or 2, antirequisite IDs contain valid
    // section ids.
    // EFFECTS: creates a Section that has a unique sectionID, a course ID, the type of the section, a start and end
    //          time as a string, a start and end time that is converted to minutes, a list of weekdays that the
    //          section is held on, the term the section is in, and a list of its antirequisites
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

    // EFFECTS: checks if two sections has conflict, (if their times overlap each other or not)
    public boolean hasConflictWith(Section section) {
        if (HelperUtil.hasSameMember(this.weekDays, section.getWeekDays())) {
            return ((this.getStartTimeInMinutes() <= section.getStartTimeInMinutes()
                    && section.getStartTimeInMinutes() < this.getEndTimeInMinutes())
                    || (this.getStartTimeInMinutes() < section.getEndTimeInMinutes()
                    && section.getEndTimeInMinutes() <= this.getEndTimeInMinutes())

                    || (section.getStartTimeInMinutes() <= this.getStartTimeInMinutes()
                    && this.getStartTimeInMinutes() < section.getEndTimeInMinutes()));
        } else {
            return false;
        }
    }

}
