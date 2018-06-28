package games.gsClever.Logic;

public class Purple extends ColorArea{

	
	private int [] fields = new int [11];
	private int fieldCount =0 ;
	
	public Purple() {
		for (int i=0;i<12;i++) {
			fields [i]=0;
		}
	}

	
	public SpecialEvent enterNumbers(int number) {
		////Switch
fieldCount++;
		
		switch(fieldCount-1) {
		case 0: fields[0] = number;
				break;
		case 1: fields[1] = number;
				break;
		case 2: fields[2] = number;
			return SpecialEvent.diceRepeat;		
		case 3: fields[3] = number;
			return SpecialEvent.enterCrossBlue;
		case 4: fields[4] = number;
			return SpecialEvent.additionalDice;	
		case 5: fields[5] = number;
			return SpecialEvent.enterCrossYellow;
		case 6: fields[6]= number;
		break;
		case 7: fields[7] = number;
			return SpecialEvent.diceRepeat;
		case 8 : fields[8] = number;
			return SpecialEvent.enterCrossGreen;
		
		case 9: fields[9] = number;
			return SpecialEvent.enterOrange6;
				
		case 10: fields[10] = number;
			return SpecialEvent.additionalDice;
		}
		return null;
		
	}

	public boolean getFox() {
		if (fields[6]!= 0) {
			return true;
			
		}else
		return false;
	}

	public boolean[] isClickable(int valueDicePurple, int valueDiceWhite){
		
		boolean [] field = new boolean [11];
		int p=0;
		for(int i=0; i<11; i++) {
			field[i] = false;
		}
		
		for (int m=0; m<11; m++) {
			if(fields[m]==0) {
				 p = m;
				 break;
			}
			
		}
		if(p ==0 || fields[p-1] == 6) {
			field[0] = true;
		}else {
			if(fields[p-1] < valueDicePurple || fields[p-1] < valueDiceWhite) 
			field[p]= true; 
		
		}
		 return field;
	}
	public int determinePoints() {
		// TODO Auto-generated method stub
		int summe =0;
		for(int i=0;i>=fieldCount;i++) {
			summe = summe+ fields[i];
		}
		return summe;
		
	}
		
		
		
	}

