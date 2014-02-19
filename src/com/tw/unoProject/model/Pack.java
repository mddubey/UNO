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
        CardValue[] values = {CardValue.ONE, CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX, CardValue.SEVEN, CardValue.EIGHT, CardValue.NINE};

        for (CardColor color : colors) {
            cards.add(new Card(CardValue.ZERO, color));
            for (int j = 1; j <= 9; j++) {
                cards.add(new Card(values[j - 1], color));
                cards.add(new Card(values[j - 1], color));
            }
        }
        return cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
