package ui;

import model.Records;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//The part of the application that shows the record menu and the choices inside of it
public class RecordPanel {
    private Records records;
    private static final String JSON_STORE = "./data/records.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    Scanner input;

    //EFFECT: creates empty lists of records and calls common workouts from supertype
    public RecordPanel() {
        records = new Records("Your records");
        input = new Scanner(System.in);      //instantiates a variable to take in user input
        input.useDelimiter("\n");    //read user input at new line input
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    //EFFECT: Runs whichPath to determine path user wants to go, call methods accordingly
    public String runRecordPanel() {

        String chosenPath = whichPath();
        if (chosenPath.equals("view")) {
            String pathFromView = runView();
            if (pathFromView.equals("back")) {
                runRecordPanel();
            }
        } else if (chosenPath.equals("create")) {
            if (createRecord().equals("back") || createRecord().equals("q")) {
                runRecordPanel();
            }
        } else if (chosenPath.equals("back")) {
            return "back";
        } else if (chosenPath.equals("save")) {
            saveRecords();
        } else if (chosenPath.equals("load")) {
            loadRecords();
        }
        return "back";
    }

    //EFFECT: display records lists
    private void viewRecords() {
        System.out.println("\nPush Records:" + records.getPushRecords());
        System.out.println("\nPull Records:" + records.getPullRecords());
        System.out.println("\nLegs Records:" + records.getLegsRecords());
    }

    //EFFECT: display options of view
    private void viewRecordsOptions() {
        System.out.println("\nremove -> remove a record from a category");
        System.out.println("back -> go back to record menu");
    }

    //EFFECT:display the records lists and checks to see if user wants to go back
    public String runView() {
        viewRecords();
        return checkBackView();
    }

    //EFFECT: check to see if user typed back and repeat if they didn't
    public String checkBackView() {
        while (true) {
            viewRecordsOptions();
            String nextPath = input.next().toLowerCase();
            if (nextPath.equals("back")) {
                return "back";
            } else if (nextPath.equals("remove")) {
                if (removeWorkoutBack().equals("back")) {
                    return "back";
                }
            } else {
                System.out.println("Please input valid option.");
            }
        }
    }

    //EFFECT: iterates through list of key-value pair and checks if key is empty
    //        if not empty then print list
    private void ignoreRecordsEmpty() {
        for (Map.Entry<String, LinkedHashMap<String, String>> categories : records.getNameOfRecords().entrySet()) {
            String category = categories.getKey(); //categories
            LinkedHashMap<String, String> categoryRecords = categories.getValue(); //the workouts in category

            if (!categoryRecords.isEmpty()) {
                System.out.println("\n" + category + " | ");
                for (Map.Entry<String, String> record : categoryRecords.entrySet()) {
                    System.out.println(record.getKey() + ":" + record.getValue());
                }
            }
        }
    }

    //EFFECT: brings user back from remove workout
    public String removeWorkoutBack() {
        String work = workoutToRemove(); //took away cat parameter
        if (work.equals("back")) {
            return "back";
        } else {
            return "back";
        }
    }

    //EFFECT: checks the category and the workout the user wants to remove
    public String workoutToRemove() {
        while (true) {
            System.out.println("\nChoose your category or go back.");
            System.out.println("back -> go back to the record menu");
            ignoreRecordsEmpty();
            String cat = input.next().toLowerCase();
            if (cat.equals("back")) {
                return "back";
            } else if (checker("category", cat)) {
                System.out.println("\nNow choose your workout");
                String workout = input.next().toLowerCase();
                if (workout.equals("back")) {
                    return "back";
                } else if (records.whichRecordList(cat, workout)) {
                    records.removeWorkoutFromRecord(cat, workout);
                    System.out.println("Workout Removed");
                    return "back";
                } else {
                    System.out.println("That workout doesn't exist.");
                }
            }
        }
    }

    //EFFECT: dynamic checker for different cases, returns true if user input is valid,
    //        false otherwise
    private boolean checker(String e, String userInput) {
        if (e.equals("category")) {
            return userInput.equals("push")
                    || userInput.equals("pull")
                    || userInput.equals("legs")
                    || userInput.equals("back");
        } else if (e.equals("path")) {
            return userInput.equals("view")
                    || userInput.equals("create")
                    || userInput.equals("back")
                    || userInput.equals("save")
                    || userInput.equals("load");
        }
        return false;
    }

    //EFFECT: creates a record and check if user inputs back at any point
    public String createRecord() {

        String category = findCategory();
        if (!category.equals("back")) {
            String exercise = findExercise(category);
            if (!exercise.equals("q")) {
                String weight = findWeight();
                records.addWorkoutToRecord(category, exercise, weight);
                records.addUncommonWorkout(category, exercise);
                System.out.println("Saved to records!");
            } else {
                return "back";
            }
        } else {
            return "back";
        }
        return "back";
    }

    //EFFECT: ask which path user wants to go inside of records
    private void path() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tview   -> view your personal records");
        System.out.println("\tcreate -> create a new personal record");
        System.out.println("\tsave   -> save your records for later viewing");
        System.out.println("\tload   -> load your old records and add to them");
        System.out.println("\tback   -> go back to main menu");
    }

