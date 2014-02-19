package com.tw.unoProject.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Player> players;
    private int noOfPacks;
    private List<Card> cards;
    private Player currentPlayer;
    private Pile pile;

    public Game(List<Player> players, int noOfPacks) {
        this.players = players;
        this.noOfPacks = noOfPacks;
        this.cards = new ArrayList<>();
        this.currentPlayer = this.players.get(0);
        useRequiredPacks();
    }

    public void start() {
        shufflePlayers();
        dealCards();
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
        for (Player player : players) {
            int i = 0;
            while (i < this.cards.size()) {
                player.addCard(this.cards.get(0));
                this.cards.remove(0);
                if (player.getMyCards().size() == 7) {
                    break;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        new Game(Arrays.asList(new Player("afdfsd"), new Player("asdfadsf")), 1);
    }
}
