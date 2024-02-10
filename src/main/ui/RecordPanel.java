package ui;

import model.Records;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

// Represents the different types of records
public class RecordPanel extends Records {
    Scanner input;

    //EFFECT: creates empty lists of records and calls common workouts from supertype
    public RecordPanel() {
        super();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECT: Runs whichPath to determine path user wants to go, call methods accordingly
    protected String runRecordPanel() {

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
        }
        return "back";
    }

    //EFFECT: display records lists
    public void viewRecords() {
        System.out.println("\nPush Records:" + pushRecords);
        System.out.println("\nPull Records: " + pullRecords);
        System.out.println("\nLegs Records:" + legsRecords);
    }

    public void viewRecordsOptions() {
        System.out.println("\nremove -> remove a record from a category");
        System.out.println("back -> go back to record menu");
    }

    //EFFECT:display the records lists and calls checkBackView to see if user wants to go back
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
                if (removeWorkout().equals("back")) {
                    return "back";
                }
            } else {
                System.out.println("Please input valid option.");
            }
        }
    }

    protected void ignoreRecordsEmpty() {
        for (Map.Entry<String, LinkedHashMap<String, String>> categories : nameOfRecords.entrySet()) {
            String category = categories.getKey();
            LinkedHashMap<String, String> categoryRecords = categories.getValue();

            if (!categoryRecords.isEmpty()) {
                System.out.println("\n" + category + " | ");
                for (Map.Entry<String, String> record : categoryRecords.entrySet()) {
                    System.out.println(record.getKey() + ":" + record.getValue());
                }
            }
        }
    }

    public String removeWorkout() {
//        String cat = categoryToRemoveText();
//        if (!cat.equals("back")) {
        String work = workoutToRemove(); //took away cat parameter
        if (work.equals("back")) {
            return "back";
        } else {
            return "back";
        }
    }

    public String workoutToRemove() {
        while (true) {
            System.out.println("\nChoose your category or go back.");
            System.out.println("\nback -> go back to the record menu");
            ignoreRecordsEmpty();
            String cat = input.next().toLowerCase();
            if (cat.equals("back")) {
                return "back";
            } else if (checker("category", cat)) {
                System.out.println("\nNow choose your workout");
                String workout = input.next().toLowerCase();
                if (workout.equals("back")) {
                    return "back";
                } else if (whichRecordList(cat, workout)) {
                    super.removeWorkoutFromRecord(cat, workout);
                    System.out.println("Workout Removed");
                    return "back";
                } else {
                    System.out.println("That workout doesn't exist.");
                }
            }
        }
    }


    public boolean checker(String e, String userInput) {
        if (e.equals("category")) {
            return userInput.equals("push")
                    || userInput.equals("pull")
                    || userInput.equals("legs")
                    || userInput.equals("back");
        } else if (e.equals("path")) {
            return userInput.equals("view")
                    || userInput.equals("create")
                    || userInput.equals("back");
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
                super.addWorkoutToRecord(category, exercise, weight);
                super.addUncommonWorkout(category, exercise);
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
    public void path() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tview   -> view your personal records");
        System.out.println("\tcreate -> create a new personal record");
        System.out.println("\tback   -> go back to main menu");
    }

    //EFFECT: run path and checkPath function, return value to runRecordPanel
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
    public void whichCategory() {
        System.out.println("\nChoose the category this workout is under:");
        System.out.println("\tpush -> workouts with chest and triceps");
        System.out.println("\tpull -> workouts with back and biceps");
        System.out.println("\tlegs -> workouts with lower body");
        System.out.println("\tback -> go back to record menu");
    }

    //EFFECT: ask which exercise of user
    public void whichExercise() {
        System.out.println("\nWhich exercise did you do in that category? (ex. bench)");
    }

    //EFFECT: ask user their record weight number
    public void whatWeight() {
        System.out.println("\nWhat weight number did you achieve?");
    }

    //EFFECT: calls checkCategory and returns input of user to createRecord
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

    //EFFECT: check whichWorkoutList and see if user wants to add workout or not to list
//        if whichWorkoutList returns false
    public String findExercise(String category) {

        while (true) {
            whichExercise();
            String exercise = input.next().toLowerCase();
            boolean inWorkoutList = whichWorkoutList(category, exercise);
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

    public void notCommonWorkout(String exercise) {
        System.out.println("\n" + exercise + " isn't in our common workouts for this category.");
        System.out.println("\nwould you like to add " + exercise + " as one of your records?");
    }

    public void notCommonWorkoutOptions() {
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
}

