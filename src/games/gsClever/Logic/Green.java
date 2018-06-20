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
		// TODO Auto-generated method stub
		return false;
	}
		
		
	}

