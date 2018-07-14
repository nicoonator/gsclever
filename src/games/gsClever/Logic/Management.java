package games.gsClever.Logic;

import java.util.ArrayList;
import java.util.List;

public class Management {

	private int diceRepeatCount;
	private int diceRepeatUsed;
	private int additionalDiceCount;
	private int additionalDiceUsed;
	private ColorArea[] colorAreas;

	public Management() {

		diceRepeatCount = 0;
		diceRepeatUsed = 0;
		additionalDiceCount = 0;
		additionalDiceUsed = 0;

		colorAreas = new ColorArea[5];
		colorAreas[Color.yellow.ordinal()] = new Yellow();
		colorAreas[Color.blue.ordinal()] = new Blue();
		colorAreas[Color.green.ordinal()] = new Green();
		colorAreas[Color.orange.ordinal()] = new Orange();
		colorAreas[Color.purple.ordinal()] = new Purple();
	}

	public Yellow getYellow() {
		return (Yellow) colorAreas[Color.yellow.ordinal()];
	}

	public Blue getBlue() {
		return (Blue) colorAreas[Color.blue.ordinal()];
	}

	public Green getGreen() {
		return (Green) colorAreas[Color.green.ordinal()];
	}

	public Orange getOrange() {
		return (Orange) colorAreas[Color.orange.ordinal()];
	}

	public Purple getPurple() {
		return (Purple) colorAreas[Color.purple.ordinal()];
	}

	public int getDiceRepeatCount() {
		return diceRepeatCount;
	}

	public int getDiceRepeatUsed() {
		return diceRepeatUsed;
	}

	public int getAdditionalDiceCount() {
		return additionalDiceCount;
	}

	public int getAdditionalDiceUsed() {
		return additionalDiceUsed;
	}

	public void incrementDiceRepeatCount() {

		diceRepeatCount++;
	}

	public void incrementAdditionalDiceCount() {

		additionalDiceCount++;
	}

	public void incrementDiceRepeatUsed() {

		diceRepeatUsed++;
	}

	public void incrementAdditionalDiceUsed() {

		additionalDiceUsed++;
	}
	
	public int determinePointsFoxes() {
		
		int[] points = new int[5];
		for (int i = 0; i < 5; i++) {

			points[i] = colorAreas[i].determinePoints();
		}
		
		int lowestPoints = points[0];
		for (int i = 1; i < 5; i++) {

			if (points[i] < lowestPoints)
				lowestPoints = points[i];
		}

		int foxes = 0;
		for (int i = 0; i < 5; i++) {

			if (colorAreas[i].getFox() == true)
				foxes++;
		}
		
		return foxes * lowestPoints;
	}

	public int determinePoints() {

		int points = 0;
		for (int i = 0; i < 5; i++) {

			points += colorAreas[i].determinePoints();
		}

		return points + determinePointsFoxes();
	}

	public IsClickable isClickable(Dice[] dices, Area currentArea) {

		IsClickable clickable = new IsClickable();

		if((dices[Color.yellow.ordinal()].isOnTray() == false && dices[Color.yellow.ordinal()].getField() == -1 && 
				dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1) || 
				currentArea == Area.additionalDice)
			clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
		else if(dices[Color.yellow.ordinal()].isOnTray() == true || 
				dices[Color.yellow.ordinal()].getField() != -1)
			clickable.setYellow(getYellow().isClickable(dices[Color.white.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
		else if(dices[Color.white.ordinal()].isOnTray() == true || 
				dices[Color.white.ordinal()].getField() != -1)
			clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
					dices[Color.yellow.ordinal()].getValue()));
		
		if((dices[Color.blue.ordinal()].isOnTray() == false && dices[Color.blue.ordinal()].getField() == -1) || 
				(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1) || 
				currentArea == Area.additionalDice)
			clickable.setBlue(getBlue().isClickable(dices[Color.blue.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
		
		if((dices[Color.green.ordinal()].isOnTray() == false && dices[Color.green.ordinal()].getField() == -1) || 
				(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1) || 
				currentArea == Area.additionalDice)
		clickable.setGreen(getGreen().isClickable(dices[Color.green.ordinal()].getValue(), 
				dices[Color.white.ordinal()].getValue()));
		
		if((dices[Color.orange.ordinal()].isOnTray() == false && dices[Color.orange.ordinal()].getField() == -1) || 
				(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1) || 
				currentArea == Area.additionalDice)
		clickable.setOrange(getOrange().isClickable(dices[Color.orange.ordinal()].getValue(), 
				dices[Color.white.ordinal()].getValue()));
		
		if((dices[Color.purple.ordinal()].isOnTray() == false && dices[Color.purple.ordinal()].getField() == -1) || 
				(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1) || 
				currentArea == Area.additionalDice)
		clickable.setPurple(getPurple().isClickable(dices[Color.purple.ordinal()].getValue(), 
				dices[Color.white.ordinal()].getValue()));

		return clickable;
	}

	public SpecialEvent specialEventRecursive(SpecialEvent specialEvent) {

		switch (specialEvent) {
		case diceRepeat:

			diceRepeatCount++;
			specialEvent = null;

			break;

		case additionalDice:

			additionalDiceCount++;
			specialEvent = null;

			break;

		case enterCrossYellow:

			return SpecialEvent.enterCrossYellow;

		case enterCrossBlue:

			return SpecialEvent.enterCrossBlue;
			
		case enterCrossGreen:

			specialEvent = getGreen().enterCross();

			break;

		case enterOrange4:

			specialEvent = getOrange().enterNumber(4);

			break;

		case enterOrange5:

			specialEvent = getOrange().enterNumber(5);

			break;

		case enterOrange6:

			specialEvent = getOrange().enterNumber(6);

			break;

		case enterPurple6:

			specialEvent = getPurple().enterNumber(6);

			break;
			
		default:
			break;
		}

		//TODO auswerten
		
		if (specialEvent != null)
			specialEvent = specialEventRecursive(specialEvent);
		
		return specialEvent;
	}

	public SpecialEvent enterCrossOrNumber(Area area, int fieldId, int number) {

		List<SpecialEvent> specialEvents = null; //TODO mehrere SpecialEvent gleichzeitig in gelb oder blau
		SpecialEvent specialEvent = null;

		switch (area) {
		case yellow:

			specialEvents = getYellow().enterCross(fieldId);

			break;

		case blue:

			specialEvents = getBlue().enterCross(fieldId);

			break;

		case green:

			specialEvent = getGreen().enterCross();

			break;

		case orange:

			specialEvent = getOrange().enterNumber(number);

			break;

		case purple:

			specialEvent = getPurple().enterNumber(number);

			break;
			
		default:
			break;
		}
		
		if(specialEvent != null) {
			
			specialEvents = new ArrayList<SpecialEvent>();	
			specialEvents.add(specialEvent);
		}
		
		SpecialEvent result = null;

		if(specialEvents != null) {
		
			for(SpecialEvent se : specialEvents) {
			
				specialEvent = specialEventRecursive(se);
				
				if(specialEvent != null)
					result = specialEvent;
			}
		}

		return result;
	}
}
