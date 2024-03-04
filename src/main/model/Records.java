package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

//Contains methods that modify record lists or check information about record list
public class Records extends Workouts implements Writable {
    protected LinkedHashMap<String, String> pushRecords;            //List of all user push records
    protected LinkedHashMap<String, String> pullRecords;            //List of all user pull records
    protected LinkedHashMap<String, String> legsRecords;            //List of all user legs records
    protected Map<String, LinkedHashMap<String, String>> nameOfRecords;   //category name of each list of records
    protected ArrayList<Map<String, LinkedHashMap<String, String>>> allRecords; //List containing all records with name
    protected ArrayList<String> empty;  //List of all error records

    public Records() {
        pushRecords = new LinkedHashMap<>();
        pullRecords = new LinkedHashMap<>();
        legsRecords = new LinkedHashMap<>();
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
    public void addWorkoutToRecord(String category, String exercise, String weight) {
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
    public void removeWorkoutFromRecord(String category, String exercise) {
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

    public LinkedHashMap<String, String> getPushRecords() {
        return pushRecords;
    }

    public LinkedHashMap<String, String> getPullRecords() {
        return pullRecords;
    }

    public LinkedHashMap<String, String> getLegsRecords() {
        return legsRecords;
    }

    public Map<String, LinkedHashMap<String, String>> getNameOfRecords() {
        return nameOfRecords;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "Your Records");
        json.put("Records", recordsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray recordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String, LinkedHashMap<String, String>> categories : nameOfRecords.entrySet()) {
            String category = categories.getKey(); //categories
            LinkedHashMap<String, String> categoryRecords = categories.getValue(); //the workouts in category


            for (Map.Entry<String, String> record : categoryRecords.entrySet()) {
                jsonArray.put(categoriesToJson(category, record.getKey(), record.getValue()));
            }
        }

        return jsonArray;
    }

    public JSONObject categoriesToJson(String category, String workout, String weight) {
        JSONObject json = new JSONObject();
        json.put(category, workoutsToJson(workout, weight));
        return json;
    }

    public JSONObject workoutsToJson(String workout, String weight) {
        JSONObject json = new JSONObject();
        json.put("Workout", workout);
        json.put("Weight", weight);
        return json;
    }


}

