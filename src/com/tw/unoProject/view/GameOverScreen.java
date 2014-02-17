package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame {

    private JLabel label;
    private JPanel panel;
    private JTable table;
    private JButton play, quit;

    public GameOverScreen() {
        setSize(600, 600);
        setTitle("Game Result");
        setLocation(50, 300);
        addToPanel();
    }

    public void addToPanel() {
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        addLabelForGameOver();
        addTableForDisplay();
        addPlayAgainButton();
        addButtonForQuit();
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
        panel.add(quit);
        quit.setBounds(320, 350, 200, 60);
    }

    private void addPlayAgainButton() {
        play = new JButton("Play Again");
        play.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        play.setBounds(60, 350, 200, 60);
        panel.add(play);
    }

    private void addTableForDisplay() {
        Object ColumnName[] = {"NAME", "CARDS", "TOTAL"};
        Object data[][] = {{"SAMIKSHA", "2", "3"}, {"MANALI", "2", "3"},
                {"GURU", "2", "3"}, {"MD", "2", "3"}, {"KASHISH", "2", "3"}};
        table = new JTable(data, ColumnName);
        table.setBounds(40, 120, 520, 200);
        table.setLayout(new GridLayout(5, 3));
        table.setBackground(Color.lightGray);
        table.setBorder(BorderFactory.createLineBorder(Color.black));
        table.setFont(new Font("serif", Font.BOLD, 20));
        table.setRowHeight(40);
        panel.add(table);
    }

    private void addLabelForGameOver() {
        label = new JLabel("GAME OVER");
        label.setBounds(20, 20, 500, 100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("serif", Font.BOLD, 50));
        panel.add(label);
    }

    public static void main(String[] args) {
        new GameOverScreen();
    }
}