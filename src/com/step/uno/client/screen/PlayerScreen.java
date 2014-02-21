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
import java.util.*;
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
    private PlayerViewObserver observer;

    private List<JLabel> imageLable;
    private List<JButton> catchButtons = new ArrayList<>();
    private JButton noAction = new JButton("Continue");

    private JScrollPane cardsPane;
    LogDisplay log = new LogDisplay();
    private Colour[] colours = {Colour.Black, Colour.Blue, Colour.Green, Colour.Red, Colour.Yellow};
    private Color[] colors = {Color.black, Color.blue, Color.green, Color.RED, Color.YELLOW};
    private Color[] foregroundColor = {Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK};


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
        log.createLog(770, 10, 300, 720);
        masterPanel.add(log.getLog());
        showUNOButton();
        showOpenedPileCard();
        showCurrentHint();
        showPlayerCards();
        showContinueButton();
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

    private void createCatchButtons(List<PlayerSummary> playerSummaries, int currentPlayerIndex) {
        if (catchButtons.size() == 0) {
            for (PlayerSummary playerSummary : playerSummaries) {
                JButton catchButton = new JButton();
                catchButtons.add(catchButton);
                playersPanel.add(catchButton);
                catchButton.setFont(new Font("serif", Font.BOLD, 30));
            }
            imageLable = new ArrayList<>();
            for (PlayerSummary playerSummary : playerSummaries) {
                JLabel e = new JLabel("=>", JLabel.CENTER);
                imageLable.add(e);
            }
        }
        for (int i = 0; i < catchButtons.size(); i++) {
            catchButtons.get(i).setText(playerSummaries.get(i).name + playerSummaries.get(i).cardsInHand);
            imageLable.get(i).setVisible(false);
        }

        imageLable.get(currentPlayerIndex).setVisible(true);
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
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.onDraw();
            }
        });
        drawButton.setBounds(15, 15, 150, 50);
        centerPanel.add(drawButton);
    }

    private void showContinueButton() {
        noAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.onNoAction();
            }
        });
        noAction.setBounds(200, 500, 120, 70);
        masterPanel.add(noAction);
    }

    private void showOpenedPileCard() {
        openPileCardPanel = new JPanel();
        openPileCardPanel.setLayout(null);
        openPileCardPanel.setBounds(170, 15, 180, 50);
        centerPanel.add(openPileCardPanel);
        openPile = new JLabel("", JLabel.CENTER);
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 30));
        openPile.setBounds(20, 5, 150, 30);
        openPileCardPanel.add(openPile);
        openPileCardPanel.setVisible(true);
    }

    private void updateOpenPile(Card card) {
        int index = Arrays.asList(colours).indexOf(card.colour);
        openPileCardPanel.setBackground(colors[index]);
        openPile.setText(String.valueOf(card.sign));
        openPile.setForeground(foregroundColor[index]);
    }

    private void showCurrentHint() {
        hintToUser = new JTextArea();
        hintToUser.setBounds(20, 75, 350, 120);
        hintToUser.setBackground(Color.gray);
        hintToUser.setForeground(Color.white);
        hintToUser.setEditable(false);
        hintToUser.setLineWrap(true);
        hintToUser.setFont(new Font("Times new Roman", Font.PLAIN, 30));
        centerPanel.add(hintToUser);
    }

    private void updateHint(Card card) {
        hintToUser.setText("Play a " + card.sign + " or " + card.colour);
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

    private void showPlayerCards() {
        playerCardsPanel = new JPanel();
        playerCardsPanel.removeAll();
        playerCardsPanel.setLayout(new GridLayout(1, 5));
        playerCardsPanel.setBackground(Color.white);
        cardsPane = new JScrollPane(playerCardsPanel);

        cardsPane = new JScrollPane(playerCardsPanel);
        cardsPane.setBounds(20, 600, 650, 100);
        masterPanel.add(cardsPane);

    }

    private void updatePlayerCards(List<Card> cards) {
        final Map<JButton, Card> myCards = new HashMap<>();
        for (final Card card : cards) {
            System.out.println(cards.size());
            int index = Arrays.asList(colours).indexOf(card.colour);
            JButton button = new JButton(String.valueOf(card.sign));
            myCards.put(button, card);
            button.setFont(new Font("serif", Font.BOLD, 18));
            button.setBackground(colors[index]);
            button.setForeground(foregroundColor[index]);
            playerCardsPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();
                    if (myCards.containsKey(source))
                        observer.onCardPlayed(myCards.get(source));
                }
            });
        }
        cardsPane.setBounds(20, 600, 650, 100);
        masterPanel.add(cardsPane);
    }

    public void update(Snapshot snapshot, PlayerViewObserver observer) {
        Card[] myCards = snapshot.myCards;
        updatePlayerCards(Arrays.asList(myCards));
        showPlayerCards();
        updateHint(snapshot.openCard);
        updateOpenPile(snapshot.openCard);
        this.observer = observer;

        PlayerSummary[] playerSummaries = snapshot.playerSummaries;
        createCatchButtons(Arrays.asList(playerSummaries), snapshot.currentPlayerIndex);

        centerPanel.setVisible(true);
        setVisible(true);
    }

    public void showDisconnected() {

    }
}