package games.gsClever.Logic;

public class Blue {
	private	boolean [] fields = new boolean [12];


	public Blue (boolean[] fields) {
		
		this.fields= fields;
	}


	public boolean[] getFields(){
		return fields;

			
	}



	public specialEvent enterCross( int fieldId ) {
		
		fields [fieldId] = true ;
		
		if ((fields[0]== true &&fields[1]==true&& fields[2]==true) && (fieldId==0 || fieldId == 1 || fieldId==2)) {
			return specialEvent.enterOrange5;
		}
		if ((fields[3]== true &&fields[4]==true&& fields[5]==true &&fields[6]==true) && (fieldId==3 || fieldId == 4 || fieldId==5 || fieldId==6)) {
			return specialEvent.enterCrossYellow;
		}
		if ((fields[3]== true &&fields[7]==true) && (fieldId==3 || fieldId == 7 )) {
			return specialEvent.diceRepeat;
		}
		if ((fields[0]== true &&fields[4]==true&& fields[8]==true) && (fieldId==0 || fieldId == 4 || fieldId==8)) {
			return specialEvent.enterCrossGreen;
		}
		if ((fields[1]== true &&fields[5]==true&& fields[9]==true) && (fieldId==1 || fieldId == 5 || fieldId==9)) {
			return specialEvent.enterPurple6;
		}
		if ((fields[2]== true &&fields[6]==true&& fields[10]==true) && (fieldId==2 || fieldId == 6 || fieldId==10)) {
			return specialEvent.additionalDice;
		}
		return null;
		
		
	}

		
}
