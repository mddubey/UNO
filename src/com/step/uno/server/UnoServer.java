package com.step.uno.server;

import com.step.communication.factory.CommunicationFactory;
import com.step.uno.server.controller.GameMasterController;

public class UnoServer{
    public static void main(String[] args) {
        GameMasterController controller = new GameMasterController(1, 1, new CommunicationFactory());
        controller.waitForConnections();
    }
}
