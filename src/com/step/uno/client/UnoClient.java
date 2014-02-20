package com.step.uno.client;

import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.controller.GameClientController;
import com.step.uno.client.screen.JoinGameScreen;

public class UnoClient {
    public static void main(String[] args) {

        CommunicationFactory factory = new CommunicationFactory();

        GameClientController controller = new GameClientController(factory);
        JoinGameScreen joinGameScreen = new JoinGameScreen(controller);
        joinGameScreen.showScreen();
    }
}
