//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class MainMenuUI extends JFrame implements ActionListener {
//    private RecordPanel recordPanel;
//    private JButton view;
//    private JButton create;
//    private JButton save;
//    private JButton load;
//
//    public MainMenuUI() {
//        this.setTitle("Liftify Interface");
//        this.setSize(500, 500);
//        this.setLayout(new FlowLayout());
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        JLabel label = new JLabel("Main Menu");
//        this.add(label);
//
//        view = new JButton();
//        view.addActionListener(e -> view.setVisible(false));
//        view.setText("View");
//        view.setFocusable(false);
//        view.setBounds(0, 100, 100, 50);
//
//        create = new JButton();
//        create.addActionListener(e -> view.setVisible(false));
//        create.setText("Create");
//        create.setFocusable(false);
//
//        create.setBounds(100, 100, 100, 50);
//
//        save = new JButton();
//        save.addActionListener(e -> view.setVisible(false));
//        save.setText("Save");
//        save.setFocusable(false);
//        save.setBounds(200, 100, 100, 50);
//
//        load = new JButton();
//        load.addActionListener(e -> view.setVisible(false));
//        load.setText("Load");
//        load.setFocusable(false);
//        load.setBounds(300, 100, 100, 50);
//
//        this.add(view);
//        this.add(create);
//        this.add(save);
//        this.add(load);
//        this.setVisible(true);
//        recordPanel = new RecordPanel();
//
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
