package games.gsClever.Logic;

public class Dice {

	
	private String color;
	private int value;
	private boolean onTray;
	private int field;
	
	public Dice(String color, int value, boolean onTray, int field) {
		
		this.color= color;
		this.value = value;
		this.onTray= onTray;
		this.field = field;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isOnTray() {
		return onTray;
	}

	public void setOnTray(boolean onTray) {
		this.onTray = onTray;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}
	
	
	
	
}
