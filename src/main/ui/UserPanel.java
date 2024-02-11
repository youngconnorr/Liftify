package ui;

import java.util.Scanner;

//The main menu of application
public class UserPanel {

    Scanner input;                //take in user input
    private final RecordPanel goToRecordPanel; //Create an instance of record ui


    public UserPanel() {
        goToRecordPanel = new RecordPanel();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userInterfaceUserPanel();
    }

    //EFFECT: prints main menu screen and handles logic of choice of user
    public void userInterfaceUserPanel() {
        String userMenuChoice = userMenu();
        boolean userDecision = decideChoice(userMenuChoice);
        if (userDecision) {
            String yesBack = goToRecordPanel.runRecordPanel();
            if (yesBack.equals("back")) {
                userInterfaceUserPanel();
            }
        } else {
            System.out.println("going to schedule when implemented!");
        }
    }

    //EFFECT: shows main menu and returns choice of user
    public String userMenu() {
        while (true) {
            userInterface();
            String choice = input.next();
            if (checkChoice(choice)) {
                return choice;
            } else {
                System.out.println("Please choose one of the options");
            }
        }
    }

    //EFFECT: prints main menu choices
    private void userInterface() {
        System.out.println("Main Menu:");
        System.out.println("\t1 -> Go to records");
        System.out.println("\t2 -> Go to schedule");
    }

    //EFFECT: checks to see if user choice is valid, return true if valid, false otherwise
    private boolean checkChoice(String choice) {
        return choice.equals("1") || choice.equals("2");
    }

    //EFFECT: handles logic of user choice and returns true if 1, false if 2
    public boolean decideChoice(String e) {
        if (e.equals("1")) {
            return true;
        } else if (e.equals("2")) {
            System.out.println("going to schedule when made!");
            return false;
        }
        return false;
    }
}