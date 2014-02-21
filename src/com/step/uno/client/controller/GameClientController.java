package com.step.uno.client.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.GameClient;
import com.step.uno.client.GameClientObserver;
import com.step.uno.client.screen.PlayerViewObserver;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.client.view.WaitingView;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;

public class GameClientController implements GameClientObserver, PlayerViewObserver {
    private CommunicationFactory factory;
    private JoinGameView playerLoginView;
    private WaitingView waitingView;
    private PlayerView playerView;
    private GameClient gameClient;

    public GameClientController(CommunicationFactory factory) {
        this.factory = factory;
        gameClient = factory.createGameClient(this);
        waitingView = factory.getWaitingView();
    }

    public void join(String serverAddress, String playerName) {
        gameClient.start(playerName, serverAddress);
        playerLoginView.showVisible(false);
        waitingView.showVisible(true);
    }

    public void bindView(JoinGameView joinGameView) {
        this.playerLoginView = joinGameView;
    }

    @Override
    public void displaySnapShotOnView(Snapshot snapshot) {
        if (playerView == null) playerView = playerLoginView.switchToPlayerView();
        playerView.update(snapshot, this);
    }

    @Override
    public void disconnectView(MessageChannel channel) {
        if (playerView != null)
            playerView.showDisconnected();
    }

    @Override
    public void decideActionAfterDraw() {
//        playerView.
    }

    @Override
    public void onCardPlayed(Card card) {
        gameClient.play(card);
    }

    @Override
    public void onDraw() {
        gameClient.draw();
    }

    @Override
    public void onNoAction() {
        gameClient.noAction();
    }
}
