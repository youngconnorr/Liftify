package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {

    private final LiftifyUI controller;

    public Tab(LiftifyUI controller) {
        this.controller = controller;
    }

    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    public LiftifyUI getController() {
        return controller;
    }
}
