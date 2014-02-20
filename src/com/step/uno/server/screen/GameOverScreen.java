package com.step.uno.server.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame {
    private JLabel gameOverLabel;
    private JPanel masterPanel;
    private JTable scoreCard;
    private JButton playAgain, quit;

    public GameOverScreen() {
        setSize(600, 600);
        setTitle("Game Result");
        setLocation(50, 300);
        addToPanel();
    }

    public void addToPanel() {
        masterPanel = new JPanel();
        this.add(masterPanel);
        masterPanel.setLayout(null);
        masterPanel.setBackground(Color.GRAY);
        addLabelForGameOver();
        addTableForDisplay();
        addPlayAgainButton();
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
    }

    private void addPlayAgainButton() {
        playAgain = new JButton("Play Again");
        playAgain.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        playAgain.setBounds(60, 350, 200, 60);
        masterPanel.add(playAgain);
    }

    private void addTableForDisplay() {
        String ColumnName[] = {"NAME", "CARDS", "TOTAL"};
        String data[][] = {{"SAMIKSHA", "2", "3"}, {"MANALI", "2", "3"},
                {"GURU", "2", "3"}, {"MD", "2", "3"}, {"KASHISH", "2", "3"}};
        scoreCard = new JTable(data, ColumnName);
        scoreCard.setBounds(40, 120, 520, 200);
        scoreCard.setLayout(new GridLayout(5, 3));
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