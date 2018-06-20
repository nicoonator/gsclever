package games.gsClever.Exceptions;

/**
 * 
 * @author Joel Wolf
 *
 */
public class CannotUseAdditionalDiceException extends Exception {
	
	public CannotUseAdditionalDiceException() {
			
	}
		
	@Override
	public String getMessage(){
		return "Cannot use dice repeat!";
	}
		
	@Override
	public String toString(){
		return getMessage();
	}
}
