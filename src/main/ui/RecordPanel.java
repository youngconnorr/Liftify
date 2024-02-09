package ui;

import model.Workouts;

import java.util.LinkedHashMap;
import java.util.Scanner;

// Represents the different types of records
public class RecordPanel extends Workouts {
    Scanner input;
    private UserPanel ui;
    protected LinkedHashMap<String, String> pushRecords;
    protected LinkedHashMap<String, String> pullRecords;
    protected LinkedHashMap<String, String> legsRecords;

    //EFFECT: creates empty lists of records and calls common workouts from supertype
    public RecordPanel() {
        super();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        pushRecords = new LinkedHashMap<>(); // is a list of all push workout records (ie. Push: 135)
        pullRecords = new LinkedHashMap<>(); // is a list of all pull workout records
        legsRecords = new LinkedHashMap<>(); // is a list of all leg workout records
    }

    //EFFECT: Runs whichPath to determine path user wants to go, call methods accordingly
    protected String runRecordPanel() {
        boolean runSystem = true;

        while (runSystem) {

            String chosenPath = whichPath();
            if (chosenPath.equals("view")) {
                String pathFromView = runView();
                if (pathFromView.equals("back")) {
                    return "back";
                }
            } else if (chosenPath.equals("create")) {
                createRecord();
            } else if (chosenPath.equals("back")) {
                runSystem = false;
            }
        }
        return "back";
    }

    //EFFECT: display records lists
    public void viewRecords() {
        System.out.println("\nPush Records:");
        System.out.println(pushRecords);
        System.out.println("\nPull Records:");
        System.out.println(pullRecords);
        System.out.println("\nLegs Records:");
        System.out.println(legsRecords);
        System.out.println("\n");
        System.out.println("back -> go back to main menu");
    }

    //EFFECT:display the records lists and calls checkBackView to see if user wants to go back
    public String runView() {

        viewRecords();
        String nextPath = input.next();
        nextPath = nextPath.toLowerCase();
        return checkBackView(nextPath);
    }

    //EFFECT: check to see if user typed back and repeat if they didn't
    public String checkBackView(String nextPath) {
        while (true) {
            if (nextPath.equals("back")) {
                return "back";
            } else {
                System.out.println("Please input valid option.");
                String rightPath = input.next();
                rightPath = rightPath.toLowerCase();
                checkBackView(rightPath);
            }
        }
    }

    //EFFECT: creates a record and check if user inputs back at any point
    public void createRecord() {

        while (true) {
            String category = findCategory();
            if (category.equals("back")) {
                runRecordPanel();
                break;
            }

            String exercise = findExercise(category);
            if (exercise.equals("q")) {
                runRecordPanel();
                break;
            }

            String weight = findWeight();

            addWorkoutToRecord(category, exercise, weight);
            System.out.println("Saved to records!");
        }
    }

    //EFFECT: ask which path user wants to go inside of records
    public void path() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tview   -> view your personal records");
        System.out.println("\tcreate -> create a new personal record");
        System.out.println("\tback   -> go back to main menu");
    }

    //EFFECT: run path and checkPath function, return value to runRecordPanel
    public String whichPath() {
        String path = null;

        while (true) {
            path();
            path = input.next();
            path = path.toLowerCase();
            if (checkPath(path)) {
                return path;
            } else {
                System.out.println("Invalid entry. Please select one of the options.");

            }
        }
    }

    //EFFECT: check user input is valid
    public boolean checkPath(String p) {
        return p.equals("view") || p.equals("create") || p.equals("back");
    }

    //EFFECT: print options of workout categories to user
    public void whichCategory() {
        System.out.println("\nChoose the category this workout is under:");
        System.out.println("\tpush -> workouts with chest and triceps");
        System.out.println("\tpull -> workouts with back and biceps");
        System.out.println("\tlegs -> workouts with lower body");
        System.out.println("\tback -> go back to user menu");
    }

    //EFFECT: check user input is valid
    public boolean checkCategory(String p) {
        return p.equals("push") || p.equals("pull") || p.equals("legs") || p.equals("back");
    }

    //EFFECT: ask which exercise of user
    public void whichExercise() {
        System.out.println("Which exercise did you do in that category? (ex. bench)");
    }

    //EFFECT: ask user their record weight number
    public void whatWeight() {
        System.out.println("What weight number did you achieve?");
    }

    //MODIFIES: this
    //EFFECT: check category chosen and call methods that adds to list
    public void addWorkoutToRecord(String category, String exercise, String weight) {
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

    //EFFECT: calls checkCategory and returns input of user to createRecord
    public String findCategory() {
        String category = null;

        while (true) {
            whichCategory();
            category = input.next();
            category = category.toLowerCase();
            if (checkCategory(category)) {
                return category;
            } else {
                System.out.println("Invalid entry. Please select one of the options.");
            }
        }
    }

    //EFFECT: check if the workout is in the common workout list or not return boolean
    public boolean whichWorkoutList(String c, String e) {
        if (c.equals("push")) {
            return push.contains(e);
        } else if (c.equals("pull")) {
            return pull.contains(e);
        } else if (c.equals("legs")) {
            return legs.contains(e);
        } else {
            return false;
        }
    }

    //EFFECT: check whichWorkoutList and see if user wants to add workout or not to list
    //        if whichWorkoutList returns false
    public String findExercise(String category) {
        String exercise = null;

        while (true) {
            whichExercise();
            exercise = input.next().toLowerCase();
            boolean inWorkoutList = whichWorkoutList(category, exercise);
            boolean con = true;
            if (inWorkoutList) {
                return exercise;
            }
            while (!inWorkoutList && con) {
                System.out.println(exercise + " isn't in our common workouts.");
                System.out.println("\nwould you like to add " + exercise + " as one of your records?");
                System.out.println("\ty -> yes add to my records");
                System.out.println("\tn -> no do not add to my records");
                System.out.println("\tq -> go back to menu");
                String output = input.next();
                if (output.equals("y")) {
                    System.out.println("Okay");
                    return exercise;
                } else if (output.equals("n")) {
                    con = false;
                } else if (output.equals("q")) {
                    return output;
                } else {
                    System.out.println("Please enter Yes or no.");
                }
            }
        }
    }

    //EFFECT: ask user for weight, checks if input is number and catches if not
    public String findWeight() {
        String weight = null;

        while (true) {
            whatWeight();
            weight = input.next();

            try {
                int number = Integer.parseInt(weight);
                return weight;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please input a number.");
            }
        }
    }
}

