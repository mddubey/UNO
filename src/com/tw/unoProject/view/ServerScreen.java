package com.tw.manalit;

import javax.swing.*;
import java.awt.*;

public class ServerScreen extends JFrame {
    private JPanel serverPanel;
    private JLabel pile;
    private JPanel playersPanel;
    private JTextArea status;
    private JScrollPane log;
    private JTextArea textArea;
    private JPanel currentStatusPanel;
    private JTable logTable;

    public ServerScreen(){
        generateUI();
    }

    private void generateUI() {
        createServerPanel();
        setContentPane(serverPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayerPanel();
        createCurrentStatusPanel();
        addToCurrentStatusPanel();
        createLog();
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
        playersPanel.setBounds(30, 30, 400, 50);
        playersPanel.setLayout(new GridLayout(1, 5));
        playersPanel.setBackground(Color.white);
    }

    private void createPile() {
        pile = new JLabel("RED : 2");
        pile.setBounds(80, 100, 100, 100);
        pile.setForeground(Color.RED);
        pile.setFont(new Font("serif", Font.BOLD, 28));
    }

    private void createLog() {
        textArea = new JTextArea();
        createTable();
        textArea.setEditable(true);
        log = new JScrollPane(logTable);
        log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        log.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        log.setBounds(550, 10, 200, 600);
    }
    private void createTable(){
            Object ColumnName[] = {"NAME","CARD"};
            Object data[][] = { {"Samiksha","RED : 2"},{"Manali","RED : 2"},
                    {"Guru","RED : 2"},{"MD","RED : 2"},{"Kashish","RED : 2"}};
            logTable = new JTable(data,ColumnName);
            logTable.setBounds(40, 120, 520, 200);
            logTable.setLayout(new GridLayout(5, 3));
            logTable.setBackground(Color.lightGray);
            logTable.setBorder(BorderFactory.createLineBorder(Color.black));
            logTable.setFont(new Font("serif", Font.BOLD, 20));
            logTable.setRowHeight(40);
//            panel.add(logTable);
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
        currentStatusPanel.add(pile);
        currentStatusPanel.add(status);
    }

    private void createCurrentStatusPanel() {
        currentStatusPanel = new JPanel();
        currentStatusPanel.setLayout(null);
        currentStatusPanel.setBackground(Color.darkGray);
        currentStatusPanel.setBounds(50, 150, 400, 350);
    }

    public void addToPanel(){
        addToPlayyersPanel();
        serverPanel.add(playersPanel);
        serverPanel.add(currentStatusPanel);
        serverPanel.add(log);
    }

    private void addToPlayyersPanel() {
        for (int i = 0; i < 5; i++) {
            JLabel player = new JLabel(String.valueOf(i+1));
            player.setFont(new Font("serif",Font.BOLD,25));
            player.setBackground(Color.black);
            player.setPreferredSize(new Dimension(50,20));
            playersPanel.add(player);
        }
    }

    public static void main(String[] args) {
        ServerScreen screen = new ServerScreen();
        screen.addToPanel();
    }
}
