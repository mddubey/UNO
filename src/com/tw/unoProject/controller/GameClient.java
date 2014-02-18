package com.tw.unoProject.controller;

import java.net.Socket;

public class GameClient implements PlayerLoginObserver, MessageChannelListener {
    private UNOFactory unoFactory;
    private Socket socket;
    private MessageChannel channel;

    public GameClient(UNOFactory unoFactory) {
        this.unoFactory = unoFactory;
    }

    public void connectTo(String serverAddress, String playerName) {
        System.out.println(serverAddress);
        socket = unoFactory.createClientSocket(serverAddress);
        this.channel = unoFactory.createChannel(socket);
        channel.send(playerName);
        channel.startListeningForMessages(this);
    }

    public static void main(String[] args) {
        new GameClient(new UNOFactory()).withLogInScreen();
    }

    private void withLogInScreen() {
        unoFactory.showPlayerLoginScreen(this);
    }

    @Override
    public void onJoin(String gameMasterAddress, String playerName) {
        connectTo(gameMasterAddress, playerName);
    }

    @Override
    public void onError(MessageChannel client, Exception e) {
        //do something
    }

    @Override
    public void onMessage(MessageChannel client, Object message) {
        System.out.println((String)message);
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {
        //do something
    }
}
