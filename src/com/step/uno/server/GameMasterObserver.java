package com.step.uno.server;

import com.step.uno.messages.Snapshot;
import com.step.uno.model.Player;

import java.util.List;

public interface GameMasterObserver {
    public void onGameStart(Snapshot snapshot);
}
