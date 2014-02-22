package com.step.uno.model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Serializable {
    public Colour colour;
    public Sign sign;
    private int cardPoints;
    private Color color;

    //in one pack
    // 4 X {wildcard,wild+4}, 2 X {1-9, +2, reverse, skip}, 0,  for colours {red, green, blue, yellow},

    public static Card[] createNewPacks(int packs) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < packs; i++)
            cards.addAll(createPack());
        return cards.toArray(new Card[]{});
    }

    private static List<Card> createPack() {
        List<Card> cards = new ArrayList<>();
        Colour[] colours = {Colour.Blue, Colour.Green, Colour.Red, Colour.Yellow};
        for (Colour c : colours) {
            cards.add(createCard(c, "_0"));
            for (int times = 0; times < 2; times++) {
                for (int i = 1; i < 10; i++) {
                    cards.add(createCard(c, "_" + i));
                }
                cards.add(createCard(c, "_Reverse"));
                cards.add(createCard(c, "_Skip"));
                cards.add(createCard(c, "_DrawTwo"));
            }
        }

        for (int times = 0; times < 4; times++) {
            cards.add(createCard(Colour.Black, "_Wild"));
            cards.add(createCard(Colour.Black, "_DrawFour"));
        }
        return cards;
    }

    public static Card createCard(Colour c, String signText) {
        Card card = new Card();
        card.colour = c;
        card.sign = Sign.valueOf(signText);
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (colour != card.colour) return false;
        if (sign != card.sign) return false;

        return true;
    }

    public boolean isCardEqual(Card card, int draw2Run) {
        if (this.sign.equals(Sign._Wild)) return true;
        if (this.sign.equals(Sign._DrawFour))
            return true;
        if (card.colour.equals(Colour.Black))
            return true;
        if (draw2Run != 0)
            if (card.sign.equals(Sign._DrawTwo))
                return this.sign.equals(Sign._DrawTwo);
            else return false;
        return this.sign.equals(card.sign) || card.colour.equals(this.colour);
    }

    @Override
    public int hashCode() {
        int result = colour != null ? colour.hashCode() : 0;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        return result;
    }

    public int getCardPoints() {
        return cardPoints;
    }

    public Color getColor() {
        return color;
    }

}


