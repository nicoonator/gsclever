package games.gsClever.Logic;

import java.util.List;

public class Game {

	private int round;
	private int playerCount;
	private Dice[] dices = new Dice[6];
	private Player[] player;
	
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
