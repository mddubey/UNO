package com.step.uno.client.screen;

import com.step.communication.factory.CommunicationFactory;
import com.step.uno.server.controller.GameMasterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerSettingScreen extends JFrame {
    private JPanel settingPanel;
    private JTextField noOfPack;
    private JTextField noOfPlayers;
    private JButton playButton;
    private JLabel labelPack;
    private JLabel playerLabel;
    private GameMasterController controller;

    public ServerSettingScreen() {
        super("Start Game");
        setupForFrame();

        addLabels();

        addTextFields();

        addJoinButton();

        addComponentsToMasterPanel();
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                int players = Integer.parseInt(noOfPlayers.getText());
                int packs = Integer.parseInt(noOfPack.getText());
                controller = new GameMasterController(players, packs, new CommunicationFactory());
                controller.waitForConnections();
            }
        });

        setVisible(true);
    }

    private void setupForFrame() {
        settingPanel = new JPanel();
        setContentPane(settingPanel);
        settingPanel.setLayout(null);
        settingPanel.setBackground(Color.GRAY);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addJoinButton() {
        playButton = new JButton("START GAME");
        playButton.setFont(new Font("Times new Roman", Font.BOLD, 20));
        playButton.setBounds(190, 190, 170, 50);
    }

    private void addComponentsToMasterPanel() {
        settingPanel.add(playButton);
        settingPanel.add(labelPack);
        settingPanel.add(noOfPack);
        settingPanel.add(playerLabel);
        settingPanel.add(noOfPlayers);
    }

    private void addLabels() {
        labelPack = new JLabel("Pack");
        labelPack.setFont(new Font("Times new Roman", Font.PLAIN, 25));
        labelPack.setBounds(30, 20, 200, 50);

        playerLabel = new JLabel("Players");
        playerLabel.setFont(new Font("Times new Roman", Font.PLAIN, 25));
        playerLabel.setBounds(30, 90, 200, 50);
    }

    private void addTextFields() {
        noOfPack = new JTextField();
        noOfPack.setFont(new Font("Times new Roman", Font.BOLD, 25));
        noOfPack.setBounds(190, 20, 290, 50);

        noOfPlayers = new JTextField();
        noOfPlayers.setFont(new Font("Times new Roman", Font.BOLD, 25));
        noOfPlayers.setBounds(190, 90, 290, 50);
    }

}
