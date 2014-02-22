package com.step.uno.controller;

import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameClientControllerTest {
    StubFactory stub = new StubFactory();
    PlayerView playerView = mock(PlayerView.class);
    JoinGameView joinGameView = mock(JoinGameView.class);
    private GameClientController controller;

    @Before
    public void setup() {
        when(joinGameView.switchToPlayerView()).thenReturn(playerView);
        controller = new GameClientController(stub);
        controller.bindView(joinGameView);
    }

    @Test
    public void sendsIntroductionAfterJoiningGame() {
        controller.join("serverAddress", "me");
        verify(stub.gameClient, times(1)).start("me", "serverAddress");
    }

    @Test
    public void viewShouldBeNotVisibleAfterJoiningGame() {
        controller.join("server", "me");
        verify(joinGameView, times(1)).showVisible(false);
    }

    @Test
    public void waitingViewShouldBeVisibleAfterJoiningTheGame() {
        controller.join("server", "me");
        verify(stub.waitingView, times(1)).showVisible(true);
    }

    @Test
    public void disconnectUserWhenConnectionClosed() {
        controller.join("serverAddress", "me");
        controller.disconnectView(stub.channel);
        verify(playerView, times(0)).showDisconnected();
    }

    @Test
    public void displaysGameSnapshot() {
        controller.join("serverAddress", "me");
        Snapshot snapshot = new Snapshot();
        controller.displaySnapShotOnView(snapshot);
        verify(stub.waitingView, times(1)).showVisible(false);
        verify(playerView, times(1)).update(snapshot, controller);
    }

    @Test
    public void shouldBeAbleToInformThatCardHasPlayed() {
        Card card = new Card();
        controller.onCardPlayed(card);
        verify(stub.gameClient, times(1)).play(card);
    }

    @Test
    public void shouldBeAbleToInformThatOneCardHasDrawn() {
        controller.onDraw();

        verify(stub.gameClient, times(1)).draw();

    }
}
