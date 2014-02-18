package com.tw.unoProject.controller;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameMaster implements ServerScreenObserver {
    private ServerSocket serverSocket;
    private List<GameClient> clients;
    private UNOFactory unoFactory;
    private int numOfPlayers;
    private int numOfPacks;

    public GameMaster(UNOFactory unoFactory) {
        this.unoFactory = unoFactory;
        this.clients = new ArrayList<>();
        this.serverSocket = unoFactory.createServerSocket();
        unoFactory.showServerStartScreen(this);
    }

    public void addClients() {
        for (int i = 0; i < numOfPlayers; i++) {
            GameClient client = unoFactory.acceptClient(serverSocket);
            System.out.println("size" + clients.size());
            clients.add(client);
            System.out.println("size" + clients.size());
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
}
