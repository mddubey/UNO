package com.step.uno.client.screen;


import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.Player;
import com.step.uno.model.Sign;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LogDisplay {
    private JScrollPane log;

    public void createLog(int x, int y, int width, int height) {
        List<Player> players = Arrays.asList(new Player("sa"), new Player("ma"));
        List<Card> cards = Arrays.asList(Card.createCard(Colour.Blue, "_0"), Card.createCard(Colour.Green, "_1"));
        log = new JScrollPane();
        log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        log.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        log.setBounds(x, y, width, height);
    }

    public JScrollPane getLog() {
        return log;
    }
}
