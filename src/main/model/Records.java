package model;

import java.util.LinkedHashMap;

public class Records extends Workouts {
    protected LinkedHashMap<String, String> pushRecords;
    protected LinkedHashMap<String, String> pullRecords;
    protected LinkedHashMap<String, String> legsRecords;

    public Records() {
        pushRecords = new LinkedHashMap<>(); // is a list of all push workout records (ie. Push: 135)
        pullRecords = new LinkedHashMap<>(); // is a list of all pull workout records
        legsRecords = new LinkedHashMap<>(); // is a list of all leg workout records
    }


    //MODIFIES: this
    //EFFECT: adds workout to category according to arguments given
    protected void addWorkoutToRecord(String category, String exercise, String weight) {
        switch (category) {
            case "push":
                pushRecords.put(exercise, weight);
                break;
            case "pull":
                pullRecords.put(exercise, weight);
                break;
            case "legs":
                legsRecords.put(exercise, weight);
                break;
        }
    }

    //MODIFIES: this
    //EFFECT: removes workout from specified category
    protected void removeWorkoutFromRecord(String category, String exercise) {
        switch (category) {
            case "push":
                pushRecords.remove(exercise);
                break;
            case "pull":
                pullRecords.remove(exercise);
                break;
            case "legs":
                legsRecords.remove(exercise);
                break;
        }
    }
}
