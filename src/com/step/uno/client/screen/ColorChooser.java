package com.step.uno.client.screen;

import com.step.uno.client.view.ColourChooserView;
import com.step.uno.model.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ColorChooser implements ColourChooserView, ActionListener {
    private JButton redButton;
    private JButton greenButton;
    private JButton yellowButton;
    private JButton blueButton;
    private JDialog dialog;
    Color buttonColor;
    Color[] colors = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
    private Colour[] colours = {Colour.Red, Colour.Green, Colour.Yellow, Colour.Blue};
    private ColorChooserObserver observer;

    public ColorChooser(ColorChooserObserver observer) {
        this.observer = observer;
        dialog = new JDialog();
        dialog.setLayout(new GridLayout(2, 2));

        redButton = new JButton();
        redButton.setBackground(colors[0]);
        redButton.addActionListener(this);
        dialog.add(redButton);

        greenButton = new JButton();
        greenButton.setBackground(colors[1]);
        greenButton.addActionListener(this);
        dialog.add(greenButton);

        yellowButton = new JButton();
        yellowButton.setBackground(colors[2]);
        yellowButton.addActionListener(this);
        dialog.add(yellowButton);

        blueButton = new JButton();
        blueButton.setBackground(colors[3]);
        blueButton.addActionListener(this);
        dialog.add(blueButton);

        dialog.setSize(200, 200);
        dialog.setBounds(600,350,200,200);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void showVisible(boolean b) {
        dialog.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonColor = ((JButton) e.getSource()).getBackground();
        int index = Arrays.asList(colors).indexOf(buttonColor);
        observer.onNewColour(Arrays.asList(colours).get(index));
    }
}