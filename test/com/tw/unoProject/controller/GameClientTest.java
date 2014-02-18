package com.tw.unoProject.controller;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameClientTest {
    UNOFactoryStub unoFactory = new UNOFactoryStub();
    @Test
    public void shouldCallCreateConnection() {

        GameClient client = new GameClient(unoFactory);
        String serverAddress = "127.0.0.1";

        String kash = "kash";
        client.connectTo(serverAddress, kash);

        verify(unoFactory.channel, times(1)).send(kash);
        verify(unoFactory.channel, times(1)).startListeningForMessages(client);
    }

}
