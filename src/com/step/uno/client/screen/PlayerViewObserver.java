package com.step.uno.client.screen;

import com.step.uno.messages.Snapshot;
import com.step.uno.model.Card;

public interface PlayerViewObserver {
    public void onCardPlayed(Card card, Snapshot snapshot);
    public void onDraw(int count);

    void onNoAction();

    void onDeclaredUno(int length);
}
