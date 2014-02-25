package com.step.uno.model;

import java.awt.*;
import java.io.Serializable;

public enum Colour implements Serializable{
    Red(new Color(255,100,100)),
    Green(new Color(100,255,100)),
    Blue(new Color(100,100,255)),
    Yellow(new Color(225,255,100)),
    Black(Color.BLACK);
    private Color color;

    Colour(Color color) {

        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }
}
