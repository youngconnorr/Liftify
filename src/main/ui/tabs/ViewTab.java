package ui.tabs;

import model.Records;
import persistence.JsonReader;
import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public ViewTab(LiftifyUI controller) {
        super(controller);
        jsonReader = new JsonReader(JSON_STORE);

        setLayout(new GridLayout(10, 10));



        label = new JLabel("Workout Records");
        label.setBounds(100, 50, 200, 50);

        removeBtn = new JButton("Remove Record");
        removeBtn.setBounds(50, 50, 100, 50);

        saveBtn = new JButton("Save Records");
        saveBtn.setBounds(50, 50, 100, 50);

        loadBtn = new JButton("Load Old Records");
        loadBtn.setBounds(50, 50, 100, 50);
        loadBtn.addActionListener(this);

        this.add(label);
        createListsDisplay();
        addToLists();
        this.add(removeBtn);
        this.add(saveBtn);
        this.add(loadBtn);
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
        if (e.getSource().equals(removeBtn)) {
            //TODO add remove
        } else if (e.getSource().equals(saveBtn)) {
           //TODO add save
        } else if (e.getSource().equals(loadBtn)) {
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