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

	/**
	 * This method increments "dice repeat count".
	 * 
	 * @author Joel Wolf
	 */
	public void incrementDiceRepeatCount() {

		diceRepeatCount++;
	}

	/**
	 * This method increments "additional dice count".
	 * 
	 * @author Joel Wolf
	 */
	public void incrementAdditionalDiceCount() {

		additionalDiceCount++;
	}

	/**
	 * This method increments "dice repeat used".
	 * 
	 * @author Joel Wolf
	 */
	public void incrementDiceRepeatUsed() {

		diceRepeatUsed++;
	}

	/**
	 * This method increments "additional dice used".
	 * 
	 * @author Joel Wolf
	 */
	public void incrementAdditionalDiceUsed() {

		additionalDiceUsed++;
	}
	
	/**
	 * This method determines the points by foxes from the player.
	 * 
	 * @author Joel Wolf
	 * @return the points by foxes
	 */
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

	/**
	 * This method determines the total points from the player.
	 * 
	 * @author Joel Wolf
	 * @return the total points
	 */
	public int determinePoints() {

		int points = 0;
		for (int i = 0; i < 5; i++) {

			points += colorAreas[i].determinePoints();
		}

		return points + determinePointsFoxes();
	}
	
	/**
	 * This method puts together the object of clickable from colored areas.
	 * 
	 * @author Joel Wolf
	 * @param dices
	 * @param area
	 * @param specialEvent
	 * @return the object of clickable from colored areas
	 */
	public IsClickable isClickable(Dice[] dices, Area area, SpecialEvent specialEvent) {

		IsClickable clickable = new IsClickable();
		
		switch(area) {
		case additionalDice:
			
			clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
			clickable.setBlue(getBlue().isClickable(dices[Color.blue.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
			clickable.setGreen(getGreen().isClickable(dices[Color.green.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
			clickable.setOrange(getOrange().isClickable(dices[Color.orange.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
			clickable.setPurple(getPurple().isClickable(dices[Color.purple.ordinal()].getValue(), 
					dices[Color.white.ordinal()].getValue()));
			
			break;
			
		case takeDiceFromTray:
			
			if(dices[Color.yellow.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray())
				clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			else if(dices[Color.yellow.ordinal()].isOnTray())
				clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
						dices[Color.yellow.ordinal()].getValue()));
			else if(dices[Color.white.ordinal()].isOnTray())
				clickable.setYellow(getYellow().isClickable(dices[Color.white.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if(dices[Color.blue.ordinal()].isOnTray() || dices[Color.white.ordinal()].isOnTray())
				clickable.setBlue(getBlue().isClickable(dices[Color.blue.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));

			if(dices[Color.green.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray())
				clickable.setGreen(getGreen().isClickable(dices[Color.green.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			else if(dices[Color.green.ordinal()].isOnTray())
				clickable.setGreen(getGreen().isClickable(dices[Color.green.ordinal()].getValue(), 
						dices[Color.green.ordinal()].getValue()));
			else if(dices[Color.white.ordinal()].isOnTray())
				clickable.setGreen(getGreen().isClickable(dices[Color.white.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if(dices[Color.orange.ordinal()].isOnTray() || dices[Color.white.ordinal()].isOnTray())
				clickable.setOrange(getOrange().isClickable(dices[Color.orange.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if(dices[Color.purple.ordinal()].isOnTray() && dices[Color.white.ordinal()].isOnTray())
				clickable.setPurple(getPurple().isClickable(dices[Color.purple.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			else if(dices[Color.purple.ordinal()].isOnTray())
				clickable.setPurple(getPurple().isClickable(dices[Color.purple.ordinal()].getValue(), 
						dices[Color.purple.ordinal()].getValue()));
			else if(dices[Color.white.ordinal()].isOnTray())
				clickable.setPurple(getPurple().isClickable(dices[Color.white.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			break;
			
		case specialEvent:
			
			switch(specialEvent) {
			case enterCrossYellow:
				
				boolean[] clickableYellow = getYellow().getFields();
				for(int j = 0; j < clickableYellow.length; j++) {
					
					if(clickableYellow[j] == true)
						clickableYellow[j] = false;
					else
						clickableYellow[j] = true;
				}
				
				clickable.setYellow(clickableYellow);
				
				break;
				
			case enterCrossBlue:
				
				boolean[] clickableBlue = getBlue().getFields();
				for(int j = 0; j < clickableBlue.length; j++) {
					
					if(clickableBlue[j] == true)
						clickableBlue[j] = false;
					else
						clickableBlue[j] = true;
				}
				
				clickable.setBlue(clickableBlue);
				
				break;
				
			case round4:
				
				clickableYellow = getYellow().getFields();
				for(int j = 0; j < clickableYellow.length; j++) {
					
					if(clickableYellow[j] == true)
						clickableYellow[j] = false;
					else
						clickableYellow[j] = true;
				}
				
				clickableBlue = getBlue().getFields();
				for(int j = 0; j < clickableBlue.length; j++) {
					
					if(clickableBlue[j] == true)
						clickableBlue[j] = false;
					else
						clickableBlue[j] = true;
				}
				
				boolean clickableGreen = false;
				if(getGreen().getFields() < 11)
					clickableGreen = true;
				
				boolean clickableOrange = false;
				if(getOrange().getFields()[10] == 0)
					clickableOrange = true;
				
				boolean clickablePurple = false;
				if(getPurple().getFields()[10] == 0)
					clickablePurple = true;
				
				clickable.setYellow(clickableYellow);
				clickable.setBlue(clickableBlue);
				clickable.setGreen(clickableGreen);
				clickable.setOrange(clickableOrange);
				clickable.setPurple(clickablePurple);
			
				break;
				
			default:
				break;
			}
			
			break;
				
		case rollDices:
			
			if(dices[Color.yellow.ordinal()].isOnTray() == false && dices[Color.yellow.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1)
				clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			else if(dices[Color.yellow.ordinal()].isOnTray() == false && dices[Color.yellow.ordinal()].getField() == -1)
				clickable.setYellow(getYellow().isClickable(dices[Color.yellow.ordinal()].getValue(), 
						dices[Color.yellow.ordinal()].getValue()));
			else if(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1)
				clickable.setYellow(getYellow().isClickable(dices[Color.white.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if((dices[Color.blue.ordinal()].isOnTray() == false && dices[Color.blue.ordinal()].getField() == -1) || 
					(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1))
				clickable.setBlue(getBlue().isClickable(dices[Color.blue.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if(dices[Color.green.ordinal()].isOnTray() == false && dices[Color.green.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1)
				clickable.setGreen(getGreen().isClickable(dices[Color.green.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			else if(dices[Color.green.ordinal()].isOnTray() == false && dices[Color.green.ordinal()].getField() == -1)
				clickable.setGreen(getGreen().isClickable(dices[Color.green.ordinal()].getValue(), 
						dices[Color.green.ordinal()].getValue()));
			else if(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1)
				clickable.setGreen(getGreen().isClickable(dices[Color.white.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if((dices[Color.orange.ordinal()].isOnTray() == false && dices[Color.orange.ordinal()].getField() == -1) || 
					(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1))
				clickable.setOrange(getOrange().isClickable(dices[Color.orange.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			if(dices[Color.purple.ordinal()].isOnTray() == false && dices[Color.purple.ordinal()].getField() == -1 && 
					dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1)
				clickable.setPurple(getPurple().isClickable(dices[Color.purple.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			else if(dices[Color.purple.ordinal()].isOnTray() == false && dices[Color.purple.ordinal()].getField() == -1)
				clickable.setPurple(getPurple().isClickable(dices[Color.purple.ordinal()].getValue(), 
						dices[Color.green.ordinal()].getValue()));
			else if(dices[Color.white.ordinal()].isOnTray() == false && dices[Color.white.ordinal()].getField() == -1)
				clickable.setPurple(getPurple().isClickable(dices[Color.white.ordinal()].getValue(), 
						dices[Color.white.ordinal()].getValue()));
			
			break;
			
		default:
			break;
		}

		return clickable;
	}

	/**
	 * This method is the recursive method to handle "special events".
	 * 
	 * @author Joel Wolf
	 * @param specialEvent
	 * @return a "special event"
	 */
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

		if (specialEvent != null)
			specialEvent = specialEventRecursive(specialEvent);
		
		return specialEvent;
	}

	/**
	 * This method forwards to enter crosses and numbers and handles "special events" recursively.
	 * 
	 * @author Joel Wolf
	 * @param area
	 * @param fieldId
	 * @param number
	 * @return a "special event"
	 */
	public SpecialEvent enterCrossOrNumber(Area area, int fieldId, int number) {

		List<SpecialEvent> specialEvents = null;
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
