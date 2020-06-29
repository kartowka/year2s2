package com.company.graphics;
import com.company.Tournaments.*;
import com.company.animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AddCompetitionDialog extends JDialog implements ActionListener{
    JPanel mainPanel,addTeamPanel,optionPanel;
    JRadioButton courierTournament,regularTournament;
    JButton addTeamButton,startButton,viewTeamsButton;
    CompetitionPanel competitionPanel;
    CourierTournament courierTournaments;
    JTable info;
    DefaultTableModel model;
    private final String[] columnNames = {"Team Number","Animal", "Category", "Type", "Speed", "Energy Amount", "Energy Consumption"};
    private String tournamentType;
        public AddCompetitionDialog(CompetitionPanel competitionPanel, ArrayList<ArrayList<Animal>> animalTeams){
        this.competitionPanel=competitionPanel;
        this.setTitle("Add Competition");
        this.setSize(500, 300);
        this.setLocationByPlatform(true);
        this.mainPanel=new JPanel();
        this.optionPanel = new JPanel();
        this.addTeamPanel = new JPanel();
        this.model = new DefaultTableModel();
        for(String item:columnNames){
            model.addColumn(item);
        }
        this.info = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.addTeamButton = new JButton("Add team");
        this.startButton = new JButton("Start Competition");
        this.viewTeamsButton = new JButton("Teams");
        courierTournament = new JRadioButton("Courier Tournament");
        regularTournament = new JRadioButton("Regular Tournament");
        ButtonGroup group = new ButtonGroup();
        group.add(courierTournament);
        group.add(regularTournament);
        addTeamPanel.add(addTeamButton);
        courierTournament.addActionListener(this);
        regularTournament.addActionListener(this);
        this.viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animalTeams.size() != 0) {
                    for (int i = 0; i < animalTeams.size(); ++i) {
                        Object[][] data = new Object[animalTeams.get(i).size()][columnNames.length];
                        for (int j = 0; j < animalTeams.get(i).size(); j++) {
                            data[j][0] = i+1;
                            data[j][1] = animalTeams.get(i).get(j).getName();
                            data[j][2] = animalTeams.get(i).get(j).getCategory();
                            data[j][3] = animalTeams.get(i).get(j).getClass().getSimpleName();
                            data[j][4] = animalTeams.get(i).get(j).getSpeed();
                            data[j][5] = animalTeams.get(i).get(j).getCurrentEnergy();
                            data[j][6] = animalTeams.get(i).get(j).getEnergyConsumption();
                        }
                        info = new JTable(data,columnNames);
                        info.setFillsViewportHeight(true);
                        JScrollPane scrollPane = new JScrollPane(info);
                        JDialog infoDialog = MakeInfoDialog(scrollPane);
                        infoDialog.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JDialog(), "there is no participants yet! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(animalTeams.isEmpty()){
                    JOptionPane.showMessageDialog(new JDialog(),"There is no teams yet,minimum 2 teams","Information",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    if(tournamentType.equals("Courier Tournament")){
                        new CourierTournament(animalTeams,competitionPanel.teamNames);
                    }
                    else{
                        new RegularTournament(animalTeams,competitionPanel.teamNames);
                    }
                    onCancel();
                }
            }
        });
        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeamDialog(competitionPanel,animalTeams,tournamentType);
                //new CompetitionDialog(competitionPanel,animalTeams);
            }
        });
        mainPanel.add(courierTournament);
        mainPanel.add(regularTournament);
        mainPanel.add(addTeamPanel);
        mainPanel.add(viewTeamsButton);
        addTeamButton.setEnabled(false);
        optionPanel.add(startButton);
        this.add(mainPanel);
        this.add(optionPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(courierTournament.isSelected()){
            courierTournament.setEnabled(false);
            regularTournament.setEnabled(false);
            tournamentType="Courier Tournament";
        }
        if(regularTournament.isSelected()){
            regularTournament.setEnabled(false);
            courierTournament.setEnabled(false);
            tournamentType="Regular Tournament";
        }
        addTeamButton.setEnabled(true);
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
