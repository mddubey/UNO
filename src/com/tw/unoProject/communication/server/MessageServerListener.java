package com.tw.unoProject.communication.server;

import com.tw.unoProject.communication.channel.MessageChannel;

public interface MessageServerListener {
    void onNewConnection(MessageChannel channel);
    void onError(Exception e);
}