    //EFFECT: determine which path user wants to go down and check if path is valid
    //        return values the path value
    public String whichPath() {

        while (true) {
            path();
            String path = input.next();
            path = path.toLowerCase();
            if (checker("path", path)) {
                return path;
            } else {
                System.out.println("Invalid entry. Please select one of the options.");
            }
        }
    }

    //EFFECT: print options of workout categories to user
    private void whichCategory() {
        System.out.println("\nChoose the category this workout is under:");
        System.out.println("\tpush -> workouts with chest and triceps");
        System.out.println("\tpull -> workouts with back and biceps");
        System.out.println("\tlegs -> workouts with lower body");
        System.out.println("\tback -> go back to record menu");
    }

    //EFFECT: ask which exercise they want to create under the category chosen
    private void whichExercise() {
        System.out.println("\nWhich exercise did you do in that category? (ex. bench)");
    }

    //EFFECT: ask user their record weight number
    private void whatWeight() {
        System.out.println("\nWhat weight number did you achieve?");
    }

    //EFFECT: asks what category user wants to add under and checks to see if category is valid
    //        returns category that user chose
    public String findCategory() {

        while (true) {
            whichCategory();
            String category = input.next();
            category = category.toLowerCase();
            if (checker("category", category)) {
                return category;
            } else {
                System.out.println("Invalid entry. Please select one of the options.");
            }
        }
    }

    //EFFECT: check to see if user wants to add uncommon workout,
    //        return exercise if yes, run method again if no, return output if quit
    public String findExercise(String category) {

        while (true) {
            whichExercise();
            String exercise = input.next().toLowerCase();
            boolean inWorkoutList = records.whichWorkoutList(category, exercise);
            boolean con = true;
            if (inWorkoutList) {
                return exercise;
            }
            notCommonWorkout(exercise);
            while (!inWorkoutList && con) {
                notCommonWorkoutOptions();
                String output = input.next().toLowerCase();
                if (output.equals("y")) {
                    return exercise;
                } else if (output.equals("n")) {
                    con = false;
                } else if (output.equals("q")) {
                    return output;
                } else {
                    System.out.println("Please enter y/n/q.");
                }
            }
        }
    }

    //EFFECT: prints statement saying their workout isn't in common list
    //        and asks if they want to add to uncommon workouts
    private void notCommonWorkout(String exercise) {
        System.out.println("\n" + exercise + " isn't in our common workouts for this category.");
        System.out.println("\nwould you like to add " + exercise + " as one of your records?");
    }

    //EFFECT: prints statement of options for uncommon workout
    private void notCommonWorkoutOptions() {
        System.out.println("\ty -> yes add to my records");
        System.out.println("\tn -> no do not add to my records");
        System.out.println("\tq -> go back to record menu");
    }

    //EFFECT: ask user for weight, checks if input is number and catches if not
    public String findWeight() {

        while (true) {
            whatWeight();
            String weight = input.next();

            try {
                Integer.parseInt(weight);
                return weight;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please input a number.");
            }
        }
    }

    //MODIFIES: records.json
    // EFFECTS: saves the workroom to file
    private void saveRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(records);
            jsonWriter.close();
            System.out.println("Saved your records to " + JSON_STORE);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadRecords() {
        try {
            records = jsonReader.read();
            System.out.println("Loaded " + records.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

