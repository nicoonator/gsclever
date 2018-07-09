package games.gsClever.Logic;

import userManagement.User;

public class Player extends User {

	
	private Management management;
	
	public Player(User user) {
		super(user.getName(), "none");
		management = new Management();
	}

	


	public Management getManagement() {
		return management;
	}

}
