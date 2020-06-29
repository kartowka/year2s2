package com.company.graphics;

import com.company.animals.*;
import com.company.mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AddAnimalDialog extends JDialog {
    JPanel mainPanel, addType, addAnimal,compPanel,routeYPanel;
    private String compType,animalType;
    private JTextField text;
    private final static String[] competitionType = {"Air", "Water", "Terrestrial"};
    private final static String[] airAnimal = {"Pigeon", "Eagle"};
    private final static String[] waterAnimal = {"Alligator", "Dolphin", "Whale"};
    private final static String[] terrestrialAnimal = {"Alligator", "Cat", "Dog", "Snake"};
    private final static String[] airYRoutes = {"1","2","3","4","5"};
    private final static int[] WATER_Y_AXIS = {55,165,275,385};
    private final static int[] AIR_Y_AXIS = {0,110,220,330,445};
    private final static String[] waterYRoutes = {"1","2","3","4"};
    private Animal animal;
    private ArrayList<Animal> team;
    public AddAnimalDialog(CompetitionPanel compPanel, String compType) {
        this.compPanel=compPanel;
        this.compType = compType;
        //Dialog settings
        this.setAlwaysOnTop(true);
        this.setTitle("Add Animal");
        this.setSize(500, 300);
        this.setLocationByPlatform(true);
        mainPanel = new JPanel();
        //ComboBoxes
        //mainPanel.add(addType = comboBoxPanelMaker("Choose Competition:", competitionType), BorderLayout.WEST);
        if (compType != null) {
            switch (compType) {
                case "Air":
                    mainPanel.add(addAnimal = comboBoxPanelMaker("Choose Animal:", airAnimal));
                    mainPanel.add(routeYPanel = comboBoxPanelMaker("Choose route:", airYRoutes));
                    routeYPanel.setVisible(true);
                    break;
                case "Water":
                    mainPanel.add(addAnimal = comboBoxPanelMaker("Choose Animal:", waterAnimal));
                    mainPanel.add(routeYPanel = comboBoxPanelMaker("Choose route:", waterYRoutes));
                    routeYPanel.setVisible(true);
                    break;
                case "Terrestrial":
                    mainPanel.add(addAnimal = comboBoxPanelMaker("Choose Animal:", terrestrialAnimal));
                    break;
            }
            addAnimal.setVisible(true);
            addAnimal.setEnabled(true);
        }
        //dialog finishing option
        JButton create = new JButton("Create");
        JButton cancel = new JButton("Cancel");
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAnimal(compPanel);
                //compPanel.setCompetitionType(null);
                onCancel();
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        JPanel optionPanel = new JPanel();
        optionPanel.add(create);
        optionPanel.add(cancel);
        this.add(mainPanel);
        this.add(optionPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void onCancel() {
        this.dispose();
    }
    private boolean createAnimal(CompetitionPanel compPanel) {
        if (animal == null) {
            return false;
        }
        new AddAnimalFieldsDialog(animal);
        animal.setPanel(compPanel);
        try {
            animal.loadImages(animalType);
        } catch (IOException except) {
            JOptionPane.showMessageDialog(new JDialog(), "Could not load animal image", "Loading Image Error", JOptionPane.ERROR_MESSAGE);
        }
        compPanel.animals.add(animal);
        compPanel.animalsArchive.add(animal);
        compPanel.repaint();
        return true;
    }

    private JPanel comboBoxPanelMaker(String labelName, String[] options) {
        JLabel label = new JLabel(labelName);
        JComboBox<String> comboBox = new JComboBox<String>();
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
    public int randomNum(){
        final int[] water = {55,165,275,385};
        final int[] air = {0,110,220,330,445};
        int[] list;
        if(compType.equals("Water")){
            list=new int[water.length];
            list=water;
        }
        else if(compType.equals("Air")){
            list=new int[air.length];
            list=air;
        }
        else{
            return 0;
        }
        Random r = new Random();
        int randomIndex  = r.nextInt(list.length);
        return list[randomIndex];
    }
    class ListenerCB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> cb = (JComboBox<String>) e.getSource();
            String name = (String) cb.getSelectedItem();
//            if (name.equals(compType)) {
//                if (compType.equals("Terrestrial")) {
//                    addAnimal.setVisible(true);
//                }
//                else {
//                    addAnimal.setVisible(true);
//                    routeYPanel.setVisible(true);
//                }
//            }
            if(name.equals("1")||name.equals("2")||name.equals("3")||name.equals("4")||name.equals("5")) {
                if(animalType==null){
                    JOptionPane.showMessageDialog(new JDialog(),"Please Choose animal","Error",JOptionPane.ERROR_MESSAGE);
                    cb.setSelectedIndex(-1);
                }
                else if(compType.equals("Water")){
                    if(animalType.equals("Alligator")){
                        animal = new Alligator("Water", 0);
                    }
                    else if(animalType.equals("Dolphin")){
                        animal = new Dolphin("sea",0);
                    }
                    else if(animalType.equals("Whale")){
                        animal = new Whale("fish",0);
                    }
                    animal.setLocation(new Point(70,WATER_Y_AXIS[Integer.parseInt(name)-1]));
                }
                else if(compType.equals("Air")) {
                    if (animalType.equals("Pigeon")) {
                        animal = new Pigeon("kuku", 1);
                    } else if (animalType.equals("Eagle")) {
                        animal = new Eagle(20, 3);
                    }
                    animal.setLocation(new Point(0, AIR_Y_AXIS[Integer.parseInt(name) - 1]));
                }
            }
            else if(name.equals("Alligator")) {
                animalType = name;
                if (compType.equals("Terrestrial")) {
                    animal = new Alligator("Water", 0);
                    animal.setCategory("Terrestrial Animal");
                    animal.setLocation(new Point(0, 0));
                }
            }
            else if(name.equals("Dolphin")) {
                animalType=name;
                routeYPanel.setEnabled(true);
            }
            else if(name.equals("Whale")) {
                animalType=name;
            }
            else if(name.equals("Cat")) {
                animalType=name;
                animal = new Cat(true,4);
                animal.setLocation(new Point(0,0));
            }
            else if(name.equals("Dog")) {
                animalType=name;
                animal = new Dog("beagle",4);
                animal.setLocation(new Point(0,0));
            }
            else if(name.equals("Snake")) {
                animalType=name;
                animal = new Snake("high",3,0);
                animal.setLocation(new Point(0,0));
            }
            else if(name.equals("Pigeon")) {
                animalType=name;
            }
            else if(name.equals("Eagle")) {
                animalType=name;
            }

            else{
                JOptionPane.showMessageDialog(new JDialog(), "Please choose the right animal category", "Error", JOptionPane.ERROR_MESSAGE);
                cb.setSelectedIndex(-1);
            }
        }
    }
}