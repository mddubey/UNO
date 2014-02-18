package com.tw.unoProject.controller;

import com.tw.unoProject.model.Player;
import com.tw.unoProject.view.GameServer;
import com.tw.unoProject.view.PlayerLogin;
import com.tw.unoProject.view.ServerScreen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.mock;

public class UNOFactoryStub extends UNOFactory {
    private ServerSocket serverSocket = mock(ServerSocket.class);
    private MessageChannel channel = mock(MessageChannel.class);
    private Socket socket = mock(Socket.class);
    private Player player = mock(Player.class);

    public ServerSocket createServerSocket() {
        return serverSocket;
    }

    public MessageChannel acceptClient(ServerSocket serverSocket) {
        return channel;
    }

    public Socket createClientSocket(String serverAddress) {
        return socket;
    }

//    public PlayerLogin showPlayerLoginScreen(PlayerLoginObserver observer) {
//        return new PlayerLogin(observer);
//    }
//
//    public GameServer showServerStartScreen(ServerScreenObserver observer) {
//        return new GameServer(observer);
//    }
//
//    public ServerScreen showServerScreen(int numOfPacks, int numOfPlayers) {
//        return new ServerScreen(numOfPacks, numOfPlayers);
//    }

    public MessageChannel createChannel(Socket socket) {
        return channel;
    }

    public Player createPlayer(MessageChannel client, String name) {
        return player;
    }
}
