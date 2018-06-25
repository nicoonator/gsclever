package games.gsClever.Logic;

import java.util.*;

import games.gsClever.Exceptions.CannotUseDiceRepeatException;

public class Game {

	private int round;
	private int playerCount;
	private int currentPlayer;
	private int[] currentFieldId;
	private Area[] currentArea;
	private Dice[] dices;
	private Player[] players;
	
	public Game(int playerCount) {
		
		round = 0;
		this.playerCount = playerCount;
		currentPlayer = 0;
		
		currentFieldId = new int[playerCount];
		currentArea = new Area[playerCount];
		
		dices = new Dice[6];
		dices[0] = new Dice(Color.yellow);
		dices[1] = new Dice(Color.blue);
		dices[2] = new Dice(Color.white);
		dices[3] = new Dice(Color.green);
		dices[4] = new Dice(Color.orange);
		dices[5] = new Dice(Color.purple);
		
		players = new Player[playerCount];
		for(int i = 0; i < playerCount; i++) {
			
			players[i] = new Player(i, "player_" + String.valueOf(i));
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
	
	public void gameMove() {
		
		clearTray();
		clearDiceFields();
		
		if(currentPlayer == 0)
			startRound();
		
		//TODO
		
		if(currentPlayer < playerCount - 1)
			currentPlayer++;
		else
			currentPlayer = 0;
	}
	
	public boolean startRound() {
		
		round++;
		
		switch(round) {
		case 1:
			
			for(int i = 0; i < playerCount; i++) {
				
				players[i].getManagement().incrementDiceRepeatCount();
			}
			
			return true;
			
		case 2:
			
			for(int i = 0; i < playerCount; i++) {
				
				players[i].getManagement().incrementAdditionalDiceCount();
			}
			
			return true;
			
		case 3:
			
			for(int i = 0; i < playerCount; i++) {
				
				players[i].getManagement().incrementDiceRepeatCount();
			}
			
			return true;
			
			//TODO
		}
		
		//TODO
		
		return false;
	}
	
	public List<Dice> rollDices(List<Dice> dices) {
		
		Random random = new Random();
		
		for(Dice dice : dices) {
			
			dice.setValue(random.nextInt(6) + 1);
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
	
	public Return click(int playerId, Area area, int fieldId) throws Exception {
		
		int clickableCount = 1;//TODO
		IsClickable clickable;
		
		switch(area) {
		case dices:
			
			int diceField = 0;
			for(Dice dice : dices) {
				
				if(dice.getField() >= diceField)
					diceField = dice.getField() + 1;
			}
			
			dices[fieldId].setField(diceField);
			
			for(Dice dice : dices) {
				
				if(dice.getField() == -1 && dice.getValue() < dices[fieldId].getValue())
					dice.setOnTray(true);
			}
			
			switch(dices[fieldId].getColor()) {
			case yellow:
				
				players[playerId].getManagement().enterCrossOrNumber(Area.yellow, 
						currentFieldId, dices[Color.yellow.ordinal()].getValue());
				
				break;
				
			case blue:
				
				players[playerId].getManagement().enterCrossOrNumber(Area.blue, 
						currentFieldId, dices[Color.blue.ordinal()].getValue() + 
						dices[Color.white.ordinal()].getValue());
				
				break;
				
			case white:
				
				if(currentArea[playerId] == Area.blue) {
					players[playerId].getManagement().enterCrossOrNumber(currentArea, 
							currentFieldId, dices[Color.white.ordinal()].getValue() + 
							dices[Color.blue.ordinal()].getValue());
				}
				else {
					players[playerId].getManagement().enterCrossOrNumber(currentArea, 
							currentFieldId, dices[Color.white.ordinal()].getValue());
				}
				
				break;
				
			case green:
				
				players[playerId].getManagement().enterCrossOrNumber(Area.green, 
						currentFieldId, dices[Color.green.ordinal()].getValue());
				
				break;
				
			case orange:
				
				players[playerId].getManagement().enterCrossOrNumber(Area.orange, 
						currentFieldId, dices[Color.orange.ordinal()].getValue());
				
				break;
				
			case purple:
				
				players[playerId].getManagement().enterCrossOrNumber(Area.purple, 
						currentFieldId, dices[Color.purple.ordinal()].getValue());
				
				break;
			}
			
			//TODO
			
			break;
			
		case diceRepeat:
			
			players[playerId].getManagement().useDiceRepeat();
			
			//TODO
			
			break;
			
		case additionalDice:
			
			players[playerId].getManagement().useAdditionalDice();
			
			//TODO
			
			break;
			
		case yellow:
			
			//TODO
			
			break;
			
		case blue:
			
			//TODO
			
			break;
			
		case green:
			
			//TODO
			
			break;
			
		case orange:
			
			//TODO
			
			break;
			
		case purple:
			
			//TODO
			
			break;
		}
		
		Return returnBack = new Return(currentPlayer, round, clickableCount, playerCount);
		
		//TODO
		
		return returnBack;
	}
}
