package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerScreen extends JFrame {
    private JPanel masterPanel;
    private JPanel playersPanel;
    private JPanel playerCardsPanel;
    private JPanel centerPanel;
    private JPanel openPileCardPanel;

    private JButton UNOButton, quit;
    private JButton drawButton;

    private JLabel openPile;
    private JTable logTable;
    private JTextArea hintToUser;

    private List<String> players;
    private List<JLabel> imageLable;


    private JScrollPane log;
    private JScrollPane cardsPane;

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

        showPlayerCards();

        createLog();
        showUNOButton();

        quit();
    }

    private void showUNOButton() {
        UNOButton = new JButton("UNO");
        UNOButton.setBounds(680, 600, 70, 50);
        masterPanel.add(UNOButton);
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
        playersPanel.setBounds(30, 30, 700, 100);
        playersPanel.setLayout(new GridLayout(2, 5));
        masterPanel.add(playersPanel);
    }

    private void createCatchButtons() {
        players = new ArrayList<>();
        players.add("mkdskd:10");
        players.add("manali:10");
        players.add("Ande:10");
        players.add("Kashish:10");
        players.add("Guru:10");
        for (int i = 0; i < players.size(); i++) {
            JButton button = new JButton(players.get(i));
            playersPanel.add(button);
        }
        imageLable = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            imageLable.add(new JLabel("=>",JLabel.CENTER));
        }
        for (JLabel label : imageLable) {
            label.setFont(new Font("serif", Font.BOLD, 30));
            playersPanel.add(label);
        }
    }

    private void addCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(200, 200, 400, 200);
        centerPanel.setBackground(Color.white);
        masterPanel.add(centerPanel);
    }

    private void createDrawButton() {
        drawButton = new JButton("Draw");
        drawButton.setBounds(15, 15, 150, 50);
        centerPanel.add(drawButton);
    }

    private void showOpenedPileCard() {
        openPileCardPanel = new JPanel();
        openPileCardPanel.setLayout(null);
        openPileCardPanel.setBounds(200, 15, 150, 50);
        openPileCardPanel.setBackground(Color.cyan);
        centerPanel.add(openPileCardPanel);
        openPile = new JLabel("1", JLabel.CENTER);
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 30));
        openPile.setForeground(Color.black);
        openPile.setBounds(20, 5, 100, 30);
        openPileCardPanel.add(openPile);
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

    public void quit() {
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameOverScreen();
            }
        });
        quit.setBounds(60, 500, 120, 70);
        masterPanel.add(quit);
    }

    private void showPlayerCards() {
        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new GridLayout(1, 5));
        playerCardsPanel.setBackground(Color.white);

        for (int i = 0; i < 20; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.setBackground(Color.GREEN);
            button.setForeground(Color.black);
            playerCardsPanel.add(button);
        }
        cardsPane = new JScrollPane(playerCardsPanel);
        cardsPane.setBounds(20, 600, 650, 100);
        masterPanel.add(cardsPane);
    }
}
