package com.step.uno.client.screen;

import com.step.uno.model.Card;

public interface PlayerViewObserver {
    public void onCardPlayed(Card card);
    public void onDraw();

    void onNoAction();
}
