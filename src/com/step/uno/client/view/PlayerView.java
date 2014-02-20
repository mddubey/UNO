package com.step.uno.client.view;

import com.step.uno.messages.GameSnapshot;

public interface PlayerView {
    void showDisconnected();

    void update(GameSnapshot snapshot);
}
