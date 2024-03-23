package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;


//creates a connection between tabs and LiftifyUI
public class Tab extends JPanel {

    private final LiftifyUI controller;

    public Tab(LiftifyUI controller) {
        this.controller = controller;
    }

    //EFFECTS: returns LiftifyUI object
    public LiftifyUI getController() {
        return controller;
    }

    //EFFECTS: display pop-up that the operation was a success
    public static void successMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    //EFFECTS: display pop-up that there was an error
    public static void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //EFFECTS: check if inputted category is a correct category, returns true if category is correct, false otherwise
    public boolean notNullCategory(String category) {
        return category.equals("push")
                ||
                category.equals("pull")
                ||
                category.equals("legs");
    }
}
