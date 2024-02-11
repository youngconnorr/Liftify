package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Records extends Workouts {
    protected LinkedHashMap<String, String> pushRecords;
    protected LinkedHashMap<String, String> pullRecords;
    protected LinkedHashMap<String, String> legsRecords;
    protected Map<String, LinkedHashMap<String, String>> nameOfRecords;
    protected ArrayList<Map<String, LinkedHashMap<String, String>>> allRecords;
    protected ArrayList<String> empty;

    public Records() {
        pushRecords = new LinkedHashMap<>(); // is a list of all push workout records
        pullRecords = new LinkedHashMap<>(); // is a list of all pull workout records
        legsRecords = new LinkedHashMap<>(); // is a list of all leg workout records
        nameOfRecords = new LinkedHashMap<>();

        nameOfRecords.put("push", pushRecords);

        nameOfRecords.put("pull", pullRecords);

        nameOfRecords.put("legs", legsRecords);

        empty = new ArrayList<>();
        allRecords = new ArrayList<>();
        allRecords.add(nameOfRecords);

    }

    //MODIFIES: this
    //EFFECT: adds workout to category according to arguments given
    protected void addWorkoutToRecord(String category, String exercise, String weight) {
        if (category.equals("push")) {
            pushRecords.put(exercise, weight);
        } else if (category.equals("pull")) {
            pullRecords.put(exercise, weight);
        } else if (category.equals("legs")) {
            legsRecords.put(exercise, weight);
        } else {
            empty.add("workout");
        }

    }

    //MODIFIES: this
    //EFFECT: removes workout from specified category
    protected void removeWorkoutFromRecord(String category, String exercise) {
        if (category.equals("push")) {
            pushRecords.remove(exercise);
        } else if (category.equals("pull")) {
            pullRecords.remove(exercise);
        } else if (category.equals("legs")) {
            legsRecords.remove(exercise);
        } else {
            empty.remove("workout");
        }
    }

    //EFFECT: return true if record contains workout, false otherwise
    public boolean whichRecordList(String category, String workout) {
        if (category.equals("push")) {
            return pushRecords.containsKey(workout);
        } else if (category.equals("pull")) {
            return pullRecords.containsKey(workout);
        } else if (category.equals("legs")) {
            return legsRecords.containsKey(workout);
        } else {
            return false;
        }
    }


}

