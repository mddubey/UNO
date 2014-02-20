package com.tw.unoProject.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Player> players;
    private int noOfPacks;
    private List<Card> cards;
    private int currentPlayerIndex;
    private Pile pile;
    private boolean isClockWise = true;
    private CardColor runningColor;

    public Game(List<Player> players, int noOfPacks) {
        this.players = players;
        this.noOfPacks = noOfPacks;
        this.cards = new ArrayList<>();
        this.currentPlayerIndex = 0;
        useRequiredPacks();
        this.pile = new Pile(cards);
    }

    public void start() {
        shufflePlayers();
        dealCards();
        startManagePiles();
    }

    private void startManagePiles() {
        this.pile = new Pile(this.cards);
        Card card = pile.drawCard();
        pile.placeCardToOpenPile(card);
    }

    private void useRequiredPacks() {
        for (int i = 0; i < this.noOfPacks; i++) {
            List<Card> cardsInOnePack = new Pack().getCards();
            this.cards.addAll(cardsInOnePack);
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    private void shufflePlayers() {
        Collections.shuffle(players);
    }

    public void dealCards() {
        shuffleCards();
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.addCard(draw());
            }
        }
    }

    private Card draw() {
        return pile.drawCard();
    }


    public static void main(String[] args) {
        new Game(Arrays.asList(new Player("afdfsd"), new Player("asdfadsf")), 1);
    }

    public Snapshot giveSnapshot(Player player) {
        Snapshot snapShot = new Snapshot();
        player.populateCards(snapShot);
        snapShot.currentPlayerIndex = currentPlayerIndex;
        snapShot.draw2Count = currentPlayerIndex;
        snapShot.isClockwise = isClockWise;
        snapShot.myPlayerIndex = players.indexOf(player);
        snapShot.openCard = pile.getTopCardFromPile();
        snapShot.runningColour = runningColor;
        return snapShot;
    }
}
