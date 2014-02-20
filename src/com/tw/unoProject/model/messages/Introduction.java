package com.tw.unoProject.model.messages;

import java.io.Serializable;

public class Introduction implements Serializable{
    public String playerName;

    public static Introduction create(String playerName) {
        Introduction intro = new Introduction();
        intro.playerName = playerName;
        return intro;
    }
}
