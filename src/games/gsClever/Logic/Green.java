package games.gsClever.Logic;

public class Green extends ColorArea {

	private int fields;
	private int fieldCount = 0;

	public Green() {
		fieldCount = 0;

	}

	public SpecialEvent enterCross() {
		fieldCount++;

		switch (fieldCount - 1) {
		case 0:
			fields++;
			break;
		case 1:
			fields++;
			break;
		case 2:
			fields++;
			break;
		case 3:
			fields++;
			return SpecialEvent.additionalDice;
		case 4:
			fields++;
			break;
		case 5:
			fields++;
			return SpecialEvent.enterCrossBlue;
		case 6:
			fields++;
			break;
		case 7:
			fields++;
			break;
		case 8:
			fields++;
			return SpecialEvent.enterPurple6;
		case 9:
			fields++;
			return SpecialEvent.diceRepeat;
		case 10:
			fields++;
			break;

		}
		return null;

	}

	public boolean getFox() {
		if (fields >= 7) {
			return true;
		} else
			return false;
	}

	public int determinePoints() {
		int a = 0;
		switch (fieldCount) {
		case 1:
			a = 1;
			break;
		case 2:
			a = 3;
			break;
		case 3:
			a = 6;
			break;
		case 4:
			a = 10;
			break;
		case 5:
			a = 15;
			break;
		case 6:
			a = 21;
			break;
		case 7:
			a = 28;
			break;
		case 8:
			a = 36;
			break;
		case 9:
			a = 45;
			break;
		case 10:
			a = 55;
			break;
		case 11:
			a = 66;
			break;
		}
		return a;
	}

	public boolean isClickable(int valueDiceGreen, int valueDiceWhite) {

		boolean field = false;
		switch (fields) {
		case 0:
			if (valueDiceGreen >= 1 || valueDiceWhite >= 1) {
				field = true;
			}
			return field;
		case 1:
			if (valueDiceGreen >= 2 || valueDiceWhite >= 2) {
				field= true;
			}
			return field;
		case 2:
			if (valueDiceGreen >= 3 || valueDiceWhite >= 3) {
				field = true;
			}
			return field;
		case 3:
			if (valueDiceGreen >= 4 || valueDiceWhite >= 4) {
				field = true;
			}
			return field;
		case 4:
			if (valueDiceGreen >= 5 || valueDiceWhite >= 5) {
				field = true;
			}
			return field;
		case 5:
			if (valueDiceGreen >= 1 || valueDiceWhite >= 1) {
				field = true;
			}
			return field;
		case 6:
			if (valueDiceGreen >= 2 || valueDiceWhite >= 2) {
				field = true;
			}
			return field;
		case 7:
			if (valueDiceGreen >= 3 || valueDiceWhite >= 3) {
				field = true;
			}
			return field;
		case 8:
			if (valueDiceGreen >= 4 || valueDiceWhite >= 4) {
				field = true;
			}
			return field;
		case 9:
			if (valueDiceGreen >= 5 || valueDiceWhite >= 5) {
				field = true;
			}
			return field;
		case 10:
			if (valueDiceGreen >= 6 || valueDiceWhite >= 6) {
				field = true;
			}
			return field;
		}

		return field;
	}

	/**
	 * @return the fields
	 */
	public int getFields() {
		return fields;
	}

	public boolean[] clickableDices(int valueDiceGreen, int valueDiceWhite) {
		boolean[] result = new boolean[2];
		for (int i = 0; i < 2; i++) {
			result[i] = false;
		}
		switch (fieldCount) {
		case 0:
			if (valueDiceGreen >= 1)
				result[0] = true;
			if (valueDiceWhite >= 1)
				result[1] = true;
			break;
		case 1:
			if (valueDiceGreen >= 2)
				result[0] = true;
			if (valueDiceWhite >= 2)
				result[1] = true;
			break;
		case 2:
			if (valueDiceGreen >= 3)
				result[0] = true;
			if (valueDiceWhite >= 3)
				result[1] = true;
			break;
		case 3:
			if (valueDiceGreen >= 4)
				result[0] = true;
			if (valueDiceWhite >= 4)
				result[1] = true;
			break;
		case 4:
			if (valueDiceGreen >= 5)
				result[0] = true;
			if (valueDiceWhite >= 5)
				result[1] = true;
			break;
		case 5:
			if (valueDiceGreen >= 1)
				result[0] = true;
			if (valueDiceWhite >= 1)
				result[1] = true;
			break;
		case 6:
			if (valueDiceGreen >= 2)
				result[0] = true;
			if (valueDiceWhite >= 2)
				result[1] = true;
			break;
		case 7:
			if (valueDiceGreen >= 3)
				result[0] = true;
			if (valueDiceWhite >= 3)
				result[1] = true;
			break;
		case 8:
			if (valueDiceGreen >= 4)
				result[0] = true;
			if (valueDiceWhite >= 4)
				result[1] = true;
			break;
		case 9:
			if (valueDiceGreen >= 5)
				result[0] = true;
			if (valueDiceWhite >= 5)
				result[1] = true;
			break;
		case 10:
			if (valueDiceGreen >= 6)
				result[0] = true;
			if (valueDiceWhite >= 6)
				result[1] = true;
			break;
		}
		return result;
	}

}
