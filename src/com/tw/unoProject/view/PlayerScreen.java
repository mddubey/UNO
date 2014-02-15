package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;

public class PlayerScreen extends JFrame {
    private JPanel homePanel;
    private JPanel playersPanel;
    private JPanel centerPanel;
    private JButton drawButton;

    public PlayerScreen() {
        super("UNO");
        generateUI();
    }

    private void generateUI() {
        homePanel = new JPanel();
        setContentPane(homePanel);
        homePanel.setBackground(Color.GRAY);
        homePanel.setLayout(null);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addPalyerPanel();
        addCenterPanel();
        createCatchButtons();
        createDrawButton();
    }

    private void addCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(200, 150, 400, 200);
        centerPanel.setBackground(Color.white);
        homePanel.add(centerPanel);
    }

    private void createDrawButton() {
        drawButton = new JButton("Draw");
        drawButton.setBounds(15, 15, 150, 50);
        centerPanel.add(drawButton);
    }

    private void createCatchButtons() {
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(String.valueOf(i));
            playersPanel.add(button);
        }
    }

    private void addPalyerPanel() {
        playersPanel = new JPanel();
        playersPanel.setBounds(30, 30, 700, 30);
        playersPanel.setLayout(new GridLayout(1, 5));
        homePanel.add(playersPanel);
    }


}
