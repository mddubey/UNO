package com.tw.unoProject.controller;

import org.junit.Test;

import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GameMasterTest {
    UNOFactoryStub unoFactory = new UNOFactoryStub();

    @Test
    public void shouldCreateAServerSocketToListenForClients() {
        UNOFactory stub = mock(UNOFactory.class);
        new GameMaster(stub);
        verify(stub, times(1)).createServerSocket();
    }

    @Test
    public void shouldAcceptClientsAsNumberOfPlayersGiven() {
        GameMaster gameMaster = new GameMaster(unoFactory);
        gameMaster.onStartGame("3", "2");
        verify(unoFactory.channel, times(2)).startListeningForMessages(gameMaster);
    }
    
    @Test
    public void sendMessageToClientWhenItGetsPlayerName() {
        GameMaster gameMaster = new GameMaster(unoFactory);
        gameMaster.onMessage(unoFactory.channel,"Kashish");

        verify(unoFactory.channel,times(1)).send("waiting");
    }
}
