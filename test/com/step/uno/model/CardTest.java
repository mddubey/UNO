package com.step.uno.model;

import com.step.uno.model.Card;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CardTest {
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

}
