package com.tw.unoProject.model;

import com.tw.unoProject.controller.UNOFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pack {
    private List<Card> cards;
    private UNOFactory factory;

    public Pack(UNOFactory factory) {
        this.factory = factory;
        this.cards = new ArrayList<>();
        addCardsToPack();
    }

    public List addCardsToPack() {
        List<Color> colors = Arrays.asList(Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW);

        for (int i = 0; i < colors.size(); i++) {
            cards.add(new Card(0, colors.get(i)));
            for (int j = 1; j <= 9; j++) {
                  cards.add(new Card(j, colors.get(i)));
                  cards.add(new Card(j, colors.get(i)));
            }
        }
        return cards;
    }

    public void display(){
        Randomizer randomizer = new Randomizer(new UNOFactory());
        randomizer.shuffleCards(this.cards);
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
    public static void main(String[] args) {
       new Pack(new UNOFactory()).display();
    }
    @Override
    public String toString() {
        String sample = null;
        for (Card card : cards) {
            sample+=card.toString();
        }
        return  sample;
    }

}
