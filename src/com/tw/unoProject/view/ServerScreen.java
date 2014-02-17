package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ServerScreen extends JFrame {
    private JPanel serverPanel;
    private JLabel openPile;
    private JPanel playersPanel;
    private JTextArea status;
    private JScrollPane log;
    private JPanel currentStatusPanel;
    private JTable logTable;
    private JPanel cardPanel;
    private ImageIcon arrow;
    private JLabel imageLabel;
    private JButton quit;
    private List<JLabel> imageLable;

    public ServerScreen(){
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
        createLog();
        arrow = new ImageIcon("D:/projects/UNO/src/com/tw/unoProject/view/arrow.jpg");
        imageLabel = new JLabel();
        imageLabel.setIcon(arrow);
        imageLabel.setBounds(10, 10, 50, 50);
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
        playersPanel.setLayout(new GridLayout(2, 5));
        playersPanel.setBackground(Color.white);

    }

    private void createPile() {
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(50,120 , 100, 50);
        cardPanel.setBackground(Color.cyan);
        currentStatusPanel.add(cardPanel);
        openPile = new JLabel("1");
        openPile.setFont(new Font("Times new Roman", Font.BOLD, 30));
        openPile.setForeground(Color.black);
        openPile.setBounds(20, 5, 100, 30);
        cardPanel.add(openPile);
    }

    private void createLog() {
        createTable();
        log = new JScrollPane(logTable);
        log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        log.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        log.setBounds(530, 10, 250, 730);
    }
    private void createTable(){
            String ColumnName[] = {"NAME","CARD"};
            String data[][] = { {"Samiksha","RED : 2"},{"Manali","RED : 2"},
                    {"Guru","RED : 2"},{"MD","RED : 2"},{"Kashish","RED : 2"}};
            logTable = new JTable(data,ColumnName);
            logTable.setBounds(40, 120, 520, 200);
            logTable.setLayout(new GridLayout(5, 3));
            logTable.setBackground(Color.lightGray);
            logTable.setBorder(BorderFactory.createLineBorder(Color.black));
            logTable.setFont(new Font("serif", Font.BOLD, 20));
            logTable.setRowHeight(40);
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

    public void addToPanel(){
        addToPlayersPanel();
        serverPanel.add(playersPanel);
        serverPanel.add(currentStatusPanel);
        serverPanel.add(log);

    }

    private void addToPlayersPanel() {
        for (int i = 0; i < 5; i++) {
            JLabel player = new JLabel(String.valueOf(i+1));
            player.setFont(new Font("serif", Font.BOLD, 25));
            player.setBackground(Color.black);
            playersPanel.add(player);
        }
        imageLable = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
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

    public void quit(){
        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameOverScreen();
            }
        });
        quit.setBounds(60,600,120,70);
        serverPanel.add(quit);
    }

    public static void main(String[] args) {
        ServerScreen screen = new ServerScreen();
        screen.addToPanel();
    }
}
