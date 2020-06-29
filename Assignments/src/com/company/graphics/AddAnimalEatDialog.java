package com.company.graphics;

import com.company.animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddAnimalEatDialog extends JDialog {
    private JTextField text;
    private JComboBox<String> animalsCB;
    private JComboBox<Integer> animalTeamCB;
    private ArrayList<ArrayList<Animal>> animals;
    private JPanel mainPanel;
    private int animalSelected,teamSelected;
    private CompetitionPanel panel;

    public AddAnimalEatDialog(CompetitionPanel panel,ArrayList<ArrayList<Animal>> animals) {
        this.panel=panel;
        this.animals = animals;
        this.setAlwaysOnTop(true);
        this.setTitle("Feed Animal");
        this.setSize(500, 300);
        this.setLocationByPlatform(true);
        mainPanel = new JPanel();
        mainPanel.add(comboBoxPanelMaker(animalsCB, "Choose Animal to feed:"));
        JLabel animalTeamLabel = new JLabel("Choose Team:");
        animalTeamCB = new JComboBox<Integer>();
        for(int i=0;i<animals.size();i++){
            int teamNumber = i+1;
            animalTeamCB.addItem(teamNumber);
        }
        JPanel animalTeamNumberPanel = new JPanel();
        animalTeamCB.setSelectedIndex(-1);
        animalTeamCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
                teamSelected = cb.getSelectedIndex();
            }
        });
        animalTeamNumberPanel.add(animalTeamLabel);
        animalTeamNumberPanel.add(animalTeamCB);
        mainPanel.add(animalTeamNumberPanel);
        mainPanel.add(textPanelMaker("how much to feed:"));
        JButton feed = new JButton("Feed");
        JButton close = new JButton("close");
        feed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(animals.get(teamSelected).get(animalSelected).getCurrentEnergy()==animals.get(teamSelected).get(animalSelected).getMaxEnergy()){
                    JOptionPane.showMessageDialog(new JDialog(), "Animal Current Energy is full.","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    animals.get(teamSelected).get(animalSelected).eat(Integer.parseInt(text.getText()));
                    JOptionPane.showMessageDialog(new JDialog(), "Animal Feed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        JPanel optionPanel = new JPanel();
        optionPanel.add(feed);
        optionPanel.add(close);
        this.add(mainPanel);
        this.add(optionPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void onCancel() {
        this.dispose();
    }
    private JPanel textPanelMaker(String labelName) {
        JLabel label=new JLabel(labelName);
        text= new JTextField(20);
        JPanel panel=new JPanel();
        panel.add(label);
        panel.add(text);
        return panel;
    }
    private JPanel comboBoxPanelMaker(JComboBox<String> comboBox, String labelName) {
        JLabel label = new JLabel(labelName);
        comboBox = new JComboBox<String>();
        int i=0;
        for (ArrayList<Animal> team : animals) {
            for (Animal animal : team) {
                String nameClass = animal.getName() + " is a " + animal.getClass().getSimpleName()+" , "+this.panel.teamNames.get(i);
                comboBox.addItem(nameClass);
                i++;
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
