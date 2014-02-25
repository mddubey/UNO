package com.step.uno.server.network;

import com.step.communication.channel.MessageChannel;
import com.step.communication.factory.CommunicationFactory;
import com.step.communication.server.MessageServer;
import com.step.communication.server.MessageServerListener;
import com.step.uno.messages.DeclareUnoAction;
import com.step.uno.messages.GameResult;
import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;
import com.step.uno.model.Game;
import com.step.uno.model.Player;
import com.step.uno.server.GameMasterObserver;

import java.util.ArrayList;
import java.util.List;

public class GameMaster implements MessageServerListener, PlayerProxyObserver {
    private final int totalPlayers;
    private final int totalPacks;
    private final CommunicationFactory factory;
    private GameMasterObserver observer;
    private MessageServer server;
    private final List<PlayerProxy> proxies = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Game game;

    public GameMaster(int totalPlayers, int packs, CommunicationFactory factory, GameMasterObserver observer) {
        this.totalPlayers = totalPlayers;
        this.totalPacks = packs;
        this.factory = factory;
        this.observer = observer;
    }

    public void start() {
        server = factory.createMessageServer();
        server.startListeningForConnections(this);
        System.out.println("Game server is started");
    }

    @Override
    public void onNewConnection(MessageChannel channel) {
        if (proxies.size() == totalPlayers) {
            channel.stop();
            return;
        }
        PlayerProxy proxy = new PlayerProxy(channel, this);
        proxy.start();
        proxies.add(proxy);
    }

    private void startGame() {
        game = new Game(totalPacks, players);
        game.initialize();
        sendSnapshot();
    }

    private void sendSnapshot() {
        for (PlayerProxy proxy : proxies)
            proxy.sendSnapshot(game);
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onPlayerRegistered(Player player) {
        players.add(player);
        if (players.size() == totalPlayers) {
            startGame();
            Snapshot snapshot = new Snapshot();
            game.populate(snapshot, player);
            observer.onGameStart(snapshot);
        }
    }

    @Override
    public void onPlayerPlayed(Player player, Card card, Colour newColour) {
        game.playCard(player, card, newColour);
        if (player.hasWon())
            sendResult();
        else
            sendSnapshot();
    }

    private void sendResult() {
        GameResult result = new GameResult();
        game.populate(result);
        for (PlayerProxy proxy : proxies)
            proxy.sendResult(result);
    }

    @Override
    public void onPlayerDrewCard(Player player) {
        Card card = game.drawCard(player);
        sendSnapshotToWaitingPlayer(player);
    }

    private void sendSnapshotToWaitingPlayer(Player player) {
        for (PlayerProxy proxy : proxies)
            proxy.sendSnapShotToPlayer(game,player);
    }

    @Override
    public void onPlayerDeclaredUno(Player player) {
        game.declareUno(player);
        sendDeclareUnoAction(new DeclareUnoAction(player.name));
    }

    private void sendDeclareUnoAction(DeclareUnoAction declareUnoAction) {
        for (PlayerProxy proxy : proxies) {
            proxy.sendDeclareUnoAction(declareUnoAction);
        }
    }

    @Override
    public void onPlayerCaughtUno(int playerIndex) {
        game.catchUno(playerIndex);
        sendSnapshot();
    }

    @Override
    public void onPlayerDrewTwoCards(Player player) {
        game.drawTwoCards(player);
        sendSnapshot();
    }

    @Override
    public void onNoActionOnDrawnCard(Player player) {
        game.moveForwardAsPlayerTookNoActionOnDrawnCard();
        sendSnapshot();
    }
}