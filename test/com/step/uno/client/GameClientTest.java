package com.step.uno.client;

import com.step.communication.channel.MessageChannelListener;
import com.step.uno.controller.StubFactory;
import com.step.uno.messages.Introduction;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class GameClientTest {
    @Test
    public void shouldSendSendIntroductionAndListenForMessage() {
        StubFactory stub = new StubFactory();
        GameClient gameClient = new GameClient(stub, mock(GameClientObserver.class));
        gameClient.start("ram", "fs");
        verify(stub.channel, times(1)).startListeningForMessages(any(MessageChannelListener.class));
        verify(stub.channel, times(1)).send(any(Introduction.class));
    }

    @Test
    public void shouldSendAPlayedActionWhenACardIsPlayed() {
        StubFactory stub = new StubFactory();
        GameClient gameClient = new GameClient(stub, mock(GameClientObserver.class));
        gameClient.start("ram", "fs");
        gameClient.play(new Card());

        verify(stub.channel, times(2)).send(anyObject());
    }

    @Test
    public void shouldInformObserverWhenAMessageArrives() {
        StubFactory stub = new StubFactory();
        GameClientObserver mock = mock(GameClientObserver.class);
        GameClient gameClient = new GameClient(stub, mock);
        Snapshot snapshot = new Snapshot();
        gameClient.onMessage(stub.channel, snapshot);

        verify(mock,times(1)).displaySnapShotOnView(snapshot);
    }
}
