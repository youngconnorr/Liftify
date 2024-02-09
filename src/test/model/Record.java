//package model;
//
//import java.util.LinkedHashMap;
//
//public class Record {
//
//    protected LinkedHashMap<String, String> pushRecords;
//    protected LinkedHashMap<String, String> pullRecords;
//    protected LinkedHashMap<String, String> legsRecords;
//
//
//    public Record() {
//        pushRecords = new LinkedHashMap<>(); // is a list of all push workout records (ie. Push: 135)
//        pullRecords = new LinkedHashMap<>(); // is a list of all pull workout records
//        legsRecords = new LinkedHashMap<>(); // is a list of all leg workout records
//    }
//
//
//    //MODIFIES: this
//    //EFFECT: check category chosen and call methods that adds to list
//    public void addWorkoutToRecord(String category, String exercise, String weight) {
//        switch (category) {
//            case "push":
//                pushRecords.put(exercise, weight);
//                break;
//            case "pull":
//                pullRecords.put(exercise, weight);
//                break;
//            case "legs":
//                legsRecords.put(exercise, weight);
//                break;
//        }
//    }
//}
