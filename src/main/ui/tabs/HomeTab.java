package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;

public class HomeTab extends Tab {

    private static final String INIT_GREETING = "Welcome, choose to create or view your records";
    private JLabel greeting;
    private JLabel iconHolder;

    public HomeTab(LiftifyUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 3));

        placeGreeting();
    }

    private void placeGreeting() {

        ImageIcon icon = new ImageIcon(getClass().getResource("workout-background.jpg"));
        iconHolder = new JLabel(icon);
        greeting = new JLabel(INIT_GREETING, SwingConstants.CENTER);

        this.setLayout(new BorderLayout()); // Use BorderLayout to properly position components
        this.add(greeting, BorderLayout.CENTER); // Add the greeting label at the center
        this.add(iconHolder, BorderLayout.NORTH); // Add the image label at the top
    }


}
