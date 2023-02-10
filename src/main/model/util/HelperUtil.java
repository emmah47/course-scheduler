package model.util;

import java.util.List;

// Utility class for common utility
public class HelperUtil {

    // REQUIRES: string is in the format of hrs:min. For example: 3:00, 21:30, etc
    // EFFECTS: given a time that is in the form of a string, converts it to minutes and returns the value.
    public static int calculateMinutes(String timeString) {
        String[] timeArr = timeString.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        return hour * 60 + minute;
    }


    // EFFECTS: returns true if two lists of strings contains duplicates
    public static boolean hasSameMember(List<String> stringList1, List<String> stringList2) {
        for (String string : stringList2) {
            if (stringList1.contains(string)) {
                return true;
            }
        }
        return false;
    }
}
