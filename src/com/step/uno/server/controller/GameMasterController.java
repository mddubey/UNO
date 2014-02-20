package com.step.uno.server.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.communication.server.MessageServerListener;
import com.step.uno.messages.GameSnapshot;
import com.step.uno.server.network.GameMaster;

import java.util.ArrayList;
import java.util.List;

public class GameMasterController implements MessageServerListener, MessageChannelListener {
    private GameMaster gameMaster;
    private final int numberOfPlayers;
    private final int numberOfPacks;
    private CommunicationFactory factory;
    private MessageServer messageServer;
    private List<MessageChannel> channels = new ArrayList<>();

    public GameMasterController(int numberOfPlayers, int numberOfPacks, CommunicationFactory factory) {
        this.gameMaster = new GameMaster(numberOfPlayers,numberOfPacks,factory);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfPacks = numberOfPacks;
        this.factory = factory;
//        messageServer = factory.createMessageServer();
        gameMaster.start();
    }

    @Override
    public void onNewConnection(MessageChannel channel) {
        gameMaster.onNewConnection(channel);
//        if (isHousefull()) {
//            channel.stop();
//            return;
//        }
//        channels.add(channel);
//        channel.startListeningForMessages(this);
//        if (isHousefull()) gameMaster.start();
    }

    private boolean isHousefull() {
        return channels.size() == numberOfPlayers;
    }

    private void startGame() {
        GameSnapshot snapshot = new GameSnapshot();
        for (MessageChannel channel : channels) {
            channel.send(snapshot);
        }
        factory.getServerView(numberOfPlayers, numberOfPacks).display();
    }

    @Override
    public void onError(Exception e) {

    }

//    public void waitForConnections() {
//        messageServer.startListeningForConnections(this);
//
//    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    @Override
    public void onMessage(MessageChannel client, Object message) {

    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }

}
