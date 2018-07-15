package games.gsClever.Logic;

public class IsClickable {

	private int playerId;
	private boolean rollDices;
	private boolean[] yellow;
	private boolean[] blue;
	private boolean green;
	private boolean orange;
	private boolean purple;
	private boolean diceRepeat;
	private boolean additionalDice;
	private boolean ready;

	public IsClickable() {

		playerId = -1;
		rollDices = false;
		yellow = new boolean[12];
		for(int i = 0; i < 12; i++)
			yellow[i] = false;
		blue = new boolean[11];
		for(int i = 0; i < 11; i++)
			blue[i] = false;
		green = false;
		orange = false;
		purple = false;
		diceRepeat = false;
		additionalDice = false;
		ready = false;
	}

	public boolean[] getYellow() {
		return yellow;
	}

	public void setYellow(boolean[] yellow) {
		this.yellow = yellow;
	}

	public boolean[] getBlue() {
		return blue;
	}

	public void setBlue(boolean[] blue) {
		this.blue = blue;
	}

	public boolean getGreen() {
		return green;
	}

	public void setGreen(boolean green) {
		this.green = green;
	}

	public boolean getOrange() {
		return orange;
	}

	public void setOrange(boolean orange) {
		this.orange = orange;
	}

	public boolean getPurple() {
		return purple;
	}

	public void setPurple(boolean purple) {
		this.purple = purple;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public boolean isRollDices() {
		return rollDices;
	}

	public void setRollDices(boolean rollDices) {
		this.rollDices = rollDices;
	}

	public boolean isDiceRepeat() {
		return diceRepeat;
	}

	public void setDiceRepeat(boolean diceRepeat) {
		this.diceRepeat = diceRepeat;
	}

	public boolean isAdditionalDice() {
		return additionalDice;
	}

	public void setAdditionalDice(boolean additionalDice) {
		this.additionalDice = additionalDice;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	public boolean anythingClickable() {
		
		boolean clickable = false;
		
		if(rollDices)
			clickable = true;
		for(int i = 0; i < 12; i++) {
			if(yellow[i])
				clickable = true;
		}
		for(int i = 0; i < 11; i++) {
			if(blue[i])
				clickable = true;
		}
		if(green)
			clickable = true;
		if(orange)
			clickable = true;
		if(purple)
			clickable = true;
		if(diceRepeat)
			clickable = true;
		if(additionalDice)
			clickable = true;
		if(ready)
			clickable = true;
		
		return clickable;
	}
}
