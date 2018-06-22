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
		colorAreas[0] = new Yellow();
		colorAreas[1] = new Blue();
		colorAreas[2] = new Green();
		colorAreas[3] = new Orange();
		colorAreas[4] = new Purple();
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
		
		clickable.setYellow(colorAreas[0].isClickable());
		clickable.setBlue(colorAreas[1].isClickable());
		clickable.setGreen(colorAreas[2].isClickable());
		clickable.setOrange(colorAreas[3].isClickable());
		clickable.setPurple(colorAreas[4].isClickable());
		
		return clickable;
	}
	
	public void enterCrossOrNumber() {
		//TODO
	}
	
	public Return click(Area area, int fieldId) {
		
		//TODO
		
		return null;
	}
}
