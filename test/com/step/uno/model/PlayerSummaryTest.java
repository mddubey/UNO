package com.step.uno.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerSummaryTest {
    @Test
    public void compareTwoPlayerSummariesAreEqual() {
        PlayerSummary summary1 = new PlayerSummary("me", 2, true);
        PlayerSummary summary2 = new PlayerSummary("me", 2, true);
        assertEquals(summary1, summary2);
    }

    @Test
    public void compareTwoPlayersSummariesNotEqualWhenNamesAreDifferent() {
        assertNotSame(new PlayerSummary("me", 2, true), new PlayerSummary("mw", 2, true));
    }

    @Test
    public void compareTwoPlayersSummariesNotEqualWhenNumberOfCardsAreDifferent() {
        assertNotSame(new PlayerSummary("me", 1, true), new PlayerSummary("me", 2, true));
    }

    @Test
    public void compareTwoPlayersSummariesNotEqualWhenDeclareUNOsAreDifferent() {
        assertNotSame(new PlayerSummary("mw", 2, false), new PlayerSummary("mw", 2, true));
    }
}
