package ui;

import model.Records;
import ui.tabs.CreateTab;
import ui.tabs.HomeTab;
import ui.tabs.RemoveTab;
import ui.tabs.ViewTab;

import javax.swing.*;
import java.awt.*;

public class LiftifyUI extends JFrame {
    public static final int HOME_TAB_INDEX  = 0;
    public static final int CREATE_TAB_INDEX = 1;
    public static final int REMOVE_TAB_INDEX = 2;
    public static final int VIEW_TAB_INDEX = 3;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private Records records;

    public static void main(String[] args) {
        new LiftifyUI();
    }

    private LiftifyUI() {
        super("Liftify Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        records = new Records("name");
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel createTab = new CreateTab(this);
        JPanel removeTab = new RemoveTab(this);
        JPanel viewTab = new ViewTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");

        sidebar.add(createTab, CREATE_TAB_INDEX);
        sidebar.setTitleAt(CREATE_TAB_INDEX, "Create");

        sidebar.add(removeTab, REMOVE_TAB_INDEX);
        sidebar.setTitleAt(REMOVE_TAB_INDEX, "Remove");

        sidebar.add(viewTab, VIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEW_TAB_INDEX, "View");
    }

    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    public Records getRecord() {
        return records;
    }

}
