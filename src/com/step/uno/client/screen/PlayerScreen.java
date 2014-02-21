package com.step.uno.client.screen;


import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.PlayerSummary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerScreen extends JFrame implements PlayerView {
    private JPanel masterPanel;
    private JPanel playersPanel;
    private JPanel playerCardsPanel;
    private JPanel centerPanel;
    private JPanel openPileCardPanel;

    private JButton UNOButton, quit;
    private JButton drawButton;

    private JLabel openPile;
    private JTextArea hintToUser;

    private List<JLabel> imageLable;


    private JScrollPane cardsPane;
    LogDisplay log = new LogDisplay();


    public PlayerScreen() {
        super("UNO");
        generateUI();
        setVisible(true);
    }

    private void generateUI() {
        setJFrame();

        addPlayerPanel();
        addCenterPanel();
        createDrawButton();
        showOpenedPileCard(Card.createCard(Colour.Yellow, "_5"));
        showCurrentHint();
        log.createLog(770, 10, 300, 720);
        masterPanel.add(log.getLog());
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

    private void createCatchButtons(List<PlayerSummary> playerSummaries) {
        for (PlayerSummary playerSummary : playerSummaries) {
            JButton button = new JButton(playerSummary.name + playerSummaries.get(0).cardsInHand);
            playersPanel.add(button);
        }

        imageLable = new ArrayList<>();
        for (PlayerSummary playerSummary : playerSummaries) {
            imageLable.add(new JLabel("=>", JLabel.CENTER));
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

    private void showOpenedPileCard(Card card) {
        openPileCardPanel = new JPanel();
        openPileCardPanel.setLayout(null);
        openPileCardPanel.setBounds(200, 15, 150, 50);
        openPileCardPanel.setBackground(Color.cyan);
        centerPanel.add(openPileCardPanel);
        openPile = new JLabel(String.valueOf(card.sign.points), JLabel.CENTER);
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

    public void quit() {
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                new GameOverScreen();
            }
        });
        quit.setBounds(60, 500, 120, 70);
        masterPanel.add(quit);
    }

    private void showPlayerCards(List<Card> cards) {
        playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new GridLayout(1, 5));
        playerCardsPanel.setBackground(Color.white);

        for (Card card : cards) {
            Colour[] colours = {Colour.Black, Colour.Blue, Colour.Green, Colour.Red, Colour.Yellow};
            Color[] colors = {Color.black, Color.blue, Color.green, Color.RED, Color.YELLOW};
            int index = Arrays.asList(colours).indexOf(card.colour);
            JButton button = new JButton(String.valueOf(cards.get(0).sign.points));
            button.setBackground(colors[index]);
            button.setForeground(Color.white);
            playerCardsPanel.add(button);
        }
        cardsPane = new JScrollPane(playerCardsPanel);
        cardsPane.setBounds(20, 600, 650, 100);
        masterPanel.add(cardsPane);

    }

    public void update(Snapshot snapshot) {
        setVisible(true);
        Card[] myCards = snapshot.myCards;
        showPlayerCards(Arrays.asList(myCards));

        PlayerSummary[] playerSummaries = snapshot.playerSummaries;
        createCatchButtons(Arrays.asList(playerSummaries));
    }

    public void showDisconnected() {

    }
}