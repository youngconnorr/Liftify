package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Contains methods to make a CreateTab for users to add to their records
public class CreateTab extends Tab implements ActionListener {
    private static final String INTRO_TEXT = "Create a new workout record";
    private JButton submit;
    private JLabel category;
    private JLabel exercise;
    private JLabel weight;
    private JTextField categoryText;
    private JTextField exerciseText;
    private JTextField weightText;
    private JPanel categoryPanel;
    private JPanel exercisePanel;
    private JPanel weightPanel;
    private JPanel buttonPanel;

    //EFFECTS: creates the UI of CreateTab
    public CreateTab(LiftifyUI controller) {
        super(controller);
        setSize(100, 100);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        placeContainer();

    }

    //EFFECTS: creates category, exercise, and weight textboxes for user input and a submit button
    public void placeContainer() {
        category = new JLabel("Category (push/pull/legs): ");
        categoryText = new JTextField(20);

        exercise = new JLabel("Exercise: ");
        exerciseText = new JTextField(20);

        weight = new JLabel("Weight: ");
        weightText = new JTextField(20);

        submit = new JButton("Create new record");
        submit.addActionListener(this);

        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(category);
        categoryPanel.add(categoryText);

        exercisePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exercisePanel.add(exercise);
        exercisePanel.add(exerciseText);

        weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightPanel.add(weight);
        weightPanel.add(weightText);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submit);

        addToPanel();
    }

    //MODIFIES: this
    //EFFECTS: positions and adds buttons to interface
    public void addToPanel() {
        add(Box.createVerticalStrut(10)); // Add spacing between components
        add(categoryPanel);
        add(Box.createVerticalStrut(10)); // Add spacing between components
        add(exercisePanel);
        add(Box.createVerticalStrut(10)); // Add spacing between components
        add(weightPanel);
        add(Box.createVerticalStrut(10)); // Add spacing between components
        add(buttonPanel);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the container

    }

    //MODIFIES: LiftifyUI records
    //EFFECTS: add inputted category, workout, and weight to records
    public void addWorkout(String c, String exercise, String weight) {

        getController().getRecord().addWorkoutToRecord(c, exercise, weight);
    }

    //EFFECTS: check if boxes are empty, returns true if any boxes are empty, false otherwise
    public boolean boxesEmpty() {
        return categoryText.getText().isEmpty()
                || exerciseText.getText().isEmpty()
                || weightText.getText().isEmpty();
    }

    //EFFECTS: finds any mis-inputs from user, calls addWorkout
    @Override
    public void actionPerformed(ActionEvent e) {
        String category = categoryText.getText().toLowerCase();
        String exercise = exerciseText.getText().toLowerCase();
        String weight = weightText.getText().toLowerCase();
        if (boxesEmpty()) {
            errorMessage("Please fill out all boxes");
        } else if (!(notNullCategory(category))) {
            errorMessage("Please put one of these categories (push/pull/legs)");
        } else if (!boxesEmpty() && notNullCategory(category)) {
            addWorkout(category, exercise, weight);
            categoryText.setText("");
            exerciseText.setText("");
            weightText.setText("");
            successMessage("Added to records!");
        }
    }

}
