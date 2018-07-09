package games.gsClever.Logic;

public class Blue extends ColorArea {
	private boolean[] fields = new boolean[12];

	public Blue() {

		for (int i = 0; i < 12; i++) {

			fields[i] = false;

		}
	}

	public boolean[] getFields() {
		return fields;

	}

	public SpecialEvent enterCross(int fieldId) {

		fields[fieldId] = true;

		if ((fields[0] == true && fields[1] == true && fields[2] == true)
				&& (fieldId == 0 || fieldId == 1 || fieldId == 2)) {
			return SpecialEvent.enterOrange5;
		}
		if ((fields[3] == true && fields[4] == true && fields[5] == true && fields[6] == true)
				&& (fieldId == 3 || fieldId == 4 || fieldId == 5 || fieldId == 6)) {
			return SpecialEvent.enterCrossYellow;
		}
		if ((fields[3] == true && fields[7] == true) && (fieldId == 3 || fieldId == 7)) {
			return SpecialEvent.diceRepeat;
		}
		if ((fields[0] == true && fields[4] == true && fields[8] == true)
				&& (fieldId == 0 || fieldId == 4 || fieldId == 8)) {
			return SpecialEvent.enterCrossGreen;
		}
		if ((fields[1] == true && fields[5] == true && fields[9] == true)
				&& (fieldId == 1 || fieldId == 5 || fieldId == 9)) {
			return SpecialEvent.enterPurple6;
		}
		if ((fields[2] == true && fields[6] == true && fields[10] == true)
				&& (fieldId == 2 || fieldId == 6 || fieldId == 10)) {
			return SpecialEvent.additionalDice;
		}
		return null;

	}

	public boolean getFox() {
		if (fields[7] == true && fields[8] == true && fields[9] == true && fields[10] == true) {
			return true;
		} else
			return false;
	}

	public boolean[] isClickable(int valueDiceBlue, int valueDiceWhite) {

		int sum = valueDiceBlue + valueDiceWhite;

		boolean[] field = new boolean[12];

		for (int i = 0; i < 12; i++) {
			field[i] = false;
		}
		if (field[sum - 2] == false) {
			field[sum - 2] = true;
		}

		return field;
	}

	public int determinePoints() {
		int sum = 0;
		for (int i = 0; i < 11; i++) {
			if (fields[i] == true) {
				sum = sum + 1;
			}
		}
		switch (sum) {

		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 4;
		case 4:
			return 7;
		case 5:
			return 11;
		case 6:
			return 16;
		case 7:
			return 22;
		case 8:
			return 29;
		case 9:
			return 37;
		case 10:
			return 46;
		case 11:
			return 56;
		}
		return 0;

	}

}
