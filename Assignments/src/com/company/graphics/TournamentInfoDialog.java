package com.company.graphics;

import com.company.Tournaments.Scores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TournamentInfoDialog extends JDialog{
    public TournamentInfoDialog(Scores score) {
        JOptionPane.showMessageDialog(new JDialog(), "The WINNER is "+ score.getAll(), "Competition Winner", JOptionPane.INFORMATION_MESSAGE);
    }
}
