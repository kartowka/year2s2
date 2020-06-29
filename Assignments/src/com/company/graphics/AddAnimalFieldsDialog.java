package com.company.graphics;

import com.company.animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalFieldsDialog extends JDialog {
    JTextField[] text;
    JComboBox<String> genderCB;
    private JPanel mainPanel;
    private Animal animal;
    private final String[] gender = {"male","female","hermaphrodite"};
    private String genderCB_Selected;
    private String animalName;

    public AddAnimalFieldsDialog(Animal animal) {
        this.animal=animal;
        //Dialog settings
        this.setAlwaysOnTop(true);
        this.setTitle("Add Animal Fields");
        this.setSize(500, 300);
        this.setLocationByPlatform(true);
        mainPanel = new JPanel();
        text=new JTextField[5];
        mainPanel.add(textPanelMaker(0,"Animal Name:"));
        mainPanel.add(comboBoxPanelMaker(genderCB,"Choose Gender:",gender));
        mainPanel.add(textPanelMaker(1,"Animal weight:"));
        mainPanel.add(textPanelMaker(2,"Animal speed:"));
        mainPanel.add(textPanelMaker(3,"Animal maxEnergy:"));
        mainPanel.add(textPanelMaker(4,"Animal energyPerMeter:"));
        JButton create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(genderCB_Selected!=null) {
                    animal.setName(text[0].getText());
                    animal.setWeight(Double.parseDouble(text[1].getText()));
                    animal.setSpeed(Double.parseDouble(text[2].getText()));
                    animal.setMaxEnergy(Integer.parseInt(text[3].getText()));
                    animal.setEnergyPerMeter(Integer.parseInt(text[4].getText()));
                    onCancel();
                }
                else{
                    JOptionPane.showMessageDialog(new JDialog(), "Please choose gender before continue", "Error", JOptionPane.ERROR_MESSAGE);                }
            }
        });
        JPanel optionPanel = new JPanel();
        optionPanel.add(create);
        this.add(mainPanel);
        this.add(optionPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void onCancel() {
        this.dispose();
    }
    private JPanel textPanelMaker(int i,String labelName) {
        JLabel label=new JLabel(labelName);
        text[i]= new JTextField(20);
        JPanel panel=new JPanel();
        panel.add(label);
        panel.add(text[i]);
        return panel;
    }
    private JPanel comboBoxPanelMaker(JComboBox<String> comboBox, String labelName, String[] options) {
        JLabel label = new JLabel(labelName);
        comboBox = new JComboBox<String>();
        for (String option : options) {
            comboBox.addItem(option);
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
            genderCB_Selected = (String) cb.getSelectedItem();
            if(genderCB_Selected.equals("male")){
                animal.setGender(genderCB_Selected);
            }
            else if(genderCB_Selected.equals("female")){
                animal.setGender(genderCB_Selected);
            }
            else if(genderCB_Selected.equals("hermaphrodite")){
                animal.setGender(genderCB_Selected);
            }

        }
    }
}
