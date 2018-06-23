package games.gsClever.Logic;

public class Return {

	private int currentPlayer;
	private IsClickable[] clickable;
	private Matchfield[] matchfield;
	
	public Return(int currentPlayer, int clickableCount, int playerCount) {
		
		this.currentPlayer = currentPlayer;
		
		clickable = new IsClickable[clickableCount];
		matchfield = new Matchfield[playerCount];
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public Matchfield getMatchfield(int index) {
		return matchfield[index];
	}

	public void setMatchfield(Matchfield matchfield, int index) {
		this.matchfield[index] = matchfield;
	}

	public IsClickable getClickable(int index) {
		return clickable[index];
	}

	public void setClickable(IsClickable clickable, int index) {
		this.clickable[index] = clickable;
	}
}
