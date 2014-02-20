package com.tw.unoProject.model;

public enum CardPoints {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9),Reverse(20),Skip(20),DrawTwo(20),WildDrawFour(50),Wild(50);;
    private int value;
    CardPoints(int i) {
        this.value = i ;
    }

    public int getValue() {
        return this.value;
    }
}

