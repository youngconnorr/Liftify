package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecordsTest {

    private Records testRecord;

    @BeforeEach
    void runBefore() {
        testRecord = new Records();
    }

    @Test
    void testConstructor() {
        assertTrue(testRecord.nameOfRecords.get("push").isEmpty());
        assertTrue(testRecord.nameOfRecords.get("pull").isEmpty());
        assertTrue(testRecord.nameOfRecords.get("legs").isEmpty());

        assertTrue(testRecord.pushRecords.isEmpty());
        assertTrue(testRecord.pullRecords.isEmpty());
        assertTrue(testRecord.legsRecords.isEmpty());

        assertEquals(testRecord.nameOfRecords, testRecord.allRecords.get(0));
    }

    @Test
    void addWorkoutToRecordTestOnce() {
        testRecord.addWorkoutToRecord("push", "bench", "413");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "310");
        assertEquals(1, testRecord.pushRecords.size());
        assertEquals(1, testRecord.pullRecords.size());
        assertEquals(1, testRecord.legsRecords.size());
        assertEquals("413", testRecord.pushRecords.get("bench"));
        assertEquals("140", testRecord.pullRecords.get("lat pulldowns"));
        assertEquals("310", testRecord.legsRecords.get("squat"));
    }

    @Test
    void addWorkoutToRecordTestMulti() {
        testRecord.addWorkoutToRecord("push", "bench", "413");
        testRecord.addWorkoutToRecord("push", "yo", "10");
        testRecord.addWorkoutToRecord("push", "yo", "10");
        testRecord.addWorkoutToRecord("push", "dips", "1");
        testRecord.addWorkoutToRecord("legs", "squat", "1");
        testRecord.addWorkoutToRecord("legs", "squat", "1");
        testRecord.addWorkoutToRecord("legs", "squat", "1");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "1");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "1");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "1");
        assertEquals(3, testRecord.pushRecords.size());
        assertEquals(1, testRecord.pullRecords.size());
        assertEquals(1, testRecord.legsRecords.size());
    }

    @Test
    void addWorkoutToRecordSameKeyDiffValue() {
        testRecord.addWorkoutToRecord("push", "bench", "135");
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "1");
        testRecord.addWorkoutToRecord("legs", "squat", "100");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "1");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "20");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "1");
        assertEquals("140", testRecord.pushRecords.get("bench"));
        assertEquals("100", testRecord.legsRecords.get("squat"));
        assertEquals("1", testRecord.pullRecords.get("lat pulldowns"));
    }

    @Test
    void addWorkoutToRecordDifferentKeySameValue() {
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "145");
        assertEquals("140", testRecord.pushRecords.get("bench"));
        assertEquals("140", testRecord.pullRecords.get("lat pulldowns"));
        assertEquals("145", testRecord.legsRecords.get("squat"));
    }

    @Test
    void addWorkoutToRecordDifferentKeys() {
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("push", "bench", "150");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "180");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "190");
        assertEquals("150", testRecord.pushRecords.get("bench"));
        assertEquals("180", testRecord.pullRecords.get("lat pulldowns"));
        assertEquals("190", testRecord.legsRecords.get("squat"));
    }

    @Test
    void removeWorkoutFromRecordOnce() {
        testRecord.addWorkoutToRecord("push", "bench", "413");
        testRecord.removeWorkoutFromRecord("push", "bench");
        assertEquals(0, testRecord.pushRecords.size());
        assertTrue(testRecord.pushRecords.isEmpty());
    }

    @Test
    void removeWorkoutFromRecordMulti() {
        testRecord.addWorkoutToRecord("push", "bench", "413");
        testRecord.addWorkoutToRecord("push", "yo", "10");
        testRecord.addWorkoutToRecord("push", "dips", "1");
        testRecord.removeWorkoutFromRecord("push", "bench");
        testRecord.removeWorkoutFromRecord("push", "yo");
        assertEquals(1, testRecord.pushRecords.size());
        testRecord.removeWorkoutFromRecord("push", "dips");
        assertEquals(0, testRecord.pushRecords.size());
    }

    @Test
    void removeWorkoutFromRecordDifferentKeys() {
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("push", "dips", "150");
        testRecord.addWorkoutToRecord("push", "dips", "150");
        testRecord.addWorkoutToRecord("push", "dips", "150");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "180");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "190");
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.removeWorkoutFromRecord("pull", "lat pulldowns");
        testRecord.removeWorkoutFromRecord("pull", "lat pulldowns");
        testRecord.removeWorkoutFromRecord("pull", "lat pulldowns");
        testRecord.removeWorkoutFromRecord("push", "dips");
        testRecord.removeWorkoutFromRecord("legs", "squat");
        testRecord.removeWorkoutFromRecord("legs", "squat");
        testRecord.removeWorkoutFromRecord("legs", "squat");
        testRecord.removeWorkoutFromRecord("legs", "squat");
        testRecord.removeWorkoutFromRecord("legs", "squat");
        assertEquals(1, testRecord.pushRecords.size());
        assertEquals(0, testRecord.pullRecords.size());
        assertEquals(0, testRecord.legsRecords.size());
    }

    @Test
    void whichRecordListTest() {
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        assertTrue(testRecord.whichRecordList("push", "bench"));
        assertTrue(testRecord.whichRecordList("pull", "lat pulldowns"));
        assertTrue(testRecord.whichRecordList("legs", "squat"));


    }

    @Test
    void whichRecordListTestFalse() {
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        assertFalse(testRecord.whichRecordList("heh", "bench"));
        assertFalse(testRecord.whichRecordList("pulll", "lats"));
        assertFalse(testRecord.whichRecordList("legsss", "squat"));
    }

    @Test
    void whichRecordListTestFalseNot() {
        testRecord.addWorkoutToRecord("push", "bench", "140");
        testRecord.addWorkoutToRecord("pull", "lat pulldowns", "140");
        testRecord.addWorkoutToRecord("legs", "squat", "140");
        assertFalse(testRecord.whichRecordList("push", "lunge"));
        assertFalse(testRecord.whichRecordList("pull", "lifts"));
        assertFalse(testRecord.whichRecordList("legs", "jumpys"));
    }

}
