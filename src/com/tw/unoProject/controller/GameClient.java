package com.tw.unoProject.controller;

import java.net.Socket;

public class GameClient implements PlayerLoginObserver {
    private UNOFactory unoFactory;
    private Socket socket;

    public GameClient(UNOFactory unoFactory) {
        this.unoFactory = unoFactory;
    }

    public void connectTo(String serverAddress, String playerName) {
        System.out.println(serverAddress);
        socket = unoFactory.createClientSocket(serverAddress);
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
}
