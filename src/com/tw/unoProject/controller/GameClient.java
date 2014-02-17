package com.tw.unoProject.controller;

import java.net.Socket;

public class GameClient {
    private Socket socket;

    public GameClient(Socket socket) {
        this.socket = socket;
    }
}
