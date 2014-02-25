package com.step.uno.client.screen;

import com.step.uno.client.view.GameOverView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.GameResult;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.PlayerSummary;
import com.step.uno.server.screen.GameOverScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

class ActivityLogPane extends JScrollPane {
    private JTextArea activityLog = new JTextArea();

    ActivityLogPane() {
        getViewport().add(activityLog);
        activityLog.setFont(new Font("serif", Font.BOLD, 20));
        activityLog.setEditable(false);
    }

    public void append(String currentTurnLog) {
        String log = activityLog.getText();
        activityLog.setText(currentTurnLog + log);
    }
}

public class PlayerScreen extends JFrame implements PlayerView {
    private JPanel masterPanel;
    private JPanel playersPanel;
    private JPanel playerCardsPanel;
    private JPanel centerPanel;
    private JPanel openPileCardPanel;
    private JButton unoButton, quit;
    private JButton drawButton;
    private JLabel openPile;
    private JTextArea hintToUser;
    private PlayerViewObserver observer;

    private List<JLabel> imageLable;
    private List<JButton> catchButtons = new ArrayList<>();
    private JButton continueAction = new JButton("Continue");

    private JScrollPane cardsPane;
    ActivityLogPane log = new ActivityLogPane();
    private Colour[] colours = {Colour.Black, Colour.Blue, Colour.Green, Colour.Red, Colour.Yellow};
    private Color[] backgroundColours = {Color.black, new Color(100, 100, 255), new Color(100, 255, 100), new Color(255, 100, 100), new Color(225, 255, 100)};
    private Color[] foregroundColor = {Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK};
    private Snapshot snapshot;
    private boolean hasDrawnOneCard = false;

    public PlayerScreen(String playerName) {
        super(playerName);
        generateUI();
        setVisible(true);
    }

    private void generateUI() {
        setJFrame();

        addPlayerPanel();
        addCenterPanel();
        createDrawButton();
        log.setBounds(770, 10, 300, 720);
        masterPanel.add(log);
        showUNOButton();
        showOpenedPileCard();
        showCurrentHint();
        showPlayerCards();
        showContinueButton();
        quit();
    }

