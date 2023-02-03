package model.util;

import java.util.List;

public class HelperUtil {

    // given a Section's start or end time, converts it to minutes.
    public static int calculateMinutes(String timeString) {
        String[] timeArr = timeString.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        return hour * 60 + minute;
    }

    // returns true if two lists of strings has same member
    public static boolean hasSameMember(List<String> stringList1, List<String> stringList2) {
        for (String string : stringList2) {
            if (stringList1.contains(string)) {
                return true;
            }
        }
        return false;
    }
}
