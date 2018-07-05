package games.gsClever.Logic;

import java.util.*;

import games.gsClever.Exceptions.*;

public class MainLogic {

	private int round;
	private int playerCount;
	private int currentPlayer;
	private int[] currentFieldId;
	private Area[] currentArea;
	private Area[] nextArea;
	private SpecialEvent[] currentSpecialEvent;
	private boolean[] ready;
	private Dice[] dices;
	private Player[] players;

	public MainLogic(int playerCount) {

		round = 0;
		this.playerCount = playerCount;
		currentPlayer = 0;
		
		currentFieldId = new int[playerCount];
		currentArea = new Area[playerCount];
		nextArea = new Area[playerCount];
		currentSpecialEvent = new SpecialEvent[playerCount];
		ready = new boolean[playerCount];

		dices = new Dice[6];
		dices[0] = new Dice(Color.yellow);
		dices[1] = new Dice(Color.blue);
		dices[2] = new Dice(Color.white);
		dices[3] = new Dice(Color.green);
		dices[4] = new Dice(Color.orange);
		dices[5] = new Dice(Color.purple);

		players = new Player[playerCount];
		for (int id = 0; id < playerCount; id++) {

			players[id] = new Player("player_" + String.valueOf(id), "pw", id);
		}
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public void nextPlayer() {

		clearTray();
		clearDiceFields();
		
		for(int i = 0; i < playerCount; i++) {
			
			currentFieldId[i] = -1;
			currentArea[i] = null;
			nextArea[i] = null;
			currentSpecialEvent[i] = null;
			ready[i] = false;
		}

		if (currentPlayer < playerCount - 1)
			currentPlayer++;
		else {
			currentPlayer = 0;
			startRound();
		}
	}

	public boolean startRound() {

		round++;

		switch (round) {
		case 1:

			for (int i = 0; i < playerCount; i++) {

				players[i].getManagement().incrementDiceRepeatCount();
			}

			return true;

		case 2:

			for (int i = 0; i < playerCount; i++) {

				players[i].getManagement().incrementAdditionalDiceCount();
			}

			return true;

		case 3:

			for (int i = 0; i < playerCount; i++) {

				players[i].getManagement().incrementDiceRepeatCount();
			}

			return true;
			
		case 4:
			
			//TODO
			
			return true;
			
		case 5:
			
			if(playerCount <= 3)
				return true;
			else
				return false;
			
		case 6:
			
			if(playerCount <= 2)
				return true;
			else
				return false;
			
		default:
			
			return false;

		}
	}

	public void rollDices(List<Dice> dices) {

		Random random = new Random();

		for (Dice dice : dices) {

			dice.setValue(random.nextInt(6) + 1);
		}
	}

	public List<Integer> investigateWinner() {

		int highestPoints = players[0].getManagement().determinePoints();

		int[] points = new int[playerCount];
		for (int i = 0; i < playerCount; i++) {

			points[i] = players[i].getManagement().determinePoints();

			if (points[i] > highestPoints)
				highestPoints = points[i];
		}

		List<Integer> winners = new ArrayList<Integer>();
		for (int i = 0; i < playerCount; i++) {

			if (points[i] == highestPoints)
				winners.add(i);
		}

		return winners;
	}

	public void clearTray() {

		for (int i = 0; i < 6; i++) {

			dices[i].setOnTray(false);
		}
	}

	public void clearDiceFields() {

		for (int i = 0; i < 6; i++) {

			dices[i].setField(-1);
		}
	}
	
	public void putSmallerDicesOnTray(int value) {
		
		for(int i = 0; i < 6; i++) {

			if(dices[i].getValue() < value && dices[i].getField() == -1)
				dices[i].setOnTray(true);
		}
	}

	public Return click(int playerId, Area area, int fieldId) throws Exception {

		Return returnBack = new Return(currentPlayer, round, playerCount);
		boolean[] clickableDices = null;
		
		int diceField = 0;
		for (Dice dice : dices) {

			if (dice.getField() >= diceField)
				diceField = dice.getField() + 1;
		}

		switch(area) {
		case null:
			
			startRound();
			
			break;
			
		case decisionMaker:

			dices[fieldId].setField(diceField);

			for (Dice dice : dices) {

				if (dice.getField() == -1 && dice.getValue() < dices[fieldId].getValue())
					dice.setOnTray(true);
			}

			switch(dices[fieldId].getColor()) {
			case yellow:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, currentFieldId[playerId], dices[Color.yellow.ordinal()].getValue());
				
				putSmallerDicesOnTray(dices[Color.yellow.ordinal()].getValue());

				break;

			case blue:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, currentFieldId[playerId], dices[Color.blue.ordinal()].getValue() + 
						dices[Color.white.ordinal()].getValue());
				
				putSmallerDicesOnTray(dices[Color.blue.ordinal()].getValue());

				break;

			case white:

				if (currentArea[playerId] == Area.blue) {
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], currentFieldId[playerId],
							dices[Color.white.ordinal()].getValue() + dices[Color.blue.ordinal()].getValue());
				} else {
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], currentFieldId[playerId], 
							dices[Color.white.ordinal()].getValue());
				}
				
				putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());

				break;

			case green:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.green, currentFieldId[playerId], dices[Color.green.ordinal()].getValue());

				putSmallerDicesOnTray(dices[Color.green.ordinal()].getValue());
				
				break;

			case orange:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.orange, currentFieldId[playerId], dices[Color.orange.ordinal()].getValue());

				putSmallerDicesOnTray(dices[Color.orange.ordinal()].getValue());
				
				break;

			case purple:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.purple, currentFieldId[playerId], dices[Color.purple.ordinal()].getValue());

				putSmallerDicesOnTray(dices[Color.purple.ordinal()].getValue());
				
				break;
			}
			
			if(currentSpecialEvent[playerId] != null) {
				
				//TODO clickable
				
				nextArea[playerId] = Area.specialEvent;
			}
			
			//TODO

			break;
			
		case rollDices:
			
			List<Dice> dices = new ArrayList<Dice>();
			
			for(Dice dice : this.dices) {
				
				if(dice.isOnTray() == false && dice.getField() == -1)
					dices.add(dice);
			}
			
			rollDices(dices);
			
			//TODO
			
			break;

		case diceRepeat:

			if (players[playerId].getManagement().getDiceRepeatUsed() < 
					players[playerId].getManagement().getDiceRepeatCount())
				players[playerId].getManagement().incrementDiceRepeatUsed();
			else
				throw new CannotUseDiceRepeatException();

			returnBack.getClickable(playerId).setRollDices(true);

			nextArea[playerId] = Area.rollDices;
			
			break;

		case additionalDice:

			if (players[playerId].getManagement().getAdditionalDiceUsed() < 
					players[playerId].getManagement().getAdditionalDiceCount())
				players[playerId].getManagement().incrementAdditionalDiceUsed();
			else
				throw new CannotUseAdditionalDiceException();

			// TODO
			
			boolean[] additionalDices = new boolean[6];
			
			for(int i = 0; i < 6; i++) {
				
				if(this.dices[i].isOnTray() == true)
					additionalDices[i] = true;
			}
			
			//TODO auf DecisionMaker anpassen
