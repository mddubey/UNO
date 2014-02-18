package com.tw.unoProject.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RandomizerTest {
    @Test
    public void shouldDealCardsToEachPlayer() {
        List<Player> players = Arrays.asList(new Player("kashish"), new Player("manali"), new Player("samiksha"), new Player("dubey"));
        Randomizer randomizer = new Randomizer(new Pack());
        randomizer.dealCards(players);
        for (Player player : players) {
            assertEquals(7, player.getMyCards().size());
        }
    }

    @Test
    public void shouldGiveOpenPile() {
        Pack pack = new Pack();
        Randomizer randomizer = new Randomizer(pack);
        randomizer.openPile();
        assertEquals(75, pack.getCards().size());

    }

    @Test
    public void shouldGiveOpenPile2() {
        List<Player> players = Arrays.asList(new Player("kashish"), new Player("manali"), new Player("samiksha"), new Player("dubey"));

        Pack pack = new Pack();
        Randomizer randomizer = new Randomizer(pack);
        randomizer.dealCards(players);
        Card card1 = pack.getCards().get(0);
        Card card2 = randomizer.openPile();
        assertEquals(card1, card2);

    }

    @Test
    public void shouldGiveOpenPile3() {
        List<Player> players = Arrays.asList(new Player("kashish"), new Player("manali"), new Player("samiksha"), new Player("dubey"));
        Pack pack = new Pack();
        Randomizer randomizer = new Randomizer(pack);
        randomizer.dealCards(players);
        randomizer.openPile();
        assertEquals(47, pack.getCards().size());
    }

    @Test
    public void shouldGiveClosePile() {
        Pack pack = new Pack();
        Randomizer randomizer = new Randomizer(pack);
        randomizer.closePile();
        assertEquals(76, pack.getCards().size());
    }
}

