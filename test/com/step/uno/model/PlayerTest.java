package com.step.uno.model;

import com.step.uno.messages.Snapshot;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void shouldBeAbleToAddACardInCardsList() {
        Player player = new Player("somePlayer");
        Card card = Card.createCard(Colour.Green, "_8");
        player.take(card);

        List<Card> allCards = player.getCards();
        assertEquals(allCards.get(0), card);
    }

    @Test
    public void shouldBeAbleToGetAllTheCardsPresentInList() {
        Player player = new Player("someone");
        player.take(Card.createCard(Colour.Yellow, "_8"));
        player.take(Card.createCard(Colour.Red, "_5"));

        List<Card> allCards = player.getCards();
        assertEquals(2, allCards.size());
    }

    @Test
    public void shouldBeAbleToPlayACard() {
        Player player = new Player("someone");
        Card card = Card.createCard(Colour.Blue, "_5");
        player.take(card);
        player.play(card);
        assertEquals(0, player.getCards().size());
    }

    @Test
    public void playedCardShouldBeRemovedFromPlayerCardsList() {
        Player player = new Player("me");
        Card card1 = Card.createCard(Colour.Green, "_8");
        Card card2 = Card.createCard(Colour.Blue, "_5");
        player.take(card1);
        player.take(card2);
        player.play(card1);
        List<Card> allCards = player.getCards();
        assertEquals(allCards.get(0), Card.createCard(Colour.Blue, "_5"));
    }

    @Test
    public void shouldBeAbleToCalculatePoints() {
        Player player = new Player("me");
        Card card1 = Card.createCard(Colour.Green, "_8");
        Card card2 = Card.createCard(Colour.Blue, "_5");
        player.take(card1);
        player.take(card2);
        assertEquals(13, player.calculatePoints());
    }

    @Test
    public void shouldBeAbleToPopulateCards() {
        Player player = new Player("me");
        Snapshot snapshot = new Snapshot();
        Card card1 = Card.createCard(Colour.Green, "_8");
        Card card2 = Card.createCard(Colour.Blue, "_5");
        player.take(card1);
        player.take(card2);
        player.populateSelf(snapshot);
        assertArrayEquals(player.getCards().toArray(), snapshot.myCards);
    }
}