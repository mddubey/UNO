package com.tw.unoProject.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CardTest {
    @Test
    public void shouldTellTwoCardsAreSame() {
        Card actual = new Card(2, CardColor.BLUE);
        Card expected = new Card(2, CardColor.BLUE);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirColorIsDifferent() {
        Card actual = new Card(3, CardColor.BLUE);
        Card expected = new Card(3, CardColor.GREEN);
        assertNotSame(expected, actual);
    }

    @Test
    public void shouldTellTwoCardsAreNotSameWhenTheirValueIsDifferent() {
        Card actual = new Card(2, CardColor.BLUE);
        Card expected = new Card(3, CardColor.BLUE);
        assertNotSame(expected, actual);
    }
}