//			returnBack.getClickable(playerId).setDices(additionalDices);
			
			nextArea[playerId] = Area.decisionMaker;

			break;

		case specialEvent:

			switch(currentSpecialEvent[playerId]) {
			case enterCrossYellow:
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, fieldId, 0);
				
				break;
				
			case enterCrossBlue:
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, fieldId, 0);
				
				break;
			}
			
			// TODO clickable

			if(currentSpecialEvent != null && playerId == currentPlayer)
				nextArea[playerId] = Area.rollDices;
			else
				nextArea[playerId] = Area.specialEvent;
			
			break;

		case yellow:

			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getYellow().clickableDices(
					this.dices[Color.yellow.ordinal()], this.dices[Color.white.ordinal()], fieldId);
			
			if(this.dices[Color.yellow.ordinal()].getValue() == this.dices[Color.white.ordinal()].getValue() && 
					this.dices[Color.yellow.ordinal()].getField() == -1 && 
					this.dices[Color.yellow.ordinal()].isOnTray() == false && 
					this.dices[Color.white.ordinal()].getField() == -1 && 
					this.dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.yellow);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(this.dices[Color.yellow.ordinal()].getField() != -1 || 
					this.dices[Color.yellow.ordinal()].isOnTray() == true ||
					clickableDices[0] == false) {
				
				this.dices[Color.white.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, currentFieldId[playerId], this.dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					switch(currentSpecialEvent[playerId]) {
					case enterCrossYellow:
						
						boolean[] clickableYellow = players[playerId].getManagement().getYellow().getFields();
						for(int i = 0; i < clickableYellow.length; i++) {
							
							if(clickableYellow[i] == true)
								clickableYellow[i] = false;
							else
								clickableYellow[i] = true;
						}
						
						returnBack.getClickable(playerId).setYellow(clickableYellow);
						
						break;
						
					case enterCrossBlue:
						
						boolean[] clickableBlue = players[playerId].getManagement().getBlue().getFields();
						for(int i = 0; i < clickableBlue.length; i++) {
							
							if(clickableBlue[i] == true)
								clickableBlue[i] = false;
							else
								clickableBlue[i] = true;
						}
						
						returnBack.getClickable(playerId).setBlue(clickableBlue);
						
						break;
					}
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(this.dices[Color.white.ordinal()].getField() != -1 || 
					this.dices[Color.white.ordinal()].isOnTray() == true ||
					clickableDices[1] == false) {
				
				this.dices[Color.yellow.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.yellow.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, currentFieldId[playerId], this.dices[Color.yellow.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			break;

		case blue:

			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getBlue().clickableDices(
					this.dices[Color.blue.ordinal()], this.dices[Color.white.ordinal()], fieldId);
			
			if(this.dices[Color.blue.ordinal()].getField() == -1 && 
					this.dices[Color.blue.ordinal()].isOnTray() == false && 
					this.dices[Color.white.ordinal()].getField() == -1 && 
					this.dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.blue);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(this.dices[Color.blue.ordinal()].getField() != -1 || 
					this.dices[Color.blue.ordinal()].isOnTray() == true ||
					clickableDices[0] == false) {
				
				this.dices[Color.white.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, currentFieldId[playerId], this.dices[Color.blue.ordinal()].getValue() + 
						this.dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(this.dices[Color.white.ordinal()].getField() != -1 || 
					this.dices[Color.white.ordinal()].isOnTray() == true ||
					clickableDices[1] == false) {
				
				this.dices[Color.blue.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.blue.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, currentFieldId[playerId], this.dices[Color.blue.ordinal()].getValue() + 
						this.dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			break;

		case green:
			
			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getGreen().clickableDices(
					this.dices[Color.green.ordinal()], this.dices[Color.white.ordinal()]);

			if(clickableDices[0] && clickableDices[1] &&
					this.dices[Color.green.ordinal()].getField() == -1 && 
					this.dices[Color.green.ordinal()].isOnTray() == false && 
					this.dices[Color.white.ordinal()].getField() == -1 && 
					this.dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.green);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(this.dices[Color.green.ordinal()].getField() != -1 || 
					this.dices[Color.green.ordinal()].isOnTray() == true ||
					clickableDices[0] == false) {
				
				this.dices[Color.white.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.green, currentFieldId[playerId], this.dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(this.dices[Color.white.ordinal()].getField() != -1 || 
					this.dices[Color.white.ordinal()].isOnTray() == true ||
					clickableDices[1] == false) {
	
				this.dices[Color.green.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.green.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.green, currentFieldId[playerId], this.dices[Color.green.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			break;

		case orange:
			
			currentFieldId[playerId] = fieldId;

			if(this.dices[Color.orange.ordinal()].getField() == -1 && 
					this.dices[Color.orange.ordinal()].isOnTray() == false && 
					this.dices[Color.white.ordinal()].getField() == -1 && 
					this.dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.orange);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(this.dices[Color.orange.ordinal()].getField() != -1 || 
					this.dices[Color.orange.ordinal()].isOnTray() == true) {
				
				this.dices[Color.white.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.orange, currentFieldId[playerId], this.dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(this.dices[Color.white.ordinal()].getField() != -1 || 
					this.dices[Color.white.ordinal()].isOnTray() == true) {
	
				this.dices[Color.orange.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.orange.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.orange, currentFieldId[playerId], this.dices[Color.orange.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			break;

		case purple:
			
			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getPurple().clickableDices(
					this.dices[Color.purple.ordinal()], this.dices[Color.white.ordinal()]);
			
			if(clickableDices[0] && clickableDices[1] &&
					this.dices[Color.purple.ordinal()].getField() == -1 && 
					this.dices[Color.purple.ordinal()].isOnTray() == false && 
					this.dices[Color.white.ordinal()].getField() == -1 && 
					this.dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.purple);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(this.dices[Color.purple.ordinal()].getField() != -1 || 
					this.dices[Color.purple.ordinal()].isOnTray() == true ||
					clickableDices[0] == false) {
				
				this.dices[Color.white.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.purple, currentFieldId[playerId], this.dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(this.dices[Color.white.ordinal()].getField() != -1 || 
					this.dices[Color.white.ordinal()].isOnTray() == true ||
					clickableDices[1] == false) {
	
				this.dices[Color.purple.ordinal()].setField(diceField);
				
				putSmallerDicesOnTray(this.dices[Color.purple.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.purple, currentFieldId[playerId], this.dices[Color.purple.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					//TODO clickable
					
					nextArea[playerId] = Area.specialEvent;
				}
				else {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}

			break;
			
		case ready:
			
			ready[playerId] = true;
			
			boolean allReady = true;
			for(int i = 0; i < playerCount; i++) {
				
				if(ready[i] == false) {
					
					allReady = false;
					break;
				}
			}
			
			if(allReady == true) {
				
				nextPlayer();
				
				returnBack.getClickable(currentPlayer).setRollDices(true);
				
				nextArea[currentPlayer] = Area.rollDices;
			}
		}

		for(int i = 0; i < playerCount; i++) {

			returnBack.setClickable(players[i].getManagement().isClickable(), i);
			returnBack.getClickable(i).setPlayerId(i);
			
			Matchfield matchfield = new Matchfield();

			matchfield.setYellow(players[i].getManagement().getYellow().getFields());
			matchfield.setBlue(players[i].getManagement().getBlue().getFields());
			matchfield.setGreen(players[i].getManagement().getGreen().getFields());
			matchfield.setOrange(players[i].getManagement().getOrange().getFields());
			matchfield.setPurple(players[i].getManagement().getPurple().getFields());

			matchfield.setDiceRepeatCount(players[i].getManagement().getDiceRepeatCount());
			matchfield.setDiceRepeatUsed(players[i].getManagement().getDiceRepeatUsed());
			matchfield.setAdditionalDiceCount(players[i].getManagement().getAdditionalDiceCount());
			matchfield.setAdditionalDiceUsed(players[i].getManagement().getAdditionalDiceUsed());

			returnBack.setMatchfield(matchfield, i);
		}
		
		returnBack.setDices(dices);

		// TODO

		return returnBack;
	}
}
