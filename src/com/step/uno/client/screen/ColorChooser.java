package com.step.uno.client.screen;

import com.step.uno.client.view.ColourChooserView;
import com.step.uno.model.Colour;

import javax.swing.*;
import java.util.Arrays;

public class ColorChooser implements ColourChooserView {
    public Colour chooseColor() {
        String[] possibleValues = {"RED", "BLUE", "YELLOW", "GREEN"};
        Colour[] colours = {Colour.Red, Colour.Blue, Colour.Yellow, Colour.Green};

        String selectedValue = (String) JOptionPane.showInputDialog(null,
                "Choose color", "COLOR",
                JOptionPane.QUESTION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        return Arrays.asList(colours).get(Arrays.asList(possibleValues).indexOf(selectedValue));
    }

    @Override
    public Colour showVisible() {
        return this.chooseColor();
    }
}