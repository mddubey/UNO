package com.tw.unoProject.controller;

import com.tw.unoProject.model.Player;
import com.tw.unoProject.view.GameServer;
import com.tw.unoProject.view.PlayerLogin;
import com.tw.unoProject.view.ServerScreen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UNOFactory {
    public ServerSocket createServerSocket() {
        try {
            return new ServerSocket(9090);
        } catch (IOException e) {
            throw new RuntimeException("Unable to start the server", e);
        }
    }

    public MessageChannel acceptClient(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(100);
            return new MessageChannel(socket);
        } catch (IOException e) {
            throw new RuntimeException("not able to accept the client");
        }
    }

    public Socket createClientSocket(String serverAddress) {
        try {
            return new Socket(serverAddress, 9090);
        } catch (IOException e) {
            throw new RuntimeException("could not connect to " + serverAddress + " at " + 9090, e);
        }
    }

    public PlayerLogin showPlayerLoginScreen(PlayerLoginObserver observer) {
        return new PlayerLogin(observer);
    }

    public GameServer showServerStartScreen(ServerScreenObserver observer) {
        return new GameServer(observer);
    }

    public ServerScreen showServerScreen(int numOfPacks, int numOfPlayers) {
        return new ServerScreen(numOfPacks, numOfPlayers);
    }

    public MessageChannel createChannel(Socket socket) {
        return new MessageChannel(socket);
    }

    public Player createPlayer(MessageChannel client, String name) {
        return new Player(name);
    }
}
