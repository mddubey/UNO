package com.step.uno.server;

import com.step.uno.messages.Snapshot;

public interface GameMasterObserver {
    public void onGameStart(Snapshot snapshot);
}
