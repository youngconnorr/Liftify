package ui.tabs;

import model.Records;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

//contains methods for user to view their records, save their records, and load their records
public class ViewTab extends Tab implements ActionListener {
    private static final String JSON_STORE = "./data/records.json";
    private DefaultListModel<LinkedHashMap<String, String>> pushList;
    private DefaultListModel<LinkedHashMap<String, String>> pullList;
    private DefaultListModel<LinkedHashMap<String, String>> legsList;
    private JList<LinkedHashMap<String, String>> pushUIList;
    private JList<LinkedHashMap<String, String>> pullUIList;
    private JList<LinkedHashMap<String, String>> legsUIList;
    private JLabel label;
    private JButton removeBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //MODIFIES: this
    //EFFECTS: creates category lists, save button, and load button
    public ViewTab(LiftifyUI controller) {
        super(controller);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        setLayout(new BorderLayout());

        label = new JLabel("Workout Records", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel listsPanel = new JPanel(new GridLayout(6, 1));
        createListsDisplay();
        addToLists();
        listsPanel.add(new JLabel("push records"));
        listsPanel.add(new JScrollPane(pushUIList));
        listsPanel.add(new JLabel("pull records"));
        listsPanel.add(new JScrollPane(pullUIList));
        listsPanel.add(new JLabel("legs records"));
        listsPanel.add(new JScrollPane(legsUIList));
        add(listsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        saveBtn = new JButton("Save Records");
        saveBtn.addActionListener(this);
        buttonPanel.add(saveBtn);

        loadBtn = new JButton("Load Old Records");
        loadBtn.addActionListener(this);
        buttonPanel.add(loadBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    //EFFECTS: Creates the displayed lists of each category
    public void createListsDisplay() {
        pushList = new DefaultListModel<>();
        pullList = new DefaultListModel<>();
        legsList = new DefaultListModel<>();

        pushUIList = new JList<>(pushList);
        pullUIList = new JList<>(pullList);
        legsUIList = new JList<>(legsList);
        pushUIList.setBounds(100, 100, 200, 100);
        pullUIList.setBounds(100, 100, 200, 100);
        legsUIList.setBounds(100, 100, 200, 100);

        this.add(pushUIList);
        this.add(pullUIList);
        this.add(legsUIList);
    }

    //MODIFIES: pushList, pullList, legsList
    //EFFECTS: adds current records to the display UI
    public void addToLists() {
        pushList.addElement(getController().getRecord().getPushRecords());
        pullList.addElement(getController().getRecord().getPullRecords());
        legsList.addElement(getController().getRecord().getLegsRecords());
    }

    //EFFECTS: save or load workouts depending on source
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveBtn)) {
            saveCurrentWorkouts();
        } else if (e.getSource().equals(loadBtn)) {
            loadOldWorkouts();
        }
    }

    //MODIFIES: LiftifyUI records
    //EFFECTS: write workouts into JSON file
    private void saveCurrentWorkouts() {
        try {
            jsonWriter.open();
            jsonWriter.write(getController().getRecord());
            jsonWriter.close();
            successMessage("Saved your records to " + JSON_STORE);
        } catch (FileNotFoundException fnfe) {
            errorMessage("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: LiftifyUI records
    //EFFECTS: adds JSON files information into records lists
    private void loadOldWorkouts() {
        try {
            clearWorkouts();
            Records loadedRecord = jsonReader.read();
            LinkedHashMap<String, String> loadedPush = loadedRecord.getPushRecords();
            LinkedHashMap<String, String> loadedPull = loadedRecord.getPullRecords();
            LinkedHashMap<String, String> loadedLegs = loadedRecord.getLegsRecords();
            addOldToPush(loadedPush);
            addOldToPull(loadedPull);
            addOldToLegs(loadedLegs);

            successMessage("Loaded " + loadedRecord.getName() + " from " + JSON_STORE);
        } catch (IOException e1) {
            errorMessage("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODFIES: LiftifyUI records
    //EFFECTS: clears current workout records
    private void clearWorkouts() {
        getController().getRecord().getPushRecords().clear();
        getController().getRecord().getPullRecords().clear();
        getController().getRecord().getLegsRecords().clear();
    }

    //MODFIES: LiftifyUI records
    //EFFECTS: adds JSON information to push records
    private void addOldToPush(LinkedHashMap<String, String> loaded) {
        for (Map.Entry<String, String> entry : loaded.entrySet()) {
            getController().getRecord().getPushRecords().put(entry.getKey(), entry.getValue());
        }
    }

    //MODFIES: LiftifyUI records
    //EFFECTS: adds JSON information to push records
    private void addOldToPull(LinkedHashMap<String, String> loaded) {
        for (Map.Entry<String, String> entry : loaded.entrySet()) {
            getController().getRecord().getPullRecords().put(entry.getKey(), entry.getValue());
        }
    }

    //MODFIES: LiftifyUI records
    //EFFECTS: adds JSON information to push records
    private void addOldToLegs(LinkedHashMap<String, String> loaded) {
        for (Map.Entry<String, String> entry : loaded.entrySet()) {
            getController().getRecord().getLegsRecords().put(entry.getKey(), entry.getValue());
        }
    }
}