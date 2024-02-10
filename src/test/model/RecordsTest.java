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
    void addWorkoutToRecordTestOnce() {
        testRecord.addWorkoutToRecord("push", "bench", "413");
        assertEquals(1, testRecord.pushRecords.size());
        assertEquals("413", testRecord.pushRecords.get("bench"));
    }

    @Test
    void addWorkoutToRecordTestMulti() {
        testRecord.addWorkoutToRecord("push", "bench", "413");
        testRecord.addWorkoutToRecord("push", "yo", "10");
        testRecord.addWorkoutToRecord("push", "dips", "1");
        assertEquals(3, testRecord.pushRecords.size());
    }

    @Test
    void addWorkoutToRecordSameKeyDiffValue() {
        testRecord.addWorkoutToRecord("push", "bench", "135");
        testRecord.addWorkoutToRecord("push", "bench", "140");
        assertEquals("140", testRecord.pushRecords.get("bench"));
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

}
