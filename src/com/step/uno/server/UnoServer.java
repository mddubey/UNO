package com.step.uno.server;

import com.step.communication.factory.CommunicationFactory;
import com.step.uno.server.controller.GameMasterController;

public class UnoServer{
    public static void main(String[] args) {
        int noOfPlayer = Integer.parseInt(args[0]);
        int noOfPacks = Integer.parseInt(args[1]);
        GameMasterController controller = new GameMasterController(noOfPlayer, noOfPacks, new CommunicationFactory());
        controller.waitForConnections();
    }
}
