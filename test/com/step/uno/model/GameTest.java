package com.step.uno.model;

import com.step.uno.messages.Snapshot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GameTest {

    private List<Player> players = Arrays.asList(new Player("me"), new Player("me2"), new Player("me4"), new Player("me1"));

    @Test
    public void shouldInitializeTheGame() {
        List<Player> givenPlayers = new ArrayList<>();
        givenPlayers.add(new Player("kash"));
        Game game = new Game(1, givenPlayers);
        game.initialize();
        PlayerSummary playerSummary = givenPlayers.get(0).generateSummary();

        assertEquals(playerSummary.cardsInHand, 7);
    }

    @Test
    public void onInitializeGameEachPlayerGetsSevenCards() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Player player3 = mock(Player.class);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Game game = new Game(1, players);

        game.initialize();
        verify(player1, times(7)).take(any(Card.class));
        verify(player2, times(7)).take(any(Card.class));
        verify(player3, times(7)).take(any(Card.class));
    }

    @Test
    public void shouldPopulateSnapshotOfTheGame() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("kash"));
        players.add(new Player("me"));

        Game game = new Game(1, players);
        Snapshot snapshot = new Snapshot();
        game.initialize();
        game.populate(snapshot, players.get(0));
        assertEquals(snapshot.currentPlayerIndex, 0);
        assertEquals(snapshot.draw2Run, 0);
        assertEquals(snapshot.isInAscendingOrder, true);
        assertEquals(snapshot.myCards.length, 7);
    }

    @Test
    public void afterPlayingASkipCardTheNextPlayerShouldBeSkipped() {
        Game game = new Game(1, players);
        game.initialize();
        Card skip = Card.createCard(Colour.Blue, "_Skip");
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, players.get(0));
        assertEquals(0, snapshot.currentPlayerIndex);
        game.playCard(players.get(0), skip, Colour.Blue);
        game.populate(snapshot, players.get(0));
        assertEquals(2, snapshot.currentPlayerIndex);
    }

    @Test
    public void afterPlayingAReverseCardTheSequenceShouldBeReversed() {
        Game game = new Game(1, players);
        game.initialize();
        Card reverse = Card.createCard(Colour.Blue, "_Reverse");
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, players.get(0));
        assertEquals(0, snapshot.currentPlayerIndex);
        game.playCard(players.get(0), reverse, Colour.Blue);
        game.populate(snapshot, players.get(0));
        assertEquals(3, snapshot.currentPlayerIndex);
    }

    @Test
    public void afterPlayingDraw2TheDraw2runCountShouldIncrementByOneAndTurnShouldChange() {
        Card drawTwo = Card.createCard(Colour.Yellow, "_DrawTwo");
        Game game = new Game(1, players);
        game.initialize();
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, players.get(0));
        assertEquals(0, snapshot.draw2Run);
        game.playCard(players.get(0), drawTwo, Colour.Blue);
        game.populate(snapshot, players.get(0));
        assertEquals(1, snapshot.draw2Run);
    }

    @Test
    public void afterPlayingACardCurrentTurnLogShouldGetUpdated() {
        Card drawTwo = Card.createCard(Colour.Yellow, "_DrawTwo");
        Game game = new Game(1, players);
        game.initialize();
        game.playCard(players.get(0), drawTwo , Colour.Yellow);
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, players.get(0));
        assertEquals("me played a Yellow DrawTwo card\n",snapshot.currentTurnLog);
    }

    @Test
    public void afterDrawingACardCurrentTurnLogShouldGetUpdated() {
        Card drawTwo = Card.createCard(Colour.Yellow, "_DrawTwo");
        Game game = new Game(1, players);
        game.initialize();
        game.drawCard(players.get(0));
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, players.get(0));
        assertEquals("me drew a card \n",snapshot.currentTurnLog);
    }

    @Test
    public void afterInitializingGenerateSnapshotShouldHaveACurrentTurnLog() {
        Card drawTwo = Card.createCard(Colour.Yellow, "_DrawTwo");
        Game game = new Game(1, players);
        game.initialize();
        Snapshot snapshot = new Snapshot();
        game.populate(snapshot, players.get(0));

        String expected = "Game starts with";
        assertTrue(snapshot.currentTurnLog.startsWith(expected));
    }

    @Test
    public void onDrawTwoAPlayerShouldTakeCardAsManyAsNumberOfDrawTwoCounts() {
        List<Player> mockPlayers =
                Arrays.asList(mock(Player.class), mock(Player.class));

        Game game = new Game(1, mockPlayers);
        game.playCard(mockPlayers.get(0), Card.createCard(Colour.Blue, "_DrawTwo"), Colour.Blue);

        game.drawTwoCards(mockPlayers.get(1));
        verify(mockPlayers.get(1), times(2)).take(any(Card.class));
    }

    @Test
    public void onPlayingADrawFourNextPlayerShouldTakeFourCards() {
        List<Player> mockPlayers = Arrays.asList(mock(Player.class), mock(Player.class));

        Game game = new Game(1, mockPlayers);
        game.playCard(mockPlayers.get(0), Card.createCard(Colour.Black, "_DrawFour"), Colour.Blue);

        verify(mockPlayers.get(1), times(4)).take(any(Card.class));
    }


}
