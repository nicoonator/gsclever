package games.gsClever.Logic;

import java.util.*;

import games.gsClever.Exceptions.*;

public class MainLogic {

	private int round;
	private int playerCount;
	private boolean additionalDiceTime;
	private int currentPlayer;
	private int[] currentFieldId;
	private Area[] currentArea;
	private Area[] nextArea;
	private SpecialEvent[] currentSpecialEvent;
	private boolean[] ready;
	private Dice[] dices;
	private Player[] players;

	public MainLogic(ArrayList<Player> userList) {

		round = 0;
		playerCount = userList.size();
		additionalDiceTime = false;
		currentPlayer = 0;
		
		currentFieldId = new int[userList.size()];
		currentArea = new Area[userList.size()];
		nextArea = new Area[userList.size()];
		currentSpecialEvent = new SpecialEvent[userList.size()];
		ready = new boolean[userList.size()];

		dices = new Dice[6];
		dices[Color.yellow.ordinal()] = new Dice(Color.yellow);
		dices[Color.blue.ordinal()] = new Dice(Color.blue);
		dices[Color.green.ordinal()] = new Dice(Color.green);
		dices[Color.orange.ordinal()] = new Dice(Color.orange);
		dices[Color.purple.ordinal()] = new Dice(Color.purple);
		dices[Color.white.ordinal()] = new Dice(Color.white);
		
		players = new Player[userList.size()];
		for (int id = 0; id < userList.size(); id++) {
			
			players[id] = userList.get(id);
		}
	}

	public int determinePlayerId(String userName) {
		
		for(int i = 0; i < playerCount; i++) {
			
			if(players[i].getName().equals(userName))
				return i;
		}
		
		return -1;
	}
	
	private boolean nextPlayer() {

		clearTray();
		clearDiceFields();
		
		additionalDiceTime = false;
		
		for(int i = 0; i < playerCount; i++) {
			
			currentFieldId[i] = -1;
			currentArea[i] = null;
			nextArea[i] = null;
			currentSpecialEvent[i] = null;
			ready[i] = false;
		}
		
		if (currentPlayer < playerCount - 1) {
			
			currentPlayer++;
			
			return true;
		}
		else {
			
			currentPlayer = 0;
			
			return startRound();
		}
	}

	private boolean startRound() {

		boolean result = false;
		
		switch (round + 1) {
		case 1:

			for (int i = 0; i < playerCount; i++) {

				players[i].getManagement().incrementDiceRepeatCount();
			}

			result = true;
			
			break;

		case 2:

			for (int i = 0; i < playerCount; i++) {

				players[i].getManagement().incrementAdditionalDiceCount();
			}

			result = true;
			
			break;

		case 3:

			for (int i = 0; i < playerCount; i++) {

				players[i].getManagement().incrementDiceRepeatCount();
			}

			result = true;
			
			break;
			
		case 4:
			
			for(int i = 0; i < playerCount; i++) {
			
				currentSpecialEvent[i] = SpecialEvent.round4;
					
				nextArea[i] = Area.specialEvent;
			}
			
			result = true;
			
			break;
			
		case 5:
			
			if(playerCount <= 3)
				result = true;
			else
				result = false;
			
			break;
			
		case 6:
			
			if(playerCount <= 2)
				result = true;
			else
				result = false;
			
			break;
			
		default:
			
			result = false;
			
			break;
		}
		
		if(result)
			round++;
		
		return result;
	}

	private void rollDices(List<Dice> dices) {

		Random random = new Random();

		for (Dice dice : dices) {

			dice.setValue(random.nextInt(6) + 1);
		}
	}

