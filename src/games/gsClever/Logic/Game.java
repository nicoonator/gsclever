package games.gsClever.Logic;

import java.util.List;

public class Game {

	private int round;
	private int playerCount;
	private Dice[] dices;
	private Player[] player;
	
	public Game(int playerCount) {
		
		this.playerCount = playerCount;
		
		dices = new Dice[6];
		dices[0] = new Dice("Yellow", 1, false, -1);//TODO Dice Konstruktor vereinfachen
		dices[0] = new Dice("Blue", 1, false, -1);
		dices[0] = new Dice("White", 1, false, -1);
		dices[0] = new Dice("Green", 1, false, -1);
		dices[0] = new Dice("Orange", 1, false, -1);
		dices[0] = new Dice("purple", 1, false, -1);
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
		
		//TODO
		
		return dices;
	}
	
	public int investigateWinner() {
		
		//TODO
		
		return 0;
	}
	
	public void clearTray() {
		
		for(int i = 0; i < 6; i++) {
			
			dices[i].setOnTray(false);
		}
	}
}
