package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

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
}
