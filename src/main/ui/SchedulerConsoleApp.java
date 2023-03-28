package ui;


import model.*;
import model.exceptions.InvalidTimeException;
import model.util.CourseRealData;
import model.util.HelperUtil;

import model.util.Scheduler;

import java.io.IOException;
import java.util.*;

// A class that represents the course scheduler console app
public class SchedulerConsoleApp extends SchedulerApp {
    private Scanner input;

    // constructor
    SchedulerConsoleApp() {
        super();
        input = new Scanner(System.in);
    }


    // EFFECTS: runs the course scheduler gui app
    public void run() {
        displayMainMenu();
    }


    // EFFECTS: displays the main menu
    private void displayMainMenu() {
        printMainMenu();
        String selection;
        while (true) {
            selection = input.nextLine();
            try {
                if (selection.equals("0") || selection.equals("1") || selection.equals("2")) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter either 0, 1, or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter either 0, 1, or 2.");
            }
        }
        if (selection.equals("1")) {
            displayPreferenceSettingMenu();
        } else if (selection.equals("2")) {
            tryDisplaySavedSchedulesMenu();
        } else {
            displaySavePreferencesMenu();
        }
    }

    // EFFECTS: prints the main menu
    private void printMainMenu() {
        System.out.println("Please select from one of the following:");
        System.out.println("0 - Quit");
        System.out.println("1 - Create Schedule");
        System.out.println("2 - Load Saved Schedule");
    }

    // EFFECTS: checks if there are any saved schedules and only displays them if there are
    private void tryDisplaySavedSchedulesMenu() {
        if (savedSchedules.size() == 0) {
            System.out.println("You don't have any saved schedules.");
            displayMainMenu();
        } else {
            displaySavedSchedulesMenu();
        }
    }

