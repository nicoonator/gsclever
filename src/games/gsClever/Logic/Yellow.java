package games.gsClever.Logic;

public class Yellow {

private	boolean [] fields = new boolean [12];


public Yellow (boolean[] fields) {
	
	this.fields= fields;
}


public boolean[] getFields(){
	return fields;

		
}



public void enterCross( int fieldId ) {
	
	switch (fieldId) {
	case 0 : fields[0]=true;
	break;
	
	case 1 : fields[1]=true;
	break;
	
	case 2 : fields[2]=true;
	break;
	
	case 3 : fields[3]=true;
	break;
	
	case 4 : fields[4]=true;
	break;
	
	case 5 : fields[5]=true;
	break;
	
	case 6 : fields[6]=true;
	break;
	
	case 7 : fields[7]=true;
	break;
	
	case 8 : fields[8]=true;
	break;
	
	case 9 : fields[9]=true;
	break;
	
	case 10 : fields[10]=true;
	break;
	
	case 11 : fields[11]=true;
	break;
	
	case 12 : fields[12]=true;
	break;

	}
}

	
}
