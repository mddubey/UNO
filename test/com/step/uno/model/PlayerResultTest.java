package com.step.uno.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class PlayerResultTest {
    @Test
    public void shouldCompareTwoPlayerResultsAreEqual() {
        Card skip = Card.createCard(Colour.Black, "_Skip");
        PlayerResult result1 = new PlayerResult("me", Arrays.asList(skip), 20);
        PlayerResult result2 = new PlayerResult("me", Arrays.asList(skip), 20);
        assertEquals(result1, result2);
    }

    @Test
    public void shouldCompareTwoPlayerResultsAreNotEqualWhenCardsAreDifferent() {
        Card skipBlue = Card.createCard(Colour.Blue, "_Skip");
        Card skipGreen = Card.createCard(Colour.Green, "_Skip");
        PlayerResult playerResult1 = new PlayerResult("me", Arrays.asList(skipBlue), 20);
        PlayerResult playerResult2 = new PlayerResult("me", Arrays.asList(skipGreen), 20);
        assertNotSame(playerResult1, playerResult2);
    }

    @Test
    public void shouldCompareTwoPlayerResultsAreNotEqualWhenNamesAreDifferent(){
        Card skipBlue = Card.createCard(Colour.Blue, "_Skip");
        PlayerResult playerResult1 = new PlayerResult("me", Arrays.asList(skipBlue), 20);
        PlayerResult playerResult2 = new PlayerResult("me1", Arrays.asList(skipBlue), 20);
        assertNotSame(playerResult1, playerResult2);
    }
}
