package com.step.uno.messages;

import java.io.Serializable;

public class DeclareUnoAction implements Serializable {
    public String playerName;

    public DeclareUnoAction(String playerName) {
        this.playerName = playerName;
    }

    public DeclareUnoAction() {
    }
}
