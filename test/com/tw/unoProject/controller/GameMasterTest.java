package com.tw.unoProject.controller;

import org.junit.Test;

import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameMasterTest {
    UNOFactory unoFactory = mock(UNOFactory.class);
    @Test
    public void shouldCreateAServerSocketToListenForClients(){
        new GameMaster(2, 1, unoFactory);
        verify(unoFactory,times(1)).createServerSocket();
    }
    
    @Test
    public void shouldAcceptClientsAsNumberOfPlayersGiven(){
        GameMaster gameMaster = new GameMaster(5,1,unoFactory);
        gameMaster.addClients();
        verify(unoFactory,times(5)).acceptClient(any(ServerSocket.class));
    }

}
