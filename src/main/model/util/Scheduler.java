package model.util;

import model.Schedule;
import model.Section;

import java.util.*;

// Util class for helping with course scheduling
public class Scheduler {

    // schedules root courses. This function sets up and calls the actual search function
    public static List<Schedule> scheduleRootCourses(Schedule schedule0) {
        List<Schedule> result = new ArrayList<>();
        scheduleRootSectionsNode(schedule0, new ArrayList<>(), schedule0.getCourseIDs(), new ArrayList<>(), result);
        return  result;
    }

    // REQUIRES: scheduleWorkList is empty, courseIDs is a copy of schedule.getCourseIDs(), courseIDsWorkList is empty,
    // resultSoFar is empty
    // Search function for root sections
    private static void scheduleRootSectionsNode(
            Schedule schedule,
            List<Schedule> scheduleWorkList,
            List<String> courseIDs, // get course IDs from schedule.
            List<List<String>> courseIDsWorkList, // tandem worklist for courseIDs
            List<Schedule> resultSoFar) {

        if (schedule.getSectionIDs().size() == schedule.getCourseIDs().size()) {
            resultSoFar.add(schedule);
        } else {
            List<Schedule> nextSchedules = Scheduler.makeNextSchedules(schedule, courseIDs.get(0));
            scheduleWorkList.addAll(nextSchedules);
            courseIDsWorkList.addAll(Collections.nCopies(nextSchedules.size(), courseIDs.subList(1, courseIDs.size())));
        }
        Scheduler.scheduleRootSectionsList(scheduleWorkList, courseIDsWorkList, resultSoFar);
    }

    private static void scheduleRootSectionsList(List<Schedule> scheduleWorkList,
                                                           List<List<String>> courseIDsWorkList,
                                                           List<Schedule> resultSoFar) {
        if (scheduleWorkList.isEmpty()) {
            return;
        } else {
            scheduleRootSectionsNode(
                    scheduleWorkList.get(0),
                    scheduleWorkList.subList(1, courseIDsWorkList.size()),
                    courseIDsWorkList.get(0),
                    courseIDsWorkList.subList(1, courseIDsWorkList.size()),
                    resultSoFar);
        }
    }

    // makes the next schedules (next layer of search tree) by getting all sections of the course
    // id and making one copy of the new schedule for every section.
    private static List<Schedule> makeNextSchedules(Schedule schedule0, String courseID) {
        List<Schedule> result = new ArrayList<>();
        List<Section> sections = schedule0.getCourseData().getRootSections(courseID);
        for (Section section : sections) {
            Schedule clonedSchedule = schedule0.clone();
            if (clonedSchedule.tryAddSection(section)) {
                result.add(clonedSchedule);
            }
        }
        return result;
    }


}
