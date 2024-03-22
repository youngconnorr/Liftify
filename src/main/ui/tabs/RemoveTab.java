package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class RemoveTab extends Tab implements ActionListener {
    private static final String INTRO_TEXT = "Remove a workout record";
    private JButton submit;
    private JButton search;
    private JLabel category;
    private JLabel exercise;
    private JTextField categoryText;
    private JTextField exerciseText;
    private JPanel listsPanel;

    public RemoveTab(LiftifyUI controller) {
        super(controller);
        setSize(100, 100);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        listsPanel = new JPanel(new GridLayout(1, 2)); // Create a panel for lists using GridLayout

        category = new JLabel("Category (push/pull/legs): ");
        categoryText = new JTextField(20);

        search = new JButton("Search category for workouts");
        search.addActionListener(this);

        exercise = new JLabel("Exercise: ");
        exerciseText = new JTextField(20);

        submit = new JButton("Remove record");
        submit.addActionListener(this);

        placeContainer();

    }

    public void placeContainer() {

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.add(category);
        categoryPanel.add(categoryText);
        categoryPanel.add(search);


        JPanel exercisePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exercisePanel.add(exercise);
        exercisePanel.add(exerciseText);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submit);


        this.add(Box.createVerticalStrut(10)); // Add spacing between components
        this.add(categoryPanel);
        this.add(Box.createVerticalStrut(10)); // Add spacing between components
        this.add(listsPanel);
        this.add(Box.createVerticalStrut(10)); // Add spacing between components
        this.add(exercisePanel);
        this.add(Box.createVerticalStrut(10)); // Add spacing between components
        this.add(buttonPanel);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the container
    }

    public void removeWorkout(String cat, String exercise) {

        getController().getRecord().removeWorkoutFromRecord(cat, exercise);
    }

    public boolean boxesEmpty() {
        return categoryText.getText().isEmpty() || exerciseText.getText().isEmpty();
    }

    public boolean notNullCategory() {
        return categoryText.getText().toLowerCase().equals("push")
                ||
                categoryText.getText().toLowerCase().equals("pull")
                ||
                categoryText.getText().toLowerCase().equals("legs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel errorInputText;
        JLabel removedText;
        String category = categoryText.getText().toLowerCase();
        String exercise = exerciseText.getText().toLowerCase();
        if (e.getSource().equals(submit)) {
            if (boxesEmpty()) {

                errorInputText = new JLabel("Please fill out all boxes");
                this.add(errorInputText);
            } else if (!(notNullCategory())) {
                errorInputText = new JLabel("Please put a valid category (push/pull/legs)");
                this.add(errorInputText);
            } else if (!(checkExercise(exercise, category))) {
                errorInputText = new JLabel("Please put a valid workout");
                this.add(errorInputText);
            }  else if (!boxesEmpty() && notNullCategory() && checkExercise(exercise, category)) {
                removeWorkout(category, exercise);
                listsPanel.removeAll();
                errorInputText = new JLabel("");
                removedText = new JLabel("Removed: " + exercise + " from your " + category + "records.");
                add(removedText);
                this.add(errorInputText);
            }
        }

        if (e.getSource().equals(search)) {
            addToSearchedList(category);
        }
    }

    public void addToSearchedList(String category) {
        listsPanel.removeAll();
        if (category.equals("push")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getPushRecords().entrySet()) {
                JLabel keyLabel = new JLabel(entry.getKey());
                JLabel valueLabel = new JLabel(entry.getValue());
                listsPanel.add(keyLabel);
                listsPanel.add(valueLabel);
            }
        } else if (category.equals("pull")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getPullRecords().entrySet()) {
                JLabel keyLabel = new JLabel(entry.getKey());
                JLabel valueLabel = new JLabel(entry.getValue());
                listsPanel.add(keyLabel);
                listsPanel.add(valueLabel);
            }
        } else if (category.equals("legs")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getLegsRecords().entrySet()) {
                JLabel keyLabel = new JLabel(entry.getKey());
                JLabel valueLabel = new JLabel(entry.getValue());
                listsPanel.add(keyLabel);
                listsPanel.add(valueLabel);
            }
        }
    }

    public boolean checkExercise(String e, String category) {
        if (category.equals("push")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getPushRecords().entrySet()) {
                String key = entry.getKey();
                if (key.equals(e)) {
                    return true;
                }
            }
        } else if (category.equals("pull")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getPullRecords().entrySet()) {
                String key = entry.getKey();
                if (key.equals(e)) {
                    return true;
                }
            }
        } else if (category.equals("legs")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getLegsRecords().entrySet()) {
                String key = entry.getKey();
                if (key.equals(e)) {
                    return true;
                }
            }
        }

        return false;
    }
}

