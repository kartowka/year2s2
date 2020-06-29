package com.company.graphics;

import com.company.animals.Animal;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompetitionDialog extends JDialog{
    private String competitionType;
    CompetitionPanel mainPanel;
    public CompetitionDialog(CompetitionPanel pan){
        this.mainPanel=pan;
        JButton air = new JButton("Air");
        JButton water = new JButton("Water");
        JButton terrestrial = new JButton("Terrestrial");
        CompetitionListener compL = new CompetitionListener(this);
        air.addActionListener(compL);
        water.addActionListener(compL);
        terrestrial.addActionListener(compL);
        JButton[] options = {air,water,terrestrial};
        JOptionPane.showOptionDialog(this,"Please choose competition","Competition Menu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
        pan.setCompetitionType(competitionType);
    }
    class CompetitionListener implements ActionListener{
        CompetitionDialog diag;

        public CompetitionListener(CompetitionDialog daig){
            this.diag=daig;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            if(name.equals("Air")){
                competitionType = "Air";
            }
            if(name.equals("Water")){
                competitionType = "Water";
            }
            if(name.equals("Terrestrial")){
                competitionType = "Terrestrial";
            }
            diag.dispose();
        }
    }
}
