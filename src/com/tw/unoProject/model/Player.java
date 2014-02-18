package com.tw.unoProject.model;

import com.tw.unoProject.controller.MessageChannel;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private MessageChannel channel;
    private String name;
    private List<Card> myCards;

    public Player(MessageChannel channel, String name) {
        this.channel = channel;
        this.name = name;
    }

    public Player(String name) {
        this.name = name;
        myCards = new ArrayList<>();
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void setMyCards(Card card) {
        this.myCards.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
