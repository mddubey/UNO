package com.step.uno.client.controller;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.uno.client.GameClient;
import com.step.uno.client.GameClientObserver;
import com.step.uno.client.screen.PlayerViewObserver;
import com.step.uno.client.view.ColourChooserView;
import com.step.uno.client.view.JoinGameView;
import com.step.uno.client.view.PlayerView;
import com.step.uno.client.view.WaitingView;
import com.step.uno.messages.DeclareUnoAction;
import com.step.uno.messages.GameResult;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;

public class GameClientController implements GameClientObserver, PlayerViewObserver {
    private CommunicationFactory factory;
    private JoinGameView playerLoginView;
    private WaitingView waitingView;
    private PlayerView playerView;
    private ColourChooserView chooserView;
    private GameClient gameClient;


    public GameClientController(CommunicationFactory factory) {
        this.factory = factory;
        gameClient = factory.createGameClient(this);
        waitingView = factory.getWaitingView();
        chooserView = factory.getColourChooserView();
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
        if (!isInAscendingOrder) return "<=";
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
    public void showPlayerDeclaredUno(DeclareUnoAction message) {
        playerView.hasDeclaredUno(message.playerName);
    }

    @Override
    public void onCardPlayed(Card card, Snapshot snapshot) {
        if (!card.canFollow(snapshot)) {
            System.out.println(playerView);
            playerView.showWarningMessage("You can not play this card");
            return;
        }
        if (card.isWild()) {
            Colour newColour = chooserView.showVisible();
            gameClient.play(card, newColour);
            return;
        }
        gameClient.play(card);
    }

    @Override
    public void onDraw(int draw2Run) {
        if (draw2Run > 0) {
            gameClient.drawTwo();
            playerView.disableContinueAfterDraw2();
        } else
            gameClient.draw();
    }

    @Override
    public void onNoAction() {
        gameClient.noAction();
    }

    @Override
    public void onDeclaredUno(int length) {
        if (length == 1)
            gameClient.declareUno();
        else
            playerView.showWarningMessage("Sorry!! You have more than 1 card");
    }
}