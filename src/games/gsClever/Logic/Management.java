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
	
	public int determinePoints() {
		
		int pointsYellow = yellow.determinePoints();
		int pointsBlue = blue.determinePoints();
		int pointsGreen = green.determinePoints();
		int pointsOrange = orange.determinePoints();
		int pointsPurple = purple.determinePoints();
		
		int lowestPoints = pointsYellow;
		if(pointsBlue < lowestPoints)
			lowestPoints = pointsBlue;
		if(pointsGreen < lowestPoints)
			lowestPoints = pointsGreen;
		if(pointsOrange < lowestPoints)
			lowestPoints = pointsOrange;
		if(pointsPurple < lowestPoints)
			lowestPoints = pointsPurple;
		
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
		
		return pointsYellow + pointsBlue + pointsGreen + pointsOrange + pointsPurple + (foxes * lowestPoints);
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
}
