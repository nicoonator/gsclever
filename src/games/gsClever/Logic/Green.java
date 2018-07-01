package games.gsClever.Logic;

public class Green extends ColorArea {

	private int fields;
	private int fieldCount = 0;

	public Green() {
		fieldCount = 0;

	}

	public SpecialEvent enterCross(int number) {

		fieldCount++;

		switch (fieldCount - 1) {
		case 0:
			fields = number;
			break;
		case 1:
			fields = number;
			break;
		case 2:
			fields = number;
			break;
		case 3:
			fields = number;
			return SpecialEvent.additionalDice;
		case 4:
			fields = number;
			break;
		case 5:
			fields = number;
			return SpecialEvent.enterCrossBlue;
		case 6:
			fields = number;
			break;
		case 7:
			fields = number;
			break;
		case 8:
			fields = number;
			return SpecialEvent.enterPurple6;
		case 9:
			fields = number;
			return SpecialEvent.diceRepeat;
		case 10:
			fields = number;
			break;

		}
		return null;

	}

	public boolean getFox() {
		if (fields >= 7) {
			return true;// TODO Auto-generated method stub
		} else
			return false;
	}

	public int determinePoints() {
		// TODO Auto-generated method stub
		int a = 0;
		switch (fieldCount) {

		case 1:
			a = 1;
			return 1;
		case 2:
			a = 3;
			return 3;
		case 3:
			a = 6;
			return 6;
		case 4:
			a = 10;
			return 10;
		case 5:
			a = 15;
			return 15;
		case 6:
			a = 21;
			return 21;
		case 7:
			a = 28;
			return 28;
		case 8:
			a = 36;
			return 36;
		case 9:
			a = 45;
			return 45;
		case 10:
			a = 55;
			return 55;
		case 11:
			a = 66;
			return 66;
		}
		return 0;
	}

	public boolean[] isClickable(int valueDiceGreen, int valueDiceWhite) {

		boolean[] field = new boolean[11];

		for (int i = 0; i < 11; i++) {
			field[i] = false;

			switch (fields) {
			case 0:
				if (valueDiceGreen >= 1 || valueDiceWhite >= 1) {
					field[fields] = true;
				}
				return field;
			case 1:
				if (valueDiceGreen >= 2 || valueDiceWhite >= 2) {
					field[fields] = true;
				}
				return field;

			case 2:
				if (valueDiceGreen >= 3 || valueDiceWhite >= 3) {
					field[fields] = true;
				}
				return field;

			case 3:
				if (valueDiceGreen >= 4 || valueDiceWhite >= 4) {
					field[fields] = true;
				}
				return field;
			case 4:
				if (valueDiceGreen >= 5 || valueDiceWhite >= 5) {
					field[fields] = true;
				}
				return field;

			case 5:
				if (valueDiceGreen >= 1 || valueDiceWhite >= 1) {
					field[fields] = true;
				}
				return field;

			case 6:
				if (valueDiceGreen >= 2 || valueDiceWhite >= 2) {
					field[fields] = true;
				}
				return field;

			case 7:
				if (valueDiceGreen >= 3 || valueDiceWhite >= 3) {
					field[fields] = true;
				}
				return field;

			case 8:
				if (valueDiceGreen >= 4 || valueDiceWhite >= 4) {
					field[fields] = true;
				}
				return field;

			case 9:
				if (valueDiceGreen >= 5 || valueDiceWhite >= 5) {
					field[fields] = true;
				}
				return field;

			case 10:
				if (valueDiceGreen >= 6 || valueDiceWhite >= 6) {
					field[fields] = true;
				}
				return field;

			}

		}

		return field;
	}
}
