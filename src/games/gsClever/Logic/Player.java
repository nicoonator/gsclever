package games.gsClever.Logic;

public class Player {

	private int id;
	private String name;
	
	Management management= new Management();
	
	public Player(int id, String name) {
		
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Management getManagement() {
		return management;
	}
	
	
	
}