    private void showUNOButton() {
        unoButton = new JButton("UNO");
        unoButton.setBounds(680, 600, 70, 50);
        unoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.onDeclaredUno(snapshot.myCards.length);
            }
        });
        masterPanel.add(unoButton);
    }

    private void setJFrame() {
        masterPanel = new JPanel();
        cardsPane = new JScrollPane();
        masterPanel.add(cardsPane);
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
        imageLable = new ArrayList<>();
        for (int i = 0; i < playerSummaries.size(); i++) {
            JButton catchButton = new JButton();
            catchButtons.add(catchButton);
            catchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    observer.onCatchUnoAction(catchButtons.indexOf(e.getSource()));
                }
            });
            playersPanel.add(catchButton);
            catchButton.setFont(new Font("serif", Font.BOLD, 30));
            imageLable.add(new JLabel("", JLabel.CENTER));
        }
    }

    private void updateCatchButtons(List<PlayerSummary> playerSummaries, int currentPlayerIndex, boolean isInAscendingOrder, String direction) {
        for (int i = 0; i < catchButtons.size(); i++) {
            catchButtons.get(i).setText(playerSummaries.get(i).name + " : " + playerSummaries.get(i).cardsInHand);
            imageLable.get(i).setVisible(false);
            catchButtons.get(i).setBackground(Color.white);
        }

        imageLable.get(currentPlayerIndex).setText(direction);
        imageLable.get(currentPlayerIndex).setVisible(true);

        for (JLabel label : imageLable) {
            label.setFont(new Font("serif", Font.BOLD, 30));
            playersPanel.add(label);
        }
    }


    private void addCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBounds(200, 200, 400, 220);
        centerPanel.setBackground(Color.white);
        masterPanel.add(centerPanel);
    }

    private void createDrawButton() {
        drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueAction.setEnabled(true);
                drawButton.setEnabled(false);
                hasDrawnOneCard = true;
                observer.onDraw(snapshot.draw2Run);
            }
        });
        drawButton.setBounds(5, 5, 120, 100);
        centerPanel.add(drawButton);
    }

    private void showContinueButton() {
        continueAction.setEnabled(false);
        continueAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observer.onNoAction();
                continueAction.setEnabled(false);
                drawButton.setEnabled(true);
            }
        });
        continueAction.setBounds(200, 500, 120, 70);
        masterPanel.add(continueAction);
    }

    private void showOpenedPileCard() {
        openPileCardPanel = new JPanel();
        openPileCardPanel.setLayout(null);
        openPileCardPanel.setBounds(5, 110, 120, 100);
        centerPanel.add(openPileCardPanel);
        openPile = new JLabel("", JLabel.CENTER);
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 18));
        openPile.setBounds(5, 0, 100, 90);
        openPileCardPanel.add(openPile);
        openPileCardPanel.setVisible(true);
    }

    private void updateOpenPile(Card card) {
        int index = Arrays.asList(colours).indexOf(card.colour);
        openPileCardPanel.setBackground(backgroundColours[index]);
        openPile.setText(card.sign.getValue());
        openPile.setForeground(foregroundColor[index]);
    }

    private void showCurrentHint() {
        hintToUser = new JTextArea();
        hintToUser.setBounds(135, 5, 240, 200);
        hintToUser.setBackground(Color.gray);
        hintToUser.setForeground(Color.white);
        hintToUser.setEditable(false);
        hintToUser.setLineWrap(true);
        hintToUser.setFont(new Font("Times new Roman", Font.PLAIN, 30));
        centerPanel.add(hintToUser);
    }

    private void updateHint(Card card) {
        hintToUser.setText("Play a " + String.valueOf(card.sign).split("_")[1] + " or " + card.colour);
    }

    public void quit() {
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        quit.setBounds(60, 500, 120, 70);
        masterPanel.add(quit);
    }

    private void showPlayerCards() {
        masterPanel.remove(cardsPane);
        playerCardsPanel = new JPanel();
        playerCardsPanel.removeAll();
        playerCardsPanel.setLayout(new GridLayout(1, 5));
        cardsPane = new JScrollPane(playerCardsPanel);
        masterPanel.add(cardsPane);
        playerCardsPanel.validate();

    }

    private void updatePlayerCards(List<Card> cards, boolean enable, final Snapshot snapshot) {
        final Map<JButton, Card> myCards = new HashMap<>();
        for (final Card card : cards) {
            JButton button = getPlayerButtons(enable, myCards, card);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawButton.setEnabled(false);
                    continueAction.setEnabled(false);
                    observer.onCardPlayed(myCards.get(e.getSource()), snapshot);
                    hasDrawnOneCard = false;
                }
            });
        }
        cardsPane.setBounds(20, 600, 650, 100);
        masterPanel.add(cardsPane);
    }

    public void showWarningMessage(String message) {
        if (hasDrawnOneCard) {
            continueAction.setEnabled(true);
            drawButton.setEnabled(false);
        }
        else
            drawButton.setEnabled(true);
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public GameOverView switchToGameOverView(GameResult result) {
        GameOverView view = new GameOverScreen(result);
        return view;
    }

    @Override
    public void disableContinueAfterDraw2() {
        continueAction.setEnabled(false);
    }

    @Override
    public void hasDeclaredUno(String playerName) {
        for (JButton catchButton : catchButtons) {
            String buttonText = catchButton.getText();
            if (buttonText.contains(playerName)) {

                catchButton.setBackground(Color.LIGHT_GRAY);
                catchButton.setText(buttonText.replaceAll("  UNO", "") + "  UNO");
            }

        }
    }

    private JButton getPlayerButtons(boolean enable, Map<JButton, Card> myCards, Card card) {
        int index = Arrays.asList(colours).indexOf(card.colour);
        JButton button = new JButton(card.sign.getValue());
        button.setEnabled(enable);
        myCards.put(button, card);
        button.setFont(new Font("serif", Font.BOLD, 18));
        button.setBackground(backgroundColours[index]);
        button.setForeground(foregroundColor[index]);
        playerCardsPanel.add(button);
        return button;
    }

    public void update(Snapshot snapshot, PlayerViewObserver observer, boolean enable, String direction) {
        this.snapshot = snapshot;
        Card[] myCards = snapshot.myCards;
        drawButton.setEnabled((snapshot.disableDraw != null) ? snapshot.disableDraw : enable);
        showPlayerCards();
        updatePlayerCards(Arrays.asList(myCards), enable, snapshot);
        updateHint(snapshot.openCard);
        updateOpenPile(snapshot.openCard);
        this.observer = observer;

        PlayerSummary[] playerSummaries = snapshot.playerSummaries;
        if (catchButtons.size() == 0)
            createCatchButtons(Arrays.asList(playerSummaries));
        updateCatchButtons(Arrays.asList(playerSummaries), snapshot.currentPlayerIndex, snapshot.isInAscendingOrder, direction);
        log.append(snapshot.currentTurnLog);
        log.setVisible(true);
        centerPanel.setVisible(true);
        setVisible(true);

    }

    public void showDisconnected() {

    }
}