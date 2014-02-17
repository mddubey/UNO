package com.tw.unoProject.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> myCards;

    public Player(String name) {
        this.name = name;
        myCards = new ArrayList();

    }

    public String getName() {
        return name;
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
