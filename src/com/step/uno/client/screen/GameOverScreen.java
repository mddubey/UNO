package com.step.uno.client.screen;

import com.step.uno.client.view.GameOverView;
import com.step.uno.messages.GameResult;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.PlayerResult;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOverScreen extends JFrame implements GameOverView {
    private JLabel gameOverLabel;
    private JPanel masterPanel;
    private JPanel myCards;
    private JButton quit;
    private JPanel individualPlayer;

    public GameOverScreen(GameResult result) {
        setSize(800, 1000);
        setTitle("Game Over Screen");
        setLocation(50, 300);
        masterPanel = new JPanel();
        addToPanel(result);
        setContentPane(masterPanel);
        setVisible(true);
    }

    private void addToPanel(GameResult result) {
        addLabelForGameOver();
        addIndividualPlayersResult(result);
        addButtonForQuit();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addIndividualPlayersResult(GameResult result) {
        Arrays.sort(result.players);
        PlayerResult[] players = result.players;
        JPanel finalPanel = new JPanel();
        finalPanel.setPreferredSize(new Dimension(600, 700));
        finalPanel.setLayout(new GridLayout(result.players.length, 1));


        for (int j = 0; j < players.length; j++) {

            individualPlayer = new JPanel();
            individualPlayer.setLayout(new FlowLayout(FlowLayout.LEADING));
            individualPlayer.setPreferredSize(new Dimension(600, 300));
            JLabel name = new JLabel(players[j].name);
            name.setFont(new Font("sansserif", Font.BOLD, 30));

            JLabel points = new JLabel(" " + String.valueOf(players[j].points));
            points.setFont(new Font("sansserif", Font.BOLD, 30));

            getCards(players[j].cards);
            JScrollPane scrollPane = new JScrollPane(myCards);
            scrollPane.setPreferredSize(new Dimension(400, 110));
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

            individualPlayer.add(name);
            individualPlayer.add(new JLabel("                    "));
            individualPlayer.add(points);
            individualPlayer.add(new JLabel("                    "));
            if(players[j].points != 0) {
                individualPlayer.add(scrollPane);
            }
            individualPlayer.setVisible(true);
            finalPanel.add(individualPlayer);
        }
        JScrollPane resultPane = new JScrollPane(finalPanel);
        resultPane.setPreferredSize(new Dimension(700, 700));
        resultPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        masterPanel.add(resultPane, BorderLayout.CENTER);
    }

    private void getCards(Card[] cards) {
        myCards = new JPanel();
        for (int i = 0; i < cards.length; i++) {
            String cardName = String.valueOf(cards[i].sign).split("_")[1];
            JButton cardButton = new JButton(cardName);
            cardButton.setPreferredSize(new Dimension(60, 100));
            cardButton.setBackground(cards[i].colour.getColor());
            cardButton.setEnabled(false);
            cardButton.setLayout(new FlowLayout());
            Border border1 = new LineBorder(Color.BLACK, 2);
            cardButton.setBorder(border1);
            cardButton.setFont(new Font("sansserif", Font.BOLD, 20));
            myCards.add(cardButton);
        }
    }

    private void addButtonForQuit() {
        quit = new JButton("Quit");
        quit.setBorder(BorderFactory.createLineBorder(Color.RED));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        quit.setPreferredSize(new Dimension(100, 60));
        masterPanel.add(quit, BorderLayout.SOUTH);
    }

    private void addLabelForGameOver() {
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setBounds(20, 20, 500, 100);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setForeground(Color.BLACK);
        gameOverLabel.setFont(new Font("serif", Font.BOLD, 50));
        masterPanel.add(gameOverLabel, BorderLayout.NORTH);
    }

   

}