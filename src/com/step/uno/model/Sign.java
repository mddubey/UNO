package com.step.uno.model;

import java.io.Serializable;

public enum Sign implements Serializable{
    _0(0,"0"),
    _1(1,"1"),
    _2(2,"2"),
    _3(3,"3"),
    _4(4,"4"),
    _5(5,"5"),
    _6(6,"6"),
    _7(7,"7"),
    _8(8,"8"),
    _9(9,"9"),
    _Reverse(20,"Reverse"),
    _Skip(20,"Skip"),
    _DrawTwo(20,"+2"),
    _DrawFour(50,"+4"),
    _Wild(50,"Wild");

    private int point;
    private String value;

    Sign(int point, String value) {

        this.point = point;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getPoint() {
        return point;
    }

}
