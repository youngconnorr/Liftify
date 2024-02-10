package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutsTest {
    private Workouts testWorkouts;

    @BeforeEach
    void runBefore() {
        testWorkouts = new Workouts();
    }

    @Test
    void testConstructor() {
        assertEquals(6, testWorkouts.getPushLength());
        assertEquals(8, testWorkouts.getPullLength());
        assertEquals(8, testWorkouts.getLegsLength());
        assertEquals(0, testWorkouts.push.indexOf("bench press"));
        assertEquals(0, testWorkouts.pull.indexOf("deadlift"));
        assertEquals(0, testWorkouts.legs.indexOf("squat"));
        assertEquals(5, testWorkouts.push.indexOf("overhead press"));
        assertEquals(7, testWorkouts.pull.indexOf("chin ups"));
        assertEquals(7, testWorkouts.legs.indexOf("leg curl"));
        assertEquals(1, testWorkouts.push.indexOf("bench"));
        assertEquals(1, testWorkouts.pull.indexOf("lat pulldowns"));
        assertEquals(1, testWorkouts.legs.indexOf("rdl"));
    }

    @Test
    void whichWorkoutListTestPush() {
        assertTrue(testWorkouts.whichWorkoutList("push", "bench"));
    }

    @Test
    void whichWorkoutListTestPull() {
        assertTrue(testWorkouts.whichWorkoutList("pull", "lat pulldowns"));
    }

    @Test
    void whichWorkoutListTestLegs() {
        assertTrue(testWorkouts.whichWorkoutList("legs", "squat"));
    }

    @Test
    void whichWorkoutListTestFalse() {
        assertFalse(testWorkouts.whichWorkoutList("heh", "bench"));
    }

    @Test
    void whichWorkoutListTestFalseNotInPush() {
        assertFalse(testWorkouts.whichWorkoutList("push", "lunge"));
    }

    @Test
    void whichWorkoutListTestFalseNotInPull() {
        assertFalse(testWorkouts.whichWorkoutList("pull", "bench"));
    }

    @Test
    void whichWorkoutListTestFalseNotInLegs() {
        assertFalse(testWorkouts.whichWorkoutList("legs", "lats"));
    }

    @Test
    void addUncommonWorkoutTestPush() {
        testWorkouts.addUncommonWorkout("push", "skull crushers");
        assertEquals(7, testWorkouts.getPushLength());
    }

    @Test
    void addUncommonWorkoutTestPull() {
        testWorkouts.addUncommonWorkout("pull", "hammer curls");
        assertEquals(9, testWorkouts.getPullLength());
    }

    @Test
    void addUncommonWorkoutTestLegs() {
        testWorkouts.addUncommonWorkout("legs", "sissy squat");
        assertEquals(9, testWorkouts.getLegsLength());
    }

    @Test
    void addUncommonWorkoutTestMultiPush() {
        testWorkouts.addUncommonWorkout("push", "pushups");
        testWorkouts.addUncommonWorkout("push", "one arm pushup");
        assertEquals(8, testWorkouts.getPushLength());

    }

    @Test
    void addUncommonWorkoutTestMultiPull() {
        testWorkouts.addUncommonWorkout("pull", "hammer curls");
        testWorkouts.addUncommonWorkout("pull", "right curls");
        assertEquals(10, testWorkouts.getPullLength());

    }

    @Test
    void addUncommonWorkoutTestMultiLegs() {
        testWorkouts.addUncommonWorkout("legs", "dumbbell one leg");
        testWorkouts.addUncommonWorkout("legs", "pistol squat");
        assertEquals(10, testWorkouts.getLegsLength());
    }

    @Test
    void addUncommonWorkoutTestNotCategory() {
        testWorkouts.addUncommonWorkout("adas", "woo");
        assertEquals(1, testWorkouts.empty.size());
    }
}
