package games.gsClever.Logic;

public class IsClickable {

	private int playerId;
	private boolean[] dices;
	private boolean[] yellow;
	private boolean[] blue;
	private boolean[] green;
	private boolean[] orange;
	private boolean[] purple;
	
	public IsClickable() {
		
		playerId = -1;
		dices = new boolean[6];
		yellow = new boolean[12];
		blue = new boolean[11];
		green = new boolean[11];
		orange = new boolean[11];
		purple = new boolean[11];
	}
	
	public boolean[] getDices() {
		return dices;
	}

	public void setDices(boolean[] dices) {
		this.dices = dices;
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

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
