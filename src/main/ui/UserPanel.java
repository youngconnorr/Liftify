package ui;

import java.util.Scanner;


public class UserPanel {

    Scanner input;
    private String name;
    private RecordPanel goToRecordPanel;


    public UserPanel() {
        goToRecordPanel = new RecordPanel();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userInterfaceUserPanel();
    }

    public void userInterfaceUserPanel() {
        String userMenuChoice = userMenu();
        boolean userDecision = decideChoice(userMenuChoice);
        if (userDecision) {
            String returnFromPanel = goToRecordPanel.runRecordPanel();
            if (returnFromPanel.equals("back")) {
                userInterfaceUserPanel();
            } else {
                goToRecordPanel.runRecordPanel();
            }
        } else {
            System.out.println("going to schedule when implemented!");
        }
    }

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

    public void userInterface() {
//        System.out.println("Hi " + name + ", what would you like to do?");
        System.out.println("Main Menu:");
        System.out.println("\t1 -> Go to records");
        System.out.println("\t2 -> Go to schedule");
    }


    public boolean checkChoice(String choice) {
        return choice.equals("1") || choice.equals("2");
    }

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