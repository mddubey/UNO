package com.step.uno.model;

import com.step.uno.messages.Snapshot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {
    @Test
    public void shouldInitializeTheGame() {
        List<Player> givenPlayers = new ArrayList<>();
        givenPlayers.add(new Player("kash"));
        Game game = new Game(1, givenPlayers);
        game.initialize();
        PlayerSummary playerSummary = givenPlayers.get(0).generateSummary();

        assertEquals(playerSummary.cardsInHand,7);
    }
    @Test
    public void testPopulateTheSnapshot() {
        ArrayList<Player> givenPlayers = new ArrayList<>();
        givenPlayers.add(new Player("kash"));

        Game game = new Game(1, givenPlayers);
        Snapshot snapshot = new Snapshot();
        game.initialize();
        game.populate(snapshot, givenPlayers.get(0));
        assertEquals(snapshot.currentPlayerIndex, 0);
        assertEquals(snapshot.draw2Run,0);
        assertEquals(snapshot.isInAscendingOrder,true);
        assertEquals(snapshot.myCards.length,7);
    }
}
