package com.company;
/** LAB05
 * Name : Anthony Eitan Fleysher
 * ID: 203192331
 * **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Box Volume & Area");
        frame.setSize(400,200);
        myPanel pan = new myPanel();
        frame.add(pan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
class myPanel extends JPanel implements ActionListener{
    private JButton calc;
    private JTextField w_txt,h_txt,l_txt;
    private JLabel w_lbl,h_lbl,l_lbl,v_lbl,a_lbl;
    private JPanel panel1,panel2;
    myPanel(){
        setLayout(new BorderLayout());
        panel1= new JPanel();
        panel1.setLayout(new GridLayout(3,2,5,5));
        w_lbl = new JLabel("width");
        h_lbl = new JLabel("height");
        l_lbl = new JLabel("length");
        w_txt = new JTextField();
        h_txt = new JTextField();
        l_txt = new JTextField();
        panel1.add(w_lbl);
        panel1.add(w_txt);
        panel1.add(h_lbl);
        panel1.add(h_txt);
        panel1.add(l_lbl);
        panel1.add(l_txt);
        add(panel1,"Center");
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));
        calc = new JButton("calculate");
        v_lbl = new JLabel("volume:");
        a_lbl = new JLabel("area:");
        panel2.add(calc);
        panel2.add(v_lbl);
        panel2.add(a_lbl);
        add(panel2,"South");
        calc.addActionListener(this);


    }
    public void actionPerformed(ActionEvent ev) {
            if(ev.getSource()==calc)
            {
                int w=0,h=0,l=0;
                try {
                    w = Integer.parseInt(w_txt.getText());
                    if(w<=0)
                        throw new Exception("Width have to be positive");
                }
                catch(NumberFormatException e) {
                    System.out.println("Width have to be a number");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                try {
                    h = Integer.parseInt(h_txt.getText());
                    if(h<=0)
                        throw new Exception("Height have to be positive");
                }
                catch(NumberFormatException e) {
                    System.out.println("Height have to be a number");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                try {
                    l = Integer.parseInt(l_txt.getText());
                    if(l<=0)
                        throw new Exception("Length have to be positive");
                }
                catch(NumberFormatException e) {
                    System.out.println("Length have to be a number");
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                v_lbl.setText("    volume: "+w*l*h);
                a_lbl.setText("area: "+2*(w*l+w*h+l*h));
            }
        }
}