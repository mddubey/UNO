package com.tw.unoProject.model.server;

import com.tw.unoProject.communication.channel.MessageChannel;
import com.tw.unoProject.communication.factory.CommunicationFactory;
import com.tw.unoProject.communication.server.MessageServer;
import com.tw.unoProject.communication.server.MessageServerListener;
import com.tw.unoProject.model.Game;
import com.tw.unoProject.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMaster implements MessageServerListener, ProxyPlayerObserver {
    private final int noOfPlayers;
    private final int noOfPacks;
    private MessageServer messageServer;
    List<ProxyPlayer> proxies = new ArrayList<>();
    private CommunicationFactory factory;
    private List<Player> players = new ArrayList<>();
    private Game game;


    public GameMaster(int noOfPlayers, int noOfPacks, CommunicationFactory factory) {
        this.noOfPlayers = noOfPlayers;
        this.noOfPacks = noOfPacks;
        this.factory = factory;
    }

    public void start() {
        messageServer = factory.createMessageServer();
        messageServer.startListeningForConnections(this);
    }


    @Override
    public void onNewConnection(MessageChannel channel) {
        if (proxies.size() == noOfPlayers) {
            channel.stop();
            return;
        }

        ProxyPlayer proxyPlayer = new ProxyPlayer(channel, this);
        proxies.add(proxyPlayer);
        proxyPlayer.start();

    }

    private void startGame() {
        game = new Game(players, noOfPacks);
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onPlayerRegistered(Player player) {
        players.add(player);
        if (players.size() == noOfPlayers)
            startGame();
    }
}
