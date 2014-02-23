package com.step.uno.model;

import java.util.Arrays;
import java.util.List;

public class PlayerResult {
    public String name;
    public Card[] cards;
    public int points;

    public PlayerResult(String name, List<Card> cards, int points) {
        this.name = name;
        this.cards = cards.toArray(new Card[]{});
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerResult)) return false;

        PlayerResult that = (PlayerResult) o;

        if (points != that.points) return false;
        if (!Arrays.equals(cards, that.cards)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }
}
