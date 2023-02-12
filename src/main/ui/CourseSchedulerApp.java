package ui;

import model.Schedule;
import model.Section;
import model.Weight;
import model.util.CourseRealData;
import model.util.HelperUtil;
import model.util.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CourseSchedulerApp {
    private static final List<String> WEEKDAYS = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri");

    private Scanner input;
    // Show list of course selection DONE
    // Select Courses DONE
    // Select term DONE
    // Select required sections DONE
    // set weights DONE
    // select num of courses to be displayed DONE
    // schedule the courses


    CourseSchedulerApp() {
        input = new Scanner(System.in);
    }

    private void initDemoSchedule(Schedule schedule) {
        List<String> courseIDs = Arrays.asList("MATH 101");
        schedule.setName("Demo Schedule");
        schedule.setCourseIDs(courseIDs);
        schedule.setTerm(2);
        schedule.setWeight(new Weight(1, 1, "8:00", "16:00"));
    }

    // EFFECTS: runs the Course Scheduler application
    public void runSchedulerApp(Schedule schedule) {
        displayCourseSelection(schedule);
        int numOfSchedule = 3;
        if (selectCourses(schedule).equals("demo")) {
            initDemoSchedule(schedule);
        } else {
            selectTerm(schedule);
            selectRequiredSections(schedule);
            setWeights(schedule);
            numOfSchedule = selectNumOfDisplayedSchedule();
        }
        List<Schedule> result = Scheduler.scheduleAndCalculateScore(schedule);
        System.out.println("Your top " + numOfSchedule + " schedules:");
        int i = 0;
        while (i < Math.max(numOfSchedule, result.size())) {
            System.out.println(displaySchedule(result.get(i)));
            i++;
        }
        System.out.println("Number of possible schedules: " + result.size());
    }

    // EFFECTS: prints out the course IDs and their corresponding descriptions of all courses in the course data
    private void displayCourseSelection(Schedule schedule) {
        List<String> courseIDs = schedule.getCourseData().getAllCourseIDs();
        System.out.println("Please select a course from the following:");
        for (String courseID : courseIDs) {
            System.out.println(courseID + ": " + schedule.getCourseData().getCourseDescription(courseID));
        }
    }

    // EFFECTS: prompts user to select courses, and then adds them to the schedule
    private String selectCourses(Schedule schedule) {
        List<String> courseIDs = new ArrayList<>();
        String selection = "";
        System.out.println("\n Enter your courses one at a time by course id, pressing enter each time.");
        System.out.println("Enter \"done\" when done.");
        System.out.println("Or enter \"demo\" to show demo schedule.");
        while (true) {
            selection = input.nextLine();
            if (selection.equals("done")) {
                break;
            }
            if (selection.equals("demo")) {
                return "demo";
            }
            courseIDs.add(selection);
            schedule.setCourseIDs(courseIDs);
        }
        System.out.println("Your selected courses are: " + schedule.getCourseIDs() + "\n|");
        return selection;
    }

    // prompts user to select a term, sets that term to the schedule
    private void selectTerm(Schedule schedule) {
        System.out.println("Please select a term. Choose either 1 or 2:");
        int selection = Integer.parseInt(input.next());
        schedule.setTerm(selection);
    }

    // prompts user to select sections that they want in the schedule
    private void selectRequiredSections(Schedule schedule) {
        System.out.println("Please select any sections that must be included in the schedule, and enter their id.");
        System.out.println("You are only allowed to select lectures.");
        System.out.println("Please enter the ids in the form of \"CPSC 210 101\". enter \"done\" when finished.");
        System.out.println("If there are no sections, enter \"none\"");
        List<String> sectionIDs = new ArrayList<>();
        while (true) {
            String selection = input.nextLine();
            if (selection.equals("done")) {
                break;
            } else if (selection.equals("none")) {
                break;
            } else {
                if (!selection.equals("")) {
                    sectionIDs.add(selection);
                }
                schedule.setSectionIDs(sectionIDs);
            }
        }
        System.out.println("Your selected sections are: " + schedule.getSectionIDs() + "\n|");
    }

    private void setWeights(Schedule schedule) {
        System.out.println("Please set weights.");
        System.out.println("The compact weight determines how close together the classes are.");
        System.out.println(
                "The balance weight determines how balanced the days are in terms of hours of class per day");
        System.out.println("The importance of these two weights are based on their ratio:");
        System.out.println("For example, compact is 2 and balance is 1, then the compactness of the schedule ");
        System.out.println("will be twice as important than the balance of the schedule. \n");

        System.out.println("Please enter an integer for the compact weight:");
        schedule.getWeight().setCompactWeight(Integer.parseInt(input.next()));

        System.out.println("Please enter an integer for the balance weight:");
        schedule.getWeight().setBalanceWeight(Integer.parseInt(input.next()));

        System.out.println("Please enter an preferred start time in the form of hr:min in 24hr time:");
        schedule.getWeight().setPreferredStartTime(HelperUtil.calculateMinutes(input.next()));

        System.out.println("Please enter an preferred end time in the form of hr:min in 24hr time:");
        schedule.getWeight().setPreferredEndTime(HelperUtil.calculateMinutes(input.next()));
    }

    private int selectNumOfDisplayedSchedule() {
        System.out.println("Please select the number of courses to be displayed:");
        return Integer.parseInt(input.next());
    }

    private String getCellString(String value) {
        String result = String.format("%-12s\t", " " + value);
        return result;
    }

    private String getRowSeparator() {
        return "----------------+---------------+---------------+---------------+--------------\n";
    }

    private String getRowString(List<String> values) {
        String result = "";
        for (int i = 0; i < values.size(); i++) {
            result += getCellString(values.get(i));
            if (i < values.size() - 1) {
                result += "|";
            }
        }
        result += "\n";
        return result;
    }

    private String getHeader() {
        String result = getRowSeparator();
        result += getRowString(WEEKDAYS);
        result += getRowSeparator();
        return result;
    }

    private int getMaxRowNumber(Schedule schedule) {
        int maxRow = 0;
        for (String weekday : WEEKDAYS) {
            if (schedule.getSortedSections().get(weekday).size() > maxRow) {
                maxRow = schedule.getSortedSections().get(weekday).size();
            }
        }
        return maxRow;

    }

    // EFFECTS: returns a string that contains every section in the schedule and their corresponding weekdays and
    //          start and end times.
    public String displaySchedule(Schedule schedule) {
        String result = getHeader();
        int maxRow = getMaxRowNumber(schedule);

        for (int row = 0; row < maxRow; row++) {
            for (int innerRow = 0; innerRow < 3; innerRow++) {
                if (!(row == maxRow - 1 && innerRow == 2)) {
                    List<String> rowValues = new ArrayList<>();
                    for (String weekday : WEEKDAYS) {
                        List<Section> sections = schedule.getSortedSections().get(weekday);
                        if (sections.size() > row) {
                            rowValues.add(
                                    innerRow == 0 ? (sections.get(row).getSectionID()) :
                                            (innerRow == 1 ? (String.format("%5s-%5s", sections.get(row).getStartTime(),
                                                    sections.get(row).getEndTime())) : " "));
                        } else {
                            rowValues.add(" ");
                        }
                    }
                    result += getRowString(rowValues);
                }
            }
        }
        result += getRowSeparator();
        return result;
    }


}
