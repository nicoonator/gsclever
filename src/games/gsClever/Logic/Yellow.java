package games.gsClever.Logic;

import java.util.ArrayList;
import java.util.List;

public class Yellow extends ColorArea {

	private boolean[] fields = new boolean[12];

	public Yellow() {

		for (int i = 0; i < 12; i++) {
			fields[i] = false;
		}
	}

	public boolean[] getFields() {
		boolean[] result = new boolean[12];
		for (int i = 0; i < 12; i++) {

			result[i] = fields[i];

		}
		return result;
	}

	public List<SpecialEvent> enterCross(int fieldId) {
		
		List<SpecialEvent> result= new ArrayList<SpecialEvent>();
		
		fields[fieldId] = true;

		if ((fields[0] == true && fields[1] == true && fields[2] == true)
				&& (fieldId == 0 || fieldId == 1 || fieldId == 2)) {
			result.add(SpecialEvent.enterCrossBlue);
		}
		if ((fields[3] == true && fields[4] == true && fields[5] == true)
				&& (fieldId == 3 || fieldId == 4 || fieldId == 5)) {
			result.add(SpecialEvent.enterOrange4);
		}
		if ((fields[6] == true && fields[7] == true && fields[8] == true)
				&& (fieldId == 6 || fieldId == 7 || fieldId == 8)) {
			result.add(SpecialEvent.enterCrossGreen);
		}
		if ((fields[0] == true && fields[4] == true && fields[7] == true && fields[11] == true)
				&& (fieldId == 0 || fieldId == 4 || fieldId == 7 || fieldId == 11)) {
			result.add(SpecialEvent.additionalDice);
		}
		return result;
	}

	public boolean getFox() {
		if (fields[9] == true && fields[10] == true && fields[11] == true) {
			return true;
		} else
			return false;
	}

	public boolean[] isClickable(int valueDiceYellow, int valueDiceWhite) {

		boolean[] field = new boolean[12];

		for (int i = 0; i < 12; i++) {
			field[i] = false;
		}

		if (valueDiceYellow == 1 || valueDiceWhite == 1) {
			if (fields[4] == false) {
				field[4] = true;
			}
			if (fields[6] == false) {
				field[6] = true;
			}
		}
		if (valueDiceYellow == 2 || valueDiceWhite == 2) {
			if (fields[3] == false) {
				field[3] = true;
			}
			if (fields[7] == false) {
				field[7] = true;
			}
		}
		if (valueDiceYellow == 3 || valueDiceWhite == 3) {
			if (fields[0] == false) {
				field[0] = true;
			}
			if (fields[9] == false) {
				field[9] = true;
			}
		}
		if (valueDiceYellow == 4 || valueDiceWhite == 4) {
			if (fields[8] == false) {
				field[8] = true;
			}
			if (fields[10] == false) {
				field[10] = true;
			}
		}
		if (valueDiceYellow == 5 || valueDiceWhite == 5) {
			if (fields[2] == false) {
				field[2] = true;
			}
			if (fields[5] == false) {
				field[5] = true;
			}
		}
		if (valueDiceYellow == 6 || valueDiceWhite == 6) {
			if (fields[1] == false) {
				field[1] = true;
			}
			if (fields[11] == false) {
				field[11] = true;
			}
		}

		return field;

	}

	public int determinePoints() {
		int points =0;
		if (fields[0] == true && fields[3] == true && fields[6] == true) {
			points+= 10;
		}
		if (fields[1] == true && fields[4] == true && fields[9] == true) {
			points+= 14;
		}
		if (fields[2] == true && fields[7] == true && fields[10] == true) {
			points+= 16;
		}
		if (fields[5] == true && fields[8] == true && fields[11] == true) {
			points+= 20;
		}
		return points;
	}

	public boolean[] clickableDices(int valueDiceYellow, int valueDiceWhite, int FieldID) {
		boolean[] result = new boolean[2];
		for (int i = 0; i < 2; i++) {
			result[i] = false;
		}
		switch (FieldID) {
		case 0:
			if (valueDiceYellow == 3)
				result[0] = true;
			if (valueDiceWhite == 3)
				result[1] = true;
			break;
		case 1:
			if (valueDiceYellow == 6)
				result[0] = true;
			if (valueDiceWhite == 6)
				result[1] = true;
			break;
		case 2:
			if (valueDiceYellow == 5)
				result[0] = true;
			if (valueDiceWhite == 5)
				result[1] = true;
			break;
		case 3:
			if (valueDiceYellow == 2)
				result[0] = true;
			if (valueDiceWhite == 2)
				result[1] = true;
			break;
		case 4:
			if (valueDiceYellow == 1)
				result[0] = true;
			if (valueDiceWhite == 1)
				result[1] = true;
			break;
		case 5:
			if (valueDiceYellow == 5)
				result[0] = true;
			if (valueDiceWhite == 5)
				result[1] = true;
			break;
		case 6:
			if (valueDiceYellow == 1)
				result[0] = true;
			if (valueDiceWhite == 1)
				result[1] = true;
			break;
		case 7:
			if (valueDiceYellow == 2)
				result[0] = true;
			if (valueDiceWhite == 2)
				result[1] = true;
			break;
		case 8:
			if (valueDiceYellow == 4)
				result[0] = true;
			if (valueDiceWhite == 4)
				result[1] = true;
			break;
		case 9:
			if (valueDiceYellow == 3)
				result[0] = true;
			if (valueDiceWhite == 3)
				result[1] = true;
			break;
		case 10:
			if (valueDiceYellow == 4)
				result[0] = true;
			if (valueDiceWhite == 4)
				result[1] = true;
			break;
		case 11:
			if (valueDiceYellow == 6)
				result[0] = true;
			if (valueDiceWhite == 6)
				result[1] = true;
			break;
		}
		return result;
	}

}
