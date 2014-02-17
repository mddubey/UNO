package com.tw.unoProject.view;

import javax.swing.*;
import java.awt.*;

public class LogDisplay {
    private JScrollPane log;
    private JTable logTable;
    public void createLog(int x,int y,int width,int height) {
        createTable();
        log = new JScrollPane(logTable);
        log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        log.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        log.setBounds(x, y, width, height);
    }

    public void createTable() {
        String ColumnName[] = {"NAME", "CARD"};
        String data[][] = {{"Samiksha", "RED : 2"}, {"Manali", "RED : 2"},
                {"Guru", "RED : 2"}, {"MD", "RED : 2"}, {"Kashish", "RED : 2"}};
        logTable = new JTable(data, ColumnName);
        logTable.setBounds(40, 120, 520, 200);
        logTable.setLayout(new GridLayout(5, 3));
        logTable.setBackground(Color.lightGray);
        logTable.setBorder(BorderFactory.createLineBorder(Color.black));
        logTable.setFont(new Font("serif", Font.BOLD, 20));
        logTable.setRowHeight(40);
    }

    public JScrollPane getLog() {
        return log;
    }

    public JTable getLogTable() {
        return logTable;
    }
}
