package games.gsClever.Logic;

public class DecisionMaker {

	private boolean colorDiceActive;
	private boolean whiteDiceActive;
	private Color colorOfDice;
	
	public DecisionMaker() {
		
		colorDiceActive = false;
		whiteDiceActive = false;
		colorOfDice = null;
	}

	public boolean isColorDiceActive() {
		return colorDiceActive;
	}

	public void setColorDiceActive(boolean colorDiceActive) {
		this.colorDiceActive = colorDiceActive;
	}

	public boolean isWhiteDiceActive() {
		return whiteDiceActive;
	}

	public void setWhiteDiceActive(boolean whiteDiceActive) {
		this.whiteDiceActive = whiteDiceActive;
	}

	public Color getColorOfDice() {
		return colorOfDice;
	}

	public void setColorOfDice(Color colorOfDice) {
		this.colorOfDice = colorOfDice;
	}
}
