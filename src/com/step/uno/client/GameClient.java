package com.step.uno.client;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.factory.CommunicationFactory;
import com.step.uno.messages.*;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;

public class GameClient implements MessageChannelListener {
    private CommunicationFactory communicationFactory;
    private MessageChannel channel;
    private String playerName;
    private GameClientObserver observer;

    public GameClient(CommunicationFactory communicationFactory, GameClientObserver observer) {
        this.communicationFactory = communicationFactory;
        this.observer = observer;
    }

    public void start(String playerName, String serverAddress) {
        this.playerName = playerName;
        this.channel = communicationFactory.connectTo(serverAddress, this);
        channel.startListeningForMessages(this);
        sendIntroduction();
    }

    private void sendIntroduction() {
        channel.send(Introduction.create(playerName));
    }

    public void play(Card card) {
        channel.send(
                new PlayCardAction(card));
    }

    public void play(Card card, Colour newColour) {
        channel.send(new PlayCardAction(card, newColour));
    }

    public void draw() {
        channel.send(new DrawCardAction());
    }

    public void drawTwo() {
        channel.send(new DrawTwoCardAction());
    }

    public void declareUno() {
        channel.send(new DeclareUnoAction());
    }

    public void catchUno(int playerIndex) {
        channel.send(new CatchUnoAction(playerIndex));
    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    @Override
    public void onMessage(MessageChannel client, Object message) {
        if (message.getClass().equals(Snapshot.class)) {
            observer.displaySnapShotOnView((Snapshot) message,playerName);
        }

        if (message.getClass().equals(GameResult.class)) {
            observer.showResults((GameResult)message);
        }

        if (message.getClass().equals(DeclareUnoAction.class)) {
            observer.showPlayerDeclaredUno((DeclareUnoAction)message);
        }

        if(message.getClass().equals(WaitingForDrawnCardAction.class)){
            observer.decideActionAfterDraw();
        }
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {
        channel.stop();
        observer.disconnectView(channel);
    }

    public void noAction() {
        channel.send(new NoActionOnDrawnCard());
    }
}
