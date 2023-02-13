package ui;

import model.Course;
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

// Course Scheduler App
public class CourseSchedulerApp {
    private static final List<String> WEEKDAYS = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri"); // List of weekdays
    private Scanner input;


    // EFFECTS: creates a Course scheduler app and then runs it
    CourseSchedulerApp() {
        Schedule schedule = new Schedule("name", new ArrayList<>(), new ArrayList<>(), 2,
                new Weight(1, 1, 1, 1),
                new CourseRealData());
        input = new Scanner(System.in);
        runSchedulerApp(schedule);
    }

    // MODIFIES: schedule
    // EFFECTS: initializes a demo schedule
    private void initDemoSchedule(Schedule schedule) {
        List<String> courseIDs = Arrays.asList("CPSC 110", "CPSC 121", "CPSC 210", "BIOL 111");
        schedule.setName("Demo Schedule");
        schedule.setCourseIDs(courseIDs);
        schedule.setTerm(2);
        schedule.setWeight(new Weight(1, 1, "8:00", "16:00"));
    }

    // MODIFIES: schedule
    // EFFECTS: runs the Course Scheduler application. Displays a demo schedule, or prompts user to set course IDs,
    //          term, weights, and choose the number of schedules to be displayed.
    private void runSchedulerApp(Schedule schedule) {
        displayCourseSelection(schedule);
        int numOfSchedule = 3;
        if (selectCourses(schedule).equals("demo")) {
            initDemoSchedule(schedule);
        } else {
            selectTerm(schedule);
            setWeights(schedule);
            numOfSchedule = selectNumOfDisplayedSchedule();
        }
        List<Schedule> result = Scheduler.scheduleAndCalculateScore(schedule);

        int i = 0;
        while (i < Math.min(numOfSchedule, result.size())) {
            printScheduleDetails(schedule);
            System.out.println(String.format("Number %d result:", i + 1));
            System.out.println(displaySchedule(result.get(i)));
            i++;
        }
        System.out.println("Number of possible schedules: " + result.size());
    }


    // EFFECTS: prints the selected courses, term, preferred time, and weights of the given schedule
    private void printScheduleDetails(Schedule schedule) {
        System.out.println(String.format(
                "Selected courses: %s, term: %s, preferred time: %s-%s, compact weight:%s, balance weight:%s",
                schedule.getCourseIDs(),
                schedule.getTerm(),
                HelperUtil.minutesToTime(schedule.getWeight().getPreferredStartTime()),
                HelperUtil.minutesToTime(schedule.getWeight().getPreferredEndTime()),
                schedule.getWeight().getCompactWeight(),
                schedule.getWeight().getBalanceWeight()
        ));
    }

    // EFFECTS: prints out the course IDs and their corresponding descriptions of all courses in the course data
    private void displayCourseSelection(Schedule schedule) {
        System.out.println("Step1: "
                + "Please select a course from the following:");
        for (Course course : schedule.getCourseData().getAllCourse()) {
            System.out.println("\t" + course.getCourseID()
                    + ": " + course.getDescription());
        }
        System.out.println("Enter your courses one at a time by course id, pressing enter each time.");
        System.out.println("Or enter \"demo\" to show the demo schedule.");
        System.out.println("Enter \"done\" when done.");
    }

    // MODIFIES: schedule
    // EFFECTS: prompts user to select courses, and then adds them to the schedule
    private String selectCourses(Schedule schedule) {
        List<String> courseIDs = schedule.getCourseIDs();
        String selection = "";
        while (true) {
            selection = input.nextLine();
            if (selection.equals("done")) {
                break;
            }
            if (selection.equals("demo")) {
                return "demo";
            }
            if (schedule.getCourseData().getAllCourseIDs().contains(selection)) {
                if (courseIDs.contains(selection)) {
                    System.out.println(String.format("\"%s\" is already selected", selection));
                } else {
                    courseIDs.add(selection);
                }
            } else {
                System.out.println(String.format("\"%s\" is invalid course id", selection));
            }

        }
        System.out.println("Your selected courses are: " + schedule.getCourseIDs() + "\n");
        return selection;
    }

