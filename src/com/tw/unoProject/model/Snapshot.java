package com.tw.unoProject.model;

import java.io.Serializable;

public class Snapshot implements Serializable {
    public Card[] myCards;
    public int currentPlayerIndex;
    public Card openCard;
    public boolean isClockwise;
    public int myPlayerIndex;
    public CardColor runningColour;
    public int draw2Count;
}
