package com.step.uno.client.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.GameClient;
import com.step.uno.client.GameClientObserver;
import com.step.uno.client.screen.PlayerViewObserver;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.client.view.WaitingView;
import com.step.uno.messages.GameResult;
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
    public void displaySnapShotOnView(Snapshot snapshot, String playerName) {
        boolean enable;
        if (playerView == null) playerView = playerLoginView.switchToPlayerView(playerName);
        waitingView.showVisible(false);
        if (snapshot.currentPlayerIndex != snapshot.myPlayerIndex) enable = false;
        else
            enable = true;
        String direction = decideDirectionOfArrow(snapshot.isInAscendingOrder);
        playerView.update(snapshot, this, enable, direction);
    }
      
    private String decideDirectionOfArrow(boolean isInAscendingOrder) {
        if (isInAscendingOrder == false) return "<=";
        else return "=>";
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
    public void showResults(GameResult result) {
        playerView.setVisible(false);
        playerView.switchToGameOverView(result);
    }

    @Override
    public void onCardPlayed(Card card, Snapshot snapshot) {
        if (card.canFallow(snapshot))
            gameClient.play(card);
        else playerView.showWarningMessage();
    }

    @Override
    public void onDraw(int count) {
        if(count > 0)
        gameClient.drawTwo();
        else
            gameClient.draw();
    }

    @Override
    public void onNoAction() {
        gameClient.noAction();
    }
}
