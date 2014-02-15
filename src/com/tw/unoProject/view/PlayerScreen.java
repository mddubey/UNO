package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;

public class PlayerScreen extends JFrame {
    private JPanel pnlWindow;
    private JPanel pnlPlayers;

    public PlayerScreen() {
        super("UNO");
        generateUI();
    }

    private void generateUI() {
        pnlWindow = new JPanel();
        setContentPane(pnlWindow);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addPalyerPanel(pnlWindow);
        pnlWindow.setBackground(Color.GRAY);
        pnlWindow.setLayout(null);
        pnlPlayers.setLayout(new GridLayout(1,5));

        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(String.valueOf(i));
            pnlPlayers.add(button);
        }
    }

    private void addPalyerPanel(JPanel pnlWindow) {
        pnlPlayers = new JPanel();
        pnlPlayers.setBounds(30,30,700,30);
        pnlPlayers.setVisible(true);
        System.out.println(pnlPlayers.getBounds());
        pnlPlayers.setBackground(Color.BLACK);
        pnlWindow.add(pnlPlayers);
    }


}
