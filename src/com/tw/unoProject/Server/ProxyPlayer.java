package com.tw.unoProject.Server;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.tw.unoProject.model.Game;
import com.tw.unoProject.model.Player;
import com.tw.unoProject.model.Snapshot;

public class ProxyPlayer implements MessageChannelListener {
    private MessageChannel channel;
    private ProxyPlayerObserver observer;
    private Player player;

    public ProxyPlayer(MessageChannel channel,ProxyPlayerObserver observer) {
        this.channel = channel;
        this.observer = observer;
    }

    @Override
    public void onError(MessageChannel client, Exception e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onMessage(MessageChannel client, Object message) {
        observer.onClientMessage(message, player);
    }

    public void sendSnapshot(Game game) {
        Snapshot snapshot = game.giveSnapshot(player);
        channel.send(snapshot);
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }
}
