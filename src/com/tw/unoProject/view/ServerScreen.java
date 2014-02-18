package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerScreen extends JFrame {
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
        addToCurrentStatusPanel();
        log.createLog(530, 10, 250, 730);
        addToPanel();
        setVisible(true);
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

    private void createPile() {
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(50, 120, 100, 50);
        cardPanel.setBackground(Color.cyan);
        currentStatusPanel.add(cardPanel);
        openPile = new JLabel("1");
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 30));
        openPile.setForeground(Color.black);
        openPile.setBounds(20, 5, 100, 30);
        cardPanel.add(openPile);
    }


    private void createStatus() {
        status = new JTextArea();
        status.setEnabled(false);
        status.setBackground(Color.GRAY);
        status.setBounds(200, 50, 150, 200);
    }

    private void addToCurrentStatusPanel() {
        createPile();
        createStatus();
        currentStatusPanel.add(cardPanel);
        currentStatusPanel.add(status);
    }

    private void createCurrentStatusPanel() {
        currentStatusPanel = new JPanel();
        currentStatusPanel.setLayout(null);
        currentStatusPanel.setBackground(Color.darkGray);
        currentStatusPanel.setBounds(50, 150, 400, 350);
    }

    public void addToPanel() {
        addToPlayersPanel();
        serverPanel.add(playersPanel);
        serverPanel.add(currentStatusPanel);
        serverPanel.add(log.getLog());
    }

    private void addToPlayersPanel() {
        for (int i = 0; i < numOfPlayers; i++) {
            JLabel player = new JLabel(String.valueOf(i + 1));
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
            label.setVisible(true);
            playersPanel.add(label);
        }
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
}
