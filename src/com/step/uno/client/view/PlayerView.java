package com.step.uno.client.view;

import com.step.uno.messages.Snapshot;

public interface PlayerView {
    void showDisconnected();

    void update(Snapshot snapshot);
}
