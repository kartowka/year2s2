package com.company;

public class Main {

    public static void main(String[] args) {
        Player p1 = new ConsecutivePlayer("kartowka");
        Player p2 = new RandomPlayer("Bliss");

        Game game1 = new PrisonerDilemmas(p1,p2);
        game1.play(10);
        Player winner = game1.getWinner();
        System.out.println("The Winner is: "+winner.getName());
        p1.updateScore(-(p1.getScore()));
        p2.updateScore(-(p2.getScore()));
        Game game2 = new RockPaperScissors(p1,p2);
        game2.play(10);
        winner = game2.getWinner();
        System.out.println("The Winner is: "+winner.getName());
    }
}
