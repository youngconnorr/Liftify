package ui;

import model.Records;

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
        System.out.println("\nremove -> remove a record from a category");
        System.out.println("back -> go back to record menu");
    }

    //EFFECT:display the records lists and calls checkBackView to see if user wants to go back
    public String runView() {

        viewRecords();
        String nextPath = input.next().toLowerCase();
        return checkBackView(nextPath);
    }

    //EFFECT: check to see if user typed back and repeat if they didn't
    public String checkBackView(String nextPath) {
        while (true) {
            if (nextPath.equals("back")) {
                return "back";
            } else if (nextPath.equals("remove")) {
                removeWorkout();
            } else {
                System.out.println("Please input valid option.");
                String rightPath = input.next().toLowerCase();
                checkBackView(rightPath);
            }
        }
    }

    public void removeWorkout() {
        String cat = categoryToRemove();
        if (!cat.equals("back")) {
            String work = workoutToRemove(cat);
            if (!work.equals("back")) {
                removeWorkoutFromRecord(cat, work);
                System.out.println("Workout Removed");
                runRecordPanel();
            } else {
                runRecordPanel();
            }
        } else {
            runRecordPanel();
        }
    }

    public void printCategoryRemoval() {
        System.out.println("\nChoose one of these categories to remove from or go back.");
        System.out.println("push -> push category");
        System.out.println("pull -> pull category");
        System.out.println("legs -> legs category");
        System.out.println("back -> go back to record menu");
    }

    public boolean pushChecker() {
        if (pushRecords.isEmpty()) {
            System.out.println("\nChoose one of these categories to remove from or go back.");
            System.out.println("push" + pushRecords);
            System.out.println("pull" + pullRecords);
            System.out.println("legs" + legsRecords);
            System.out.println("back -> go back to record menu");

        } else {
            return true;
        }
        return false;
    }

    public boolean pullChecker() {
        if (pullRecords.isEmpty()) {
            System.out.println("\nChoose one of these categories to remove from or go back.");
            System.out.println("push" + pushRecords);
            System.out.println("pull" + pullRecords);
            System.out.println("legs" + legsRecords);
            System.out.println("back -> go back to record menu");

        } else {
            return true;
        }
        return false;
    }

    public boolean legsChecker() {
        if (legsRecords.isEmpty()) {
            System.out.println("\nChoose one of these categories to remove from or go back.");
            System.out.println("push" + pushRecords);
            System.out.println("pull" + pullRecords);
            System.out.println("legs" + legsRecords);
            System.out.println("back -> go back to record menu");
        } else {
            return true;
        }
        return false;
    }


    public String categoryToRemove() {
        printCategoryRemoval();
        while (true) {
            String removeCat = input.next().toLowerCase();
            if (checker("category", removeCat)) {
                if (removeCat.equals("push")) {
                    if (pushChecker()) {
                        return removeCat;
                    }
                } else if (removeCat.equals("pull")) {
                    if (pullChecker()) {
                        return removeCat;
                    }
                } else if (removeCat.equals("legs")) {
                    if (legsChecker()) {
                        return removeCat;
                    }
                } else if (removeCat.equals("back")) {
                    runRecordPanel();
                }
            } else {
                categoryToRemove();
            }
        }
    }

    public String workoutToRemove(String cat) {
        while (true) {
            System.out.println("\nWhat workout would you like to remove from this category?");
            if (cat.equals("push")) {
                System.out.println(pushRecords);
            } else if (cat.equals("pull")) {
                System.out.println(pullRecords);
            } else if (cat.equals("legs")) {
                System.out.println(legsRecords);
            }
            System.out.println("\nback -> go back to record menu");
            String removeWork = input.next().toLowerCase();
            if (whichWorkoutList(cat, removeWork)) {
                return removeWork;
            } else {
                System.out.println("Please input a valid answer.");
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
            while (!inWorkoutList && con) {
                notCommonWorkout(exercise);
                String output = input.next();
                if (output.equals("y")) {
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

    public void notCommonWorkout(String exercise) {
        System.out.println("\n" + exercise + " isn't in our common workouts.");
        System.out.println("\nwould you like to add " + exercise + " as one of your records?");
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

