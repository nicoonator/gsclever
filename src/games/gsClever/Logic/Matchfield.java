package games.gsClever.Logic;

public class Matchfield {

	private int playerId;
	private boolean[] yellow;
	private boolean[] blue;
	private int green;
	private int[] orange;
	private int[] purple;
	private int diceRepeatCount;
	private int diceRepeatUsed;
	private int additionalDiceCount;
	private int additionalDiceUsed;

	public Matchfield() {

		playerId = -1;
		yellow = null;
		blue = null;
		green = 0;
		orange = null;
		purple = null;
		diceRepeatCount = 0;
		diceRepeatUsed = 0;
		additionalDiceCount = 0;
		additionalDiceUsed = 0;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int[] getOrange() {
		return orange;
	}

	public void setOrange(int[] orange) {
		this.orange = orange;
	}

	public int[] getPurple() {
		return purple;
	}

	public void setPurple(int[] purple) {
		this.purple = purple;
	}

	public int getDiceRepeatCount() {
		return diceRepeatCount;
	}

	public void setDiceRepeatCount(int diceRepeatCount) {
		this.diceRepeatCount = diceRepeatCount;
	}

	public int getDiceRepeatUsed() {
		return diceRepeatUsed;
	}

	public void setDiceRepeatUsed(int diceRepeatUsed) {
		this.diceRepeatUsed = diceRepeatUsed;
	}

	public int getAdditionalDiceCount() {
		return additionalDiceCount;
	}

	public void setAdditionalDiceCount(int additionalDiceCount) {
		this.additionalDiceCount = additionalDiceCount;
	}

	public int getAdditionalDiceUsed() {
		return additionalDiceUsed;
	}

	public void setAdditionalDiceUsed(int additionalDiceUsed) {
		this.additionalDiceUsed = additionalDiceUsed;
	}
}
