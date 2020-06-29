package com.company;

import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
    private int totalScore;
	transient private int tournamentScore;
	
	public Player(String name, int total) {
		this.name = new String(name);
		totalScore = total;
		tournamentScore = 0;
	}

	public int getTotalScore() {
		 return totalScore;
	}

	public void updateTotalScore() {
		 totalScore += tournamentScore;
	}

	public void updateGameWin() {
		 tournamentScore += 10;
	}
	
	public String toString() {
		return name+" "+totalScore;
	}
	
	public String getName() {
		return name;
	}
}