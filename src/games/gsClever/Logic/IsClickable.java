package games.gsClever.Logic;

public class IsClickable {

	private boolean[] diceRepeat;
	private boolean[] additionalDice;
	private boolean[] yellow;
	private boolean[] blue;
	private boolean[] green;
	private boolean[] orange;
	private boolean[] purple;
	
	public IsClickable() {
		
		diceRepeat = new boolean[7];
		additionalDice = new boolean[7];
		yellow = new boolean[12];
		blue = new boolean[11];
		green = new boolean[11];
		orange = new boolean[11];
		purple = new boolean[11];
	}
	
	public boolean[] getDiceRepeat() {
		return diceRepeat;
	}

	public void setDiceRepeat(boolean[] diceRepeat) {
		this.diceRepeat = diceRepeat;
	}

	public boolean[] getAdditionalDice() {
		return additionalDice;
	}

	public void setAdditionalDice(boolean[] additionalDice) {
		this.additionalDice = additionalDice;
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

	public boolean[] getGreen() {
		return green;
	}

	public void setGreen(boolean[] green) {
		this.green = green;
	}

	public boolean[] getOrange() {
		return orange;
	}

	public void setOrange(boolean[] orange) {
		this.orange = orange;
	}

	public boolean[] getPurple() {
		return purple;
	}

	public void setPurple(boolean[] purple) {
		this.purple = purple;
	}
}
