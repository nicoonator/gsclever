package games.gsClever.Logic;

public class Points {

	private int[] points;
	private int playerId;
	
	public Points() {
		
		points = new int[7];
		playerId = -1;
	}
	
	public int getPoints(int index) {
		return points[index];
	}
	public void setPoints(int points, int index) {
		this.points[index] = points;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
