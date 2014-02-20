package com.step.uno.client.screen;

import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.GameSnapshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen extends JFrame implements JoinGameView {
    private GameClientController controller;

    //UI Components
    JButton join = new JButton("join");
    JTextField masterName = new JTextField("127.0.0.1", 15);
    JTextField name = new JTextField("me", 15);

    public JoinGameScreen(GameClientController controller) {
        this.controller = controller;
        Panel panel = new Panel();
        panel.add(masterName);
        panel.add(name);
        panel.add(join);

        setSize(200, 200);
        setLocationRelativeTo(null);
        add(panel);
    }

    @Override
    public PlayerView switchToPlayerView() {
        PlayerView view = new PlayerView() {
            @Override
            public void showDisconnected() {
                System.out.println("disconnected");
            }

            @Override
            public void update(GameSnapshot snapshot) {
                System.out.println("Got a snapshot");
            }
        };
        setVisible(false);
        return view;
    }

    public void showScreen() {
        controller.bindView(this);
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("one two");
                controller.join(masterName.getText(), name.getText());
            }
        });
        setVisible(true);
    }
}
