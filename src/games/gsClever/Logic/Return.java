package games.gsClever.Logic;

public class Return {

	private int currentPlayer;
	private int round;
	private IsClickable[] clickable;
	private Matchfield[] matchfield;
	private Dice[] dices;

	public Return(int currentPlayer, int round, int playerCount) {

		this.currentPlayer = currentPlayer;
		this.round = round;

		clickable = new IsClickable[playerCount];
		matchfield = new Matchfield[playerCount];
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getRound() {
		return round;
	}

	public Matchfield getMatchfield(int playerId) {
		return matchfield[playerId];
	}

	public void setMatchfield(Matchfield matchfield, int playerId) {
		this.matchfield[playerId] = matchfield;
	}

	public IsClickable getClickable(int playerId) {
		return clickable[playerId];
	}

	public void setClickable(IsClickable clickable, int playerId) {
		this.clickable[playerId] = clickable;
	}

	public Dice[] getDices() {
		return dices;
	}

	public void setDices(Dice[] dices) {
		this.dices = dices;
	}
}
