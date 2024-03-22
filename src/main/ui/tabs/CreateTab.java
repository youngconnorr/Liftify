package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTab extends Tab implements ActionListener {
    private static final String INTRO_TEXT = "Create a new workout record";
    private JButton submit;
    private JLabel category;
    private JLabel exercise;
    private JLabel weight;
    private JTextField categoryText;
    private JTextField exerciseText;
    private JTextField weightText;

    public CreateTab(LiftifyUI controller) {
        super(controller);
        setSize(100, 100);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        placeContainer();

    }

    public void placeContainer() {
        category = new JLabel("Category (push/pull/legs): ");
        categoryText = new JTextField(20);

        exercise = new JLabel("Exercise: ");
        exerciseText = new JTextField(20);

        weight = new JLabel("Weight: ");
        weightText = new JTextField(20);

        submit = new JButton("Create new record");
        submit.addActionListener(this);

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(category);
        categoryPanel.add(categoryText);

        JPanel exercisePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exercisePanel.add(exercise);
        exercisePanel.add(exerciseText);

        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightPanel.add(weight);
        weightPanel.add(weightText);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submit);

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

    public void addWorkout(String c, String w, String l) {

        getController().getRecord().addWorkoutToRecord(c, w, l);
    }

    public boolean boxesEmpty() {
        return categoryText.getText().isEmpty()
                || exerciseText.getText().isEmpty()
                || weightText.getText().isEmpty();
    }

    public boolean notNullCategory() {
        return categoryText.getText().equals("push")
                ||
                categoryText.getText().equals("pull")
                ||
                categoryText.getText().equals("legs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String category = categoryText.getText();
        String exercise = exerciseText.getText();
        String weight = weightText.getText();
        if (!boxesEmpty() && notNullCategory()) {
            addWorkout(category, exercise, weight);
            System.out.println("saved: " + category + " and " + exercise + " and " + weight);
        }
    }
}
