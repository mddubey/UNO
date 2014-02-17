package com.tw.unoProject.controller;

import java.net.Socket;

public class GameClient {
    private UNOFactory unoFactory;
    private Socket socket;

    public GameClient(UNOFactory unoFactory) {
        this.unoFactory = unoFactory;
    }

    public void run(String serverAddress, String playerName){
        socket = unoFactory.createClientSocket(serverAddress);
    }

    public static void main(String[] args) {
        new GameClient(new UNOFactory()).run("127.0.0.1","kash");
    }
}
