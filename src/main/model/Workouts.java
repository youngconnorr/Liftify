package model;

import java.util.ArrayList;

public class Workouts {
    protected ArrayList<String> push = new ArrayList<>();
    protected ArrayList<String> pull = new ArrayList<>();
    protected ArrayList<String> legs = new ArrayList<>();

    public Workouts() {
        push.add("bench press");
        push.add("bench");
        push.add("dips");
        push.add("lateral raises");
        push.add("incline bench press");
        push.add("overhead press");

        pull.add("deadlift");
        pull.add("lat pulldowns");
        pull.add("barbell rows");
        pull.add("pullups");
        pull.add("dumbbell curl");
        pull.add("barbell curl");
        pull.add("bicep curl");
        pull.add("chin ups");


        legs.add("squat");
        legs.add("rdl");
        legs.add("romanian deadlift");
        legs.add("leg press");
        legs.add("calf raises");
        legs.add("bulgarian split squat");
        legs.add("front squat");
        legs.add("leg curl");
    }

    //EFFECT: check if the workout is in the common workout list or not return boolean
    public boolean whichWorkoutList(String category, String workout) {
        if (category.equals("push")) {
            return push.contains(workout);
        } else if (category.equals("pull")) {
            return pull.contains(workout);
        } else if (category.equals("legs")) {
            return legs.contains(workout);
        } else {
            return false;
        }
    }

    public void addUncommonWorkout(String category, String workout) {
        if (category.equals("push")) {
            push.add(workout);
        } else if (category.equals("pull")) {
            pull.add(workout);
        } else if (category.equals("legs")) {
            legs.add(workout);
        }
    }
}
