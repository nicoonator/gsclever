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

	public String doSomething(MainLogic game, int[] data) {
		String result = "";
		playerID = game.determinePlayerId(this.getName());

		switch (playerID) {
		case 1: {
			if (data[151] == 1) {
				return "WUERFELN";
			}
			if (data[152] == 1) {
				return "COLORDECIDER";
			}
			if (data[155] == 1) {
				return "CLICKYELLOW0";
			}
			if (data[156] == 1) {
				return "CLICKYELLOW1";
			}
			if (data[157] == 1) {
				return "CLICKYELLOW2";
			}
			if (data[158] == 1) {
				return "CLICKYELLOW3";
			}
			if (data[159] == 1) {
				return "CLICKYELLOW4";
			}
			if (data[160] == 1) {
				return "CLICKYELLOW5";
			}
			if (data[161] == 1) {
				return "CLICKYELLOW6";
			}
			if (data[162] == 1) {
				return "CLICKYELLOW7";
			}
			if (data[163] == 1) {
				return "CLICKYELLOW8";
			}
			if (data[164] == 1) {
				return "CLICKYELLOW9";
			}
			if (data[165] == 1) {
				return "CLICKYELLOW10";
			}
			if (data[166] == 1) {
				return "CLICKYELLOW11";
			}
			if (data[167] == 1) {
				return "CLICKBLUE0";
			}
			if (data[168] == 1) {
				return "CLICKBLUE1";
			}
			if (data[169] == 1) {
				return "CLICKBLUE2";
			}
			if (data[170] == 1) {
				return "CLICKBLUE3";
			}
			if (data[171] == 1) {
				return "CLICKBLUE4";
			}
			if (data[172] == 1) {
				return "CLICKBLUE5";
			}
			if (data[173] == 1) {
				return "CLICKBLUE6";
			}
			if (data[174] == 1) {
				return "CLICKBLUE7";
			}
			if (data[175] == 1) {
				return "CLICKBLUE8";
			}
			if (data[176] == 1) {
				return "CLICKBLUE9";
			}
			if (data[177] == 1) {
				return "CLICKBLUE10";
			}
			if (data[178] == 1) {
				return "CLICKGREEN";
			}
			if (data[179] == 1) {
				return "CLICKORANGE";
			}
			if (data[180] == 1) {
				return "CLICKPURPLE";
			}
			return"SKIP";
		}
		case 2: {

			break;
		}
		case 3: {

			break;
		}
		}

		return result;
	}
}
