/**
 * 
 */
package games.gsClever.Logic;

import userManagement.User;

/**
 * @author Nico Rychlik
 *
 */
public class KI extends User {

	private Player player;
	private Return returnback;
	private int playerID;

	/**
	 * @param name
	 * @param pw
	 */
	public KI(int id) {
		super(Integer.toString(id), "none");

	}

	public Return doSomething(MainLogic currentGame, Return returnBack2) {
		playerID = currentGame.determinePlayerId(this.getName());
		returnback = returnBack2;
		while (returnback.getCurrentPlayer() == playerID) {
			if (returnback.getClickable(playerID).isRollDices()) {
				try {
					returnback = currentGame.click(playerID, Area.rollDices, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} /*
				 * else if(//TODO){ //TODO }
				 */

		}
		return returnback;
	}
}
