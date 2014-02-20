package com.tw.unoProject.model;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> myCards = new ArrayList<>();
    private boolean saidUno;

    public Player(String name) {
        this.name = name;
    }


    public void addCard(Card card) {
        this.myCards.add(card);
    }
    public void play(Card card) {
        myCards.remove(card);
    }

    public void sayUno() {
        saidUno = true;
    }
    public boolean hasWon() {
        return myCards.size() == 0;
    }

    public PlayerStatus generateStatus() {
        return new PlayerStatus(name, myCards.size(), saidUno);
    }
    public int calculatePoints() {
        int total = 0;
        for (Card card : myCards) total += card.getCardPoints();
        return total;
    }

    public void populateCards(Snapshot snapshot) {
        snapshot.myCards = myCards.toArray(new Card[]{});
    }

    public ResultOfPlayer generateResult() {
        return new ResultOfPlayer(name, myCards, calculatePoints());
    }

    public boolean playACard(Card card) {
        return this.myCards.remove(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
