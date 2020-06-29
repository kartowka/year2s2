package com.company.Tournaments;

import javax.swing.*;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class TournamentThread implements Runnable {
    private Scores scores;
    private final AtomicBoolean startSignal;
    private int group;

    public TournamentThread(AtomicBoolean startSignal, int numberOfTeams, Scores scores) {
        this.scores = scores;
        this.startSignal = startSignal;
        this.group = numberOfTeams;
    }

    @Override
    public void run() {
        synchronized (this.startSignal) {
            while (!startSignal.get()) {
                try {
                    startSignal.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                startSignal.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showScoresTable();
            //JOptionPane.showMessageDialog(new JDialog(), "The WINNER is ", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JDialog MakeInfoDialog(JScrollPane scrollPane) {

        JDialog infoDialog = new JDialog();
        infoDialog.add(scrollPane);
        infoDialog.setAlwaysOnTop(true);
        infoDialog.setTitle("Scores Information");
        infoDialog.setSize(350, 300);
        infoDialog.setLocationByPlatform(true);
        return infoDialog;
    }

    private void showScoresTable() {
        String[] columnNames = {"Team Name", "Score"};
        Object[][] data = new Object[group][columnNames.length];
        int k = 0;
        for (Map.Entry<String, Date> entry : this.scores.getAll().entrySet()) {
            data[k][0] = entry.getKey();
            data[k][1] = entry.getValue();
            k++;
        }

        JTable info = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        info.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(info);
        JDialog infoDialog = MakeInfoDialog(scrollPane);
        infoDialog.setVisible(true);
    }


}
