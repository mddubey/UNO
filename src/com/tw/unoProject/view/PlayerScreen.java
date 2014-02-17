package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerScreen extends JFrame {
    private JPanel masterPanel;
    private JPanel playersPanel;
    private JPanel centerPanel;
    private JButton drawButton;
    private JPanel cardPanel;
    private JLabel openPile;
    private JTextArea hintToUser;
    private JPanel playerCardsPanel;
    private JTable logTable;
    private JScrollPane log;
    private JButton UNOButton,quit;

    public PlayerScreen() {
        super("UNO");
        generateUI();
        setVisible(true);
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
        createLog();
        UNOButton = new JButton("UNO");
        UNOButton.setBounds(680, 600, 70, 50);
        masterPanel.add(UNOButton);

        quit();
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
        openPile = new JLabel("1", JLabel.CENTER);
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

    private void createLog() {
        createTable();
        log = new JScrollPane(logTable);
        log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        log.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        log.setBounds(770, 10, 300, 720);
        masterPanel.add(log);
    }

    private void createTable() {
        Object ColumnName[] = {"NAME", "CARD"};
        Object data[][] = {{"Samiksha", "RED : 2"}, {"Manali", "RED : 2"},
                {"Guru", "RED : 2"}, {"MD", "RED : 2"}, {"Kashish", "RED : 2"}};
        logTable = new JTable(data, ColumnName);
        logTable.setBounds(40, 120, 520, 200);
        logTable.setLayout(new GridLayout(5, 3));
        logTable.setBackground(Color.lightGray);
        logTable.setBorder(BorderFactory.createLineBorder(Color.black));
        logTable.setFont(new Font("serif", Font.BOLD, 20));
        logTable.setRowHeight(40);
    }

    public void quit(){
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameOverScreen();
            }
        });
        quit.setBounds(60,500,120,70);
        masterPanel.add(quit);
    }

    private void addPlayerCardsPanel() {
        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new GridLayout());
        playerCardsPanel.setBackground(Color.white);
        playerCardsPanel.setBounds(20, 600, 650, 100);
        masterPanel.add(playerCardsPanel);
    }
}
