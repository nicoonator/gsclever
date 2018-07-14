package games.gsClever.Logic;

import java.util.List;

public class Winner {

	private List<Integer> winners;
	private Points[] points;

	public Winner(int playerCount) {
		
		winners = null;
		points = new Points[playerCount];
		for(int i = 0; i < playerCount; i++) {
			points[i] = new Points();
		}
	}
	
	public List<Integer> getWinners() {
		return winners;
	}

	public void setWinners(List<Integer> winners) {
		this.winners = winners;
	}

	public Points getPoints(int playerId) {
		return points[playerId];
	}

	public void setPoints(Points points, int playerId) {
		this.points[playerId] = points;
	}
	
	
}
