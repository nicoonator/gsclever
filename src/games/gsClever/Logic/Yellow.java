package games.gsClever.Logic;

public class Yellow {

private	boolean [] fields = new boolean [12];


public Yellow () {
	
for (int i=0;i<12;i++) {
	fields [i]=false;
}
}


public boolean[] getFields(){
	return fields;

		
}



public specialEvent enterCross( int fieldId ) {
	
	fields [fieldId] = true ;
	
	
	if ((fields[0]== true &&fields[1]==true&& fields[2]==true) && (fieldId==0 || fieldId == 1 || fieldId==2)) {
		return specialEvent.enterCrossBlue;
	}
	if ((fields[3]== true &&fields[4]==true&& fields[5]==true) && (fieldId==3 || fieldId == 4 || fieldId==5)) {
		return specialEvent.enterOrange4;
		}
	if ((fields[6]== true &&fields[7]==true&& fields[8]==true) && (fieldId==6 || fieldId == 7 || fieldId==8)) {
		return specialEvent.enterCrossGreen;
		}
	if ((fields[0]== true &&fields[4]==true&& fields[7]==true&& fields[11]==true) && (fieldId==0 || fieldId == 4 || fieldId==7|| fieldId==11)) {
		return specialEvent.additionalDice;
		}
	return null;
}


public boolean getFox() {
	if (fields[9]== true &&fields[10]==true&& fields[11]==true) {
		return true;
		}
	else
	return false;
}


public int determinePoints() {
	// TODO Auto-generated method stub
	if ( fields[0]==true&&fields[3]==true&&fields[6]==true) {
		return 10;
	}
	if ( fields[1]==true&&fields[4]==true&&fields[9]==true) {
		return 14;
	}
	if ( fields[2]==true&&fields[7]==true&&fields[10]==true) {
		return 16;
	}
	if ( fields[5]==true&&fields[8]==true&&fields[11]==true) {
		return 20;
	}
	return 0;
}

}
