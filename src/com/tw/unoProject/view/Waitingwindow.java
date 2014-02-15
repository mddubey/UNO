package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;

public class WaitingWindow extends JFrame{
    private JPanel windowPanel;
    private JPanel displayPanel;
    private JLabel message;

    public WaitingWindow() {
        super("Waiting Window");
        windowPanel = new JPanel();
        setContentPane(windowPanel);
        windowPanel.setLayout(null);
        masterPanelSetup();
        addPanel();
        addLabel();
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

    public static void main(String[] args) {
        WaitingWindow waitWindow = new WaitingWindow();
        waitWindow.setVisible(true);
    }
}
