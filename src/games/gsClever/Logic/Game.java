package games.gsClever.Logic;

import java.util.*;

public class Game {

	private int round;
	private int playerCount;
	private Dice[] dices;
	private Player[] player;
	
	public Game(int playerCount) {
		
		this.playerCount = playerCount;
		
		dices = new Dice[6];
		dices[0] = new Dice("Yellow");
		dices[0] = new Dice("Blue");
		dices[0] = new Dice("White");
		dices[0] = new Dice("Green");
		dices[0] = new Dice("Orange");
		dices[0] = new Dice("purple");
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
		
		//TODO
		
		return false;
	}
	
	public List<Dice> rollDices(List<Dice> dices) {
		
		Random random = new Random();
		Iterator<Dice> iter = dices.iterator();
		
		for(int i = 0; i < dices.size(); i++) {
			
			dices.get(i).setValue(random.nextInt(6) + 1);
		}
		
		return dices;
	}
	
	public List<Integer> investigateWinner() {
		
		//TODO
		
//		List<Integer> winners;
		
		
		
		return null;
	}
	
	public void clearTray() {
		
		for(int i = 0; i < 6; i++) {
			
			dices[i].setOnTray(false);
		}
	}
}
