package com.tw.unoProject.model;

import java.util.List;

public class ResultOfPlayer {
    private String name;
    private List<Card> cardsOfPlayer;
    private int points;

    public ResultOfPlayer(String name, List<Card> cardsOfPlayer, int points) {

        this.name = name;
        this.cardsOfPlayer = cardsOfPlayer;
        this.points = points;
    }
}
