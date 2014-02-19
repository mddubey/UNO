package com.tw.unoProject.model;

public enum CardValue {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9);
    private int value;
    CardValue(int i) {
        this.value = i ;
    }

    public int getValue() {
        return this.value;
    }
}

