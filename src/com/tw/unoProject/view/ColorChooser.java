package com.tw.unoProject.view;

import javax.swing.*;

public class ColorChooser {
    public String chhoseColor() {
        String[] possibleValues = { "RED", "BLUE", "YELLOW","GREEN" };
        String selectedValue = (String) JOptionPane.showInputDialog(null,
                "Choose color", "COLOR",
                JOptionPane.QUESTION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        return selectedValue;
    }

    public static void main(String[] args) {
        System.out.println(new ColorChooser().chhoseColor());
    }
}