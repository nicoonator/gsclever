package games.gsClever.Logic;

import java.util.*;

import games.gsClever.Exceptions.*;
import userManagement.User;

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
		this.playerCount = userList.size();
		additionalDiceTime = false;
		currentPlayer = 0;
		
		currentFieldId = new int[userList.size()];
		currentArea = new Area[userList.size()];
		nextArea = new Area[userList.size()];
		currentSpecialEvent = new SpecialEvent[userList.size()];
		ready = new boolean[userList.size()];

		dices = new Dice[6];
		dices[0] = new Dice(Color.yellow);
		dices[1] = new Dice(Color.blue);
		dices[2] = new Dice(Color.white);
		dices[3] = new Dice(Color.green);
		dices[4] = new Dice(Color.orange);
		dices[5] = new Dice(Color.purple);

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
	
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public boolean nextPlayer() {

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
			
			for(int i = 0; i < playerCount; i++) {
			
				currentSpecialEvent[i] = SpecialEvent.round4;
					
				nextArea[i] = Area.specialEvent;
			}
			
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
	
	public void diceAvailable() {
		
		boolean diceAvailable = false;
		
		for(int i = 0; i < 6; i++) {
			
			if(dices[i].getField() == -1 && dices[i].isOnTray() == false) {
				
				diceAvailable = true;
				
				break;
			}
		}
		
		if(diceAvailable == false)
			additionalDiceTime = true;
	}
	
	public void clickableSpecialEvent(Return returnBack, int playerId) {
		
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
			
		case round4:
			
			clickableYellow = players[playerId].getManagement().getYellow().getFields();
			for(int i = 0; i < clickableYellow.length; i++) {
				
				if(clickableYellow[i] == true)
					clickableYellow[i] = false;
				else
					clickableYellow[i] = true;
			}
			
			clickableBlue = players[playerId].getManagement().getBlue().getFields();
			for(int i = 0; i < clickableBlue.length; i++) {
				
				if(clickableBlue[i] == true)
					clickableBlue[i] = false;
				else
					clickableBlue[i] = true;
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
			if(diceField >= 2)
				putSmallerDicesOnTray(7);

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
			
			diceAvailable();
			
			if(currentSpecialEvent[playerId] != null) {
				
				clickableSpecialEvent(returnBack, playerId);
				
				nextArea[playerId] = Area.specialEvent;
			}
			else if(currentPlayer == playerId) {
				
				returnBack.getClickable(playerId).setRollDices(true);
				
				nextArea[playerId] = Area.rollDices;
			}
			
			break;
			
		case rollDices:
			
			List<Dice> dicesToRoll = new ArrayList<Dice>();
			
			for(Dice dice : this.dices) {
				
				if(dice.isOnTray() == false && dice.getField() == -1)
					dicesToRoll.add(dice);
			}
			
			rollDices(dicesToRoll);
			
			returnBack.setClickable(players[currentPlayer].getManagement().isClickable(), currentPlayer);
			
			if(players[currentPlayer].getManagement().getDiceRepeatUsed() < 
					players[currentPlayer].getManagement().getDiceRepeatCount())
				returnBack.getClickable(currentPlayer).setDiceRepeat(true);
			
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

			returnBack.setClickable(players[playerId].getManagement().isClickable(), playerId);
				
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
				
			case round4:
				
				//"fieldId" = fieldId + Color * 100
				if(fieldId >= Color.yellow.ordinal() * 100 && fieldId < Color.yellow.ordinal() * 100 + 100) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.yellow, fieldId - Color.yellow.ordinal() * 100, 6);
				}
				
				if(fieldId >= Color.blue.ordinal() * 100 && fieldId < Color.blue.ordinal() * 100 + 100) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.blue, fieldId - Color.blue.ordinal() * 100, 6);
				}
				
				if(fieldId >= Color.green.ordinal() * 100 && fieldId < Color.green.ordinal() * 100 + 100) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.green, fieldId - Color.green.ordinal() * 100, 6);
				}
				
				if(fieldId >= Color.orange.ordinal() * 100 && fieldId < Color.orange.ordinal() * 100 + 100) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.orange, fieldId - Color.orange.ordinal() * 100, 6);
				}
				
				if(fieldId >= Color.purple.ordinal() * 100 && fieldId < Color.purple.ordinal() * 100 + 100) {
					
					currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
							Area.purple, fieldId - Color.purple.ordinal() * 100, 6);
				}
				
				break;
				
			default:
				break;
			}
			
			if(currentSpecialEvent[playerId] != null) {
				
				clickableSpecialEvent(returnBack, playerId);
				
				nextArea[playerId] = Area.specialEvent;
			}
			else if(currentPlayer == playerId) {
				
				returnBack.getClickable(playerId).setRollDices(true);
				
				nextArea[playerId] = Area.rollDices;
			}
			
			break;

		case yellow:

			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getYellow().clickableDices(
					dices[Color.yellow.ordinal()], dices[Color.white.ordinal()], fieldId);
			
			if(dices[Color.yellow.ordinal()].getValue() == dices[Color.white.ordinal()].getValue() && 
					dices[Color.yellow.ordinal()].getField() == -1 && 
					dices[Color.yellow.ordinal()].isOnTray() == false && 
					dices[Color.white.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.yellow);
				
				nextArea[playerId] = Area.decisionMaker;
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
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
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
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			diceAvailable();
			
			break;

		case blue:

			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getBlue().clickableDices(
					dices[Color.blue.ordinal()], dices[Color.white.ordinal()], fieldId);
			
			if(dices[Color.blue.ordinal()].getField() == -1 && 
					dices[Color.blue.ordinal()].isOnTray() == false && 
					dices[Color.white.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.blue);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(dices[Color.blue.ordinal()].getField() != -1 || 
					dices[Color.blue.ordinal()].isOnTray() == true ||
					clickableDices[0] == false) {
				
				dices[Color.white.ordinal()].setField(diceField);
				if(diceField >= 2)
					putSmallerDicesOnTray(7);
				
				putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, currentFieldId[playerId], dices[Color.blue.ordinal()].getValue() + 
						dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(dices[Color.white.ordinal()].getField() != -1 || 
					dices[Color.white.ordinal()].isOnTray() == true ||
					clickableDices[1] == false) {
				
				dices[Color.blue.ordinal()].setField(diceField);
				if(diceField >= 2)
					putSmallerDicesOnTray(7);
				
				putSmallerDicesOnTray(dices[Color.blue.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, currentFieldId[playerId], dices[Color.blue.ordinal()].getValue() + 
						dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			diceAvailable();
			
			break;

		case green:
			
			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getGreen().clickableDices(
					dices[Color.green.ordinal()], this.dices[Color.white.ordinal()]);

			if(clickableDices[0] && clickableDices[1] &&
					dices[Color.green.ordinal()].getField() == -1 && 
					dices[Color.green.ordinal()].isOnTray() == false && 
					dices[Color.white.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.green);
				
				nextArea[playerId] = Area.decisionMaker;
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
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
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
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			diceAvailable();
			
			break;

		case orange:
			
			currentFieldId[playerId] = fieldId;

			if(dices[Color.orange.ordinal()].getField() == -1 && 
					dices[Color.orange.ordinal()].isOnTray() == false && 
					dices[Color.white.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.orange);
				
				nextArea[playerId] = Area.decisionMaker;
			}
			else if(dices[Color.orange.ordinal()].getField() != -1 || 
					dices[Color.orange.ordinal()].isOnTray() == true) {
				
				dices[Color.white.ordinal()].setField(diceField);
				if(diceField >= 2)
					putSmallerDicesOnTray(7);
				
				putSmallerDicesOnTray(dices[Color.white.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.orange, currentFieldId[playerId], dices[Color.white.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			else if(dices[Color.white.ordinal()].getField() != -1 || 
					dices[Color.white.ordinal()].isOnTray() == true) {
	
				dices[Color.orange.ordinal()].setField(diceField);
				if(diceField >= 2)
					putSmallerDicesOnTray(7);
				
				putSmallerDicesOnTray(dices[Color.orange.ordinal()].getValue());
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.orange, currentFieldId[playerId], dices[Color.orange.ordinal()].getValue());
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			diceAvailable();
			
			break;

		case purple:
			
			currentFieldId[playerId] = fieldId;
			
			clickableDices = players[playerId].getManagement().getPurple().clickableDices(
					dices[Color.purple.ordinal()], dices[Color.white.ordinal()]);
			
			if(clickableDices[0] && clickableDices[1] &&
					dices[Color.purple.ordinal()].getField() == -1 && 
					dices[Color.purple.ordinal()].isOnTray() == false && 
					dices[Color.white.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false) {
				
				returnBack.getDecisionMaker(playerId).setColorDiceActive(true);
				returnBack.getDecisionMaker(playerId).setWhiteDiceActive(true);
				returnBack.getDecisionMaker(playerId).setColorOfDice(Color.purple);
				
				nextArea[playerId] = Area.decisionMaker;
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
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
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
				
				if(currentSpecialEvent[playerId] != null) {
					
					clickableSpecialEvent(returnBack, playerId);
					
					nextArea[playerId] = Area.specialEvent;
				}
				else if(currentPlayer == playerId) {
					
					returnBack.getClickable(playerId).setRollDices(true);
					
					nextArea[playerId] = Area.rollDices;
				}
			}
			
			diceAvailable();

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
				
				returnBack.getClickable(currentPlayer).setRollDices(true);
				
				nextArea[currentPlayer] = Area.rollDices;
			}
		}

		for(int i = 0; i < playerCount; i++) {

			if(currentSpecialEvent[i] == SpecialEvent.round4) {
				
				clickableSpecialEvent(returnBack, playerId);
				
				nextArea[i] = Area.specialEvent;
			}
			
			returnBack.getClickable(i).setPlayerId(i);
			
			if(players[i].getManagement().getAdditionalDiceUsed() < 
					players[i].getManagement().getAdditionalDiceCount() && additionalDiceTime)
				returnBack.getClickable(i).setAdditionalDice(true);
			
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

		return returnBack;
	}
}
