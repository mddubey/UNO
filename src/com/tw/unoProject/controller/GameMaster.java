package com.tw.unoProject.controller;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameMaster {
    private int noOfPlayers;
    private int noOfPacks;
    private ServerSocket serverSocket;
    private List<Client> clients;
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
            Client client = unoFactory.acceptClient(serverSocket);
            System.out.println("size"+clients.size());
            clients.add(client);
            System.out.println("size"+clients.size());
        }
    }

    public static void main(String[] args) {
        new GameMaster(2,1,new UNOFactory()).addClients();
    }
}
