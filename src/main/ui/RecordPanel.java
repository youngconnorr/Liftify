package ui;

import model.Workouts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

//MAKE A LIST OF WORKOUTS !!!!!!!!!!!!!!!!!!!!!!!!!!
// Represents the different types of records
public class RecordPanel extends Workouts {
    Scanner input;
    private LinkedHashMap<String, String> push;
    private LinkedHashMap<String, String> pull;
    private LinkedHashMap<String, String> legs;


    public RecordPanel() {
        super();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        push = new LinkedHashMap<>(); // is a list of all push workout records (ie. Push: 135)
        pull = new LinkedHashMap<>(); // is a list of all pull workout records
        legs = new LinkedHashMap<>(); // is a list of all leg workout records
        runRecordPanel();
    }

    private void runRecordPanel() {
        boolean runSystem = true;

        while (runSystem) {

            String category = findCategory();
            if (category.equals("back")) {
                break;
            }

            String exercise = findExercise(category);
            if (exercise.equals("q")) {
                break;
            }

            String weight = findWeight();

            addWorkoutToRecord(category, exercise, weight);
            runSystem = false;
        }
        //go back to user
    }

//    public void path() {
//        System.out.println("\nWhat would you like to do?");
//        System.out.println("\tview   -> view your personal records");
//        System.out.println("\tcreate -> create a new personal record");
//    }
//
//    public void whichPath() {
//        String path = null;
//
//        path();
//        path = input.next();
//        path = path.toLowerCase();
//        if (checkPath(path)) {
//            return path;
//        } else {
//            System.out.println("Invalid entry. Please select one of the options.");
//        }
//    }

//    public boolean checkPath(String p) {
//        return p.equals("view") || p.equals("create");
//    }

    public void whichCategory() {
        System.out.println("\nChoose the category this workout is under:");
        System.out.println("\tpush -> workouts with chest and triceps");
        System.out.println("\tpull -> workouts with back and biceps");
        System.out.println("\tlegs -> workouts with lower body");
        System.out.println("\tback -> go back to user menu");
    }

    public boolean checkCategory(String p) {
        return p.equals("push") || p.equals("pull") || p.equals("legs") || p.equals("back");
    }

    public void whichExercise() {
        System.out.println("Which exercise did you do in that category?");
    }

    public void whatWeight() {
        System.out.println("What weight number did you achieve?");
    }

    public void addWorkoutToRecord(String category, String exercise, String weight) {
        switch (category) {
            case "push":
                addToPush(exercise, weight);
                break;
            case "pull":
                addToPull(exercise, weight);
                break;
            case "legs":
                addToLegs(exercise, weight);
                break;
        }
    }

    public void addToPush(String exercise, String weight) {
        push.put(exercise, weight);
    }

    public void addToPull(String exercise, String weight) {
        pull.put(exercise, weight);
    }

    public void addToLegs(String exercise, String weight) {
        legs.put(exercise, weight);
    }

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

    public String findExercise(String category) {
        String exercise = null;

        while (true) {
            whichExercise();
            exercise = input.next();
            exercise = exercise.toLowerCase();
            boolean con = true;

            while (!category.contains(exercise) && con) {
                System.out.println("Would you like to add " + exercise + " to list or quit? (y/n/q)");
                String output = input.next();
                if (output.equals("y")) {
                    System.out.println("Okay added workout to list");
                    exercise = output;
                    return exercise;
                } else if (output.equals("n")) {
                    con = false;
                } else if (output.equals("q")) {
                    exercise = output;
                    return exercise;
                } else {
                    System.out.println("Please enter Yes or no.");
                }
            }
        }
    }

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

