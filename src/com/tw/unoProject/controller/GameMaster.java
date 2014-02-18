package com.tw.unoProject.controller;

import com.tw.unoProject.model.Player;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameMaster implements ServerScreenObserver, MessageChannelListener {
    private ServerSocket serverSocket;
    private List<Player> players;
    private UNOFactory unoFactory;
    private MessageChannel channel;
    private int numOfPlayers;
    private int numOfPacks;

    public GameMaster(UNOFactory unoFactory) {
        this.unoFactory = unoFactory;
        this.players = new ArrayList<>();
        this.serverSocket = unoFactory.createServerSocket();
        unoFactory.showServerStartScreen(this);
    }

    public void addClients() {
        for (int i = 0; i < numOfPlayers; i++) {
            unoFactory.acceptClient(serverSocket).startListeningForMessages(this);
        }
    }

    public static void main(String[] args) {
        new GameMaster(new UNOFactory());
    }

    @Override
    public void onStartGame(String noOfPacks, String noOfPlayers) {
        numOfPlayers = Integer.parseInt(noOfPlayers);
        numOfPacks = Integer.parseInt(noOfPacks);
        unoFactory.showServerScreen(numOfPlayers, numOfPacks);
        addClients();
    }

    @Override
    public void onError(MessageChannel client, Exception e) {
        //do something
    }

    @Override
    public void onMessage(MessageChannel channel, Object message) {
        Player player = unoFactory.createPlayer(channel, (String) message);
        players.add(player);
        channel.send("waiting");
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }
}
