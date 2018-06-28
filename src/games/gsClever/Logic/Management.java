package games.gsClever.Logic;

import games.gsClever.Exceptions.*;

public class Management {

	private int diceRepeatCount;
	private int diceRepeatUsed;
	private int additionalDiceCount;
	private int additionalDiceUsed;
	ColorArea[] colorAreas;
	
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
		return (Yellow)colorAreas[Color.yellow.ordinal()];
	}

	public Blue getBlue() {
		return (Blue)colorAreas[Color.blue.ordinal()];
	}

	public Green getGreen() {
		return (Green)colorAreas[Color.green.ordinal()];
	}

	public Orange getOrange() {
		return (Orange)colorAreas[Color.orange.ordinal()];
	}

	public Purple getPurple() {
		return (Purple)colorAreas[Color.purple.ordinal()];
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
	
	public int determinePoints() {
		
		int[] points = new int[5];
		for(int i = 0; i < 5; i++) {
			
			points[i] = colorAreas[i].determinePoints();
		}
		
		int lowestPoints = points[0];
		for(int i = 1; i < 5; i++) {
			
			if(points[i] < lowestPoints)
				lowestPoints = points[i];
		}
		
		int foxes = 0;
		for(int i = 0; i < 5; i++) {
			
			if(colorAreas[i].getFox() == true)
				foxes++;
		}
		
		return points[0] + points[1] + points[2] + points[3] + points[4] + (foxes * lowestPoints);
	}
	
	public void useDiceRepeat() throws CannotUseDiceRepeatException {
		
		if(diceRepeatUsed < diceRepeatCount)
			diceRepeatUsed++;
		else
			throw new CannotUseDiceRepeatException();
		
		//TODO
	}
	
	public void useAdditionalDice() throws CannotUseAdditionalDiceException {
		
		if(additionalDiceUsed < additionalDiceCount)
			additionalDiceUsed++;
		else
			throw new CannotUseAdditionalDiceException();
		
		//TODO
	}
	
	public IsClickable isClickable() {
		
		IsClickable clickable = new IsClickable();
		
		clickable.setYellow(colorAreas[Color.yellow.ordinal()].isClickable());
		clickable.setBlue(colorAreas[Color.blue.ordinal()].isClickable());
		clickable.setGreen(colorAreas[Color.green.ordinal()].isClickable());
		clickable.setOrange(colorAreas[Color.orange.ordinal()].isClickable());
		clickable.setPurple(colorAreas[Color.purple.ordinal()].isClickable());
		
		return clickable;
	}
	
	public void specialEventRecursive(SpecialEvent specialEvent) {
		
		switch(specialEvent) {
		case diceRepeat:
			
			diceRepeatCount++;
			specialEvent = null;
			
			break;
			
		case additionalDice:
			
			additionalDiceCount++;
			specialEvent = null;
			
			break;
			
		case enterCrossYellow:
			
			specialEvent = null;//TODO
			
			break;
			
		case enterCrossBlue:
			
			specialEvent = null;//TODO
			
			break;
			
		case enterCrossGreen:
			
			specialEvent = colorAreas[Color.green.ordinal()].enterCross();
			
			break;
			
		case enterOrange4:
			
			specialEvent = colorAreas[Color.orange.ordinal()].enterNumber(4);
			
			break;
			
		case enterOrange5:
			
			specialEvent = colorAreas[Color.orange.ordinal()].enterNumber(5);
			
			break;
			
		case enterOrange6:
			
			specialEvent = colorAreas[Color.orange.ordinal()].enterNumber(6);
			
			break;
			
		case enterPurple6:
			
			specialEvent = colorAreas[Color.orange.ordinal()].enterNumber(6);
			
			break;
		}
		
		if(specialEvent != null)
			specialEventRecursive(specialEvent);
	}
	
	public SpecialEvent enterCrossOrNumber(Area area, int fieldId, int number) {
		
		SpecialEvent specialEvent;
		
		switch(area) {
		case yellow:
			
			specialEvent = colorAreas[Color.yellow.ordinal()].enterCross(fieldId);
			
			break;
			
		case blue:
			
			specialEvent = colorAreas[Color.blue.ordinal()].enterCross(fieldId);
			
			break;
			
		case green:
			
			specialEvent = colorAreas[Color.green.ordinal()].enterCross();
			
			break;
			
		case orange:
			
			specialEvent = colorAreas[Color.orange.ordinal()].enterNumber(number);
			
			break;
			
		case purple:
			
			specialEvent = colorAreas[Color.purple.ordinal()].enterNumber(number);
			
			break;
		}
		
		if(specialEvent != null)
			specialEventRecursive(specialEvent);
		
		return null;//TODO
	}
}
