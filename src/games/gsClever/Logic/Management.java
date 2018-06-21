package games.gsClever.Logic;

import games.gsClever.Exceptions.*;

public class Management {

	private int diceRepeatCount;
	private int diceRepeatUsed;
	private int additionalDiceCount;
	private int additionalDiceUsed;
	Yellow yellow = new Yellow();
	Blue blue = new Blue();
	Green green = new Green();
	Orange orange = new Orange();
	Purple purple = new Purple();
	
	public Management() {
		
		diceRepeatCount = 0;
		diceRepeatUsed = 0;
		additionalDiceCount = 0;
		additionalDiceUsed = 0;
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
		points[0] = yellow.determinePoints();
		points[1] = blue.determinePoints();
		points[2] = green.determinePoints();
		points[3] = orange.determinePoints();
		points[4] = purple.determinePoints();
		
		int lowestPoints = points[0];
		for(int i = 1; i < 5; i++) {
			
			if(points[i] < lowestPoints)
				lowestPoints = points[i];
		}
		
		int foxes = 0;
		if(yellow.getFox() == true)
			foxes++;
		if(blue.getFox() == true)
			foxes++;
		if(green.getFox() == true)
			foxes++;
		if(orange.getFox() == true)
			foxes++;
		if(purple.getFox() == true)
			foxes++;
		
		return points[0] + points[1] + points[2] + points[3] + points[4] + (foxes * lowestPoints);
	}
	
	public void useDiceRepeat() throws CannotUseDiceRepeatException {
		
		if(diceRepeatUsed < diceRepeatCount)
			diceRepeatUsed++;
		else
			throw new CannotUseDiceRepeatException();
	}
	
	public void useAdditionalDice() throws CannotUseAdditionalDiceException {
		
		if(additionalDiceUsed < additionalDiceCount)
			additionalDiceUsed++;
		else
			throw new CannotUseAdditionalDiceException();
	}
	
	public IsClickable isClickable() {
		
		IsClickable clickable = new IsClickable();
		
		boolean[] diceRepeat = new boolean[7];
		for(int i = 0; i < 7; i++) {
			
			if(diceRepeatCount > i && diceRepeatUsed <= i)
				diceRepeat[i] = true;
			else
				diceRepeat[i] = false;
		}
		clickable.setDiceRepeat(diceRepeat);
		
		boolean[] additionalDice = new boolean[7];
		for(int i = 0; i < 7; i++) {
			
			if(additionalDiceCount > i && additionalDiceUsed <= i)
				additionalDice[i] = true;
			else
				additionalDice[i] = false;
		}
		clickable.setAdditionalDice(additionalDice);
		
		clickable.setYellow(yellow.isClickable());
		clickable.setBlue(blue.isClickable());
		clickable.setGreen(green.isClickable());
		clickable.setOrange(orange.isClickable());
		clickable.setPurple(purple.isClickable());
		
		return clickable;
	}
	
	public void enterCrossOrNumber() {
		//TODO
	}
}
