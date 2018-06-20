package games.gsClever.Logic;

import games.gsClever.Exceptions.*;

public class Management {

	private int diceRepeatCount;
	private int diceRepeatUsed;
	private int additionalDiceCount;
	private int additionalDiceUsed;
	
	public int determinePoints() {
		
		//TODO
		
		return 0;
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
