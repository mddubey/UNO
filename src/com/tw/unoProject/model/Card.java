package com.tw.unoProject.model;

import java.awt.*;

public class Card {
    private CardValue cardValue;
    private CardColor color;

    public Card(CardValue cardValue, CardColor color) {
        this.cardValue = cardValue;
        this.color = color;
    }

    public int getCardValue() {
        return cardValue.getValue();
    }

    public Color getColor() {
        return color.getCardColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return cardValue.getValue() == card.cardValue.getValue() && color == card.color;

    }
}
