package com.tw.unoProject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Pile {
    private Stack<Card> openPile;
    private Stack<Card> closePile;
    private int currentPanaltyCards;

    public Pile(List<Card> closePileList) {
        this.currentPanaltyCards = 0;
        this.openPile = new Stack<>();
        this.closePile = new Stack<>();
        for (Card card : closePileList) this.closePile.push(card);
    }

    public void placeCardToOpenPile(Card card) {
        openPile.push(card);
    }

    public Card getTopCardFromPile() {
        return openPile.peek();
    }

    public List<Card> drawPenaltyCards() {
        List<Card> penaltyCards = new ArrayList<>();
        if (currentPanaltyCards == 0) {
            penaltyCards.add(closePile.pop());
            return penaltyCards;
        }
        while (currentPanaltyCards > 0) {
            penaltyCards.add(closePile.pop());
            currentPanaltyCards--;
        }
        return penaltyCards;
    }
}
