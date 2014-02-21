package com.step.uno.server.screen;

import com.step.uno.client.screen.LogDisplay;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.PlayerSummary;
import com.step.uno.server.view.ServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerScreen extends JFrame implements ServerView {
    private JPanel serverPanel;
    private JLabel openPile;
    private JPanel playersPanel;
    private JTextArea status;

    private JPanel currentStatusPanel;

    private JPanel cardPanel;
    private JButton quit;
    private List<JLabel> imageLable;
    private int numOfPlayers;
    private int numOfPacks;
    private Colour[] colours = {Colour.Black, Colour.Blue, Colour.Green, Colour.Red, Colour.Yellow};
    private Color[] colors = {Color.black, Color.blue, Color.green, Color.RED, Color.YELLOW};
    private Color[] foregroundColor = {Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK};
    LogDisplay log = new LogDisplay();

    public ServerScreen(int numOfPlayers, int numOfPacks) {
        this.numOfPlayers = numOfPlayers;
        this.numOfPacks = numOfPacks;
        generateUI();
        quit();
    }

    private void generateUI() {
        createServerPanel();
        setContentPane(serverPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createPlayerPanel();
        createCurrentStatusPanel();
        log.createLog(530, 10, 250, 730);
        addToPanel();
    }

    private void createServerPanel() {
        serverPanel = new JPanel();
        serverPanel.setLayout(null);
        serverPanel.setBackground(Color.GRAY);
        setSize(800, 800);
    }

    private void createPlayerPanel() {
        playersPanel = new JPanel();
        playersPanel.setBounds(30, 30, 400, 100);
        playersPanel.setLayout(new GridLayout(2, numOfPlayers));
        playersPanel.setBackground(Color.white);

    }

    private void createPile(Card card) {
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(50, 120, 140, 100);
        int index = Arrays.asList(colours).indexOf(card.colour);
        cardPanel.setBackground(colors[index]);
        currentStatusPanel.add(cardPanel);
        openPile = new JLabel(card.sign + "");
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 30));
        openPile.setForeground(foregroundColor[index]);
        openPile.setBounds(5, 5, 150, 30);
        cardPanel.add(openPile);
    }


    private void createStatus(Card card) {
        status = new JTextArea();
        status.setEditable(false);
        status.setText("Play a " + card.sign + " or " + card.colour);
        status.setBackground(Color.GRAY);
        status.setForeground(Color.WHITE);
        status.setLineWrap(true);
        status.setFont(new Font("Times new Roman", Font.PLAIN, 30));
        status.setBounds(200, 50, 150, 200);
    }

    private void addToCurrentStatusPanel(Card openCard) {
        createPile(openCard);
        currentStatusPanel.add(cardPanel);
    }

    private void createCurrentStatusPanel() {
        currentStatusPanel = new JPanel();
        currentStatusPanel.setLayout(null);
        currentStatusPanel.setBackground(Color.darkGray);
        currentStatusPanel.setBounds(50, 150, 400, 350);
    }

    public void addToPanel() {
        serverPanel.add(playersPanel);
        serverPanel.add(currentStatusPanel);
        serverPanel.add(log.getLog());
    }

    private void addToPlayersPanel(PlayerSummary[] playerSummaries, int currentPlayerIndex) {
        for (int i = 0; i < numOfPlayers; i++) {
            JLabel player = new JLabel(playerSummaries[i].name + "(" + playerSummaries[i].cardsInHand + ")");
            player.setFont(new Font("serif", Font.BOLD, 25));
            player.setBackground(Color.black);
            playersPanel.add(player);
        }
        imageLable = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            imageLable.add(new JLabel());
        }
        for (JLabel label : imageLable) {
            label.setText("=>");
            label.setFont(new Font("serif", Font.BOLD, 30));
            label.setBounds(10, 10, 50, 50);
            label.setVisible(false);
            playersPanel.add(label);
        }
        imageLable.get(currentPlayerIndex).setVisible(true);
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
        quit.setBounds(60, 600, 120, 70);
        serverPanel.add(quit);
    }

    @Override
    public void display(Snapshot snapshot) {
        addToPlayersPanel(snapshot.playerSummaries, snapshot.currentPlayerIndex);
        createStatus(snapshot.openCard);
        currentStatusPanel.add(status);
        addToCurrentStatusPanel(snapshot.openCard);

        setVisible(true);

    }
}
