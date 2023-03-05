package model;

import model.util.CourseData;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// represents a schedule with a name, list of courses, list of scheduled sections, term, preferred weights, and score.
public class Schedule implements Writable {
    private String name;                                 // name of schedule
    private List<Course> courses;                        // list of courses
    private List<String> sectionIDs;                     // list of section IDs (ex. "CPSC 210 101", "CPSC 210 L1Y")
    private int term;                                    // the term that the schedule is for
    private Weight weight;                               // a set of weights that will be used to calculate the score
    private float score;                                 // used for ranking, a higher score is a better schedule
    private Map<String, List<Section>> sortedSections;   // sections sorted into weekdays and further sorted by time
    private CourseData courseData;                       // data for courses and sections used for creating the schedule


    // REQUIRES: courseIDs must all be valid course ids, sectionIDs must all be valid section ids, term must be either
    //           1 or 2
    // EFFECTS: Schedule name is set to name, list of courses to be scheduled is set to courseIDs, list of scheduled
    //          sections is set to sectionIDs, the term of the schedule is set to term, the weights are set to weight,
    //          the score is initialized as 0, the data used for this schedule is set to courseData, sortedSections is
    //          initialized to a hashmap of empty arrays.
    public Schedule(String name,
                    int term,
                    Weight weight,
                    CourseData courseData) {
        this.name = name;
        this.courses = new ArrayList<>();
        this.sectionIDs = new ArrayList<>();
        this.term = term;
        this.weight = weight;
        this.score = 0;
        this.courseData = courseData;

        this.sortedSections = new HashMap<>();
        sortedSections.put("Mon", new ArrayList<>());
        sortedSections.put("Tue", new ArrayList<>());
        sortedSections.put("Wed", new ArrayList<>());
        sortedSections.put("Thu", new ArrayList<>());
        sortedSections.put("Fri", new ArrayList<>());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns all the course ids of the courses in the schedule
    public List<String> getCourseIDs() {
        List<String> courseIDs = new ArrayList<>();
        for (Course course : courses) {
            courseIDs.add(course.getCourseID());
        }
        return courseIDs;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    // MODIFIES: this
    // EFFECTS: given a course id, adds the corresponding course to the schedule
    public void addCoursesByID(String courseID) {
        this.courses.add(courseData.getCourseByID(courseID));
    }

    // MODIFIES: this
    // EFFECTS: given a list of course ids, adds the corresponding courses to the schedule
    public void addCoursesByIDs(List<String> courseIDs) {
        for (String courseID : courseIDs) {
            this.courses.add(courseData.getCourseByID(courseID));
        }
    }


    public List<String> getSectionIDs() {
        return sectionIDs;
    }

    public void addSectionID(String sectionID) {
        this.sectionIDs.add(sectionID);
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

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Map<String, List<Section>> getSortedSections() {
        return sortedSections;
    }

    // EFFECTS: returns a copy of the given schedule
    public Schedule makeCopy() {
        Schedule newSchedule = new Schedule(
                this.name,
                this.term,
                new Weight(this.weight.getCompactWeight(),
                        this.weight.getBalanceWeight(),
                        this.weight.getPreferredStartTime(),
                        this.weight.getPreferredEndTime()
                ),
                this.courseData
        );
        newSchedule.setScore(this.score);
        for (Course course : this.courses) {
            newSchedule.addCourse(course);
        }
        for (String sectionID : this.sectionIDs) {
            newSchedule.addSectionID(sectionID);
        }
        return newSchedule;
    }

    // MODIFIES: this
    // EFFECTS: if it is possible to add the given section to the schedule, it will add the section and return true.
    //          if not, returns false
    public boolean tryAddSection(Section section) {
        if (this.checkConflicts(section)) {
            return false;
        } else {
            this.sectionIDs.add(section.getSectionID());
            return true;
        }
    }

    // EFFECTS: checks if a section has conflicts with the sections already in schedule,
    //          returns false if no conflict is found.
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

    // EFFECTS: returns all the antirequisits section IDs of the sections in the schedule
    public List<List<String>> getAllAntirequisits() {
        List<List<String>> results = new ArrayList<>();
        for (String sectionID : this.getSectionIDs()) {
            results.addAll(courseData.getSection(sectionID).getAntiRequisiteIDs());
        }
        return results;
    }




    // SCORE CALCULATION METHODS BELOW HERE

    // REQUIRES: this.sectionIDs is not empty
    // MODIFIES: this
    // EFFECTS: Calculates the score of the schedule, and assigns that score to the schedule
    public void calculateScore() {
        List<Section> sections = courseData.getSections(sectionIDs);
        for (Section section : sections) {
            for (String day : section.getWeekDays()) {
                sortedSections.get(day).add(section);
            }
        }
        for (List<Section> daySections : sortedSections.values()) {
            Collections.sort(daySections, new Comparator<Section>() {
                @Override
                public int compare(Section o1, Section o2) {
                    return o1.getStartTimeInMinutes() - o2.getStartTimeInMinutes();
                }
            });
        }
        float compactScore = calculateCompactScore() * weight.getCompactWeight();
        float balanceScore = calculateBalanceScore() * weight.getBalanceWeight();
        float overtimeScore = calculateOvertimeScore();
        float finalScore = (compactScore + balanceScore) * overtimeScore;
        setScore(finalScore);
    }

    // REQUIRES: sections in sortedSections are sorted by day and time
    // EFFECTS: calculates a score for how close together the sections are. For example, two back to back courses will
    //          receive a higher score than two courses with a three-hour gap in between.
    private float calculateCompactScore() {
        int maxScore = 60 * 10 * 5; // 60min/hr, * 10hrs a day worst case scenario, * 5 days
        int result = maxScore;
        for (List<Section> sections : sortedSections.values()) {
            for (int i = 0; i < sections.size() - 1; i++) {
                result -= sections.get(i + 1).getStartTimeInMinutes() - sections.get(i).getEndTimeInMinutes();
            }
        }
        return 100 * (float)result / maxScore;
    }

    // REQUIRES: sections in sortedSections are sorted by day and time
    // EFFECTS: calculates how balanced the total hours of class per day is in the week. For example, a schedule that
    //          has 3 hours of class every day will receive a higher score than a schedule that has three days with
    //          7 hours of class and two days with no class.
    private float calculateBalanceScore() {
        int maxScore = 1728;
        int result = maxScore;
        int totalTime = 0;
        Map<String, Integer> totalClassTimePerDay = new HashMap();
        for (String weekday : sortedSections.keySet()) {
            Integer totalMinutes = 0;
            for (Section section : sortedSections.get(weekday)) {
                totalMinutes += section.getEndTimeInMinutes() - section.getStartTimeInMinutes();
            }
            totalClassTimePerDay.put(weekday, totalMinutes);
            totalTime += totalMinutes;
        }
        float averageTime = (float)totalTime / 5;
        for (Integer minutes : totalClassTimePerDay.values()) {
            result -= Math.abs(averageTime - minutes);
        }
        return 100 * (float)result / maxScore;
    }


    // EFFECTS: calculates the overtime score. Schedules that have classes before the set start time or after the set
    //          end time will have a lower score. The result will be a number between 0 and 1, and will be used to
    //          scale the compact and balance scores (so the compact and balance scores will be multiplied by the
    //          overtime score). This is to ensure that the schedules with sections outside of the set start and end
    //          times will have a very low score, but will still show up if there are no other possible better options.
    private float calculateOvertimeScore() {
        int result = 1;
        for (List<Section> sections : sortedSections.values()) {
            for (Section section : sections) {
                if (section.getStartTimeInMinutes() < weight.getPreferredStartTime()) {
                    result += (weight.getPreferredStartTime() - section.getStartTimeInMinutes()) / 30;
                }
                if (section.getEndTimeInMinutes() > weight.getPreferredEndTime()) {
                    result += (section.getEndTimeInMinutes() - weight.getPreferredEndTime()) / 30;
                }
            }
        }
        return 1 / (float)result;
    }





    // PERSISTENCE METHODS BELOW HERE

    // EFFECTS: converts this to a json object
    @Override
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("courses", courseIDsToJson());
        json.put("sectionIDs", sectionIDsToJson());
        json.put("term", term);
        json.put("weight", weight.toJsonObject());
        return json;
    }

    // EFFECTS: converts courseIDs to a json array
    private JSONArray courseIDsToJson() {
        JSONArray json = new JSONArray();
        for (String courseID : this.getCourseIDs()) {
            json.put(courseID);
        }
        return json;
    }

    // EFFECTS: converts sectionIDs to a json array
    private JSONArray sectionIDsToJson() {
        JSONArray json = new JSONArray();
        for (String sectionID : sectionIDs) {
            json.put(sectionID);
        }
        return json;
    }
}
