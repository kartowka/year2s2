package com.company.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitionFrame extends JFrame implements ActionListener{
    public static void main(String[] args) {
        CompetitionFrame frame = new CompetitionFrame();
        frame.setTitle("Competition");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private JMenu jMenuFile, jMenuHelp;
    private JMenuItem jMenuItemExit, jMenuItemAbout;
    CompetitionPanel pan;


    public CompetitionFrame() {
        super("Competition");
        setPreferredSize(new Dimension(800,600));
        jMenuFile = new JMenu("File");
        jMenuHelp = new JMenu("Help");
        jMenuItemExit = new JMenuItem("Exit");
        jMenuItemAbout = new JMenuItem("About");
        jMenuFile.add(jMenuItemExit);
        jMenuHelp.add(jMenuItemAbout);
        JMenuBar mb = new JMenuBar();
        mb.add(jMenuFile);
        mb.add(jMenuHelp);
        setJMenuBar(mb);
        setBackground(Color.BLACK);
        jMenuItemExit.addActionListener(this);
        jMenuItemAbout.addActionListener(this);
        pan = new CompetitionPanel();
        this.add(pan);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jMenuItemAbout) {
            JOptionPane.showMessageDialog(null, "Home Work 2\nGUI");
        } else if (e.getSource() == jMenuItemExit) {
            System.exit(0);
        }
    }
}
