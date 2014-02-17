package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingScreen extends JFrame{
    private JPanel windowPanel;
    private JPanel displayPanel;
    private JLabel message;

    public WaitingScreen() {
        super("Waiting Window");
        windowPanel = new JPanel();
        setContentPane(windowPanel);
        windowPanel.setLayout(null);
        masterPanelSetup();
        addPanel();
        addLabel();

        Timer timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlayerScreen();
            }
        });
        timer.start();
        timer.setRepeats(false);
        setVisible(true);

    }

    private void masterPanelSetup() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addPanel(){
        displayPanel = new JPanel();
        displayPanel.setBounds(10, 70, 450, 100);
        displayPanel.setBackground(Color.WHITE);
        windowPanel.add(displayPanel);
    }

    public void addLabel(){
        displayPanel.setLayout(null);
        message = new JLabel("Waiting for the Connection to be established....",JLabel.CENTER);
        message.setFont(new Font("serif", Font.BOLD, 20));
        message.setBounds(25, 25, 400, 25);
        displayPanel.add(message);
    }
}
