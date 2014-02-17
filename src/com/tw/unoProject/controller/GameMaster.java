package com.tw.unoProject.controller;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameMaster {
    private int noOfPlayers;
    private int noOfPacks;
    private ServerSocket serverSocket;
    private List<GameClient> clients;
    private UNOFactory unoFactory;

    public GameMaster(int noOfPlayers, int noOfPacks, UNOFactory unoFactory) {
        this.noOfPlayers = noOfPlayers;
        this.noOfPacks = noOfPacks;
        this.unoFactory = unoFactory;
        this.clients = new ArrayList<>();
        this.serverSocket = unoFactory.createServerSocket();
    }

    public void addClients(){
        for (int i = 0; i < noOfPlayers; i++) {
            GameClient gameClient = unoFactory.acceptClient(serverSocket);
            clients.add(gameClient);
        }
    }
}
