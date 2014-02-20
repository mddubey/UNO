package com.tw.unoProject.model;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CardTest {
    @Test
    public void shouldTellTwoCardsAreSame() {
        Card actual = new Card(CardPoints.TWO, CardColor.BLUE);
        Card expected = new Card(CardPoints.TWO, CardColor.BLUE);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirColorIsDifferent() {
        Card actual = new Card(CardPoints.THREE, CardColor.BLUE);
        Card expected = new Card(CardPoints.THREE, CardColor.GREEN);
        assertNotSame(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirValueIsDifferent() {
        Card actual = new Card(CardPoints.TWO, CardColor.BLUE);
        Card expected = new Card(CardPoints.THREE, CardColor.BLUE);
        assertNotSame(expected, actual);
    }

    @Test
    public void shouldGiveValueAndColorOfGivenCard() {
        Card card = new Card(CardPoints.ZERO, CardColor.BLUE);
        Assert.assertEquals(0, card.getCardPoints());
        Assert.assertEquals(Color.BLUE, card.getColor());
    }
}
