package com.company.graphics;

import com.company.animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AddTeamDialog extends JDialog {
    JButton addAnimalButton,finishAddButton, teamParticipantsButton;
    JPanel mainPanel,optionPanel,teamNamePanel;
    String competitionType,tournamentType;
    JTextField teamName;
    protected ArrayList<ArrayList<Animal>> animalTeams;
    private static final int MAX_ANIMALS_PER_TEAM = 4;
    private static final int MIN_ANIMALS_PER_TEAM = 1;
    private int i=0;
    public AddTeamDialog(CompetitionPanel competitionPanel, ArrayList<ArrayList<Animal>> animalTeams,String tournamentType){
        if(competitionPanel.getCompetitionType()==null) {
            new CompetitionDialog(competitionPanel);
        }
        this.competitionType=competitionPanel.getCompetitionType();
        this.tournamentType = tournamentType;
        this.animalTeams = animalTeams;
        this.setTitle("Add Team");
        this.setSize(700, 300);
        this.setLocationByPlatform(true);
        this.mainPanel=new JPanel();
        this.optionPanel = new JPanel();
        this.teamNamePanel = new JPanel();
        this.addAnimalButton = new JButton("Add Animal");
        this.teamParticipantsButton = new JButton("Team participants");
        JLabel teamNameLabel=new JLabel("Team Name");
        this.teamName= new JTextField(20);

        this.teamParticipantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (competitionPanel.animals.size() != 0) {
                    final String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Energy Consumption"};
                    Object[][] data = new Object[competitionPanel.animals.size()][columnNames.length];
                    for (int i = 0; i < competitionPanel.animals.size(); i++) {
                        data[i][0] = competitionPanel.animals.get(i).getName();
                        data[i][1] = competitionPanel.animals.get(i).getCategory();
                        data[i][2] = competitionPanel.animals.get(i).getClass().getSimpleName();
                        data[i][3] = competitionPanel.animals.get(i).getSpeed();
                        data[i][4] = competitionPanel.animals.get(i).getCurrentEnergy();
                        data[i][5] = competitionPanel.animals.get(i).getEnergyConsumption();
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
                else{
                    JOptionPane.showMessageDialog(new JDialog(),"there is no participants yet! ","Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        this.finishAddButton = new JButton("Create");
        this.finishAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(competitionPanel.animals.size()!=0) {
                    animalTeams.add(new ArrayList<Animal>(competitionPanel.animals));
                    competitionPanel.teamNames.add(teamName.getText());
                    competitionPanel.animals.clear();
                    onCancel();
                }
                else{
                    JOptionPane.showMessageDialog(new JDialog(),"please add participants","Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i<MAX_ANIMALS_PER_TEAM && tournamentType.equals("Courier Tournament")) {
                    new AddAnimalDialog(competitionPanel, competitionType);
                    i++;
                }
                else if(i<MIN_ANIMALS_PER_TEAM && tournamentType.equals("Regular Tournament")){
                    new AddAnimalDialog(competitionPanel,competitionType);
                    i++;
                }
                else{
                    JOptionPane.showMessageDialog(new JDialog(),"you have added max animals per team","Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.teamNamePanel.add(teamNameLabel);
        this.teamNamePanel.add(teamName);
        this.optionPanel.add(finishAddButton);
        this.mainPanel.add(addAnimalButton);
        this.mainPanel.add(teamParticipantsButton);
        this.mainPanel.add(teamNamePanel);
        this.add(mainPanel,BorderLayout.NORTH);
        this.add(optionPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void onCancel() {
        this.dispose();
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
