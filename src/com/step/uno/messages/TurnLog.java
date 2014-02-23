package com.step.uno.messages;

import com.step.uno.model.Card;

import java.io.Serializable;

public class TurnLog implements Serializable {
    public String playerName;
    public Card cardPlayed;

    public TurnLog(String playerName, Card cardPlayed) {
        this.playerName = playerName;
        this.cardPlayed = cardPlayed;
    }
}
