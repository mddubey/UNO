package com.step.uno.model;

import org.junit.Test;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertNotSame;

public class CardTest {
    int draw2Run = 0;
    @Test
    public void shouldTellTwoCardsAreSame() {
        Card actual = Card.createCard(Colour.Blue,"_2" );
        Card expected = Card.createCard(Colour.Blue,"_2" );
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirColorIsDifferent() {
        Card actual = Card.createCard(Colour.Blue,"_3" );
        Card expected = Card.createCard(Colour.Green,"_3" );
        assertNotSame(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirValueIsDifferent() {
        Card actual = Card.createCard(Colour.Blue,"_2" );
        Card expected = Card.createCard(Colour.Blue,"_3" );
        assertNotSame(expected, actual);
    }
    @Test
    public void shouldTellPlayingCardIsSameWithOpenPileCardWhenColorsAreEqual() {
        Card actual = Card.createCard(Colour.Blue,"_2" );
        Card expected = Card.createCard(Colour.Blue,"_3" );
        assertTrue(actual.isCardEqual(expected, draw2Run));
    }
    @Test
    public void shouldTellPlayingCardIsSameWithOpenPileCardWhenValuesAreEqual() {
        Card actual = Card.createCard(Colour.Green,"_2" );
        Card expected = Card.createCard(Colour.Blue,"_2" );
        assertTrue(actual.isCardEqual(expected, draw2Run));
    }
    @Test
    public void shouldTellPlayingCardIsSameWithOpenPileCardWhenValuesAndColorAreEqual() {
        Card actual = Card.createCard(Colour.Blue,"_2" );
        Card expected = Card.createCard(Colour.Blue,"_2" );
        assertTrue(actual.isCardEqual(expected, draw2Run));
    }
    @Test
    public void shouldTellPlayingCardIsDifferentWithOpenPileCardWhenValuesAndColorAreEqual() {
        Card actual = Card.createCard(Colour.Green,"_3" );
        Card expected = Card.createCard(Colour.Blue,"_2" );
        assertFalse(actual.isCardEqual(expected, draw2Run));
    }
    @Test
    public void shouldTellWildCardIsPlayableOnAnyCard() {
        Card expected = Card.createCard(Colour.Green,"_Wild" );
        Card actual = Card.createCard(Colour.Blue,"_2" );
        assertTrue(expected.isCardEqual(actual, draw2Run));
    }
    @Test
     public void shouldTellDrawFourIsPlayableOnAnyCard() {
        Card expected = Card.createCard(Colour.Green,"_DrawFour" );
        Card actual = Card.createCard(Colour.Blue,"_2" );
        assertTrue(expected.isCardEqual(actual, draw2Run));
    }
    @Test
    public void shouldTellDrawTwoIsPlayableOnCardWithSameSign() {
        Card expected = Card.createCard(Colour.Green,"_DrawTwo" );
        Card actual = Card.createCard(Colour.Blue,"_DrawTwo" );
        assertTrue(expected.isCardEqual(actual, 1));
    }
    @Test
    public void shouldTellDrawTwoIsPlayableOnCardWithSameSignOrSameColor() {
        Card expected = Card.createCard(Colour.Green,"_DrawTwo" );
        Card actual = Card.createCard(Colour.Green,"_3" );
        assertTrue(expected.isCardEqual(actual, draw2Run));
    }
}
