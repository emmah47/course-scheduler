package model.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeHelperTest {

    @Test
    public void calculateMinutesTest() {
        String timeStr1 = "0:00";
        String timeStr2 = "0:30";
        String timeStr3 = "20:00:";
        String timeStr4 = "15:30";
        assertEquals( 0, HelperUtil.calculateMinutes(timeStr1));
        assertEquals(30, HelperUtil.calculateMinutes(timeStr2));
        assertEquals(20 * 60, HelperUtil.calculateMinutes(timeStr3));
        assertEquals(15 * 60 + 30, HelperUtil.calculateMinutes(timeStr4));
    }

}