package com.tw.unoProject.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pack {
    private List<Card> cards;

    public Pack() {
        this.cards = new ArrayList<>();
        addCardsToPack();
    }

    public List addCardsToPack() {
        List<Color> colors = Arrays.asList(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);

        for (int i = 0; i < colors.size(); i++) {
            cards.add(new Card(0, colors.get(i)));
            for (int j = 1; j <= 9; j++) {
                cards.add(new Card(j, colors.get(i)));
                cards.add(new Card(j, colors.get(i)));
            }
        }
        return cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        String sample = null;
        for (Card card : cards) {
            sample += card.toString();
        }
        return sample;
    }

}
