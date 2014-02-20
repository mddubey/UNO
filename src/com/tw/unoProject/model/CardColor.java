package com.tw.unoProject.model;

import java.awt.*;


public enum CardColor {
    RED(Color.RED), GREEN(Color.GREEN), YELLOW(Color.YELLOW), BLUE(Color.BLUE),BLACK(Color.BLACK);
    private final Color cardColor;

    CardColor(Color cardColor) {
        this.cardColor = cardColor;
    }

    public Color getCardColor() {
        return cardColor;
    }
}
