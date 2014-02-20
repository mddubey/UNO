package com.tw.unoProject.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> myCards;

    public Player(String name) {
        this.name = name;
        myCards = new ArrayList<>();
    }


    public void addCard(Card card) {
        this.myCards.add(card);
    }

    public boolean playACard(Card card) {
        return this.myCards.remove(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
