package games.gsClever.Logic;

public class Yellow extends ColorArea {

	private boolean[] fields = new boolean[12];

	public Yellow() {

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
			return SpecialEvent.enterCrossBlue;
		}
		if ((fields[3] == true && fields[4] == true && fields[5] == true)
				&& (fieldId == 3 || fieldId == 4 || fieldId == 5)) {
			return SpecialEvent.enterOrange4;
		}
		if ((fields[6] == true && fields[7] == true && fields[8] == true)
				&& (fieldId == 6 || fieldId == 7 || fieldId == 8)) {
			return SpecialEvent.enterCrossGreen;
		}
		if ((fields[0] == true && fields[4] == true && fields[7] == true && fields[11] == true)
				&& (fieldId == 0 || fieldId == 4 || fieldId == 7 || fieldId == 11)) {
			return SpecialEvent.additionalDice;
		}
		return null;
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

		
		if( valueDiceYellow == 1 || valueDiceWhite==1) {
			if(fields[4] ==false) {
				field[4] = true;
			}
			if(fields[6]==false) {
				field[6] = true;
			}
			return field;
		}
		if( valueDiceYellow == 2 || valueDiceWhite==2) {
			if(fields[3] ==false) {
				field[3] = true;
			}
			if(fields[7] ==false) {
				field[7] = true;
			}
			return field;
		}
		if( valueDiceYellow == 3 || valueDiceWhite==3) {
			if (fields[0] == false) {
				field[0] = true;
			}
			if(fields[9] == false) {
				field[9] = true;
			}
			return field;
		}
		if( valueDiceYellow == 4 || valueDiceWhite==4) {
			if( fields[8] == false) {
				field[8] = true;
			}
			if( fields[10] == false) {
				field[10] = true;
			}
			return field;
		}
		if( valueDiceYellow == 5 || valueDiceWhite==5) {
			if (fields[2] == false) {
				field[2] = true;
			}
			if ( fields[5] == false) {
				field[5] = true;
			}
			return field;
		}
		if( valueDiceYellow == 6 || valueDiceWhite==6) {
			if (fields[1] == false) {
				field[1] = true;
			}
			if(fields[11] ==false) {
				field[11] = true;
			}
			return field;
		}
		
		return field;

	}

	public int determinePoints() {
		// TODO Auto-generated method stub
		if (fields[0] == true && fields[3] == true && fields[6] == true) {
			return 10;
		}
		if (fields[1] == true && fields[4] == true && fields[9] == true) {
			return 14;
		}
		if (fields[2] == true && fields[7] == true && fields[10] == true) {
			return 16;
		}
		if (fields[5] == true && fields[8] == true && fields[11] == true) {
			return 20;
		}
		return 0;
	}

}
