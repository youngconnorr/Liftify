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
        assertFalse(testWorkouts.push.isEmpty());
        assertFalse(testWorkouts.pull.isEmpty());
        assertFalse(testWorkouts.legs.isEmpty());
    }

    @Test
    void whichWorkoutListTest() {
        assertTrue(testWorkouts.whichWorkoutList("pull", "lat pulldowns"));
        assertTrue(testWorkouts.whichWorkoutList("push", "bench"));
        assertTrue(testWorkouts.whichWorkoutList("legs", "squat"));
    }

    @Test
    void whichWorkoutListTestFalse() {
        assertFalse(testWorkouts.whichWorkoutList("heh", "bench"));
        assertFalse(testWorkouts.whichWorkoutList("heh", "squat"));
        assertFalse(testWorkouts.whichWorkoutList("heh", "latp pulldowns"));
    }

    @Test
    void whichWorkoutListTestFalseNot() {
        assertFalse(testWorkouts.whichWorkoutList("push", "lunge"));
        assertFalse(testWorkouts.whichWorkoutList("pull", "bench"));
        assertFalse(testWorkouts.whichWorkoutList("legs", "lats"));
    }

    @Test
    void addUncommonWorkoutTestPush() {
        testWorkouts.addUncommonWorkout("push", "skull crushers");
        testWorkouts.addUncommonWorkout("pull", "hammer curls");
        testWorkouts.addUncommonWorkout("legs", "sissy squat");
        assertEquals(7, testWorkouts.getPushLength());
        assertEquals(9, testWorkouts.getPullLength());
        assertEquals(9, testWorkouts.getLegsLength());
    }

    @Test
    void addUncommonWorkoutTestPushDups() {
        testWorkouts.addUncommonWorkout("push", "skull crushers");
        testWorkouts.addUncommonWorkout("push", "skull crushers");
        testWorkouts.addUncommonWorkout("push", "skull crushers");
        testWorkouts.addUncommonWorkout("pull", "hammer curls");
        testWorkouts.addUncommonWorkout("pull", "hammer curls");
        testWorkouts.addUncommonWorkout("pull", "hammer curls");
        testWorkouts.addUncommonWorkout("legs", "sissy squat");
        testWorkouts.addUncommonWorkout("legs", "sissy squat");
        testWorkouts.addUncommonWorkout("legs", "sissy squat");
        assertEquals(9, testWorkouts.getPushLength());
        assertEquals(11, testWorkouts.getPullLength());
        assertEquals(11, testWorkouts.getLegsLength());
    }


    @Test
    void addUncommonWorkoutTestMulti() {
        testWorkouts.addUncommonWorkout("PUSH", "pushups");
        testWorkouts.addUncommonWorkout("PUsH", "PUSPS");
        testWorkouts.addUncommonWorkout("push", "one arm pushup");
        testWorkouts.addUncommonWorkout("", "one arm pushup");
        testWorkouts.addUncommonWorkout("p u s h", "one arm pushup");
        testWorkouts.addUncommonWorkout("", "HAMmer curls");
        testWorkouts.addUncommonWorkout("PuLL", "hammer curls");
        testWorkouts.addUncommonWorkout("pull", "right curls");
        testWorkouts.addUncommonWorkout("LEGS", "dumbbell one leg");
        testWorkouts.addUncommonWorkout("", "dumbbell one leg");
        testWorkouts.addUncommonWorkout("LeGS", "dumbbell one leg");
        testWorkouts.addUncommonWorkout("legs", "pistol squat");
        assertEquals(7, testWorkouts.getPushLength());
        assertEquals(9, testWorkouts.getPullLength());
        assertEquals(9, testWorkouts.getLegsLength());
        assertEquals(9, testWorkouts.empty.size());


    }

    @Test
    void addUncommonWorkoutTestNotCategory() {
        testWorkouts.addUncommonWorkout("adas", "woo");
        assertEquals(1, testWorkouts.empty.size());
    }

    @Test
    void addUncommonWorkoutTestNotCategoryMulti() {
        testWorkouts.addUncommonWorkout("adas", "woo");
        testWorkouts.addUncommonWorkout("SAS", "jo");
        testWorkouts.addUncommonWorkout("ooas", "WOO");
        assertEquals(3, testWorkouts.empty.size());
    }

    @Test
    void addUncommonWorkoutTestNotCategoryMultiWithOthers() {
        testWorkouts.addUncommonWorkout("adas", "woo");
        testWorkouts.addUncommonWorkout("sas", "jo");
        assertEquals(2, testWorkouts.empty.size());
        testWorkouts.addUncommonWorkout("ooas", "woo");
        testWorkouts.addUncommonWorkout("push", "skull crushers");
        testWorkouts.addUncommonWorkout("pull", "HAMMER CURLES");
        testWorkouts.addUncommonWorkout("legs", "SIsY squat");
        testWorkouts.addUncommonWorkout("push", "sissy squat");
        assertEquals(3, testWorkouts.empty.size());
        assertEquals(9, testWorkouts.pull.size());
        assertEquals(9, testWorkouts.legs.size());
        assertEquals(8, testWorkouts.push.size());
    }
}
