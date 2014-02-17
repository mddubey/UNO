package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPage extends JFrame {
    private JPanel settingPanel;
    private JTextField pack;
    private JTextField players;
    private JButton playButton;
    private JLabel labelPack;
    private JLabel playerLabel;

    public SettingPage(){
        super("PLAY");
        setupForFrame();

        addLabels();

        addTextFields();

        addJoinButton();

        addComponentsToMasterPanel();
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ServerScreen().addToPanel();
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
        settingPanel.add(pack);
        settingPanel.add(playerLabel);
        settingPanel.add(players);
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
        pack = new JTextField();
        pack.setFont(new Font("Times new Roman", Font.BOLD, 25));
        pack.setBounds(190, 20, 290, 50);

        players = new JTextField();
        players.setFont(new Font("Times new Roman", Font.BOLD, 25));
        players.setBounds(190, 90, 290, 50);
    }

    public static void main(String[] args) {
        SettingPage startPage = new SettingPage();
    }
}
