package games.gsClever.Logic;

public class Orange extends ColorArea {

	private int[] fields = new int[11];
	private int fieldCount = 0;

	public Orange() {
		for (int i = 0; i < 11; i++) {
			fields[i] = 0;
		}
	}

	public SpecialEvent enterNumber(int number) {
		fieldCount++;

		switch (fieldCount - 1) {
		case 0:
			fields[0] = number;
			break;
		case 1:
			fields[1] = number;
			break;
		case 2:
			fields[2] = number;
			return SpecialEvent.diceRepeat;
		case 3:
			fields[3] = number * 2;
			break;
		case 4:
			fields[4] = number;
			return SpecialEvent.enterCrossYellow;
		case 5:
			fields[5] = number;
			return SpecialEvent.additionalDice;
		case 6:
			fields[6] = number * 2;
			break;
		case 7:
			fields[7] = number;
			break;
		case 8:
			fields[8] = number * 2;
			break;
		case 9:
			fields[9] = number;
			return SpecialEvent.enterPurple6;

		case 10:
			fields[10] = number * 3;
			break;

		}
		return null;

	}

	/**
	 * @return the fields
	 */
	public int[] getFields() {
		return fields;
	}

	public boolean getFox() {
		if (fields[7] != 0) {
			return true;

		} else
			return false;
	}

	public int determinePoints() {
		int summe = 0;
		for (int i = 0; i < fields.length; i++) {
			summe = summe + fields[i];
		}
		return summe;
	}

	public boolean isClickable(int valueDiceOrange, int valueDiceWhite) {

		if (fields[10]==0) {
			return true;	
		} else return false;

	}

}
