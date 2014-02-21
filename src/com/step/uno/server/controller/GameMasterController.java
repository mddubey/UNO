package com.step.uno.server.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServerListener;
import com.step.uno.server.network.GameMaster;

public class GameMasterController implements MessageServerListener{
    private GameMaster gameMaster;
    private CommunicationFactory factory;

    public GameMasterController(int numberOfPlayers, int numberOfPacks, CommunicationFactory factory) {
        this.gameMaster = factory.createGameServer(numberOfPlayers,numberOfPacks);
        this.factory = factory;
        gameMaster.start();
    }

    @Override
    public void onNewConnection(MessageChannel channel) {
        gameMaster.onNewConnection(channel);
    }

    @Override
    public void onError(Exception e) {
        throw new RuntimeException(e);
    }
}
