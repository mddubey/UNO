package com.tw.unoProject.model;

public class Card {
    private int value;
    private CardColor color;

    public Card(int value, CardColor color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }
}
