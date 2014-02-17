package com.tw.unoProject.controller;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameClientTest {
    @Test
    public void shouldCallCreateConnection() {
        UNOFactory stub = mock(UNOFactory.class);

        GameClient connection = new GameClient(stub);
        String serverAddress = "127.0.0.1";
        connection.run(serverAddress,"kash");

        verify(stub, times(1)).createClientSocket(serverAddress);
    }
}
