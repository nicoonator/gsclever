package games.gsClever.Logic;

public class Dice {

	
	private String color;
	private int value;
	private boolean onTray;
	private int field;
	
	public Dice(String color ) {
		
		this.color= color;
		value = 1;
		onTray= false;
		field = -1;
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
