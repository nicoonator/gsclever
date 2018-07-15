/**
 * 
 */
package games.gsClever.Logic;

import userManagement.User;

/**
 * @author Nico Rychlik
 *
 */
@SuppressWarnings("serial")
public class KI extends User {

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
			if (data[151] == 1) {
				return "WUERFELN";
			}
			return "SKIP";
		}
		case 2: {
			if (data[233] == 1) {
				return "WUERFELN";
			}
			if (data[234] == 1) {
				return "COLORDECIDER";
			}
			if (data[237] == 1) {
				return "CLICKYELLOW0";
			}
			if (data[238] == 1) {
				return "CLICKYELLOW1";
			}
			if (data[239] == 1) {
				return "CLICKYELLOW2";
			}
			if (data[240] == 1) {
				return "CLICKYELLOW3";
			}
			if (data[241] == 1) {
				return "CLICKYELLOW4";
			}
			if (data[242] == 1) {
				return "CLICKYELLOW5";
			}
			if (data[243] == 1) {
				return "CLICKYELLOW6";
			}
			if (data[244] == 1) {
				return "CLICKYELLOW7";
			}
			if (data[245] == 1) {
				return "CLICKYELLOW8";
			}
			if (data[246] == 1) {
				return "CLICKYELLOW9";
			}
			if (data[247] == 1) {
				return "CLICKYELLOW10";
			}
			if (data[248] == 1) {
				return "CLICKYELLOW11";
			}
			if (data[249] == 1) {
				return "CLICKBLUE0";
			}
			if (data[250] == 1) {
				return "CLICKBLUE1";
			}
			if (data[251] == 1) {
				return "CLICKBLUE2";
			}
			if (data[252] == 1) {
				return "CLICKBLUE3";
			}
			if (data[253] == 1) {
				return "CLICKBLUE4";
			}
			if (data[254] == 1) {
				return "CLICKBLUE5";
			}
			if (data[255] == 1) {
				return "CLICKBLUE6";
			}
			if (data[256] == 1) {
				return "CLICKBLUE7";
			}
			if (data[257] == 1) {
				return "CLICKBLUE8";
			}
			if (data[258] == 1) {
				return "CLICKBLUE9";
			}
			if (data[259] == 1) {
				return "CLICKBLUE10";
			}
			if (data[260] == 1) {
				return "CLICKGREEN";
			}
			if (data[261] == 1) {
				return "CLICKORANGE";
			}
			if (data[262] == 1) {
				return "CLICKPURPLE";
			}
			return "SKIP";
		}
		case 3: {
			if (data[315] == 1) {
				return "WUERFELN";
			}
			if (data[316] == 1) {
				return "COLORDECIDER";
			}
			if (data[319] == 1) {
				return "CLICKYELLOW0";
			}
			if (data[320] == 1) {
				return "CLICKYELLOW1";
			}
			if (data[321] == 1) {
				return "CLICKYELLOW2";
			}
			if (data[322] == 1) {
				return "CLICKYELLOW3";
			}
			if (data[323] == 1) {
				return "CLICKYELLOW4";
			}
			if (data[324] == 1) {
				return "CLICKYELLOW5";
			}
			if (data[325] == 1) {
				return "CLICKYELLOW6";
			}
			if (data[326] == 1) {
				return "CLICKYELLOW7";
			}
			if (data[327] == 1) {
				return "CLICKYELLOW8";
			}
			if (data[328] == 1) {
				return "CLICKYELLOW9";
			}
			if (data[329] == 1) {
				return "CLICKYELLOW10";
			}
			if (data[330] == 1) {
				return "CLICKYELLOW11";
			}
			if (data[331] == 1) {
				return "CLICKBLUE0";
			}
			if (data[332] == 1) {
				return "CLICKBLUE1";
			}
			if (data[333] == 1) {
				return "CLICKBLUE2";
			}
			if (data[334] == 1) {
				return "CLICKBLUE3";
			}
			if (data[335] == 1) {
				return "CLICKBLUE4";
			}
			if (data[336] == 1) {
				return "CLICKBLUE5";
			}
			if (data[337] == 1) {
				return "CLICKBLUE6";
			}
			if (data[338] == 1) {
				return "CLICKBLUE7";
			}
			if (data[339] == 1) {
				return "CLICKBLUE8";
			}
			if (data[340] == 1) {
				return "CLICKBLUE9";
			}
			if (data[341] == 1) {
				return "CLICKBLUE10";
			}
			if (data[342] == 1) {
				return "CLICKGREEN";
			}
			if (data[343] == 1) {
				return "CLICKORANGE";
			}
			if (data[344] == 1) {
				return "CLICKPURPLE";
			}
			return "SKIP";
		}
		}

		return result;
	}

	/**
	 * @param data 
	 * @return 
	 * 
	 */
	public boolean checkRollDice(int[] data) {
		switch (playerID) {
		case 1:
			if (data[151] == 1) {
				return true;
			} else return false;

		case 2:
			if (data[233] == 1) {
				return true;
			} else return false;
		case 3:
			if (data[315] == 1) {
				return true;
			} else return false;
		}
		return false;
	}
}
