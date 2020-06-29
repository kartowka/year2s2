package com.company;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tournament {
	final int NUMBER_OF_PLAYERS = 8;
	private String name, file_name;
	QueueAsList playersQueue;
	ObjectOutputStream playerOut;
	ObjectInputStream playerIn;
	
	public Tournament(String name, String file_name) {
		this.name = new String(name);
		this.file_name = new String(file_name);
		playersQueue = new QueueAsList();
		playerOut = null;
		playerIn = null;
		
		// Fill players queue
		try{			
			FileInputStream theFile = new FileInputStream(file_name);
			//BufferedInputStream theBufferStream = new BufferedInputStream(theFile);
			playerIn = new ObjectInputStream(theFile);			
			System.out.println("Load from file");
			for(int i=0; i<NUMBER_OF_PLAYERS; i++)
				playersQueue.enqueue((Player)(playerIn.readObject()));
			playerIn.close();			
		}
		catch(IOException e){
			if(playerIn==null)
			{
				playersQueue.enqueue(new Player("Novak Djokovic",10000));
				playersQueue.enqueue(new Player("David Ferrer",3000));
				playersQueue.enqueue(new Player("Stanislas Wawrinka",7000));
				playersQueue.enqueue(new Player("Rafael Nadal",6000));
				playersQueue.enqueue(new Player("Andy Murray",9000));
				playersQueue.enqueue(new Player("Tomas Berdych",4000));
				playersQueue.enqueue(new Player("Kei Nishikori",5000));
				playersQueue.enqueue(new Player("Roger Federer",8000));
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			FileOutputStream theFile = new FileOutputStream(file_name);
			//BufferedOutputStream theBufferStream = new BufferedOutputStream(theFile);
			playerOut = new ObjectOutputStream(theFile);
		}
		catch(IOException e){			
		}
	}

	private Player simulateGame(Player first, Player second) {
		Player winner;
		int res = (int)(Math.random()*(first.getTotalScore() + second.getTotalScore()));
		if(res > second.getTotalScore())
			winner = first;
		else
			winner = second;
		winner.updateGameWin();
		System.out.println("Game "+first.getName()+" - "+second.getName()+": winner - "+winner.getName());
		return winner;
	}
	
	public void simulateTournament() {
		System.out.println("*******************************************************");
		System.out.println("Tennis tournament \""+name+"\" is starting now");
		System.out.println("*******************************************************");
		while(!playersQueue.isEmpty()) {
			Player first = (Player)(playersQueue.dequeue());
			if(playersQueue.isEmpty())
			{
				first.updateTotalScore();
				try {
					playerOut.writeObject(first);
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						playerOut.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//break;
			}
			else
			{
				Player second = (Player)(playersQueue.dequeue());
				Player winner = simulateGame(first, second);
				Player looser = null;
				playersQueue.enqueue(winner);
				if(winner == second)
					looser = first;			
				else
					looser = second;
				looser.updateTotalScore();
				try {
					playerOut.writeObject(looser);
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
	
	public void printPlayers() {
		Player [] players = new Player[NUMBER_OF_PLAYERS];
		System.out.println("Players ranking:");
		try{			
			FileInputStream theFile = new FileInputStream(file_name);
			playerIn = new ObjectInputStream(theFile);			
			for(int i=0; i<NUMBER_OF_PLAYERS; i++)
				players[i]=(Player)(playerIn.readObject());
			playerIn.close();			

			for(int i=0; i<NUMBER_OF_PLAYERS-1; i++)
				for(int j=i+1; j<NUMBER_OF_PLAYERS; j++)
					if(players[j].getTotalScore()>players[i].getTotalScore())
					{
						Player tmp = new Player(players[j].getName(),players[j].getTotalScore());
						players[j] = players[i];
						players[i] = tmp;
					}
		
			for(int i=0; i<NUMBER_OF_PLAYERS; i++)
				System.out.println(i+1+". "+players[i]);
		}
		catch(IOException e) { 
			e.printStackTrace();
		}		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Put players to file according to ranking
		try{
			FileOutputStream theFile = new FileOutputStream(file_name);
			//BufferedOutputStream theBufferStream = new BufferedOutputStream(theFile);
			playerOut = new ObjectOutputStream(theFile);
			playerOut.writeObject(players[0]);
			playerOut.writeObject(players[7]);
			playerOut.writeObject(players[3]);
			playerOut.writeObject(players[4]);
			playerOut.writeObject(players[2]);
			playerOut.writeObject(players[5]);
			playerOut.writeObject(players[6]);
			playerOut.writeObject(players[1]);
			
		}
		catch(IOException e){			
		}
		finally {
			try {
				playerOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}