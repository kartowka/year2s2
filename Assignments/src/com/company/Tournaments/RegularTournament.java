package com.company.Tournaments;

import com.company.animals.Animal;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegularTournament extends Tournament{
    private double neededDistance;
    private AtomicBoolean finishFlag;
    private AtomicBoolean finish;
    public RegularTournament(ArrayList<ArrayList<Animal>> animalTeams, ArrayList<String> teamNames) {
        super(animalTeams, teamNames);
    }

    @Override
    protected void setup(ArrayList<ArrayList<Animal>> animalTeams, ArrayList<String> teamNames) {
        Scores score = new Scores();
        AtomicBoolean startFlag = new AtomicBoolean(true);
        finish = new AtomicBoolean(false);
        int i=0;
        for (ArrayList<Animal> animalTeam : animalTeams) {
            if(animalTeam.get(0).getCategory().equals("Terrestrial Animal")){
                neededDistance = 2365;
            }
            else if(animalTeam.get(0).getCategory().equals("Water Animal")){
                neededDistance = 590;
            }
            else if(animalTeam.get(0).getCategory().equals("Air Animal")){
                neededDistance = 725;
            }
            finishFlag=new AtomicBoolean(false);
            new Thread(new AnimalThread(animalTeam.get(0), startFlag, finishFlag,neededDistance)).start();
            new Thread(new Referee(teamNames.get(i),score,finishFlag)).start();
            i++;
        }
        new Thread(new TournamentThread(finishFlag,animalTeams.size(),score)).start();



    }
}
