package games.gsClever.Logic;

public class Blue {
	private	boolean [] fields = new boolean [12];


	public Blue (boolean[] fields) {
		
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
