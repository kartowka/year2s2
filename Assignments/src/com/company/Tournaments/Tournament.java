package com.company.Tournaments;

import com.company.animals.Animal;
import java.util.ArrayList;

public abstract class Tournament {
    protected TournamentThread tournamentThread;
    public Tournament(ArrayList<ArrayList<Animal>> animalTeams,ArrayList<String>teamNames){
        setup(animalTeams,teamNames);
    }
    protected abstract void setup(ArrayList<ArrayList<Animal>> animalTeams,ArrayList<String>teamNames);
    public TournamentThread getTournamentThread(){
        return this.tournamentThread;
    }
    public void setTournamentThread(TournamentThread tournamentThread) {
        this.tournamentThread = tournamentThread;
    }

}
