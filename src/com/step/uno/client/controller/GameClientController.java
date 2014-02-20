package com.step.uno.client.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.GameClient;
import com.step.uno.client.GameClientObserver;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.messages.GameSnapshot;
import com.step.uno.messages.Introduction;
import com.step.uno.messages.Snapshot;

import java.lang.reflect.InvocationTargetException;

public class GameClientController implements MessageChannelListener, GameClientObserver {
    private CommunicationFactory factory;
    private JoinGameView playerLoginView;
    private PlayerView playerView;
    private MessageChannel channel;
    private GameClient gameClient;

    public GameClientController(CommunicationFactory factory) {
        this.factory = factory;
        gameClient = new GameClient(factory, this);
    }

    public void join(String serverAddress, String playerName) {
        gameClient.start(playerName, serverAddress);
//        channel = factory.connectTo(serverAddress,this);
//        channel.startListeningForMessages(this);
//        channel.send(Introduction.create(playerName));
    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    //    private void handle(GameSnapshot snapshot) {
//        if (playerView == null) playerView = playerLoginView.switchToPlayerView();
//        playerView.update(snapshot);
//    }
//
    @Override
    public void onMessage(MessageChannel client, Object message) {
//
//        try {
//            getClass().getDeclaredMethod("handle", message.getClass()).invoke(this, message);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {
        client.stop();
        if (playerView != null)
            playerView.showDisconnected();
    }

    public void bindView(JoinGameView joinGameView) {
        this.playerLoginView = joinGameView;
    }

    @Override
    public void displaySnapShotOnView(Snapshot snapshot) {
        if (playerView == null) playerView = playerLoginView.switchToPlayerView();
        playerView.update(snapshot);
    }
}
