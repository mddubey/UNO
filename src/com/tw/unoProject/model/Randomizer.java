package com.tw.unoProject.model;

import java.util.Collections;
import java.util.List;

public class Randomizer {

    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public void dealCards(List<Player> players, Pack pack) {
        List<Card> cards = pack.getCards();
        shuffleCards(cards);

        for (int j = 0; j < players.size(); j++) {

            for (int i = 0; i < cards.size(); i = i++) {
                players.get(j).getMyCards().add(cards.get(i));
                cards.remove(i);
                if (players.get(j).getMyCards().size() == 7) {
                    break;
                }
            }
        }
    }
}