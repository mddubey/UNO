package com.tw.unoProject.model;

public class PlayerStatus {
    private String name;
    private int noOfCards;
    private boolean sayUno;

    public PlayerStatus(String name, int noOfCards, boolean sayUno) {

        this.name = name;
        this.noOfCards = noOfCards;
        this.sayUno = sayUno;
    }
}
