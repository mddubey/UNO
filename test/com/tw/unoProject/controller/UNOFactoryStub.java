package com.tw.unoProject.controller;

import com.tw.unoProject.model.Player;
import com.tw.unoProject.view.GameServer;
import com.tw.unoProject.view.PlayerLogin;
import com.tw.unoProject.view.ServerScreen;

import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.mock;

public class UNOFactoryStub extends UNOFactory {
    public ServerSocket serverSocket = mock(ServerSocket.class);
    public MessageChannel channel = mock(MessageChannel.class);
    public ServerScreen serverScreen = mock(ServerScreen.class);
    public PlayerLogin playerLogin = mock(PlayerLogin.class);
    public Socket socket = mock(Socket.class);
    public Player player = mock(Player.class);

    public ServerSocket createServerSocket() {
        return serverSocket;
    }

    @Override
    public MessageChannel acceptClient(ServerSocket serverSocket) {
        return channel;
    }

    public Socket createClientSocket(String serverAddress) {
        return socket;
    }

    public PlayerLogin showPlayerLoginScreen(PlayerLoginObserver observer) {
        return playerLogin;
    }

//    public GameServer showServerStartScreen(ServerScreenObserver observer) {
//        return new GameServer(observer);
//    }

    public ServerScreen showServerScreen(int numOfPacks, int numOfPlayers) {
        return serverScreen;
    }

    public MessageChannel createChannel(Socket socket) {
        return channel;
    }

    public Player createPlayer(MessageChannel client, String name) {
        return player;
    }
}
