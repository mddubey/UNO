package com.tw.unoProject.model;

import java.awt.*;

public class Card {
    private CardPoints cardPoints;
    private CardColor color;

    public Card(CardPoints cardPoints, CardColor color) {
        this.cardPoints = cardPoints;
        this.color = color;
    }

    public int getCardPoints() {
        return cardPoints.getValue();
    }

    public Color getColor() {
        return color.getCardColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return cardPoints.getValue() == card.cardPoints.getValue() && color == card.color;

    }

    @Override
    public String toString() {
        return cardPoints.getValue() +" " + color.getCardColor().toString();
    }
}
