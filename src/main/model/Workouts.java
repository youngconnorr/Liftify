package model;

import java.util.ArrayList;

public class Workouts {
    ArrayList<String> push = new ArrayList<>();
    ArrayList<String> pull = new ArrayList<>();
    ArrayList<String> legs = new ArrayList<>();

    public Workouts() {
        push.add("bench press");
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
        legs.add("leg press");
        legs.add("calf raises");
        legs.add("bulgarian split squat");
        legs.add("front squat");
        legs.add("leg curl");
    }
}
