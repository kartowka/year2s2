package com.company.graphics;

import com.company.Tournaments.RegularTournament;
import com.company.animals.Alligator;
import com.company.animals.Animal;
import com.company.mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionPanel extends JPanel {
    private String competitionType;
    ArrayList<Animal> animals;
    ArrayList<Animal> animalsArchive;
    ArrayList<ArrayList<Animal>> animalteams;
    ArrayList<Integer> randomNumber;
    private int ID = 0;
    private BufferedImage img = null;
    protected ArrayList<String> teamNames;

    public CompetitionPanel() {
        super(new BorderLayout());
        try {
            img = ImageIO.read(new File(IDrawable.PICTURE_PATH + "competitionBackground.png"));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JDialog(), "Could not open background file");
            System.exit(1);//Without background the competition wont work.
        }
        CompListener compL = new CompListener(this);
        animals = new ArrayList<Animal>();
        animalsArchive = new ArrayList<Animal>();
        animalteams = new ArrayList<ArrayList<Animal>>();
        randomNumber = new ArrayList<Integer>();
        teamNames = new ArrayList<String>();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        //adding buttons
        JButton competition = new JButton("Competition");
        JButton clear = new JButton("Clear");
        JButton eat = new JButton("Eat");
        JButton info = new JButton("Info");
        JButton exit = new JButton("Exit");
        //Adding listeners
        competition.addActionListener(compL);
        clear.addActionListener(compL);
        eat.addActionListener(compL);
        info.addActionListener(compL);
        exit.addActionListener(compL);
        //Adding the buttons.
        buttonPanel.add(competition);
        buttonPanel.add(clear);
        buttonPanel.add(eat);
        buttonPanel.add(info);
        buttonPanel.add(exit);
        //Other settings.
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight() - 30, this);
        doDraw(g);
    }

    private void doDraw(Graphics g) {
        if(animalteams.size()!=0) {
            for (ArrayList<Animal> team : animalteams) {
                for (Animal item : team) {
                    item.drawObject(g);
                }
            }
        }
    }

    public ArrayList<ArrayList<Animal>> getAnimalteams() {
        return animalteams;
    }
    class CompListener implements ActionListener {

        private CompetitionPanel comp;

        public CompListener(CompetitionPanel comp) {
            this.comp = comp;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            try {
                if (name.equals("Competition")) {
                    new AddCompetitionDialog(comp, animalteams);
                }
                if (name.equals("Clear")) {
                    if (animalteams.size() == 0) {
                        JOptionPane.showMessageDialog(new JDialog(), "There is no animals yet", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        new AnimalClearDialog(comp, animalteams);
                    }
                }
                if (name.equals("Eat")) {
                    if (animalteams.size() == 0) {
                        JOptionPane.showMessageDialog(new JDialog(), "There is no animals yet", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                       new AddAnimalEatDialog(comp, animalteams);

                    }
                }
            } catch (Exception except) {
                JOptionPane.showMessageDialog(new JDialog(), except.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } // end of catch.
//
            if (name.equals("Info")) {
                final String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
                Object[][] data = new Object[animalsArchive.size()][columnNames.length];
                for (int i = 0; i < animalsArchive.size(); i++) {
                    data[i][0] = animalsArchive.get(i).getName();
                    data[i][1] = animalsArchive.get(i).getCategory();
                    data[i][2] = animalsArchive.get(i).getClass().getSimpleName();
                    data[i][3] = animalsArchive.get(i).getSpeed();
                    data[i][4] = animalsArchive.get(i).getCurrentEnergy();
                    data[i][5] = animalsArchive.get(i).getTotalDistance();
                    data[i][6] = animalsArchive.get(i).getEnergyConsumption();
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

            if (name.equals("Exit")) {
                System.exit(0);//Simply exits the program.
            }
        }
    }

    public String getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(String competitionType) {
        this.competitionType = competitionType;
    }

    private JDialog MakeInfoDialog(JScrollPane scrollPane) {

        JDialog infoDialog = new JDialog();
        infoDialog.add(scrollPane);
        infoDialog.setAlwaysOnTop(true);
        infoDialog.setTitle("Animal Information");
        infoDialog.setSize(900, 600);
        infoDialog.setLocationByPlatform(true);
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoDialog.dispose();
            }
        });
        infoDialog.add(exit, BorderLayout.SOUTH);
        return infoDialog;
    }
}

