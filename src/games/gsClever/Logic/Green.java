package games.gsClever.Logic;

public class Green {

	
	private int fields;
	private int fieldCount =0 ;
	
	public Green() {
	fieldCount =0 ;
		
	}
	
	public specialEvent enterCross(int number) {
		
fieldCount++;
		
		switch(fieldCount-1) {
		case 0: fields = number;
				break;
		case 1: fields = number;
				break;
		case 2: fields = number;	
				break;
		case 3: fields = number;
		return specialEvent.additionalDice;
		case 4: fields = number;
				break;	
		case 5: fields = number;
		return specialEvent.enterCrossBlue;
		case 6: fields= number;
				break;
		case 7: fields = number;
				break;
		case 8 : fields = number;
		return specialEvent.enterPurple6;
		case 9: fields = number;
		return specialEvent.diceRepeat;
		case 10: fields = number;
				break;
				
		}
		return null;
		
	}

	public boolean getFox() {
		if (fields>=7) {
			return true;// TODO Auto-generated method stub
		}else
		return false;
	}

	public int determinePoints() {
		// TODO Auto-generated method stub
		int a =0;
		switch(fieldCount) {
		
		case 1: a= 1;
				return 1;
		case 2: a =3;
				return 3;
		case 3: a=6;
				return 6;
		case 4: a=10;
				return 10;
		case 5: a=15;
				return 15;
		case 6: a = 21;
				return 21;
		case 7: a=28;
				return 28;
		case 8: a=36;
				return 36;
		case 9: a =45;
				return 45;
		case 10:a= 55;
				return 55;
		case 11:a = 66; 
				return 66;
		}
		return 0;
	}
		
		
	}

