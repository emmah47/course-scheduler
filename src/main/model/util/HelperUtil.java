package model.util;

import java.util.List;

// Utility class for common utility
public class HelperUtil {
    private HelperUtil() {
    }

    // REQUIRES: string is in the format of hrs:mins, in 24hr time. For example: 3:00, 21:30, etc
    // EFFECTS: given a time that is in the form of a string, converts it to minutes and returns the value.
    public static int calculateMinutes(String timeString) {
        String[] timeArr = timeString.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        return hour * 60 + minute;
    }

    // REQUIRES: 0 <= minutes < 1400
    // EFFECTS: converts minutes to a time in the form of "hrs:mins"
    public static String minutesToTime(int minutes) {
        return String.format("%2d:%2d", minutes / 60, minutes % 60)
                .replace(" ", "0");
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
