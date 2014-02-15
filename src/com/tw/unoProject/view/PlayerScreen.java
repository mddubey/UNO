package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;

public class PlayerScreen extends JFrame {
    private JPanel playerMasterPanel;
    private JPanel playersPanel;
    private JPanel centerPanel;
    private JButton drawButton;
    private JPanel cardPanel;

    public PlayerScreen() {
        super("UNO");
        generateUI();
    }

    private void generateUI() {
        playerMasterPanel = new JPanel();
        setContentPane(playerMasterPanel);
        playerMasterPanel.setBackground(Color.GRAY);
        playerMasterPanel.setLayout(null);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addPlayerPanel();
        createCatchButtons();

        addCenterPanel();
        createDrawButton();
        showOpenedPileCard();
    }

    private void addPlayerPanel() {
        playersPanel = new JPanel();
        playersPanel.setBounds(30, 30, 700, 30);
        playersPanel.setLayout(new GridLayout(1, 5));
        playerMasterPanel.add(playersPanel);
    }

    private void createCatchButtons() {
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            playersPanel.add(button);
        }
    }

    private void addCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(200, 150, 400, 200);
        centerPanel.setBackground(Color.white);
        playerMasterPanel.add(centerPanel);
    }

    private void createDrawButton() {
        drawButton = new JButton("Draw");
        drawButton.setBounds(15, 15, 150, 50);
        centerPanel.add(drawButton);
    }

    private void showOpenedPileCard() {
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(200, 15, 150, 50);
        cardPanel.setBackground(Color.cyan);
        centerPanel.add(cardPanel);
        JLabel openPileCard = new JLabel("1");
        openPileCard.setFont(new Font("Serif", Font.BOLD, 30));
        openPileCard.setForeground(Color.black);
        openPileCard.setBounds(20, 5, 100, 30);
        cardPanel.add(openPileCard);
    }
}
