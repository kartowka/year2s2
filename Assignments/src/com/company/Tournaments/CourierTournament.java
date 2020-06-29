package com.company.Tournaments;

import com.company.animals.Animal;
import com.company.mobility.Point;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {
    private Scores scores;
    public Vector<AtomicBoolean> startFlags;
    private double neededDistance;

    public CourierTournament(ArrayList<ArrayList<Animal>> animalTeams,ArrayList<String> teamNames) {
        super(animalTeams,teamNames);
    }

    @Override
    protected void setup(ArrayList<ArrayList<Animal>> animalTeams, ArrayList<String> teamNames) {
        for (int n = 0; n < animalTeams.size(); n++) {
            startFlags = new Vector<>(animalTeams.size());
            for (int i = 0; i < animalTeams.get(n).size(); i++) {
                startFlags.add(new AtomicBoolean(false));
                if(animalTeams.get(n).get(i).getCategory().equals("Terrestrial Animal")){
                    neededDistance = 2365;
                }
                else if(animalTeams.get(n).get(i).getCategory().equals("Water Animal")){
                    neededDistance = 590;
                }
                else if(animalTeams.get(n).get(i).getCategory().equals("Air Animal")){
                    neededDistance = 725;
                }
                if (i == 0) {
                    new Thread(new AnimalThread(animalTeams.get(n).get(i), new AtomicBoolean(true), startFlags.get(i), neededDistance  / animalTeams.get(n).size())).start();
                }else {
                    if(animalTeams.get(n).get(i).getCategory().equals("Terrestrial Animal")) {
                        animalTeams.get(n).get(i).setLocation(animalTeams.get(n).get(i).nextLocationTerrestrial(animalTeams.get(n).size(), i));
                    }
                    else if(animalTeams.get(n).get(i).getCategory().equals("Water Animal")) {
                        animalTeams.get(n).get(i).setLocation(animalTeams.get(n).get(i).nextLocationWater(animalTeams.get(n).size(), i));
                    }
                    else if(animalTeams.get(n).get(i).getCategory().equals("Air Animal")) {
                        animalTeams.get(n).get(i).setLocation(animalTeams.get(n).get(i).nextLocationAir(animalTeams.get(n).size(), i));
                    }
                        new Thread(new AnimalThread(animalTeams.get(n).get(i), startFlags.get(i - 1), startFlags.get(i), neededDistance / animalTeams.get(n).size())).start();
                }
            }
            this.scores = new Scores();
            new Thread(new Referee(teamNames.get(n), scores, startFlags.lastElement())).start();

        }
        new Thread(new TournamentThread(startFlags.lastElement(), animalTeams.size(), scores)).start();
    }
}
