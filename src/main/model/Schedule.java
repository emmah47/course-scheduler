package model;

import model.util.CourseData;

import java.util.ArrayList;
import java.util.List;

// A schedule of scheduled sections. Contains all data for a single schedule.
public class Schedule {
    private String name;
    private List<String> courseIDs;
    private List<String> sectionIDs;
    private Weight weight;
    private float score;
    private CourseData courseData;


    // Constructor
    public Schedule(String name,
                    List<String> courseIDs,
                    List<String> sectionIDs,
                    Weight weight,
                    CourseData courseData) {
        this.name = name;
        this.courseIDs = courseIDs;
        this.sectionIDs = sectionIDs;
        this.weight = weight;
        this.score = 0;
        this.courseData = courseData;
    }


    // creates a clone of the current schedule
    public Schedule clone() {
        Schedule newSchedule = new Schedule(
                this.name,
                new ArrayList<>(this.courseIDs),
                new ArrayList<>(this.sectionIDs),
                new Weight(this.weight.getCompactWeight(),
                        this.weight.getBalanceWeight(),
                        this.weight.getPreferredStartTime(),
                        this.weight.getPreferredEndTime()),
                this.courseData
        );

        newSchedule.setScore(this.score);
        return newSchedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourseIDs() {
        return courseIDs;
    }

    public void setCourseIDs(List<String> courseIDs) {
        this.courseIDs = courseIDs;
    }

    public List<String> getSectionIDs() {
        return sectionIDs;
    }

    public void setSectionIDs(List<String> sectionIDs) {
        this.sectionIDs = sectionIDs;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public CourseData getCourseData() {
        return courseData;
    }

    public void setCourseData(CourseData courseData) {
        this.courseData = courseData;
    }

    // tries to add section to schedule if possible, return true if added and false if not
    public boolean tryAddSection(Section section) {
        if (this.checkConflicts(section)) {
            return false;
        } else {
            this.sectionIDs.add(section.getSectionID());
            return true;
        }
    }

    // checks if a section has conflicts with the sections already in schedule, returns false if no conflict is found.
    private boolean checkConflicts(Section section) {
        boolean hasConflict = false;
        for (String scheduleSectionsID : sectionIDs) {
            Section scheduleSection = courseData.getSection(scheduleSectionsID);
            if (scheduleSection.hasConflictWith(section)) {
                hasConflict = true;
                break;
            }
        }
        return hasConflict;
    }

    @Override
    public String toString() {
        return "Schedule{"
                + "sectionIDs=" + sectionIDs
                + '}';
    }
}
