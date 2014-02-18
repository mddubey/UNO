package com.tw.unoProject.model;

import java.util.Collections;
import java.util.List;

public class Randomizer {
    Pack pack;

    public Randomizer(Pack pack) {
        this.pack = pack;
    }

    public void shuffleCards() {
        Collections.shuffle(pack.getCards());
    }

    public void dealCards(List<Player> players) {
        shuffleCards();
        for (int j = 0; j < players.size(); j++) {
            for (int i = 0; i < pack.getCards().size(); i = i++) {
                players.get(j).getMyCards().add(pack.getCards().get(i));
                pack.getCards().remove(i);
                if (players.get(j).getMyCards().size() == 7) {
                    break;
                }
            }
        }
    }

    public Card openPile() {
        Card card = pack.getCards().get(0);
        pack.getCards().remove(0);
        return card;
    }

    public Pack closePile() {
        return pack;
    }
}