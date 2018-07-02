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
		for(int i = 0; i < playerCount; i++) {
			ready[i] = false;
		}

		if (currentPlayer == 0)
			startRound();

		// TODO

		if (currentPlayer < playerCount - 1)
			currentPlayer++;
		else
			currentPlayer = 0;
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

		// TODO
		}

		// TODO

		return false;
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

	public Return click(int playerId, Area area, int fieldId) throws Exception {

		Return returnBack = new Return(currentPlayer, round, playerCount);

		switch(area) {
		case dices:

			int diceField = 0;
			for (Dice dice : dices) {

				if (dice.getField() >= diceField)
					diceField = dice.getField() + 1;
			}

			dices[fieldId].setField(diceField);

			for (Dice dice : dices) {

				if (dice.getField() == -1 && dice.getValue() < dices[fieldId].getValue())
					dice.setOnTray(true);
			}

			switch(dices[fieldId].getColor()) {
			case yellow:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, currentFieldId[playerId], dices[Color.yellow.ordinal()].getValue());

				break;

			case blue:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.blue, currentFieldId[playerId], dices[Color.blue.ordinal()].getValue() + 
						dices[Color.white.ordinal()].getValue());

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

				break;

			case green:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.green, currentFieldId[playerId], dices[Color.green.ordinal()].getValue());

				break;

			case orange:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.orange, currentFieldId[playerId], dices[Color.orange.ordinal()].getValue());

				break;

			case purple:

				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.purple, currentFieldId[playerId], dices[Color.purple.ordinal()].getValue());

				break;
			}
			
			if(currentSpecialEvent[playerId] == SpecialEvent.enterCrossYellow || 
					currentSpecialEvent[playerId] == SpecialEvent.enterCrossBlue) {
				
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
			
			returnBack.getClickable(playerId).setDices(additionalDices);
			
			nextArea[playerId] = Area.dices;

			break;

		case specialEvent:

			switch(currentSpecialEvent[playerId]) {
			case enterCrossYellow:
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, fieldId, 0);
				
				break;
				
			case enterCrossBlue:
				
				currentSpecialEvent[playerId] = players[playerId].getManagement().enterCrossOrNumber(
						Area.yellow, fieldId, 0);
				
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
			
			returnBack.getClickable(playerId).setDices(
					players[playerId].getManagement().getYellow().clickableDices(
					dices[Color.yellow.ordinal(), dices[Color.white.ordinal()]));
			
			//TODO
			
			nextArea[playerId] = Area.dices;

			break;

		case blue:

			currentFieldId[playerId] = fieldId;
			
			returnBack.getClickable(playerId).setDices(
					players[playerId].getManagement().getYellow().clickableDices(
					dices[Color.blue.ordinal(), dices[Color.white.ordinal()]));
			
			// TODO
			
			nextArea[playerId] = Area.dices;

			break;

		case green:
			
			currentFieldId[playerId] = fieldId;

			returnBack.getClickable(playerId).setDices(
					players[playerId].getManagement().getYellow().clickableDices(
					dices[Color.green.ordinal(), dices[Color.white.ordinal()]));
			
			// TODO
			
			nextArea[playerId] = Area.dices;

			break;

		case orange:
			
			currentFieldId[playerId] = fieldId;

			returnBack.getClickable(playerId).setDices(
					players[playerId].getManagement().getYellow().clickableDices(
					dices[Color.orange.ordinal(), dices[Color.white.ordinal()]));
			
			// TODO
			
			nextArea[playerId] = Area.dices;

			break;

		case purple:
			
			currentFieldId[playerId] = fieldId;

			returnBack.getClickable(playerId).setDices(
					players[playerId].getManagement().getYellow().clickableDices(
					dices[Color.purple.ordinal(), dices[Color.white.ordinal()]));
			
			// TODO
			
			nextArea[playerId] = Area.dices;

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
				
				//TODO nächster Spieler
			}
		}

		for(int i = 0; i < playerCount; i++) {

			returnBack.setClickable(players[i].getManagement().isClickable(), i);
			returnBack.getClickable(i).setPlayerId(i);
			returnBack.getClickable(i).setRollDices(false);// TODO

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
