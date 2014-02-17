package com.tw.unoProject.model;

import com.tw.unoProject.controller.UNOFactory;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {

    private UNOFactory unoFactory;

    public Randomizer(UNOFactory unoFactory) {
        this.unoFactory = unoFactory;
    }

    public List shuffleCards(List cards) {
        return unoFactory.shuffleCards(cards);
    }

}
