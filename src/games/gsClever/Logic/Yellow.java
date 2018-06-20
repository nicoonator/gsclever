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
	
	fields [fieldId] = true ;
	
}


public boolean getFox() {
	// TODO Auto-generated method stub
	return false;
}

	
}