    // MODIFIES: schedule
    // prompts user to select a term, sets that term to the schedule
    private void selectTerm(Schedule schedule) {
        System.out.println("Step 2: Please select a term. Choose either 1 or 2:");
        while (true) {
            String selection = input.nextLine();
            if (Arrays.asList("1", "2").contains(selection)) {
                schedule.setTerm(Integer.parseInt(selection));
                break;
            } else {
                System.out.println(String.format("\"%s\" is invalid term, Choose either 1 or 2: ", selection));
            }
        }

    }

    // MODIFIES: schedule
    // EFFECTS: displays information about the weights and prompts user to set them
    private void setWeights(Schedule schedule) {
        System.out.println("Step 3: Please set weights based on following rules:");
        System.out.println("\tThe compact weight determines how close together the classes are.");
        System.out.println(
                "\tThe balance weight determines how balanced the days are in terms of hours of class per day");
        System.out.println("\tThe importance of these two weights are based on their ratio:");
        System.out.println("\tFor example: compact is 2 and balance is 1, then the compactness of the schedule ");
        System.out.println("\t             will be twice as important than the balance of the schedule. \n");

        inputCompactWeight(schedule);
        inputBalanceWeight(schedule);
        inputStartTime(schedule);
        inputEndTime(schedule);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the compact weight, and sets it
    private void inputCompactWeight(Schedule schedule) {
        System.out.println("Please enter an integer for the compact weight:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int weight = Integer.parseInt(inputStr);
                schedule.getWeight().setCompactWeight(weight);
                break;
            } catch (NumberFormatException nfe) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an integer for the compact weight: ",
                        inputStr));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the balance weight, and sets it
    private void inputBalanceWeight(Schedule schedule) {
        System.out.println("Please enter an integer for the balance weight:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int weight = Integer.parseInt(inputStr);
                schedule.getWeight().setBalanceWeight(weight);
                break;
            } catch (NumberFormatException nfe) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an integer for the balance weight: ",
                        inputStr));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the preferred start time, and sets it
    private void inputStartTime(Schedule schedule) {
        System.out.println("Please enter an preferred start time in the form of hh:mm in 24hr time:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int startMinutes = HelperUtil.calculateMinutes(inputStr);
                schedule.getWeight().setPreferredStartTime(startMinutes);
                break;
            } catch (Exception e) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an preferred start time in the form of hh:mm in 24hr time: ",
                        inputStr));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the preferred end time, and sets it
    private void inputEndTime(Schedule schedule) {
        System.out.println("Please enter an preferred end time in the form of hh:mm in 24hr time:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int endMinutes = HelperUtil.calculateMinutes(inputStr);
                schedule.getWeight().setPreferredEndTime(endMinutes);
                break;
            } catch (Exception e) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an preferred end time in the form of hh:mm in 24hr time: ",
                        inputStr));
            }
        }
    }

    // Prompts user to set the number of courses to be displayed
    private int selectNumOfDisplayedSchedule() {
        System.out.println("Please select the number of courses to be displayed:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                return Integer.parseInt(inputStr);
            } catch (NumberFormatException nfe) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please select the number of courses to be displayed:",
                        inputStr));
            }
        }
    }

    // MODIFIES: value
    // EFFECTS: adds whitespace onto the end of strings so that they are the length of one cell in the display table
    private String getCellString(String value) {
        String result = String.format("%-12s\t", " " + value);
        return result;
    }

    // EFFECTS: returns a row separator for the table
    private String getRowSeparator() {
        return "----------------+---------------+---------------+---------------+--------------\n";
    }

    // MODIFIES: values
    // EFFECTS: formats and returns one row of the table
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

    // EFFECTS: returns the header for the table
    private String getHeader() {
        String result = getRowSeparator();
        result += getRowString(WEEKDAYS);
        result += getRowSeparator();
        return result;
    }

    // EFFECTS: returns the maximum number of sections in one day. Used to determine the number of rows in the table
    private int getMaxRowNumber(Schedule schedule) {
        int maxRow = 0;
        for (String weekday : WEEKDAYS) {
            if (schedule.getSortedSections().get(weekday).size() > maxRow) {
                maxRow = schedule.getSortedSections().get(weekday).size();
            }
        }
        return maxRow;

    }

    // EFFECTS: Displays the given schedule in the form of a table, with the courseIDs and the corresponding days and
    // times
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
