package com.step.uno.client;

import com.step.communication.channel.MessageChannel;
import com.step.uno.messages.Snapshot;

public interface GameClientObserver {
    public void displaySnapShotOnView(Snapshot snapshot);

    void disconnectView(MessageChannel channel);

    void decideActionAfterDraw();
}
