package com.step.uno.model;

public class PlayerSummary {
    public String name;
    public int cardsInHand;
    private boolean declaredUno;

    public PlayerSummary(String name, int cardsInHand, boolean declaredUno) {

        this.name = name;
        this.cardsInHand = cardsInHand;
        this.declaredUno = declaredUno;
    }
}
