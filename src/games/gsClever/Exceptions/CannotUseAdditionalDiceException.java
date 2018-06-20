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
		return "Cannot use additional dice!";
	}
		
	@Override
	public String toString(){
		return getMessage();
	}
}
