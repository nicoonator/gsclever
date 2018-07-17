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

	private int userID;
	private int skipZaehler=0;

	/**
	 * @param name
	 * @param pw
	 */
	public KI(int id) {
		super(Integer.toString(id), "none");

	}

	public String doSomething(MainLogic game, int[] data) {
		String result = "";
		userID = game.determinePlayerId(this.getName());


		switch (userID) {
		case 1: {
			if (data[152] == 1) {
				skipZaehler=0; return "COLORDECIDER";
			}
			if (data[155] == 1) {
				skipZaehler=0; return "CLICKYELLOW0";
			}
			if (data[156] == 1) {
				skipZaehler=0; return "CLICKYELLOW1";
			}
			if (data[157] == 1) {
				skipZaehler=0; return "CLICKYELLOW2";
			}
			if (data[158] == 1) {
				skipZaehler=0; return "CLICKYELLOW3";
			}
			if (data[159] == 1) {
				skipZaehler=0; return "CLICKYELLOW4";
			}
			if (data[160] == 1) {
				skipZaehler=0; return "CLICKYELLOW5";
			}
			if (data[161] == 1) {
				skipZaehler=0; return "CLICKYELLOW6";
			}
			if (data[162] == 1) {
				skipZaehler=0; return "CLICKYELLOW7";
			}
			if (data[163] == 1) {
				skipZaehler=0; return "CLICKYELLOW8";
			}
			if (data[164] == 1) {
				skipZaehler=0; return "CLICKYELLOW9";
			}
			if (data[165] == 1) {
				skipZaehler=0; return "CLICKYELLOW10";
			}
			if (data[166] == 1) {
				skipZaehler=0; return "CLICKYELLOW11";
			}
			if (data[167] == 1) {
				skipZaehler=0; return "CLICKBLUE0";
			}
			if (data[168] == 1) {
				skipZaehler=0; return "CLICKBLUE1";
			}
			if (data[169] == 1) {
				skipZaehler=0; return "CLICKBLUE2";
			}
			if (data[170] == 1) {
				skipZaehler=0; return "CLICKBLUE3";
			}
			if (data[171] == 1) {
				skipZaehler=0; return "CLICKBLUE4";
			}
			if (data[172] == 1) {
				skipZaehler=0; return "CLICKBLUE5";
			}
			if (data[173] == 1) {
				skipZaehler=0; return "CLICKBLUE6";
			}
			if (data[174] == 1) {
				skipZaehler=0; return "CLICKBLUE7";
			}
			if (data[175] == 1) {
				skipZaehler=0; return "CLICKBLUE8";
			}
			if (data[176] == 1) {
				skipZaehler=0; return "CLICKBLUE9";
			}
			if (data[177] == 1) {
				skipZaehler=0; return "CLICKBLUE10";
			}
			if (data[178] == 1) {
				skipZaehler=0;
				return "CLICKGREEN";
			}
			if (data[179] == 1) {
				skipZaehler=0;
				return "CLICKORANGE";
			}
			if (data[180] == 1) {
				skipZaehler=0;
				return "CLICKPURPLE";
			}
			if (data[151] == 1) {
				skipZaehler=0;
				return "WUERFELN";
			}
			if (data[346] == 1) {
				if(skipZaehler<1){
					//skipZaehler++;
					return "SKIP";
				}
				
			}
			return "";
		}
		case 2: {
			
			if (data[234] == 1) {
				skipZaehler=0; return "COLORDECIDER";
			}
			if (data[237] == 1) {
				skipZaehler=0; return "CLICKYELLOW0";
			}
			if (data[238] == 1) {
				skipZaehler=0; return "CLICKYELLOW1";
			}
			if (data[239] == 1) {
				skipZaehler=0; return "CLICKYELLOW2";
			}
			if (data[240] == 1) {
				skipZaehler=0; return "CLICKYELLOW3";
			}
			if (data[241] == 1) {
				skipZaehler=0; return "CLICKYELLOW4";
			}
			if (data[242] == 1) {
				skipZaehler=0; return "CLICKYELLOW5";
			}
			if (data[243] == 1) {
				skipZaehler=0; return "CLICKYELLOW6";
			}
			if (data[244] == 1) {
				skipZaehler=0; return "CLICKYELLOW7";
			}
			if (data[245] == 1) {
				skipZaehler=0; return "CLICKYELLOW8";
			}
			if (data[246] == 1) {
				skipZaehler=0; return "CLICKYELLOW9";
			}
			if (data[247] == 1) {
				skipZaehler=0; return "CLICKYELLOW10";
			}
			if (data[248] == 1) {
				skipZaehler=0; return "CLICKYELLOW11";
			}
			if (data[249] == 1) {
				skipZaehler=0; return "CLICKBLUE0";
			}
			if (data[250] == 1) {
				skipZaehler=0; return "CLICKBLUE1";
			}
			if (data[251] == 1) {
				skipZaehler=0; return "CLICKBLUE2";
			}
			if (data[252] == 1) {
				skipZaehler=0; return "CLICKBLUE3";
			}
			if (data[253] == 1) {
				skipZaehler=0; return "CLICKBLUE4";
			}
			if (data[254] == 1) {
				skipZaehler=0; return "CLICKBLUE5";
			}
			if (data[255] == 1) {
				skipZaehler=0; return "CLICKBLUE6";
			}
			if (data[256] == 1) {
				skipZaehler=0; return "CLICKBLUE7";
			}
			if (data[257] == 1) {
				skipZaehler=0; return "CLICKBLUE8";
			}
			if (data[258] == 1) {
				skipZaehler=0; return "CLICKBLUE9";
			}
			if (data[259] == 1) {
				skipZaehler=0; return "CLICKBLUE10";
			}
			if (data[260] == 1) {
				skipZaehler=0; return "CLICKGREEN";
			}
			if (data[261] == 1) {
				skipZaehler=0; return "CLICKORANGE";
			}
			if (data[262] == 1) {
				skipZaehler=0; return "CLICKPURPLE";
			}
			if (data[233] == 1) {
				skipZaehler=0;
				skipZaehler=0; return "WUERFELN";
			}
			if (data[347] == 1) {
				if(skipZaehler<1){
					//skipZaehler++;
					return "SKIP";
				}
			}
			return "";
		}
		case 3: {
			
			if (data[316] == 1) {
				skipZaehler=0; return "COLORDECIDER";
			}
			if (data[319] == 1) {
				skipZaehler=0; return "CLICKYELLOW0";
			}
			if (data[320] == 1) {
				skipZaehler=0; return "CLICKYELLOW1";
			}
			if (data[321] == 1) {
				skipZaehler=0; return "CLICKYELLOW2";
			}
			if (data[322] == 1) {
				skipZaehler=0; return "CLICKYELLOW3";
			}
			if (data[323] == 1) {
				skipZaehler=0; return "CLICKYELLOW4";
			}
			if (data[324] == 1) {
				skipZaehler=0; return "CLICKYELLOW5";
			}
			if (data[325] == 1) {
				skipZaehler=0; return "CLICKYELLOW6";
			}
			if (data[326] == 1) {
				skipZaehler=0; return "CLICKYELLOW7";
			}
			if (data[327] == 1) {
				skipZaehler=0; return "CLICKYELLOW8";
			}
			if (data[328] == 1) {
				skipZaehler=0; return "CLICKYELLOW9";
			}
			if (data[329] == 1) {
				skipZaehler=0; return "CLICKYELLOW10";
			}
			if (data[330] == 1) {
				skipZaehler=0; return "CLICKYELLOW11";
			}
			if (data[331] == 1) {
				skipZaehler=0; return "CLICKBLUE0";
			}
			if (data[332] == 1) {
				skipZaehler=0; return "CLICKBLUE1";
			}
			if (data[333] == 1) {
				skipZaehler=0; return "CLICKBLUE2";
			}
			if (data[334] == 1) {
				skipZaehler=0; return "CLICKBLUE3";
			}
			if (data[335] == 1) {
				skipZaehler=0; return "CLICKBLUE4";
			}
			if (data[336] == 1) {
				skipZaehler=0; return "CLICKBLUE5";
			}
			if (data[337] == 1) {
				skipZaehler=0; return "CLICKBLUE6";
			}
			if (data[338] == 1) {
				skipZaehler=0; return "CLICKBLUE7";
			}
			if (data[339] == 1) {
				skipZaehler=0; return "CLICKBLUE8";
			}
			if (data[340] == 1) {
				skipZaehler=0; return "CLICKBLUE9";
			}
			if (data[341] == 1) {
				skipZaehler=0; return "CLICKBLUE10";
			}
			if (data[342] == 1) {
				skipZaehler=0; return "CLICKGREEN";
			}
			if (data[343] == 1) {
				skipZaehler=0; return "CLICKORANGE";
			}
			if (data[344] == 1) {
				skipZaehler=0; return "CLICKPURPLE";
			}
			if (data[315] == 1) {
				skipZaehler=0; return "WUERFELN";
			}
			if (data[348] == 1) {
				if(skipZaehler<1){
					//skipZaehler++;
					return "SKIP";
				}
			}
			return "";
		}
		}

		return result;
	}
	
	public void doKITurn(MainLogic game, int[] data, Return returnBack) throws Exception {
		String result = "";
		userID = game.determinePlayerId(this.getName());


		switch (userID) {
		case 1: {
			
			if (data[152] == 1) {
				returnBack = game.click(userID, Area.decisionMaker,
						returnBack.getDecisionMaker(userID).getColorOfDice().ordinal());
				return;
			}
			if (data[155] == 1) {
				returnBack = game.click(userID, Area.yellow,0);
				return;
			}
			if (data[156] == 1) {
				returnBack = game.click(userID, Area.yellow,1);
				return;
			}
			if (data[157] == 1) {
				returnBack = game.click(userID, Area.yellow,2);
				return;
			}
			if (data[158] == 1) {
				returnBack = game.click(userID, Area.yellow,3);
				return;
			}
			if (data[159] == 1) {
				returnBack = game.click(userID, Area.yellow,4);
				return;
			}
			if (data[160] == 1) {
				returnBack = game.click(userID, Area.yellow,5);
				return;
			}
			if (data[161] == 1) {
				returnBack = game.click(userID, Area.yellow,6);
				return;
			}
			if (data[162] == 1) {
				returnBack = game.click(userID, Area.yellow,7);
				return;
			}
			if (data[163] == 1) {
				returnBack = game.click(userID, Area.yellow,8);
				return;
			}
			if (data[164] == 1) {
				returnBack = game.click(userID, Area.yellow,9);
				return;
			}
			if (data[165] == 1) {
				returnBack = game.click(userID, Area.yellow,10);
				return;
			}
			if (data[166] == 1) {
				returnBack = game.click(userID, Area.yellow,11);
				return;
			}
			if (data[167] == 1) {
				returnBack = game.click(userID, Area.blue,0);
				return;
			}
			if (data[168] == 1) {
				returnBack = game.click(userID, Area.blue,1);
				return;
			}
			if (data[169] == 1) {
				returnBack = game.click(userID, Area.blue,2);
				return;
			}
			if (data[170] == 1) {
				returnBack = game.click(userID, Area.blue,3);
				return;
			}
			if (data[171] == 1) {
				returnBack = game.click(userID, Area.blue,4);
				return;
			}
			if (data[172] == 1) {
				returnBack = game.click(userID, Area.blue,5);
				return;
			}
			if (data[173] == 1) {
				returnBack = game.click(userID, Area.blue,6);
				return;
			}
			if (data[174] == 1) {
				returnBack = game.click(userID, Area.blue,7);
				return;
			}
			if (data[175] == 1) {
				returnBack = game.click(userID, Area.blue,8);
				return;
			}
			if (data[176] == 1) {
				returnBack = game.click(userID, Area.blue,9);
				return;
				}
			if (data[177] == 1) {
				returnBack = game.click(userID, Area.blue,10);
				return;
			}
			if (data[178] == 1) {
				returnBack = game.click(userID, Area.green,0);
				return;
			}
			if (data[179] == 1) {
				returnBack = game.click(userID, Area.orange,0);
				return;
			}
			if (data[180] == 1) {
				returnBack = game.click(userID, Area.purple,0);
				return;
			}
			if (data[151] == 1) {
				returnBack = game.click(userID, Area.rollDices, 0);
				return;
			}
			return;
		}
		/*case 2: {
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
		}*/
		}

		return;
	}

	/**
	 * @param data 
	 * @return 
	 * 
	 */
	public boolean checkRollDice(int[] data) {
		switch (userID) {
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
