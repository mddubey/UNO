package com.step.uno.model;

import com.step.uno.messages.Snapshot;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldBeAbleToAddACardInCardsList() {
        Player player = new Player("somePlayer");
        Card card = Card.createCard(Colour.Green, "_8");
        player.take(card);

        Snapshot snapshot = new Snapshot();
        player.populateSelf(snapshot);
        assertEquals(snapshot.myCards[0], card);

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
        assertEquals(card1, snapshot.myCards[0]);
        assertEquals(card2, snapshot.myCards[1]);
    }

    @Test
    public void shouldBeAbleToGetAllTheCardsPresentInList() {
        Player player = new Player("someone");
        player.take(Card.createCard(Colour.Yellow, "_8"));
        player.take(Card.createCard(Colour.Red, "_5"));

        Snapshot snapshot = new Snapshot();
        assertNull(snapshot.myCards);
        player.populateSelf(snapshot);
        assertEquals(2, snapshot.myCards.length);
    }

    @Test
    public void shouldBeAbleToPlayACard() {
        Player player = new Player("someone");
        Card card = Card.createCard(Colour.Blue, "_5");
        player.take(card);
        Snapshot snapshot = new Snapshot();
        player.play(card);
        player.populateSelf(snapshot);
        assertEquals(0, snapshot.myCards.length);
    }

    @Test
    public void playedCardShouldBeRemovedFromCardListOfPlayer() {
        Player player = new Player("me");
        Card card1 = Card.createCard(Colour.Green, "_8");
        Card card2 = Card.createCard(Colour.Blue, "_5");
        player.take(card1);
        player.take(card2);
        Snapshot snapshot = new Snapshot();
        player.play(card1);
        player.populateSelf(snapshot);
        assertEquals(snapshot.myCards[0], card2);
    }

    @Test
    public void shouldBeAbleToGenerateSummary() {
        Player player = new Player("some");
        player.take(new Card());
        player.take(new Card());
        PlayerSummary playerSummary = player.generateSummary();
        PlayerSummary expectedSummary = new PlayerSummary("some", 2, false);
        assertEquals(playerSummary, expectedSummary);
    }

    @Test
    public void playerWinsTheGameWhenHeOrSheHasNoCardInHand() {
        Player player = new Player("me");
        Card card = new Card();
        player.take(card);
        player.play(card);
        assertTrue(player.hasWon());
    }

    @Test
    public void playerDoesNotWinTheGameWhenHeOrSheHasCards() {
        Player player = new Player("me");
        player.take(new Card());
        assertFalse(player.hasWon());
    }

    @Test
    public void shouldBeAbleToCalculatePoints() {
        Player player = new Player("me");
        player.take(Card.createCard(Colour.Black, "_DrawFour"));
        player.take(Card.createCard(Colour.Blue, "_Skip"));
        player.take(Card.createCard(Colour.Blue, "_5"));
        assertEquals(75, player.calculatePoints());
    }

    @Test
    public void playerShouldGenerateResult() {
        Player player = new Player("me");
        Card drawFour = Card.createCard(Colour.Black, "_DrawFour");
        Card skip = Card.createCard(Colour.Blue, "_Skip");
        Card _5 = Card.createCard(Colour.Blue, "_5");
        player.take(drawFour);
        player.take(skip);
        player.take(_5);
        PlayerResult playerResult = player.generateResult();
        PlayerResult expectedResult = new PlayerResult("me", Arrays.asList(drawFour, skip, _5), 75);
        assertEquals(expectedResult, playerResult);
    }
}