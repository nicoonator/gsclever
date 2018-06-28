package games.gsClever.Logic;

import userManagement.User;

public class Player extends User {

	public Player(String name, String pw, int id) {
		super(name, pw);
		this.id = id;
	}

	private int id;

	Management management = new Management();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Management getManagement() {
		return management;
	}

}
