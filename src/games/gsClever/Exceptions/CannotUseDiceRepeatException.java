package games.gsClever.Exceptions;

/**
 * 
 * @author Joel Wolf
 *
 */
public class CannotUseDiceRepeatException extends Exception {
	
	public CannotUseDiceRepeatException() {
			
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
