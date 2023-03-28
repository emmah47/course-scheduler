package ui;


import model.Schedule;
import model.Weight;
import model.util.CourseRealData;
import org.json.JSONArray;
import persistence.JsonReaderPreferences;
import persistence.JsonReaderSchedule;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// An abstract class that represents the course scheduler app
public abstract class SchedulerApp {
    public static final String JSON_STORE_WEIGHT = "./data/weight.json";
    public static final String JSON_STORE_SCHEDULE = "./data/schedule.json";
    public static final List<String> WEEKDAYS = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri");
    protected Weight preferredWeights = new Weight(1, 1, "9:00", "16:00");
    protected JsonReaderPreferences jsonReaderPreferences;
    protected JsonReaderSchedule jsonReaderSchedule;
    protected JsonWriter jsonWriterPreferences;
    protected JsonWriter jsonWriterSchedules;
    protected List<Schedule> savedSchedules;
    protected CourseRealData data = new CourseRealData("./data/courses.json", "./data/sections.json");

    // constructor
    SchedulerApp() {
        jsonReaderPreferences = new JsonReaderPreferences(JSON_STORE_WEIGHT);
        jsonReaderSchedule = new JsonReaderSchedule(JSON_STORE_SCHEDULE);
        jsonWriterPreferences = new JsonWriter(JSON_STORE_WEIGHT);
        jsonWriterSchedules = new JsonWriter(JSON_STORE_SCHEDULE);
        this.savedSchedules = loadSavedSchedules();
    }

    public void loadSavedWeight() {
        try {
            this.preferredWeights = jsonReaderPreferences.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CourseRealData getData() {
        return data;
    }

    public Weight getPreferredWeights() {
        return preferredWeights;
    }

    public List<Schedule> getSavedSchedules() {
        return savedSchedules;
    }


    // EFFECTS: runs the course scheduler app
    public abstract void run();


    // MODIFIES: file
    // EFFECTS: saves this.savedSchedules to file
    protected void saveSchedulesToFile() {
        try {
            jsonWriterSchedules.open();
            JSONArray jsonArraySchedules = new JSONArray();
            for (Schedule schedule : this.savedSchedules) {
                jsonArraySchedules.put(schedule.toJsonObject());
            }
            jsonWriterSchedules.writeJsonArray(jsonArraySchedules);
            jsonWriterSchedules.close();
        } catch (IOException e) {
            System.out.println("Cannot save file.");
        }

    }

    // EFFECTS: loads the saved schedules from file and returns them
    public List<Schedule> loadSavedSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try {
            schedules = jsonReaderSchedule.read();
        } catch (IOException e) {
            System.out.println("Unable to load schedules");
        }
        return schedules;
    }


    // MODIFIES: this, file
    // EFFECTS: deletes the schedule from file
    public void deleteSchedule(Schedule s) {
        this.savedSchedules.remove(s);
        saveSchedulesToFile();
    }


    // MODIFIES: file
    // EFFECTS: adds schedule to be saved to this.saved schedule and then saves this.savedSchedules to file
    public void saveSchedule(Schedule schedule) {
        this.savedSchedules.add(schedule);
        saveSchedulesToFile();
    }


    // MODIFIES: file
    // EFFECTS: saves the preferences
    public void savePreference() {
        try {
            jsonWriterPreferences.open();
            jsonWriterPreferences.writeJsonObject(preferredWeights.toJsonObject());
            jsonWriterPreferences.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file to save to.");
        }
    }

    // EFFECTS: returns the schedule with a name matching the given string, returns null if not found
    public Schedule getSavedScheduleByName(String name) {
        Schedule schedule = null;
        for (Schedule s : savedSchedules) {
            if (s.getName().equals(name)) {
                schedule = s;
                break;
            }
        }
        return schedule;
    }

}