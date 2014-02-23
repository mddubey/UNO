package com.step.uno.model;

import java.io.Serializable;

public class PlayerSummary implements Serializable {
    public String name;
    public int cardsInHand;
    private boolean declaredUno;

    public PlayerSummary(String name, int cardsInHand, boolean declaredUno) {

        this.name = name;
        this.cardsInHand = cardsInHand;
        this.declaredUno = declaredUno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerSummary)) return false;

        PlayerSummary that = (PlayerSummary) o;

        if (cardsInHand != that.cardsInHand) return false;
        if (declaredUno != that.declaredUno) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }


}
