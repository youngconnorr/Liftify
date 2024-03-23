package ui.tabs;

import ui.LiftifyUI;

import javax.swing.*;
import java.awt.*;

//Home tab and is the first tab users see when opening application
public class HomeTab extends Tab {

    private JLabel iconHolder;

    //EFFECTS: constructs a home tab with a picture that has a greeting graphic
    public HomeTab(LiftifyUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 3));

        placeGreeting();
    }

    //MODIFIES: this
    //EFFECTS: adds picture to interface and positions it
    private void placeGreeting() {

        ImageIcon icon = new ImageIcon(getClass().getResource("workout.jpg"));
        iconHolder = new JLabel(icon);

        this.setLayout(new BorderLayout());
        this.add(iconHolder, BorderLayout.NORTH);
    }


}
