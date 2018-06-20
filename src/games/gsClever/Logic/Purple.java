package games.gsClever.Logic;

public class Purple {

	
	private int [] fields = new int [11];
	private int fieldCount =0 ;
	
	public void enterNumbers(int number) {
		////Switch
fieldCount++;
		
		switch(fieldCount-1) {
		case 0: fields[0] = number;
				break;
		case 1: fields[1] = number;
				break;
		case 2: fields[2] = number;
				
				break;
		case 3: fields[3] = number;
				break;
		case 4: fields[4] = number;
				break;	
		case 5: fields[5] = number;
				break;
		case 6: fields[6]= number;
				break;
		case 7: fields[7] = number;
				break;
		case 8 : fields[8] = number;
				break;
		case 9: fields[9] = number;
				
		case 10: fields[10] = number;
				break;
				
		}
		return;
		
	}
		
		
		
	}

