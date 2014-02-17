package com.tw.unoProject.controller;

import org.junit.Test;

import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GameMasterTest {
    UNOFactory unoFactory = mock(UNOFactory.class);

    @Test
    public void shouldCreateAServerSocketToListenForClients(){
        new GameMaster( unoFactory);
        verify(unoFactory,times(1)).createServerSocket();
    }

    @Test
    public void shouldAcceptClientsAsNumberOfPlayersGiven(){
        GameMaster gameMaster = new GameMaster(unoFactory);
        gameMaster.onStartGame("3","2");
        verify(unoFactory,times(2)).acceptClient(any(ServerSocket.class));
    }
}
