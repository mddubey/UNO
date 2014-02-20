package com.tw.unoProject.Server;

import com.tw.unoProject.model.Player;

public interface ProxyPlayerObserver {
    public void onClientMessage(Object message, Player player);
}
