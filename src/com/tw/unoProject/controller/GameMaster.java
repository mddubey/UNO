package com.tw.unoProject.controller;

import com.tw.unoProject.view.GameServer;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameMaster implements ServerScreenObserver {
    private int noOfPlayers;
    private int noOfPacks;
    private ServerSocket serverSocket;
    private List<GameClient> clients;
    private UNOFactory unoFactory;
    private final GameServer gameServer;

    public GameMaster(int noOfPlayers, int noOfPacks, UNOFactory unoFactory) {
        this.noOfPlayers = noOfPlayers;
        this.noOfPacks = noOfPacks;
        this.unoFactory = unoFactory;
        this.clients = new ArrayList<>();
        this.serverSocket = unoFactory.createServerSocket();
        gameServer = unoFactory.showServerScreen(this);
    }

    public void addClients(){
        for (int i = 0; i < noOfPlayers; i++) {
            GameClient client = unoFactory.acceptClient(serverSocket);
            System.out.println("size"+clients.size());
            clients.add(client);
            System.out.println("size" + clients.size());
        }
    }

    public static void main(String[] args) {
        new GameMaster(2,1,new UNOFactory()).addClients();
    }

    @Override
    public void onStartGame(String noOfPlayers, String noOfPacks) {
        System.out.println(noOfPlayers + " " + noOfPacks);
    }
}
