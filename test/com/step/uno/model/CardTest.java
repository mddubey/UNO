package com.step.uno.model;

import com.step.uno.messages.Snapshot;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertNotSame;

public class CardTest {
    int draw2Run = 0;
    Snapshot snapshot = new Snapshot();

    @Test
    public void shouldTellTwoCardsAreSame() {
        Card actual = Card.createCard(Colour.Blue, "_2");
        Card expected = Card.createCard(Colour.Blue, "_2");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirColorIsDifferent() {
        Card actual = Card.createCard(Colour.Blue, "_3");
        Card expected = Card.createCard(Colour.Green, "_3");
        assertNotSame(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirValueIsDifferent() {
        Card actual = Card.createCard(Colour.Blue, "_2");
        Card expected = Card.createCard(Colour.Blue, "_3");
        assertNotSame(expected, actual);
    }

    @Test
    public void shouldTellPlayingCardIsSameWithOpenPileCardWhenColorsAreEqual() {
        Card actual = Card.createCard(Colour.Blue, "_2");
        snapshot.openCard = Card.createCard(Colour.Blue, "_3");
        snapshot.draw2Run = draw2Run;
        assertTrue(actual.canFallow(snapshot));
    }

    @Test
    public void shouldTellPlayingCardIsSameWithOpenPileCardWhenValuesAreEqual() {
        Card actual = Card.createCard(Colour.Green, "_2");
        snapshot.openCard = Card.createCard(Colour.Blue, "_2");
        snapshot.draw2Run = draw2Run;
        assertTrue(actual.canFallow(snapshot));
    }

    @Test
    public void shouldTellPlayingCardIsSameWithOpenPileCardWhenValuesAndColorAreEqual() {
        Card actual = Card.createCard(Colour.Blue, "_2");
        snapshot.openCard = Card.createCard(Colour.Blue, "_2");
        snapshot.draw2Run = draw2Run;
        assertTrue(actual.canFallow(snapshot));
    }

    @Test
    public void shouldTellPlayingCardIsDifferentWithOpenPileCardWhenValuesAndColorAreEqual() {
        Card actual = Card.createCard(Colour.Green, "_3");
        snapshot.openCard = Card.createCard(Colour.Blue, "_2");
        snapshot.draw2Run = draw2Run;
        assertFalse(actual.canFallow(snapshot));
    }

    @Test
    public void shouldTellWildCardIsPlayableOnAnyCard() {
        Card expected = Card.createCard(Colour.Black, "_Wild");
        snapshot.openCard = Card.createCard(Colour.Blue, "_2");
        snapshot.draw2Run = draw2Run;
        assertTrue(expected.canFallow(snapshot));
    }

    @Test
    public void shouldTellDrawTwoIsPlayableOnCardWithSameSign() {
        Card expected = Card.createCard(Colour.Green, "_DrawTwo");
        snapshot.openCard = Card.createCard(Colour.Blue, "_DrawTwo");
        snapshot.draw2Run = draw2Run;
        assertTrue(expected.canFallow(snapshot));
    }

    @Test
    public void shouldTellDrawTwoIsPlayableOnCardWithSameSignOrSameColor() {
        Card expected = Card.createCard(Colour.Green, "_DrawTwo");
        snapshot.openCard = Card.createCard(Colour.Green, "_3");
        snapshot.draw2Run = draw2Run;
        assertTrue(expected.canFallow(snapshot));
    }
}
