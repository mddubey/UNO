package com.tw.unoProject.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PileTest {
    @Test
    public void shouldPlaceOneCardOnOpenPile() {
        Pile pile = new Pile(new ArrayList<Card>());
        pile.placeCardToOpenPile(new Card(CardValue.EIGHT, CardColor.GREEN));

        Card topCardFromPile = pile.getTopCardFromPile();

        assertEquals(topCardFromPile, new Card(CardValue.EIGHT, CardColor.GREEN));
    }

    @Test
    public void shouldBeAbleToGetTheTopMostCardFromOPenPile() {
        Pile pile = new Pile(new ArrayList<Card>());
        pile.placeCardToOpenPile(new Card(CardValue.FIVE, CardColor.YELLOW));
        pile.placeCardToOpenPile(new Card(CardValue.EIGHT, CardColor.GREEN));

        Card topCardFromPile = pile.getTopCardFromPile();

        assertEquals(topCardFromPile, new Card(CardValue.EIGHT, CardColor.GREEN));
    }

    @Test
    public void shouldBeAbleToDrawOneCardFromPile() {
        List<Card> closedPile = new ArrayList<>();
        closedPile.add(new Card(CardValue.ZERO, CardColor.BLUE));
        closedPile.add(new Card(CardValue.EIGHT, CardColor.YELLOW));
        Card greenCardWithFive = new Card(CardValue.FIVE, CardColor.GREEN);
        closedPile.add(greenCardWithFive);

        List<Card> cards = new Pile(closedPile).drawPenaltyCards();
        assertEquals(cards.get(0), greenCardWithFive);
    }


}
