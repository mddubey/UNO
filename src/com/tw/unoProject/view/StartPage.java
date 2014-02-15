package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {
    private JPanel joinMasterPanel;
    private JTextField gameMaster;
    private JTextField playerName;
    private JButton joinButton;
    private JLabel labelGameMaster;
    private JLabel nameLabel;

    public StartPage(){
        super("JOIN");
        joinMasterPanel = new JPanel();
        setContentPane(joinMasterPanel);
        setupForMasterPanel();


        addLabels();

        addTextFields();

        addJoinButton();

        addComponentsToMasterPanel();

    }

    private void setupForMasterPanel() {
        joinMasterPanel.setLayout(null);
        joinMasterPanel.setBackground(Color.cyan);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addJoinButton() {
        joinButton = new JButton("Join");
        joinButton.setBounds(120, 190, 150, 50);
    }

    private void addComponentsToMasterPanel() {
        joinMasterPanel.add(joinButton);
        joinMasterPanel.add(labelGameMaster);
        joinMasterPanel.add(gameMaster);
        joinMasterPanel.add(nameLabel);
        joinMasterPanel.add(playerName);
        setVisible(true);
    }

    private void addLabels() {
        labelGameMaster = new JLabel("Game Master");
        labelGameMaster.setBounds(30, 20, 200, 50);

        nameLabel = new JLabel("Player Name");
        nameLabel.setBounds(30,90,200,50);
    }

    private void addTextFields() {
        gameMaster = new JTextField();
        gameMaster.setBounds(120, 20, 290, 50);

        playerName = new JTextField();
        playerName.setBounds(120, 90, 290, 50);
    }

    public static void main(String[] args) {
        StartPage startPage = new StartPage();
    }
}
