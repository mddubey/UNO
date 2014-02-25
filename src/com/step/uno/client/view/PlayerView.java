package com.step.uno.client.view;

import com.step.uno.client.screen.PlayerViewObserver;
import com.step.uno.messages.GameResult;
import com.step.uno.messages.Snapshot;

public interface PlayerView {
    void showDisconnected();

    void update(Snapshot snapshot, PlayerViewObserver observer, boolean enable, String direction);

    void showWarningMessage(String message);

    GameOverView switchToGameOverView(GameResult result);

    void setVisible(boolean enable);

    void setEnable(boolean enable);

    public void disableContinueAfterDraw2();

    void hasDeclaredUno(String playerName);
}
