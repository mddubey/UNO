package com.step.uno.server.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.communication.server.MessageServerListener;
import com.step.uno.messages.GameSnapshot;

import java.util.ArrayList;
import java.util.List;

public class GameMasterController implements MessageServerListener, MessageChannelListener {
    private final int numberOfPlayers;
    private final int numberOfPacks;
    private MessageServer messageServer;
    private List<MessageChannel> channels = new ArrayList<>();

    public GameMasterController(int numberOfPlayers, int numberOfPacks,CommunicationFactory factory) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfPacks = numberOfPacks;
        messageServer = factory.createMessageServer();
    }

    @Override
    public void onNewConnection(MessageChannel channel) {
        System.out.println("got a new connection");
        if(isHousefull()){
            channel.stop();
            return;
        }
        channels.add(channel);
        channel.startListeningForMessages(this);
        if(isHousefull())startGame();
    }

    private boolean isHousefull() {
        return channels.size() == numberOfPlayers;
    }

    private void startGame() {
        GameSnapshot snapshot = new GameSnapshot();
        for (MessageChannel channel : channels) {
            channel.send(snapshot);
        }
    }

    @Override
    public void onError(Exception e) {

    }

    public void waitForConnections() {
        messageServer.startListeningForConnections(this);
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
}
