package com.tw.unoProject.model.server;

import com.tw.unoProject.communication.channel.MessageChannel;
import com.tw.unoProject.communication.channel.MessageChannelListener;
import com.tw.unoProject.model.Game;
import com.tw.unoProject.model.Player;
import com.tw.unoProject.model.Snapshot;
import com.tw.unoProject.model.messages.Introduction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyPlayer implements MessageChannelListener {
    private MessageChannel channel;
    private ProxyPlayerObserver observer;
    private Player player;

    public ProxyPlayer(MessageChannel channel, ProxyPlayerObserver proxyPlayerObserver) {
        this.channel = channel;
        this.observer = proxyPlayerObserver;
    }

    public void start() {
        channel.startListeningForMessages(this);
    }

    @Override
    public void onError(MessageChannel client, Exception e) {
        throw new RuntimeException(e);
    }

    private void onClientMessage(Introduction introduction){
        this.player = new Player(introduction.playerName);
        observer.onPlayerRegistered(this.player);
    }


    @Override
    public void onMessage(MessageChannel client, Object message) {
        try {
            Method method = this.getClass().getMethod("onClientMessage",message.getClass());
            method.invoke(this,message);
        } catch (NoSuchMethodException e) {

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void sendSnapshot(Game game) {
        Snapshot snapshot = game.giveSnapshot(player);
        channel.send(snapshot);
    }

    @Override
    public void onConnectionClosed(MessageChannel client) {

    }
}
