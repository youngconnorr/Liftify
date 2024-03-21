package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;

public class HomeTab extends Tab {

    private static final String INIT_GREETING = "Welcome, choose to create or view your records";
    private JLabel greeting;

    public HomeTab(LiftifyUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 3));

        placeGreeting();
    }

    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }


}
