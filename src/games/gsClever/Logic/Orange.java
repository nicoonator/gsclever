package games.gsClever.Logic;

public class Orange {

	private int [] fields = new int [11];
	private int fieldCount =0 ;
	public specialEvent  enterNumbers(int number) {
		////Switch
		fieldCount++;
		
		switch(fieldCount-1) {
		case 0: fields[0] = number;
				break;
		case 1: fields[1] = number;
				break;
		case 2: fields[2] = number;
		return specialEvent.diceRepeat;
		case 3: fields[3] = number*2;
				break;
		case 4: fields[4] = number;
				return specialEvent.enterCrossYellow;	
		case 5: fields[5] = number;
				return specialEvent.additionalDice;
		case 6: fields[6]= number*2;
				break;
		case 7: fields[7] = number;
				break;
		case 8 : fields[8] = number*2;
				break;
		case 9: fields[9] = number;
		return specialEvent.enterPurple6;
				
		case 10: fields[10] = number*3;
				break;
				
		}
		return null;
		
	}
	public boolean getFox() {
		// TODO Auto-generated method stub
		return false;
	}
}
