package com.company.graphics;

import com.company.animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimalClearDialog extends JDialog {
    private JComboBox<String> animalsCB;
    private ArrayList<ArrayList<Animal>> animals;
    private JPanel mainPanel,animalRemovePanel;
    private int animalSelected,teamSelected;
    private JComboBox<String> animalTeamCB;
    private CompetitionPanel panel;

    public AnimalClearDialog(CompetitionPanel panel,ArrayList<ArrayList<Animal>> animals) {
        this.panel=panel;
        this.animals = animals;
        this.setAlwaysOnTop(true);
        this.setTitle("Add Animal Fields");
        this.setSize(500, 300);
        this.setLocationByPlatform(true);
        mainPanel = new JPanel();
        JLabel animalTeamLabel = new JLabel("Choose Team:");
        animalTeamCB = new JComboBox<String>();
        for(int i=0;i<animals.size();i++){
            String teamNumber = panel.teamNames.get(i);
            animalTeamCB.addItem(teamNumber);
        }
        JPanel animalTeamNumberPanel = new JPanel();
        animalTeamCB.setSelectedIndex(-1);
        animalTeamCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                teamSelected = cb.getSelectedIndex();
            }
        });
        animalTeamNumberPanel.add(animalTeamLabel);
        animalTeamNumberPanel.add(animalTeamCB);
        mainPanel.add(animalTeamNumberPanel);
        mainPanel.add(comboBoxPanelMaker(animalsCB, "Choose Animal to remove:"));
        JButton remove = new JButton("Remove Animal");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animals.get(teamSelected).remove(animalSelected);
                if(animals.get(teamSelected).size()==0){
                    animals.remove(teamSelected);
                }
                panel.repaint();
                JOptionPane.showMessageDialog(new JDialog(), "Animal deleted.","Success",JOptionPane.INFORMATION_MESSAGE);
                onCancel();
            }
        });
        JPanel optionPanel = new JPanel();
        optionPanel.add(remove);
        this.add(mainPanel);
        this.add(optionPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void onCancel() {
        this.dispose();
    }

    private JPanel comboBoxPanelMaker(JComboBox<String> comboBox, String labelName) {
        JLabel label = new JLabel(labelName);
        comboBox = new JComboBox<String>();
        for (ArrayList<Animal> team : animals) {
            for (Animal animal : team) {
                String nameClass = animal.getName() + "," + animal.getClass().getSimpleName();
                comboBox.addItem(nameClass);
            }
        }
        JPanel panel = new JPanel();
        comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(new ListenerCB());
        panel.add(label);
        panel.add(comboBox);
        return panel;
    }

    class ListenerCB implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            animalSelected = (int) cb.getSelectedIndex();
        }
    }
}
