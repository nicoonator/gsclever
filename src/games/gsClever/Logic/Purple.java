package games.gsClever.Logic;

public class Purple {

	
	private int [] fields = new int [11];
	private int fieldCount =0 ;
	
	public Purple() {
		for (int i=0;i<12;i++) {
			fields [i]=0;
		}
	}

	
	public specialEvent enterNumbers(int number) {
		////Switch
fieldCount++;
		
		switch(fieldCount-1) {
		case 0: fields[0] = number;
				break;
		case 1: fields[1] = number;
				break;
		case 2: fields[2] = number;
			return specialEvent.diceRepeat;		
		case 3: fields[3] = number;
			return specialEvent.enterCrossBlue;
		case 4: fields[4] = number;
			return specialEvent.additionalDice;	
		case 5: fields[5] = number;
			return specialEvent.enterCrossYellow;
		case 6: fields[6]= number;
		break;
		case 7: fields[7] = number;
			return specialEvent.diceRepeat;
		case 8 : fields[8] = number;
			return specialEvent.enterCrossGreen;
		
		case 9: fields[9] = number;
			return specialEvent.enterOrange6;
				
		case 10: fields[10] = number;
			return specialEvent.additionalDice;
		}
		return null;
		
	}

	public boolean getFox() {
		if (fields[6]!= 0) {
			return true;
			
		}else
		return false;
	}


	public int determinePoints() {
		// TODO Auto-generated method stub
		return 0;
	}
		
		
		
	}

