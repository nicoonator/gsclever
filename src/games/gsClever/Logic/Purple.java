package games.gsClever.Logic;

public class Purple extends ColorArea {

	private int[] fields = new int[11];
	private int fieldCount = 0;

	public Purple() {
		for (int i = 0; i < 12; i++) {
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
			fields[3] = number;
			return SpecialEvent.enterCrossBlue;
		case 4:
			fields[4] = number;
			return SpecialEvent.additionalDice;
		case 5:
			fields[5] = number;
			return SpecialEvent.enterCrossYellow;
		case 6:
			fields[6] = number;
			break;
		case 7:
			fields[7] = number;
			return SpecialEvent.diceRepeat;
		case 8:
			fields[8] = number;
			return SpecialEvent.enterCrossGreen;
		case 9:
			fields[9] = number;
			return SpecialEvent.enterOrange6;
		case 10:
			fields[10] = number;
			return SpecialEvent.additionalDice;
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
		if (fields[6] != 0) {
			return true;

		} else
			return false;
	}

	public boolean isClickable(int valueDicePurple, int valueDiceWhite) {

		boolean field = false;
		int p = 0;

		for (int m = 0; m < 11; m++) {
			if (fields[m] == 0) {
				p = m;
				break;
			}

		}
		if (p == 0 || fields[p - 1] == 6) {
			field = true;
		} else {
			if (fields[p - 1] < valueDicePurple || fields[p - 1] < valueDiceWhite)
				field = true;

		}
		return field;
	}

	public int determinePoints() {
		int summe = 0;
		for (int i = 0; i >= fieldCount; i++) {
			summe = summe + fields[i];
		}
		return summe;

	}

	public boolean[] clickableDices(int valueDicePurple, int valueDiceWhite) {
		boolean[] result = new boolean[2];
		for (int i = 0; i < 2; i++) {
			result[i] = false;
		}
		switch (fieldCount) {
		case 0:
			if (valueDicePurple >= 1)
				result[0] = true;
			if (valueDiceWhite >= 1)
				result[1] = true;
			break;
		case 1:
			if (valueDicePurple >= fields[0] || fields[0] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[0] || fields[0] == 6)
				result[1] = true;
			break;
		case 2:
			if (valueDicePurple >= fields[1] || fields[1] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[1] || fields[1] == 6)
				result[1] = true;
			break;
		case 3:
			if (valueDicePurple >= fields[2] || fields[2] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[2] || fields[2] == 6)
				result[1] = true;
			break;
		case 4:
			if (valueDicePurple >= fields[3] || fields[3] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[3] || fields[3] == 6)
				result[1] = true;
			break;
		case 5:
			if (valueDicePurple >= fields[4] || fields[4] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[4] || fields[4] == 6)
				result[1] = true;
			break;
		case 6:
			if (valueDicePurple >= fields[5] || fields[5] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[5] || fields[5] == 6)
				result[1] = true;
			break;
		case 7:
			if (valueDicePurple >= fields[6] || fields[6] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[6] || fields[6] == 6)
				result[1] = true;
			break;
		case 8:
			if (valueDicePurple >= fields[7] || fields[7] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[7] || fields[7] == 6)
				result[1] = true;
			break;
		case 9:
			if (valueDicePurple >= fields[8] || fields[8] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[8] || fields[8] == 6)
				result[1] = true;
			break;
		case 10:
			if (valueDicePurple >= fields[9] || fields[9] == 6)
				result[0] = true;
			if (valueDiceWhite >= fields[9] || fields[9] == 6)
				result[1] = true;
			break;
		}
		return result;
	}
}
