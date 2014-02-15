package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame {
    private JPanel joinMasterPanel;
    private JTextField gameMaster;
    private JTextField playerName;
    private JButton joinButton;
    private JLabel labelGameMaster;
    private JLabel nameLabel;

    public StartPage(){
        super("JOIN UNO");
        joinMasterPanel = new JPanel();
        setContentPane(joinMasterPanel);
        setupForMasterPanel();


        addLabels();

        addTextFields();

        addJoinButton();

        addComponentsToMasterPanel();
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerScreen();
            }
        });

    }

    private void setupForMasterPanel() {
        joinMasterPanel.setLayout(null);
        joinMasterPanel.setBackground(Color.cyan);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addJoinButton() {
        joinButton = new JButton("Join");
        joinButton.setFont(new Font("Times new Roman", Font.BOLD, 25));
        joinButton.setBounds(190, 190, 150, 50);
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
        labelGameMaster.setFont(new Font("Times new Roman", Font.PLAIN, 25));
        labelGameMaster.setBounds(30, 20, 200, 50);

        nameLabel = new JLabel("Player Name");
        nameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 25));
        nameLabel.setBounds(30,90,200,50);
    }

    private void addTextFields() {
        gameMaster = new JTextField();
        gameMaster.setFont(new Font("Times new Roman", Font.BOLD, 25));
        gameMaster.setBounds(190, 20, 290, 50);

        playerName = new JTextField();
        playerName.setFont(new Font("Times new Roman", Font.BOLD, 25));
        playerName.setBounds(190, 90, 290, 50);
    }

    public static void main(String[] args) {
        StartPage startPage = new StartPage();
    }
}
