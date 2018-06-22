package games.gsClever.Logic;

import java.util.*;

public class Game {

	private int round;
	private int playerCount;
	private int currentPlayer;
	private Dice[] dices;
	private Player[] players;
	
	public Game(int playerCount) {
		
		round = 0;
		this.playerCount = playerCount;
		currentPlayer = 0;
		
		dices = new Dice[6];
		dices[0] = new Dice("Yellow");
		dices[0] = new Dice("Blue");
		dices[0] = new Dice("White");
		dices[0] = new Dice("Green");
		dices[0] = new Dice("Orange");
		dices[0] = new Dice("Purple");
		
		players = new Player[playerCount];
		for(int i = 0; i < playerCount; i++) {
			
			players[i] = new Player(i, "xyz" + i);
		}
	}
	
	public int getRound() {
		return round;
	}
	
	public int getPlayerCount() {
		return playerCount;
	}
	
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
	public boolean startRound() {
		
		clearTray();
		clearDiceFields();
		
		round++;
		
		switch(round) {
		case 1:
			
			for(int i = 0; i < playerCount; i++) {
				
				players[i].getManagement().incrementDiceRepeatCount();
			}
			
			break;
			
		case 2:
			
			for(int i = 0; i < playerCount; i++) {
				
				players[i].getManagement().incrementAdditionalDiceCount();
			}
			
			break;
			
		case 3:
			
			for(int i = 0; i < playerCount; i++) {
				
				players[i].getManagement().incrementDiceRepeatCount();
			}
			
			break;
			
			//TODO
		}
		
		
		
		//TODO
		
		return false;
	}
	
	public List<Dice> rollDices(List<Dice> dices) {
		
		Random random = new Random();
		
		for(int i = 0; i < dices.size(); i++) {
			
			dices.get(i).setValue(random.nextInt(6) + 1);
		}
		
		return dices;
	}
	
	public List<Integer> investigateWinner() {
		
		int highestPoints = players[0].getManagement().determinePoints();
		
		int[] points = new int[playerCount];
		for(int i = 0; i < playerCount; i++) {
			
			points[i] = players[i].getManagement().determinePoints();
			
			if(points[i] > highestPoints)
				highestPoints = points[i];
		}
		
		List<Integer> winners = new ArrayList<Integer>();
		for(int i = 0; i < playerCount; i++) {
			
			if(points[i] == highestPoints)
				winners.add(i);
		}
		
		return winners;
	}
	
	public void clearTray() {
		
		for(int i = 0; i < 6; i++) {
			
			dices[i].setOnTray(false);
		}
	}
	
	public void clearDiceFields() {
		
		for(int i = 0; i < 6; i++) {
			
			dices[i].setField(-1);
		}
	}
	
	public Return click(Area area, int fieldId) {
		
		//TODO
		
		return null;
	}
}
