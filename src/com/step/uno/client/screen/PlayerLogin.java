package com.step.uno.client.screen;

import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerLogin extends JFrame implements JoinGameView {
    private JPanel joinMasterPanel;
    private JTextField gameMaster;
    private JTextField playerName;
    private JButton joinButton;
    private JLabel labelGameMaster;
    private JLabel nameLabel;
    private GameClientController controller;

    public PlayerLogin(GameClientController controller) {
        super("JOIN UNO");
        this.controller = controller;
        setupForFrame();

        addLabels();

        addTextFields();

        addJoinButton();

        addComponentsToMasterPanel();
    }

    private void setupForFrame() {
        joinMasterPanel = new JPanel();
        setContentPane(joinMasterPanel);
        joinMasterPanel.setLayout(null);
        joinMasterPanel.setBackground(Color.cyan);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addJoinButton() {
        joinButton = new JButton("Join");
        joinButton.setFont(new Font("Times new Roman", Font.BOLD, 25));
        joinButton.setBounds(190, 190, 150, 50);
    }

    private void addComponentsToMasterPanel() {
        joinMasterPanel.add(joinButton);
        joinMasterPanel.add(labelGameMaster);
        joinMasterPanel.add(gameMaster);
        joinMasterPanel.add(nameLabel);
        joinMasterPanel.add(playerName);
    }

    private void addLabels() {
        labelGameMaster = new JLabel("Game Master");
        labelGameMaster.setFont(new Font("Times new Roman", Font.PLAIN, 25));
        labelGameMaster.setBounds(30, 20, 200, 50);

        nameLabel = new JLabel("Player Name");
        nameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 25));
        nameLabel.setBounds(30, 90, 200, 50);
    }

    private void addTextFields() {
        gameMaster = new JTextField("127.0.0.1");
        gameMaster.setFont(new Font("Times new Roman", Font.BOLD, 25));
        gameMaster.setBounds(190, 20, 290, 50);

        playerName = new JTextField("me");
        playerName.setFont(new Font("Times new Roman", Font.BOLD, 25));
        playerName.setBounds(190, 90, 290, 50);
    }

    public PlayerView switchToPlayerView() {
        PlayerView view = new PlayerScreen();
//        setVisible(false);
        return view;
    }

    @Override
    public void showVisible(boolean b) {
        setVisible(b);
    }

    public void showScreen() {
        controller.bindView(this);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.join(gameMaster.getText(), playerName.getText());
            }
        });
        setVisible(true);
    }
}
