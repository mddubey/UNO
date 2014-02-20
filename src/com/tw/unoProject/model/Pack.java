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
        CardPoints[] values = {CardPoints.ONE, CardPoints.TWO, CardPoints.THREE, CardPoints.FOUR, CardPoints.FIVE, CardPoints.SIX, CardPoints.SEVEN, CardPoints.EIGHT, CardPoints.NINE};

        for (CardColor color : colors) {
            cards.add(new Card(CardPoints.ZERO, color));
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
