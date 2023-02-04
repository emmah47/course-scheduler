package model.util;

import model.Schedule;
import model.Section;

import java.util.*;

// Util class for helping with course scheduling
public class Scheduler {

    // schedules all courses. This function sets up and calls the actual search functions
    public static List<Schedule> scheduleCourses(Schedule schedule0) {
        List<Schedule> result0 = new ArrayList<>();
        scheduleRootSections(schedule0, new LinkedList<>(), schedule0.getCourseIDs(), new LinkedList<>(), result0);

        List<Schedule> result = new ArrayList<>();
        for (Schedule schedule : result0) {
            List<Schedule> tempResult = new ArrayList<>();
            List<List<String>> sectionIDsSet = schedule.getAllAntirequisits();
            scheduleSecondSections(schedule, new LinkedList<>(),
                    sectionIDsSet, new LinkedList<>(), sectionIDsSet.size(), tempResult);
            result.addAll(tempResult);
        }
        return result;
    }

    // REQUIRES: scheduleWorkList is empty, courseIDs is a copy of schedule.getCourseIDs(), courseIDsWorkList is empty,
    //           resultSoFar is empty.
    // MODIFIES: resultSoFar
    // EFFECTS: Search function for root sections, returns a list of schedules, where each schedule has all root
    //          sections of the course IDs.
    private static void scheduleRootSections(
            Schedule schedule,
            LinkedList<Schedule> scheduleWorkList,
            List<String> courseIDs, // get course IDs from schedule.
            LinkedList<List<String>> courseIDsWorkList, // tandem worklist for courseIDs
            List<Schedule> resultSoFar) {

        if (schedule.getSectionIDs().size() == schedule.getCourseIDs().size()) {
            resultSoFar.add(schedule);
        } else {
            List<Schedule> nextSchedules = Scheduler.makeNextSchedules(schedule, courseIDs.get(0));
            scheduleWorkList.addAll(nextSchedules);
            courseIDsWorkList.addAll(Collections.nCopies(nextSchedules.size(), courseIDs.subList(1, courseIDs.size())));
        }
        if (!scheduleWorkList.isEmpty()) {
            scheduleRootSections(
                    scheduleWorkList.pollFirst(),
                    scheduleWorkList,
                    courseIDsWorkList.pollFirst(),
                    courseIDsWorkList,
                    resultSoFar);
        }
    }

    // Schedules a second time
    private static void scheduleSecondSections(
            Schedule schedule,
            LinkedList<Schedule> scheduleWorkList,
            List<List<String>> sectionIDsSet, // list of list of section IDs. ex. List(allLabs1, allLabs2)
            LinkedList<List<List<String>>> sectionIDsWorkList,// tandem worklist for sectionIDsSet
            int sectionIDsSize,
            List<Schedule> resultSoFar) {

        if (schedule.getSectionIDs().size() == schedule.getCourseIDs().size() + sectionIDsSize) {
            resultSoFar.add(schedule);
        } else {
            List<Schedule> nextSchedules = Scheduler.makeNextSchedulesWithAntirequsite(schedule, sectionIDsSet.get(0));
            scheduleWorkList.addAll(nextSchedules);
            sectionIDsWorkList.addAll(Collections.nCopies(nextSchedules.size(),
                    sectionIDsSet.subList(1, sectionIDsSet.size())));
        }
        if (!scheduleWorkList.isEmpty()) {
            scheduleSecondSections(
                    scheduleWorkList.pollFirst(),
                    scheduleWorkList,
                    sectionIDsWorkList.pollFirst(),
                    sectionIDsWorkList,
                    sectionIDsSize,
                    resultSoFar);
        }
    }


    // MODIFIES: result
    // EFFECTS: makes the next schedules (next layer of search tree) by getting all sections from the course
    // ids and making one copy of the new schedule for every section.
    private static List<Schedule> makeNextSchedules(Schedule schedule0, String courseID) {
        List<Schedule> result = new ArrayList<>();
        List<Section> sections = schedule0.getCourseData().getRootSections(courseID);
        for (Section section : sections) {
            Schedule clonedSchedule = schedule0.makeCopy();
            if (clonedSchedule.tryAddSection(section)) {
                result.add(clonedSchedule);
            }
        }
        return result;
    }


    // MODIFIES: result
    // EFFECTS: makes the next schedules (next layer of search tree) by getting sections from the section
    // ids and making one copy of the new schedule for every section.
    private static List<Schedule> makeNextSchedulesWithAntirequsite(Schedule schedule, List<String> sectionIDs) {
        List<Schedule> result = new ArrayList<>();
        for (String sectionID : sectionIDs) {
            Schedule clonedSchedule = schedule.makeCopy();
            Section section = schedule.getCourseData().getSection(sectionID);
            if (clonedSchedule.tryAddSection(section)) {
                result.add(clonedSchedule);
            }
        }
        return result;
    }


}
