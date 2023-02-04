package model.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HelperUtilTest {

    @Test
    void testCalculateMinutes() {
        assertEquals(0, HelperUtil.calculateMinutes("0:00"));
        assertEquals(9*60 + 30, HelperUtil.calculateMinutes("9:30"));
        assertEquals(12 * 60, HelperUtil.calculateMinutes("12:00"));
    }

    @Test
    void testHasSameMember() {
        assertTrue(HelperUtil.hasSameMember(Arrays.asList("a", "b", "a", "a"), Arrays.asList("b")));
        assertTrue(HelperUtil.hasSameMember(Arrays.asList("a", "b"), Arrays.asList("b", "a")));
        assertTrue(HelperUtil.hasSameMember(Arrays.asList("a"), Arrays.asList("b", "c", "a")));
        assertTrue(HelperUtil.hasSameMember(Arrays.asList("a", "b", "c"), Arrays.asList("a", "b", "c")));

        assertFalse(HelperUtil.hasSameMember(Arrays.asList("a", "b"), Arrays.asList()));
        assertFalse(HelperUtil.hasSameMember(Arrays.asList(), Arrays.asList("a", "b")));
        assertFalse(HelperUtil.hasSameMember(Arrays.asList("a", "b"), Arrays.asList("c")));
        assertFalse(HelperUtil.hasSameMember(Arrays.asList("b"), Arrays.asList("a", "c")));
    }
}