	private List<Integer> investigateWinner() {

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

	private void clearTray() {

		for (int i = 0; i < 6; i++) {

			dices[i].setOnTray(false);
		}
	}

	private void clearDiceFields() {

		for (int i = 0; i < 6; i++) {

			dices[i].setField(-1);
		}
	}
	
	private void putSmallerDicesOnTray(int value) {
		
		for(int i = 0; i < 6; i++) {

			if(dices[i].getValue() < value && dices[i].getField() == -1)
				dices[i].setOnTray(true);
		}
	}
	
	private void diceAvailable() {
		
		boolean diceAvailable = false;
		
		for(int i = 0; i < 6; i++) {
			
			if(dices[i].getField() == -1 && dices[i].isOnTray() == false) {
				
				diceAvailable = true;
				
				break;
			}
		}
		
		boolean specialEvent = false;
		for(int i = 0; i < playerCount; i++) {
			
			if(nextArea[i] == Area.specialEvent)
				specialEvent = true;
		}
		
		if(diceAvailable == false && specialEvent == false) {
			
			additionalDiceTime = true;
			
			for(int i = 0; i < playerCount; i++) {
				
				if(i != currentPlayer)
					nextArea[i] = Area.takeDiceFromTray;
				else
					nextArea[i] = null;
			}
		}
	}
	
	private void stealDice(int playerId, int fieldId, Area actualArea) {
		
		currentFieldId[playerId] = fieldId;
		
		if(currentArea[playerId] != null) {
		
			switch(currentArea[playerId]) {
			case yellow:
				
				if(dices[Color.yellow.ordinal()].isOnTray() || dices[Color.white.ordinal()].isOnTray() || 
						actualArea == Area.additionalDiceExecute) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, 0);
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				
				break;
				
			case blue:
				
				if(dices[Color.blue.ordinal()].isOnTray() || dices[Color.white.ordinal()].isOnTray() || 
						actualArea == Area.additionalDiceExecute) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, dices[Color.blue.ordinal()].getValue() + 
							dices[Color.white.ordinal()].getValue());
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				
				break;
				
			case green:
				
				if(dices[Color.green.ordinal()].isOnTray() || dices[Color.white.ordinal()].isOnTray() || 
						actualArea == Area.additionalDiceExecute) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, 0);
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				
				break;
				
			case orange:
				
				if(((dices[Color.orange.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray()) || 
						actualArea == Area.additionalDiceExecute) && 
						dices[Color.orange.ordinal()].getValue() != dices[Color.white.ordinal()].getValue()) {
					
					nextArea[playerId] = Area.decisionMaker;
				}
				else if(actualArea == Area.additionalDiceExecute && 
						dices[Color.orange.ordinal()].getValue() == dices[Color.white.ordinal()].getValue()) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, dices[Color.orange.ordinal()].getValue());
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				else if(dices[Color.orange.ordinal()].isOnTray()) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, dices[Color.orange.ordinal()].getValue());
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				else if(dices[Color.white.ordinal()].isOnTray()) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, dices[Color.white.ordinal()].getValue());
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				
				break;
				
			case purple:
				
				boolean[] clickableDices = players[playerId].getManagement().getPurple().clickableDices(
						dices[Color.purple.ordinal()].getValue(), dices[Color.white.ordinal()].getValue());
				
				if(clickableDices[0] && clickableDices[1] && (actualArea == Area.additionalDiceExecute ||
						dices[Color.purple.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray())) {
					
					nextArea[playerId] = Area.decisionMaker;
				}
				else if(clickableDices[0] && (actualArea == Area.additionalDiceExecute || 
						dices[Color.purple.ordinal()].isOnTray())) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, dices[Color.purple.ordinal()].getValue());
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				else if(clickableDices[1] && (actualArea == Area.additionalDiceExecute || 
						dices[Color.white.ordinal()].isOnTray())) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							currentArea[playerId], fieldId, dices[Color.white.ordinal()].getValue());
					
					nextArea[playerId] = null;
					currentArea[playerId] = null;
				}
				
				break;
				
			default:
				break;
			}
		}
	}
	
	public Return click(int playerId, Area area, int fieldId) throws Exception {

		if(nextArea[playerId] == Area.specialEvent) {
		
			currentArea[playerId] = area;
			area = Area.specialEvent;
		}
		else if(additionalDiceTime && (area == Area.yellow || area == Area.blue || 
				area == Area.green || area == Area.orange || area == Area.purple)) {
			
			currentArea[playerId] = area;
				
			if(nextArea[playerId] == Area.takeDiceFromTray)
				area = Area.takeDiceFromTray;
			else if(nextArea[playerId] == Area.additionalDiceExecute)
				area = Area.additionalDiceExecute;
			
			nextArea[playerId] = null;
		}
		
		Return returnBack = new Return(currentPlayer, round, playerCount);
		boolean[] clickableDices = null;
		
		int diceField = 0;
		for (Dice dice : dices) {

			if (dice.getField() >= diceField)
				diceField = dice.getField() + 1;
		}
		
		if(area == null)
			startRound();
		else {
			
			switch(area) {
			case decisionMaker:
	
				if(!additionalDiceTime) {
					
					dices[fieldId].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
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
				
				currentArea[playerId] = null;
				
				if(currentSpecialEvent[playerId] != null)
					nextArea[playerId] = Area.specialEvent;
				else if(currentPlayer == playerId && !additionalDiceTime)
					nextArea[playerId] = Area.rollDices;
				else
					nextArea[playerId] = null;
				
				diceAvailable();
				
				break;
				
			case rollDices:
				
				List<Dice> dicesToRoll = new ArrayList<Dice>();
				
				for(Dice dice : this.dices) {
					
					if(dice.isOnTray() == false && dice.getField() == -1)
						dicesToRoll.add(dice);
				}
				
				rollDices(dicesToRoll);
				
				currentArea[currentPlayer] = Area.rollDices;
//TODO			nextArea[currentPlayer] = null;
				
				break;
	
			case diceRepeat:
	
				if (players[playerId].getManagement().getDiceRepeatUsed() < 
						players[playerId].getManagement().getDiceRepeatCount())
					players[playerId].getManagement().incrementDiceRepeatUsed();
				else
					throw new CannotUseDiceRepeatException();
	
				nextArea[playerId] = Area.rollDices;
				
				currentArea[playerId] = null;
				
				break;
	
			case additionalDice:
	
				if (players[playerId].getManagement().getAdditionalDiceUsed() < 
						players[playerId].getManagement().getAdditionalDiceCount())
					players[playerId].getManagement().incrementAdditionalDiceUsed();
				else
					throw new CannotUseAdditionalDiceException();
				
				nextArea[playerId] = Area.additionalDiceExecute;
				
				currentArea[playerId] = Area.additionalDice;
					
				break;
				
			case additionalDiceExecute:
				
				stealDice(playerId, fieldId, Area.additionalDiceExecute);
				
				break;
	
			case specialEvent:
	
				switch(currentSpecialEvent[playerId]) {
				case enterCrossYellow:
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.yellow, fieldId, 0);
					
					if(!additionalDiceTime)
						diceAvailable();
					
					break;
					
				case enterCrossBlue:
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.blue, fieldId, 0);
					
					if(!additionalDiceTime)
						diceAvailable();
					
					break;
					
				case round4:
					
					switch(currentArea[playerId]) {
					case yellow:
						
						currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
								Area.yellow, fieldId, 6);
						
						break;
						
					case blue:
						
						currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
								Area.blue, fieldId, 6);
						
						break;
						
					case green:
	
						currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
								Area.green, fieldId, 6);
	
						break;
	
					case orange:
	
						currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
								Area.orange, fieldId, 6);
						
						break;
						
					case purple:
						
						currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
								Area.purple, fieldId, 6);
						
						break;
						
					default:
						break;
					}
					
					break;
					
				default:
					break;
				}
				
				if(currentSpecialEvent[playerId] != null)
					nextArea[playerId] = Area.specialEvent;
				else if(currentPlayer == playerId && !additionalDiceTime)
					nextArea[playerId] = Area.rollDices;
				else
					nextArea[playerId] = null;
				
				currentArea[playerId] = null;
				
				break;
	
			case yellow:
	
				currentArea[playerId] = null;
				
				currentFieldId[playerId] = fieldId;
				
				clickableDices = players[playerId].getManagement().getYellow().clickableDices(
						dices[Color.yellow.ordinal()].getValue(), dices[Color.white.ordinal()].getValue(), fieldId);
				
				if(dices[Color.yellow.ordinal()].getValue() == dices[Color.white.ordinal()].getValue() && 
						dices[Color.yellow.ordinal()].getField() == -1 && 
						dices[Color.yellow.ordinal()].isOnTray() == false && 
						dices[Color.white.ordinal()].getField() == -1 && 
						dices[Color.white.ordinal()].isOnTray() == false) {
					
					nextArea[playerId] = Area.decisionMaker;
					
					currentArea[playerId] = Area.yellow;
				}
				else if(dices[Color.yellow.ordinal()].getField() != -1 || 
						dices[Color.yellow.ordinal()].isOnTray() == true ||
						clickableDices[0] == false) {
					
					dices[Color.white.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.yellow, currentFieldId[playerId], dices[Color.white.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				else if(dices[Color.white.ordinal()].getField() != -1 || 
						dices[Color.white.ordinal()].isOnTray() == true ||
						clickableDices[1] == false) {
					
					dices[Color.yellow.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.yellow.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.yellow, currentFieldId[playerId], dices[Color.yellow.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				
				diceAvailable();
				
				break;
	
			case blue:
	
				currentArea[playerId] = null;
				
				currentFieldId[playerId] = fieldId;
				
				if(dices[Color.blue.ordinal()].getField() == -1 && 
						dices[Color.blue.ordinal()].isOnTray() == false && 
						dices[Color.white.ordinal()].getField() == -1 && 
						dices[Color.white.ordinal()].isOnTray() == false) {
					
					nextArea[playerId] = Area.decisionMaker;
					
					currentArea[playerId] = Area.blue;
				}
				else if(dices[Color.blue.ordinal()].getField() != -1 || 
						dices[Color.blue.ordinal()].isOnTray() == true) {
					
					dices[Color.white.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.blue, currentFieldId[playerId], dices[Color.blue.ordinal()].getValue() + 
							dices[Color.white.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				else if(dices[Color.white.ordinal()].getField() != -1 || 
						dices[Color.white.ordinal()].isOnTray() == true) {
					
					dices[Color.blue.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.blue.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.blue, currentFieldId[playerId], dices[Color.blue.ordinal()].getValue() + 
							dices[Color.white.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				
				diceAvailable();
				
				break;
	
			case green:
				
				currentArea[playerId] = null;
				
				currentFieldId[playerId] = fieldId;
				
				clickableDices = players[playerId].getManagement().getGreen().clickableDices(
						dices[Color.green.ordinal()].getValue(), this.dices[Color.white.ordinal()].getValue());
	
				if(clickableDices[0] && clickableDices[1] &&
						dices[Color.green.ordinal()].getField() == -1 && 
						dices[Color.green.ordinal()].isOnTray() == false && 
						dices[Color.white.ordinal()].getField() == -1 && 
						dices[Color.white.ordinal()].isOnTray() == false) {
					
					nextArea[playerId] = Area.decisionMaker;
					
					currentArea[playerId] = Area.green;
				}
				else if(dices[Color.green.ordinal()].getField() != -1 || 
						dices[Color.green.ordinal()].isOnTray() == true ||
						clickableDices[0] == false) {
					
					dices[Color.white.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.green, currentFieldId[playerId], dices[Color.white.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				else if(dices[Color.white.ordinal()].getField() != -1 || 
						dices[Color.white.ordinal()].isOnTray() == true ||
						clickableDices[1] == false) {
		
					dices[Color.green.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.green.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.green, currentFieldId[playerId], dices[Color.green.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				
				diceAvailable();
				
				break;
	
			case orange:
				
				currentArea[playerId] = null;
				
				currentFieldId[playerId] = fieldId;
	
				if(dices[Color.orange.ordinal()].getField() == -1 && 
						dices[Color.orange.ordinal()].isOnTray() == false && 
						dices[Color.white.ordinal()].getField() == -1 && 
						dices[Color.white.ordinal()].isOnTray() == false) {
					
					nextArea[playerId] = Area.decisionMaker;
					
					currentArea[playerId] = Area.orange;
				}
				else if(dices[Color.orange.ordinal()].getField() != -1 || 
						dices[Color.orange.ordinal()].isOnTray() == true) {
					
					dices[Color.white.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.orange, currentFieldId[playerId], dices[Color.white.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				else if(dices[Color.white.ordinal()].getField() != -1 || 
						dices[Color.white.ordinal()].isOnTray() == true) {
		
					dices[Color.orange.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.orange.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.orange, currentFieldId[playerId], dices[Color.orange.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				
				diceAvailable();
				
				break;
	
			case purple:
				
				currentArea[playerId] = null;
				
				currentFieldId[playerId] = fieldId;
				
				clickableDices = players[playerId].getManagement().getPurple().clickableDices(
						dices[Color.purple.ordinal()].getValue(), dices[Color.white.ordinal()].getValue());
				
				if(clickableDices[0] && clickableDices[1] &&
						dices[Color.purple.ordinal()].getField() == -1 && 
						dices[Color.purple.ordinal()].isOnTray() == false && 
						dices[Color.white.ordinal()].getField() == -1 && 
						dices[Color.white.ordinal()].isOnTray() == false) {
					
					nextArea[playerId] = Area.decisionMaker;
					
					currentArea[playerId] = Area.purple;
				}
				else if(dices[Color.purple.ordinal()].getField() != -1 || 
						dices[Color.purple.ordinal()].isOnTray() == true ||
						clickableDices[0] == false) {
					
					dices[Color.white.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.purple, currentFieldId[playerId], dices[Color.white.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				else if(dices[Color.white.ordinal()].getField() != -1 || 
						dices[Color.white.ordinal()].isOnTray() == true ||
						clickableDices[1] == false) {
		
					dices[Color.purple.ordinal()].setField(diceField);
					if(diceField >= 2)
						putSmallerDicesOnTray(7);
					
					putSmallerDicesOnTray(dices[Color.purple.ordinal()].getValue());
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.purple, currentFieldId[playerId], dices[Color.purple.ordinal()].getValue());
					
					if(currentSpecialEvent[playerId] != null)
						nextArea[playerId] = Area.specialEvent;
					else if(currentPlayer == playerId)
						nextArea[playerId] = Area.rollDices;
				}
				
				diceAvailable();
				
				break;
				
			case takeDiceFromTray:
				
				stealDice(playerId, fieldId, Area.takeDiceFromTray);
				
				currentArea[playerId] = Area.takeDiceFromTray;
				
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
					
					if(nextPlayer() == false) {
					
						returnBack.setWinner(new Winner(playerCount));
						returnBack.getWinner().setWinners(investigateWinner());
						
						for(int i = 0; i < playerCount; i++) {
							
							returnBack.getWinner().getPoints(i).setPlayerId(i);
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().getYellow().determinePoints(), 
									Color.yellow.ordinal());
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().getBlue().determinePoints(), 
									Color.blue.ordinal());
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().getGreen().determinePoints(), 
									Color.green.ordinal());
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().getOrange().determinePoints(), 
									Color.orange.ordinal());
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().getPurple().determinePoints(), 
									Color.purple.ordinal());
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().determinePointsFoxes(), 5);
							returnBack.getWinner().getPoints(i).setPoints(
									players[i].getManagement().determinePoints(), 6);
						}
					}
					
					nextArea[currentPlayer] = Area.rollDices;
					
					currentArea[playerId] = null;
				}
			}
		}
		
		for(int i = 0; i < playerCount; i++) {
			
			if(additionalDiceTime && nextArea[i] != Area.takeDiceFromTray && 
					currentArea[i] != Area.additionalDice && nextArea[i] != Area.decisionMaker) {
				
				returnBack.getClickable(i).setReady(true);
			}
			
			if(currentArea[i] != null) {
				
				switch(currentArea[i]) {
				case rollDices:
					
					if(i == currentPlayer) {
						
						returnBack.setClickable(players[currentPlayer].getManagement().isClickable(dices), currentPlayer);
						
						if(players[currentPlayer].getManagement().getDiceRepeatUsed() < 
								players[currentPlayer].getManagement().getDiceRepeatCount())
							returnBack.getClickable(currentPlayer).setDiceRepeat(true);
					}
					
					break;
					
				case additionalDice:
					
					returnBack.setClickable(players[playerId].getManagement().isClickable(dices), playerId);
					
					break;
					
				default:
					break;
				}
			}
				
			if(nextArea[i] != null) {
				
				switch(nextArea[i]) {
				case decisionMaker:
					
					returnBack.getDecisionMaker(i).setColorDiceActive(true);
					returnBack.getDecisionMaker(i).setWhiteDiceActive(true);
					
					if(currentArea[i] != null) {
					
						switch(currentArea[i]) {
						case yellow:
							
							returnBack.getDecisionMaker(i).setColorOfDice(Color.yellow);
							
							break;
							
						case blue:
							
							returnBack.getDecisionMaker(i).setColorOfDice(Color.blue);
							
							break;
							
						case green:
							
							returnBack.getDecisionMaker(i).setColorOfDice(Color.green);
							
							break;
							
						case orange:
							
							returnBack.getDecisionMaker(i).setColorOfDice(Color.orange);
							
							break;
							
						case purple:
							
							returnBack.getDecisionMaker(i).setColorOfDice(Color.purple);
							
							break;
							
						default:
							break;
						}
					}
					
					break;
					
				case rollDices:
					
					if(currentPlayer == i)
						returnBack.getClickable(i).setRollDices(true);
					
					break;
					
				case specialEvent:
					
					switch(currentSpecialEvent[i]) {
					case enterCrossYellow:
						
						boolean[] clickableYellow = players[playerId].getManagement().getYellow().getFields();
						for(int j = 0; j < clickableYellow.length; j++) {
							
							if(clickableYellow[j] == true)
								clickableYellow[j] = false;
							else
								clickableYellow[j] = true;
						}
						
						returnBack.getClickable(playerId).setYellow(clickableYellow);
						
						break;
						
					case enterCrossBlue:
						
						boolean[] clickableBlue = players[playerId].getManagement().getBlue().getFields();
						for(int j = 0; j < clickableBlue.length; j++) {
							
							if(clickableBlue[j] == true)
								clickableBlue[j] = false;
							else
								clickableBlue[j] = true;
						}
						
						returnBack.getClickable(playerId).setBlue(clickableBlue);
						
						break;
						
					case round4:
						
						clickableYellow = players[playerId].getManagement().getYellow().getFields();
						for(int j = 0; j < clickableYellow.length; j++) {
							
							if(clickableYellow[j] == true)
								clickableYellow[j] = false;
							else
								clickableYellow[j] = true;
						}
						
						clickableBlue = players[playerId].getManagement().getBlue().getFields();
						for(int j = 0; j < clickableBlue.length; j++) {
							
							if(clickableBlue[j] == true)
								clickableBlue[j] = false;
							else
								clickableBlue[j] = true;
						}
						
						boolean clickableGreen = false;
						if(players[playerId].getManagement().getGreen().getFields() < 11)
							clickableGreen = true;
						
						boolean clickableOrange = false;
						if(players[playerId].getManagement().getOrange().getFields()[10] == 0)
							clickableOrange = true;
						
						boolean clickablePurple = false;
						if(players[playerId].getManagement().getPurple().getFields()[10] == 0)
							clickablePurple = true;
						
						returnBack.getClickable(playerId).setYellow(clickableYellow);
						returnBack.getClickable(playerId).setBlue(clickableBlue);
						returnBack.getClickable(playerId).setGreen(clickableGreen);
						returnBack.getClickable(playerId).setOrange(clickableOrange);
						returnBack.getClickable(playerId).setPurple(clickablePurple);
						
						break;
						
					default:
						break;
					}
					
					break;
					
				case takeDiceFromTray:
					
					if(dices[Color.yellow.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setYellow(players[i].getManagement().getYellow().isClickable(
								dices[Color.yellow.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					else if(dices[Color.yellow.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setYellow(players[i].getManagement().getYellow().isClickable(
								dices[Color.yellow.ordinal()].getValue(), dices[Color.yellow.ordinal()].getValue()));
					}
					else if(dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setYellow(players[i].getManagement().getYellow().isClickable(
								dices[Color.white.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					
					if(dices[Color.blue.ordinal()].isOnTray() || dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setBlue(players[i].getManagement().getBlue().isClickable(
								dices[Color.blue.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}

					if(dices[Color.green.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setGreen(players[i].getManagement().getGreen().isClickable(
								dices[Color.green.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					else if(dices[Color.green.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setGreen(players[i].getManagement().getGreen().isClickable(
								dices[Color.green.ordinal()].getValue(), dices[Color.green.ordinal()].getValue()));
					}
					else if(dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setGreen(players[i].getManagement().getGreen().isClickable(
								dices[Color.white.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					
					if(dices[Color.orange.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setOrange(players[i].getManagement().getOrange().isClickable(
								dices[Color.orange.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					else if(dices[Color.orange.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setOrange(players[i].getManagement().getOrange().isClickable(
								dices[Color.orange.ordinal()].getValue(), dices[Color.orange.ordinal()].getValue()));
					}
					else if(dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setOrange(players[i].getManagement().getOrange().isClickable(
								dices[Color.white.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					
					if(dices[Color.purple.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setPurple(players[i].getManagement().getPurple().isClickable(
								dices[Color.purple.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
					else if(dices[Color.purple.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setPurple(players[i].getManagement().getPurple().isClickable(
								dices[Color.purple.ordinal()].getValue(), dices[Color.purple.ordinal()].getValue()));
					}
					else if(dices[Color.white.ordinal()].isOnTray()) {
						
						returnBack.getClickable(i).setPurple(players[i].getManagement().getPurple().isClickable(
								dices[Color.white.ordinal()].getValue(), dices[Color.white.ordinal()].getValue()));
					}
						
					break;
					
				default:
					break;
				}
			}
			
			if(players[i].getManagement().getAdditionalDiceUsed() < 
					players[i].getManagement().getAdditionalDiceCount() && 
					additionalDiceTime && nextArea[i] != Area.takeDiceFromTray &&
					currentArea[i] != Area.decisionMaker && nextArea[i] != Area.decisionMaker)
				returnBack.getClickable(i).setAdditionalDice(true);
			
			returnBack.setRound(round);
			
			Matchfield matchfield = returnBack.getMatchfield(i);
			
			matchfield.setYellow(players[i].getManagement().getYellow().getFields());
			matchfield.setBlue(players[i].getManagement().getBlue().getFields());
			matchfield.setGreen(players[i].getManagement().getGreen().getFields());
			matchfield.setOrange(players[i].getManagement().getOrange().getFields());
			matchfield.setPurple(players[i].getManagement().getPurple().getFields());
			
			matchfield.setDiceRepeatCount(players[i].getManagement().getDiceRepeatCount());
			matchfield.setDiceRepeatUsed(players[i].getManagement().getDiceRepeatUsed());
			matchfield.setAdditionalDiceCount(players[i].getManagement().getAdditionalDiceCount());
			matchfield.setAdditionalDiceUsed(players[i].getManagement().getAdditionalDiceUsed());
		}
		
		returnBack.setDices(dices);

		return returnBack;
	}
}
