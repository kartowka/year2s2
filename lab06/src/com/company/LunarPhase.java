package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LunarPhase {

    public static void main(String[] args) {
     //final String dir = System.getProperty("user.dir");
        myFrame frame;
        frame = new myFrame("Lunar Phases");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,600);
        frame.setVisible(true);

    }
}
class myFrame extends JFrame implements ActionListener{
    final static int NUM_IMAGES = 8;
    final static int START_INDEX = 3;

    final static String FOLDER_PATH = System.getProperty("user.dir");
    final static String IMAGES_PATH = FOLDER_PATH+"/src/com/company/moon_phases/";
    final static String IMAGES_SUFIX = ".jpg";
    final static String COMBO_BOX_CHANGED_COMMAND = "comboBoxChanged";
    final static String[] PHASES = { "New", "Waxing Crescent", "First Quarter",
            "Waxing Gibbous", "Full", "Waning Gibbous",
            "Third Quarter", "Waning Crescent" };
    private Image[] images;
    JPanel mainPanel, selectPanel;
    myPanel displayPanel;
    JComboBox<String> phaseChoices;
    JButton start, stop;
    public myFrame(String title){
        super(title);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1,5,5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(mainPanel);

        selectPanel = new JPanel();
        displayPanel = new myPanel();

        mainPanel.add(selectPanel);
        mainPanel.add(displayPanel);

        phaseChoices = new JComboBox<String>(PHASES);
        phaseChoices.setSelectedIndex(START_INDEX);
        selectPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Select Phase"),
            BorderFactory.createEmptyBorder(5,5,5,5)));
	    selectPanel.setLayout(new BorderLayout());
		selectPanel.add(phaseChoices,"North");

        JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1,2));
        start = new JButton("Start show");
        stop  = new JButton("Stop show");
		pan.add(start);
		pan.add(stop);
		start.addActionListener(this);
		stop.addActionListener(this);
		selectPanel.add(pan,"South");

    // Listen to events from combo box.
		phaseChoices.addActionListener(this);

    // Load images.
        loadImages();

    // Set the start image
		displayPanel.setImage(images[START_INDEX]);
}

    // Load images of the phases of the moon.
    private void loadImages() {

        images = new Image[NUM_IMAGES];

        // Get the images and put them into an array of ImageIcon.
        for (int i = 0; i < NUM_IMAGES; i++) {
            String imageName = IMAGES_PATH + i + IMAGES_SUFIX;
            try {
                images[i] = ImageIO.read(new File(imageName));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Implementation of ActionListener interface.
    @Override
    public void actionPerformed(ActionEvent event) {
        if (COMBO_BOX_CHANGED_COMMAND.equals(event.getActionCommand())) {
            // update the image to display the new phase
            displayPanel.setImage(images[phaseChoices.getSelectedIndex()]);
        }
        else if(event.getSource()==start)
            displayPanel.startShow(images);
        else if(event.getSource()==stop)
            displayPanel.stopShow();
    }

}

class myPanel extends JPanel implements Runnable{
    private Image image;
    private boolean isShow;
    Thread th;
    Image[] img;

    public myPanel() {

        // Add border around the display panel.
        setBorder(BorderFactory.createLoweredBevelBorder());

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,10,10,10),
                getBorder()));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Display Phase"),
                getBorder()));

        image = null;
        isShow = false;
        th = null;
        img = null;
    }

    public void setImage(Image img) {
        image = img;
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Dimension dm = getSize();

        if(image != null)
            g.drawImage(image, 20, 35, dm.width-40, dm.height-55, this);
    }

    public void run() {
        boolean firstTime = true;
        int index = 0;
        if(image==null)
            firstTime = false;
        while(isShow)
        {
            if(firstTime)
            {
                firstTime = false;
                for(int i=0; i<img.length; i++)
                    if(image==img[i])
                    {
                        index = (i+1)%(img.length);
                        break;
                    }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(!isShow)
                break;
            image = img[index];
            index = (index+1)%(img.length);
            repaint();
        }
    }

    public void startShow(Image[] img) {
        if(!isShow)
        {
            isShow = true;
            this.img = img;
            th = new Thread(this);
            th.start();
        }
    }

    public void stopShow() {
        isShow = false;
    }

}
