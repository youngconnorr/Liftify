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
        category.setBounds(10, 20, 80, 25);
        categoryText = new JTextField(20);
        categoryText.setBounds(100, 20, 165, 25);

        exercise = new JLabel("Exercise: ");
        exercise.setBounds(10, 20, 80, 25);
        exerciseText = new JTextField(20);
        exerciseText.setBounds(100, 20, 165, 25);

        weight = new JLabel("Weight: ");
        exercise.setBounds(10, 20, 80, 25);
        weightText = new JTextField(20);
        weightText.setBounds(100, 20, 165, 25);
        submit = new JButton("Create new record");
        submit.addActionListener(this);

        this.add(category);
        this.add(categoryText);
        this.add(exercise);
        this.add(exerciseText);
        this.add(weight);
        this.add(weightText);
        this.add(submit);
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
