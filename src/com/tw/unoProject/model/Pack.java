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
        List<CardColor> colors = Arrays.asList(CardColor.BLUE, CardColor.GREEN, CardColor.RED, CardColor.YELLOW);

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
}
