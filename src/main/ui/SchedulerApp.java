package ui;


import model.Schedule;
import model.Weight;
import org.json.JSONArray;
import persistence.JsonReaderPreferences;
import persistence.JsonReaderSchedule;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// An abstract class that represents the course scheduler app
public abstract class SchedulerApp {
    protected static final String JSON_STORE_WEIGHT = "./data/weight.json";
    protected static final String JSON_STORE_SCHEDULE = "./data/schedule.json";
    protected static final List<String> WEEKDAYS = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri");
    protected Weight preferredWeights = new Weight(1, 1, "9:00", "16:00");
    protected JsonReaderPreferences jsonReaderPreferences;
    protected JsonReaderSchedule jsonReaderSchedule;
    protected JsonWriter jsonWriterPreferences;
    protected JsonWriter jsonWriterSchedules;
    protected List<Schedule> savedSchedules;

    // constructor
    SchedulerApp() {
        jsonReaderPreferences = new JsonReaderPreferences(JSON_STORE_WEIGHT);
        jsonReaderSchedule = new JsonReaderSchedule(JSON_STORE_SCHEDULE);
        jsonWriterPreferences = new JsonWriter(JSON_STORE_WEIGHT);
        jsonWriterSchedules = new JsonWriter(JSON_STORE_SCHEDULE);
        this.savedSchedules = loadSavedSchedules();
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
    protected List<Schedule> loadSavedSchedules() {
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
    protected void deleteSchedule(Schedule s) {
        this.savedSchedules.remove(s);
        saveSchedulesToFile();
        System.out.println("Schedule has been deleted!");
    }


    // MODIFIES: file
    // EFFECTS: adds schedule to be saved to this.saved schedule and then saves this.savedSchedules to file
    protected void saveSchedule(Schedule schedule) {
        System.out.println("Saving the schedule...");
        this.savedSchedules.add(schedule);
        saveSchedulesToFile();
    }


    // MODIFIES: file
    // EFFECTS: saves the preferences
    protected void savePreference() {
        try {
            jsonWriterPreferences.open();
            jsonWriterPreferences.writeJsonObject(preferredWeights.toJsonObject());
            jsonWriterPreferences.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file to save to.");
        }
        System.out.println("Your preferences have been saved");
    }

}