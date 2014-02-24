package com.step.uno.server.screen;

import com.step.uno.client.view.GameOverView;
import com.step.uno.messages.GameResult;
import com.step.uno.model.PlayerResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame implements GameOverView {
    private JLabel gameOverLabel;
    private JPanel masterPanel;
    private JTable scoreCard;
    private JButton playAgain, quit;

    public GameOverScreen(GameResult result) {
        setSize(600, 600);
        setTitle("Game Result");
        setLocation(50, 300);
        addToPanel(result);
    }

    private void addToPanel(GameResult result) {
        masterPanel = new JPanel();
        this.add(masterPanel);
        masterPanel.setLayout(null);
        masterPanel.setBackground(Color.GRAY);
        addLabelForGameOver();
        addTableForDisplay(result);
//        addPlayAgainButton();
        addButtonForQuit();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
        masterPanel.add(quit);
        quit.setBounds(320, 350, 200, 60);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void addPlayAgainButton() {
        playAgain = new JButton("Play Again");
        playAgain.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        playAgain.setBounds(60, 350, 200, 60);
        masterPanel.add(playAgain);
    }

    private void addTableForDisplay(GameResult result) {
        String columnName[] = {"NAME", "CARDS", "TOTAL"};
        PlayerResult[] players = result.players;
        String data[][] = new String[players.length][3];

        for (int j = 0; j < players.length; j++) {
            for (int i = 0; i < 1; i++) {
                data[j][i] = players[j].name;
                data[j][i + 1] = String.valueOf(players[j].points);
                data[j][i + 2] = String.valueOf(players[j].cards.length);
            }
        }
        scoreCard = new JTable(data, columnName);
        scoreCard.getTableHeader().setVisible(true);
        System.out.println(scoreCard.getTableHeader().toString());
        scoreCard.setBounds(40, 120, 520, 200);
        scoreCard.setLayout(new GridLayout(data.length, 3));
        scoreCard.setBackground(Color.lightGray);
        scoreCard.setBorder(BorderFactory.createLineBorder(Color.black));
        scoreCard.setFont(new Font("serif", Font.BOLD, 20));
        scoreCard.setRowHeight(40);
        masterPanel.add(scoreCard);
    }

    private void addLabelForGameOver() {
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setBounds(20, 20, 500, 100);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setForeground(Color.BLACK);
        gameOverLabel.setFont(new Font("serif", Font.BOLD, 50));
        masterPanel.add(gameOverLabel);
    }

}