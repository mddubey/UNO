package com.tw.unoProject.model.server;

import com.tw.unoProject.communication.channel.MessageChannel;
import com.tw.unoProject.communication.channel.MessageChannelListener;

public class ProxyPlayer implements MessageChannelListener {
    private MessageChannel channel;
    private ProxyPlayerObserver proxyPlayerObserver;

    public ProxyPlayer(MessageChannel channel, ProxyPlayerObserver proxyPlayerObserver) {
        this.channel = channel;
        this.proxyPlayerObserver = proxyPlayerObserver;
    }

    public void start() {
        channel.startListeningForMessages(this);
    }

    @Override
    public void onError(MessageChannel client, Exception e) {

    }

    @Override
    public void onMessage(MessageChannel client, Object message) {

    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }
}