    // EFFECTS: displays saved schedules menu
    private void displaySavedSchedulesMenu() {
        printSavedScheduleMenu(savedSchedules);
        System.out.println("Please select a number to view the corresponding schedule:");
        String selection;
        while (true) {
            selection = input.nextLine();
            try {
                if (Integer.parseInt(selection) > savedSchedules.size()
                        || Integer.parseInt(selection) < 0) {
                    System.out.println("That schedule does not exist");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        if (selection.equals("0")) {
            displayMainMenu();
        } else {
            displaySavedSchedule(savedSchedules.get(Integer.parseInt(selection) - 1));
        }
    }


    // EFFECTS: displays menu with all the names of the saved schedules
    private void printSavedScheduleMenu(List<Schedule> savedSchedules) {
        System.out.println("Saved schedules:");
        System.out.println("0 - Back");
        for (int i = 0; i < savedSchedules.size(); i++) {
            System.out.println(String.format("%d - %s", i + 1, savedSchedules.get(i).getName()));
        }
    }


    // EFFECTS: gets and displays a schedule from save menu
    private void displaySavedSchedule(Schedule s) {
        printDisplaySavedScheduleHelper(s);
        System.out.println("0 - back");
        System.out.println("1 - delete current schedule");
        String selection;
        while (true) {
            selection = input.nextLine();
            try {
                if (selection.equals("0") || selection.equals("1")) {
                    break;
                } else {
                    System.out.println("Please choose either 0 or 1");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        if (selection.equals("0")) {
            displaySavedSchedulesMenu();
        } else {
            deleteSchedule(s);
            System.out.println("Schedule has been deleted!");
            tryDisplaySavedSchedulesMenu();
        }
    }

    // EFFECTS: layouts and prints out the saved schedule
    private void printDisplaySavedScheduleHelper(Schedule s) {
        System.out.println("Schedule: " + s.getName());
        printScheduleDetails(s);
        System.out.print(displaySchedule(s));
    }


    // EFFECTS: displays the preference setting menu
    private void displayPreferenceSettingMenu() {
        printPreferenceSettingMenu();
        System.out.println("Would you like to change these settings? Enter \"yes\", \"no\", or \"load\""
                + " to load previously saved preferences.");
        String selection;
        while (true) {
            selection = input.nextLine();
            if (selection.equals("yes") || selection.equals("no") || selection.equals("load")) {
                break;
            } else {
                System.out.println(String.format(selection + " is invalid."));
            }
        }
        if (selection.equals("yes")) {
            setWeights();
        } else if (selection.equals("no")) {
            displayCourseSelectionMenu();
        } else {
            loadAndSetWeights();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the saved weights
    private void loadAndSetWeights() {
        System.out.println("loading weights...");
        try {
            this.preferredWeights = jsonReaderPreferences.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_WEIGHT);
        } finally {
            displayPreferenceSettingMenu();
        }
    }

    // EFFECTS: displays current preferences
    private void printPreferenceSettingMenu() {
        System.out.println("Your current preference settings are:");
        System.out.println("Compact weight: " + preferredWeights.getCompactWeight());
        System.out.println("Balance weight: " + preferredWeights.getBalanceWeight());
        System.out.println("Preferred start time: "
                + HelperUtil.minutesToTime(preferredWeights.getPreferredStartTime()));
        System.out.println("Preferred end time: "
                + HelperUtil.minutesToTime(preferredWeights.getPreferredEndTime()));

    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the weights, and sets them
    private void setWeights() {
        System.out.println("Please set weights based on following rules:");
        System.out.println("\tThe compact weight determines how close together the classes are.");
        System.out.println(
                "\tThe balance weight determines how balanced the days are in terms of hours of class per day");
        System.out.println("\tThe importance of these two weights are based on their ratio:");
        System.out.println("\tFor example: compact is 2 and balance is 1, then the compactness of the schedule ");
        System.out.println("\t             will be twice as important than the balance of the schedule. \n");

        inputCompactWeight();
        inputBalanceWeight();
        inputStartTime();
        inputEndTime();

        displayPreferenceSettingMenu();
    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the compact weight, and sets it
    private void inputCompactWeight() {
        System.out.println("Please enter an integer for the compact weight:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int weight = Integer.parseInt(inputStr);
                preferredWeights.setCompactWeight(weight);
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
    private void inputBalanceWeight() {
        System.out.println("Please enter an integer for the balance weight:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int weight = Integer.parseInt(inputStr);
                preferredWeights.setBalanceWeight(weight);
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
    private void inputStartTime() {
        System.out.println("Please enter an preferred start time in the form of hh:mm in 24hr time:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int startMinutes = HelperUtil.calculateMinutes(inputStr);
                preferredWeights.setPreferredStartTime(startMinutes);
                break;
            } catch (InvalidTimeException ite) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an preferred end time in the form of hh:mm in 24hr time: ",
                        inputStr));
            } catch (Exception e) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an preferred start time in the form of hh:mm in 24hr time: ",
                        inputStr));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to set the preferred end time, and sets it
    private void inputEndTime() {
        System.out.println("Please enter an preferred end time in the form of hh:mm in 24hr time:");
        while (true) {
            String inputStr = input.nextLine();
            try {
                int endMinutes = HelperUtil.calculateMinutes(inputStr);
                preferredWeights.setPreferredEndTime(endMinutes);
                break;
            } catch (InvalidTimeException ite) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an preferred end time in the form of hh:mm in 24hr time: ",
                        inputStr));
            } catch (Exception e) {
                System.out.println(String.format(
                        "\"%s\" is invalid, Please enter an preferred end time in the form of hh:mm in 24hr time: ",
                        inputStr));
            }
        }
    }

    // EFFECTS: displays the course selection menu
    private void displayCourseSelectionMenu() {
        Schedule schedule = new Schedule("default name", 1,
                preferredWeights, new CourseRealData("./data/courses.json", "./data/sections.json"));
        displayCourseSelection(schedule);
    }

    // EFFECTS: prints out the course IDs and their corresponding descriptions of all courses in the course data
    private void displayCourseSelection(Schedule schedule) {
        System.out.println("Please select a course from the following:");
        for (Course course : schedule.getCourseData().getAllCourse()) {
            System.out.println("\t" + course.getCourseID()
                    + ": " + course.getDescription());
        }
        System.out.println("Enter your courses one at a time by course id, pressing enter each time.");
        System.out.println("Enter \"done\" when done.");
        selectCourses(schedule);
    }

    // MODIFIES: schedule
    // EFFECTS: prompts user to select courses, and then adds them to the schedule
    private void selectCourses(Schedule schedule) {
        List<String> courseIDs = schedule.getCourseIDs();
        while (true) {
            String selection = input.nextLine();
            if (selection.equals("done")) {
                break;
            }
            if (schedule.getCourseData().getAllCourseIDs().contains(selection)) {
                if (courseIDs.contains(selection)) {
                    System.out.println(String.format("\"%s\" is already selected", selection));
                } else {
                    schedule.addCoursesByID(selection);
                }
            } else {
                System.out.println(String.format("\"%s\" is invalid course id", selection));
            }

        }
        System.out.println("Your selected courses are: " + schedule.getCourseIDs() + "\n");
        selectTerm(schedule);
    }


    // MODIFIES: schedule
    // prompts user to select a term, sets that term to the schedule
    private void selectTerm(Schedule schedule) {
        System.out.println("Please select a term. Choose either 1 or 2:");
        String selection;
        while (true) {
            selection = input.nextLine();
            if (selection.equals("1") || selection.equals("2")) {
                schedule.setTerm(Integer.parseInt(selection));
                break;
            } else {
                System.out.println(String.format("\"%s\" is an invalid term, Choose either 1 or 2: ", selection));
            }
        }
        calculateAndPrintSchedules(schedule);
    }


    // EFFECTS: calculates the schedules and displays them
    private void calculateAndPrintSchedules(Schedule schedule) {
        int numOfSchedule = selectNumOfDisplayedSchedule();
        List<Schedule> result = Scheduler.scheduleAndCalculateScore(schedule);
        int i = 0;
        List<Schedule> topResults = new ArrayList<>();
        while (i < Math.min(numOfSchedule, result.size())) {
            printScheduleDetails(schedule);
            System.out.println(String.format("Number %d result:", i + 1));
            System.out.println(displaySchedule(result.get(i)));
            topResults.add(result.get(i));
            i++;
        }
        System.out.println("Number of possible schedules: " + result.size());
        displaySaveMenu(topResults);
    }


    // EFFECTS: displays the save schedule menu
    private void displaySaveMenu(List<Schedule> schedules) {
        System.out.println("Would you like to save your schedules? Enter \"yes\" or \"no\".");
        String selection;
        while (true) {
            selection = input.nextLine();
            if (selection.equals("yes") || selection.equals("no")) {
                break;
            } else {
                System.out.println(String.format("\"%s\" is invalid, choose either \"yes\" or \"no\"", selection));
            }
        }
        if (selection.equals("no")) {
            displayMainMenu();
        } else {
            Schedule scheduleToBeSaved = selectScheduleToSave(schedules);
            nameSchedule(scheduleToBeSaved);
        }
    }

    // EFFECTS: prompts user to select the schedule they want to save
    private Schedule selectScheduleToSave(List<Schedule> schedules) {
        System.out.println("Please enter the number of the schedule you want to save.");
        String selection;
        while (true) {
            selection = input.nextLine();
            try {
                if (Integer.parseInt(selection) <= schedules.size() && Integer.parseInt(selection) > 0) {
                    break;
                } else {
                    System.out.println(String.format("Schedule number %s does not exist.", selection));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        return schedules.get(Integer.parseInt(selection) - 1);
    }


    // EFFECTS: assigns a name to group of schedules, and each individual schedule is names "name_1", "name_2"...
    private void nameSchedule(Schedule schedule) {
        System.out.println("Please enter a name for your schedule.");
        String name = input.nextLine();
        if (savedSchedules.stream().anyMatch(s -> s.getName().equals(name))) {
            System.out.println("A schedule with the name \"" + name + "\" already exists."
                              + " Please choose a different name.");
            nameSchedule(schedule);
        } else {
            schedule.setName(name);
            System.out.println("Saving the schedule...");
            saveSchedule(schedule);
            System.out.println("Your schedule, " + name + ", has been saved!");
            displayMainMenu();
        }
    }



    // EFFECTS: Prompts user to set the number of courses to be displayed
    private int selectNumOfDisplayedSchedule() {
        System.out.println("Please select the number of schedules to be displayed:");
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

    // EFFECTS: displays the save preferences menu
    private void displaySavePreferencesMenu() {
        System.out.println("Would you like to save your current preferences? Enter \"yes\" or \"no\".");
        printPreferenceSettingMenu();
        String selection;
        while (true) {
            selection = input.nextLine();
            if (selection.equals("yes") || selection.equals("no")) {
                break;
            } else {
                System.out.println("Please enter \"yes\" or \"no\".");
            }
        }
        if (selection.equals("yes")) {
            savePreference();
            System.out.println("Your preferences have been saved");
        }
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