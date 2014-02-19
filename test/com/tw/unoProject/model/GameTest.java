package com.tw.unoProject.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Player[] playerInArray = new Player[]{new Player("kashish"), new Player("manali"), new Player("samiksha"), new Player("dubey")};

    @Test
    public void shouldDealCardsToEachPlayer() {
        List<Player> players = Arrays.asList(playerInArray);
        new Game(players, 1).start();
        for (Player player : players) {
            assertEquals(7, player.getMyCards().size());
        }
    }

    @Test
    public void shouldDealCardsToEachPlayerWhenNoOfPacksAreMoreThanOne() {
        List<Player> players = Arrays.asList(playerInArray);
        new Game(players, 3).start();
        for (Player player : players) {
            assertEquals(7, player.getMyCards().size());
        }
    }
}
