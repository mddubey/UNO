package com.step.uno.server.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServerListener;
import com.step.uno.messages.GameSnapshot;
import com.step.uno.messages.Snapshot;
import com.step.uno.server.GameMasterObserver;
import com.step.uno.server.network.GameMaster;

import java.util.List;

public class GameMasterController implements MessageServerListener, MessageChannelListener,GameMasterObserver {
    private final int numberOfPlayers;
    private final int numberOfPacks;
    private GameMaster gameMaster;
    private CommunicationFactory factory;
    private List<MessageChannel> channels;

    public GameMasterController(int numberOfPlayers, int numberOfPacks, CommunicationFactory factory) {
        this.gameMaster = factory.createGameServer(numberOfPlayers,numberOfPacks,this);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfPacks = numberOfPacks;
        this.factory = factory;
        gameMaster.start();
    }

    @Override
    public void onNewConnection(MessageChannel channel) {
        gameMaster.onNewConnection(channel);
    }
    private void startGame() {
        GameSnapshot snapshot = new GameSnapshot();
        for (MessageChannel channel : channels) {
            channel.send(snapshot);
        }
    }

    @Override
    public void onError(Exception e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    @Override
    public void onMessage(MessageChannel client, Object message) {

    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }

    @Override
    public void onGameStart(Snapshot snapshot) {
//        factory.getServerView(numberOfPlayers,numberOfPacks).display(snapshot);
    }
}
