package com.step.uno.client;

import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.screen.PlayerLogin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnoClient {
    public static void main(String[] args) {
        CommunicationFactory factory = new CommunicationFactory();

        GameClientController controller = new GameClientController(factory);
        PlayerLogin playerLogin = new PlayerLogin(controller);
        playerLogin.showScreen();
    }
}
