package com.tw.unoProject.model;

public class Card {
    private int cardValue;
    private CardColor color;

    public Card(int cardValue, CardColor color) {
        this.cardValue = cardValue;
        this.color = color;
    }

    public int getCardValue() {
        return cardValue;
    }

    public CardColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (cardValue != card.cardValue) return false;
        if (color != card.color) return false;

        return true;
    }
}
