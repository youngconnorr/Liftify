package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

//contains methods to remove workouts from records and create an interface
public class RemoveTab extends Tab implements ActionListener {
    private JButton submit;
    private JButton search;
    private JLabel label;
    private JLabel category;
    private JLabel exercise;
    private JTextField categoryText;
    private JTextField exerciseText;
    private JPanel listsPanel;

    //EFFECTS: place labels and panels for interface
    public RemoveTab(LiftifyUI controller) {
        super(controller);
        setSize(100, 100);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        label = new JLabel("Workout Records", SwingConstants.CENTER);
        add(label, BorderLayout.PAGE_START);

        listsPanel = new JPanel(new GridLayout(0, 1)); // Create a panel for lists using GridLayout

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

    //MODIFIES: this
    //EFFECTS: Creates and adds text boxes for categories and exercises, creates submit and search button
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
        this.add(Box.createVerticalStrut(10));
        this.add(listsPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(exercisePanel);
        this.add(Box.createVerticalStrut(10));
        this.add(buttonPanel);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    //MODIFIES: LiftifyUI records
    //EFFECTS: removes specified workout from records
    public void removeWorkout(String cat, String exercise) {
        getController().getRecord().removeWorkoutFromRecord(cat, exercise);
    }

    //EFFECTS: check to see if text boxes are empty, returns true if any boxes are empty, false otherwise
    public boolean boxesEmpty() {
        return categoryText.getText().isEmpty() || exerciseText.getText().isEmpty();
    }

    //MODIFIES: this
    //EFFECTS: checks user inputs, and calls to remove specified workout or display list of specified category
    @Override
    public void actionPerformed(ActionEvent e) {
        String category = categoryText.getText().toLowerCase();
        String exercise = exerciseText.getText().toLowerCase();
        if (e.getSource().equals(submit)) {
            if (boxesEmpty()) {
                errorMessage("Please fill out all boxes");
            } else if (!(notNullCategory(category))) {
                errorMessage("Please put one of these categories (push/pull/legs");
            } else if (!(checkExercise(exercise, category))) {
                errorMessage("Please put a valid workout");
            } else if (!boxesEmpty() && notNullCategory(category) && checkExercise(exercise, category)) {
                removeWorkout(category, exercise);
                categoryText.setText("");
                exerciseText.setText("");
                listsPanel.removeAll();
                successMessage("Removed: " + exercise + " from your " + category + " records.");
            }
        }

        if (e.getSource().equals(search)) {
            addToSearchedList(category);
        }
    }

    //MODIFIES: this
    //EFFECTS: create list of workouts for specified category
    public void addToSearchedList(String category) {
        listsPanel.removeAll();
        if (category.equals("push")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getPushRecords().entrySet()) {
                JLabel keyLabel = new JLabel(entry.getKey());
                listsPanel.add(keyLabel);
            }
        } else if (category.equals("pull")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getPullRecords().entrySet()) {
                JLabel keyLabel = new JLabel(entry.getKey());
                listsPanel.add(keyLabel);
            }
        } else if (category.equals("legs")) {
            for (Map.Entry<String, String> entry : getController().getRecord().getLegsRecords().entrySet()) {
                JLabel keyLabel = new JLabel(entry.getKey());
                listsPanel.add(keyLabel);
            }
        }
    }

    //EFFECTS: check if inputted workout is in list, return true if inside list, false otherwise
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

