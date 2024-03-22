package ui.tabs;

import model.Records;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.util.Map.Entry;
import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    public ViewTab(LiftifyUI controller) {
        super(controller);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        setLayout(new BorderLayout());

        label = new JLabel("Workout Records", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel listsPanel = new JPanel(new GridLayout(3, 1)); // Create a panel for lists using GridLayout
        createListsDisplay();
        addToLists();
        listsPanel.add(new JScrollPane(pushUIList));
        listsPanel.add(new JScrollPane(pullUIList));
        listsPanel.add(new JScrollPane(legsUIList));
        add(listsPanel, BorderLayout.CENTER); // Add the lists panel to the center

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); // Create a panel for buttons using GridLayout
        saveBtn = new JButton("Save Records");
        saveBtn.addActionListener(this);
        buttonPanel.add(saveBtn);

        loadBtn = new JButton("Load Old Records");
        loadBtn.addActionListener(this);
        buttonPanel.add(loadBtn);

        add(buttonPanel, BorderLayout.SOUTH); // Add the buttons panel to the bottom
    }

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

    public void addToLists() {
        pushList.addElement(getController().getRecord().getPushRecords());
        pullList.addElement(getController().getRecord().getPullRecords());
        legsList.addElement(getController().getRecord().getLegsRecords());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveBtn)) {
            saveCurrentWorkouts();
        } else if (e.getSource().equals(loadBtn)) {
            loadOldWorkouts();
        }
    }


    private void saveCurrentWorkouts() {
        try {
            jsonWriter.open();
            jsonWriter.write(getController().getRecord());
            jsonWriter.close();
            System.out.println("Saved your records to " + JSON_STORE);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadOldWorkouts() {
        try {
            clearWorkouts();
            Records loadedRecord = jsonReader.read();
            LinkedHashMap<String, String> loadedPush = loadedRecord.getPushRecords();
            LinkedHashMap<String, String> loadedPull = loadedRecord.getPushRecords();
            LinkedHashMap<String, String> loadedLegs = loadedRecord.getPushRecords();
            addOldToPush(loadedPush);
            addOldToPull(loadedPull);
            addOldToLegs(loadedLegs);

            System.out.println("Loaded " + loadedRecord.getName() + " from " + JSON_STORE);
        } catch (IOException e1) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    private void clearWorkouts() {
        getController().getRecord().getPushRecords().clear();
        getController().getRecord().getPullRecords().clear();
        getController().getRecord().getLegsRecords().clear();
    }

    private void addOldToPush(LinkedHashMap<String, String> loaded) {
        for (Map.Entry<String, String> entry : loaded.entrySet()) {
            getController().getRecord().getPushRecords().put(entry.getKey(), entry.getValue());
        }
    }

    private void addOldToPull(LinkedHashMap<String, String> loaded) {
        for (Map.Entry<String, String> entry : loaded.entrySet()) {
            getController().getRecord().getPushRecords().put(entry.getKey(), entry.getValue());
        }
    }

    private void addOldToLegs(LinkedHashMap<String, String> loaded) {
        for (Map.Entry<String, String> entry : loaded.entrySet()) {
            getController().getRecord().getPushRecords().put(entry.getKey(), entry.getValue());
        }
    }
}