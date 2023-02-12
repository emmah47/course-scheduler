package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightTest {
    @Test
    void weightConstructorTestString() {
        Weight weight1 = new Weight(1, 2, "3:00", "4:00");
        assertEquals(1, weight1.getCompactWeight());
        assertEquals(2, weight1.getBalanceWeight());
        assertEquals(180, weight1.getPreferredStartTime());
        assertEquals(240, weight1.getPreferredEndTime());
    }

    @Test
    void weightConstructorTestInteger() {
        Weight weight1 = new Weight(3, 4, 60, 120);
        assertEquals(3, weight1.getCompactWeight());
        assertEquals(4, weight1.getBalanceWeight());
        assertEquals(60, weight1.getPreferredStartTime());
        assertEquals(120, weight1.getPreferredEndTime());
    }

    @Test
    void testGetterSetter() {
        Weight weight = new Weight( 1,1,1,1);
        weight.setBalanceWeight(2);
        weight.setCompactWeight(3);
        weight.setPreferredEndTime(4);
        weight.setPreferredStartTime(5);
        assertEquals(2, weight.getBalanceWeight());
        assertEquals(3, weight.getCompactWeight());
        assertEquals(4, weight.getPreferredEndTime());
        assertEquals(5, weight.getPreferredStartTime());
    }

}