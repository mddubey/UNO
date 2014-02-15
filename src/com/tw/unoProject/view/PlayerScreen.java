package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerScreen extends JFrame {
    private JPanel masterPanel;
    private JPanel playersPanel;
    private JPanel centerPanel;
    private JButton drawButton;
    private JPanel cardPanel;
    private JLabel openPile;
    private JTextArea hintToUser;
    private JPanel playerCardsPanel;
    private List<JButton> myCards;

    public PlayerScreen() {
        super("UNO");
        generateUI();
    }

    private void generateUI() {
        setJFrame();

        addPlayerPanel();
        createCatchButtons();

        addCenterPanel();
        createDrawButton();
        showOpenedPileCard();
        showCurrentHint();

        addPlayerCardsPanel();
    }

    private void addPlayerCardsPanel() {
        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new GridLayout());
        playerCardsPanel.setBackground(Color.white);
        playerCardsPanel.setBounds(20,600,700,100);
        masterPanel.add(playerCardsPanel);
    }

    private void setJFrame() {
        masterPanel = new JPanel();
        setContentPane(masterPanel);
        masterPanel.setBackground(Color.GRAY);
        masterPanel.setLayout(null);
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addPlayerPanel() {
        playersPanel = new JPanel();
        playersPanel.setBounds(30, 30, 700, 30);
        playersPanel.setLayout(new GridLayout(1, 5));
        masterPanel.add(playersPanel);
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
        masterPanel.add(centerPanel);
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
        openPile = new JLabel("1");
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 30));
        openPile.setForeground(Color.black);
        openPile.setBounds(20, 5, 100, 30);
        cardPanel.add(openPile);
    }

    private void showCurrentHint() {
        hintToUser = new JTextArea();
        hintToUser.setBounds(20, 75, 350, 120);
        hintToUser.setBackground(Color.gray);
        hintToUser.setForeground(Color.white);
        hintToUser.setText("Play a 4 or Green card");
        hintToUser.setEditable(false);
        hintToUser.setFont(new Font("Times new Roman", Font.PLAIN, 30));
        centerPanel.add(hintToUser);
    }

    public static void main(String[] args) {
        new PlayerScreen().setVisible(true);
    }
}
