package games.gsClever;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameClasses.Game;
import gameClasses.GameState;
import games.gsClever.Logic.Color;
import games.gsClever.Logic.Dice;
import games.gsClever.Logic.KI;
import games.gsClever.Logic.MainLogic;
import games.gsClever.Logic.Return;
import global.FileHelper;
import userManagement.User;

/**
 * Game Class which get accessed by server
 * 
 * @author Nico Rychlik
 *
 */

public class gsClever extends Game {

	/*
	 * game Status 1x Spielerzahl + 1x rundenzaehler + 1x aktueller Spieler + 6x
	 * Wuefelflaeche wuerfel +4xSpielfeld: (2xNachwuerfeln(Freigeschaltet +
	 * genutzt)+2x Zusatzwuerfel+12Gelb+11Blau+1Gruen+11Orange+11Lila) Wuerfel: 0-36
	 * ([Blau, Gelb, Gruen, Lila, Orange, Weiss] 1: 1blau 7: 1Gelb 30: 6Weiss
	 */
	private int[] gameStatus = new int[345];
	private User playerTurn = null;
	private ArrayList<User> playerList = new ArrayList<User>();
	private ArrayList<User> spectatorList = new ArrayList<User>();
	private String playerLeft = null;
	private MainLogic currentGame;

	@Override
	public String getSite() {
		try {
			return FileHelper.getFile("gsClever/gsClever.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getCSS() {
		try {
			return global.FileHelper.getFile("gsClever/css/gsClever.css");
		} catch (IOException e) {
			System.err.println("Loading of file gsClever/css/gsClever.css failed");
		}
		return null;
	}

	@Override
	public String getJavaScript() {
		return "<link rel=\"stylesheet\"\r\n"
				+ "	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
				+ "<script src=\"javascript/gsClever.js\"></script>";
	}

	@Override
	public int getMaxPlayerAmount() {
		return 4;
	}

	@Override
	public int getCurrentPlayerAmount() {
		return playerList.size();
	}

	/*
	 * game Status 1x Spielerzahl + 1x rundenzaehler + 1x aktueller Spieler + 6x
	 * Wuefelflaechen+3x genommener wuerfel +4xSpielfeld:
	 * (2xNachwuerfeln(Freigeschaltet + genutzt)+2x
	 * Zusatzwuerfel+12Gelb+11Blau+1Gruen+11Orange+11Lila) Wuerfel: 0-36 ([Blau,
	 * Gelb, Gruen, Lila, Orange, Weiss] 1: 1blau 7: 1Gelb 30: 6Weiss
	 */

	@SuppressWarnings("null")
	private int[] getGameStatus() {
		// Hier muessen wir jedes mal wenn die Methode aufgerufen wird, das
		// Array aus
		// der Logic holen
		Return testReturn = null;
		int[] result = new int[345];
		for (int i = 0; i < 345; i++) {
			switch (i) {
			case 0:
				result[i] = testReturn.getRound();
				break;
			case 1:
				result[i] = testReturn.getCurrentPlayer();
				break;
			case 2:
				result[i] = testReturn.getDices()[Color.blue.ordinal()].getValue();
				break;
			case 3:
				result[i] = testReturn.getDices()[Color.yellow.ordinal()].getValue();
				break;
			case 4:
				result[i] = testReturn.getDices()[Color.orange.ordinal()].getValue();
				break;
			case 5:
				result[i] = testReturn.getDices()[Color.purple.ordinal()].getValue();
				break;
			case 6:
				result[i] = testReturn.getDices()[Color.green.ordinal()].getValue();
				break;
			case 7:
				result[i] = testReturn.getDices()[Color.white.ordinal()].getValue();
				break;
			case 8:
				result[i] = 0;
				for (Dice dice : testReturn.getDices()) {
					if (dice.getField() == 0) {
						result[i] = dice.getColor().ordinal() + 1;
						break;
					}
				}
				break;
			case 9:
				result[i] = 0;
				for (Dice dice : testReturn.getDices()) {
					if (dice.getField() == 0) {
						result[i] = dice.getColor().ordinal() + 1;
						break;
					}
				}
				break;
			case 10:
				result[i] = 0;
				for (Dice dice : testReturn.getDices()) {
					if (dice.getField() == 0) {
						result[i] = dice.getColor().ordinal() + 1;
						break;
					}
				}
				break;
			case 11:
				if (testReturn.getDices()[Color.blue.ordinal()].isOnTray()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 12:
				if (testReturn.getDices()[Color.yellow.ordinal()].isOnTray()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 13:
				if (testReturn.getDices()[Color.orange.ordinal()].isOnTray()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 14:
				if (testReturn.getDices()[Color.purple.ordinal()].isOnTray()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 15:
				if (testReturn.getDices()[Color.green.ordinal()].isOnTray()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 16:
				if (testReturn.getDices()[Color.white.ordinal()].isOnTray()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 17:
				result[i] = testReturn.getMatchfield(0).getDiceRepeatCount();
				break;

			case 18:
				result[i] = testReturn.getMatchfield(0).getDiceRepeatUsed();
				break;
			case 19:
				result[i] = testReturn.getMatchfield(0).getAdditionalDiceCount();
				break;
			case 20:
				result[i] = testReturn.getMatchfield(0).getAdditionalDiceUsed();
				break;
			case 21:
				if (testReturn.getMatchfield(0).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 22:
				if (testReturn.getMatchfield(0).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 23:
				if (testReturn.getMatchfield(0).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 24:
				if (testReturn.getMatchfield(0).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 25:
				if (testReturn.getMatchfield(0).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 26:
				if (testReturn.getMatchfield(0).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 27:
				if (testReturn.getMatchfield(0).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 28:
				if (testReturn.getMatchfield(0).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 29:
				if (testReturn.getMatchfield(0).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 30:
				if (testReturn.getMatchfield(0).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 31:
				if (testReturn.getMatchfield(0).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 32:
				if (testReturn.getMatchfield(0).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 33:
				if (testReturn.getMatchfield(0).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 34:
				if (testReturn.getMatchfield(0).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 35:
				if (testReturn.getMatchfield(0).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 36:
				if (testReturn.getMatchfield(0).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 37:
				if (testReturn.getMatchfield(0).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 38:
				if (testReturn.getMatchfield(0).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 39:
				if (testReturn.getMatchfield(0).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 40:
				if (testReturn.getMatchfield(0).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 41:
				if (testReturn.getMatchfield(0).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 42:
				if (testReturn.getMatchfield(0).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 43:
				if (testReturn.getMatchfield(0).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 44:
				result[i] = testReturn.getMatchfield(0).getGreen();
				break;
			case 45:
				result[i] = testReturn.getMatchfield(0).getOrange()[0];
				break;
			case 46:
				result[i] = testReturn.getMatchfield(0).getOrange()[1];
				break;
			case 47:
				result[i] = testReturn.getMatchfield(0).getOrange()[2];
				break;
			case 48:
				result[i] = testReturn.getMatchfield(0).getOrange()[3];
				break;
			case 49:
				result[i] = testReturn.getMatchfield(0).getOrange()[4];
				break;
			case 50:
				result[i] = testReturn.getMatchfield(0).getOrange()[5];
				break;
			case 51:
				result[i] = testReturn.getMatchfield(0).getOrange()[6];
				break;
			case 52:
				result[i] = testReturn.getMatchfield(0).getOrange()[7];
				break;
			case 53:
				result[i] = testReturn.getMatchfield(0).getOrange()[8];
				break;
			case 54:
				result[i] = testReturn.getMatchfield(0).getOrange()[9];
				break;
			case 55:
				result[i] = testReturn.getMatchfield(0).getOrange()[10];
				break;
			case 56:
				result[i] = testReturn.getMatchfield(0).getPurple()[0];
				break;
			case 57:
				result[i] = testReturn.getMatchfield(0).getPurple()[1];
				break;
			case 58:
				result[i] = testReturn.getMatchfield(0).getPurple()[2];
				break;
			case 59:
				result[i] = testReturn.getMatchfield(0).getPurple()[3];
				break;
			case 60:
				result[i] = testReturn.getMatchfield(0).getPurple()[4];
				break;
			case 61:
				result[i] = testReturn.getMatchfield(0).getPurple()[5];
				break;
			case 62:
				result[i] = testReturn.getMatchfield(0).getPurple()[6];
				break;
			case 63:
				result[i] = testReturn.getMatchfield(0).getPurple()[7];
				break;
			case 64:
				result[i] = testReturn.getMatchfield(0).getPurple()[8];
				break;
			case 65:
				result[i] = testReturn.getMatchfield(0).getPurple()[9];
				break;
			case 66:
				result[i] = testReturn.getMatchfield(0).getPurple()[10];
				break;
			case 67:
				result[i] = 0;
				if (testReturn.getClickable(0).isDiceRepeat()) {
					result[i] = 1;
				}
				break;
			case 68:
				result[i] = 0;
				if (testReturn.getClickable(0).isAdditionalDice()) {
					result[i] = 1;
				}
				break;
			case 69:
				result[i] = 0;
				if (testReturn.getClickable(0).isRollDices()) {
					result[i] = 1;
				}
				break;
				/*
			case 70:
				result[i] = 0;
				if (testReturn.getClickable(0).) {
					result[i] = 1;
				}
				break;
			case 71:
				result[i] = 0;
				if (testReturn.getClickable(0).) {
					result[i] = 1;
				}
				break;
			case 72:
				result[i] = 0;
				if (testReturn.getClickable(0).isRollDices()) {
					result[i] = 1;
				}
				break;
				*/
				//TODO
			case 73:
				if (testReturn.getClickable(0).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 74:
				if (testReturn.getClickable(0).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 75:
				if (testReturn.getClickable(0).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 76:
				if (testReturn.getClickable(0).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 77:
				if (testReturn.getClickable(0).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 78:
				if (testReturn.getClickable(0).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 79:
				if (testReturn.getClickable(0).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 80:
				if (testReturn.getClickable(0).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 81:
				if (testReturn.getClickable(0).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 82:
				if (testReturn.getClickable(0).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 83:
				if (testReturn.getClickable(0).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 84:
				if (testReturn.getClickable(0).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 85:
				if (testReturn.getClickable(0).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 86:
				if (testReturn.getClickable(0).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 87:
				if (testReturn.getClickable(0).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 88:
				if (testReturn.getClickable(0).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 89:
				if (testReturn.getClickable(0).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 90:
				if (testReturn.getClickable(0).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 91:
				if (testReturn.getClickable(0).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 92:
				if (testReturn.getClickable(0).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 93:
				if (testReturn.getClickable(0).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 94:
				if (testReturn.getClickable(0).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 95:
				if (testReturn.getClickable(0).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 96:
				if (testReturn.getClickable(0).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 97:
				if (testReturn.getClickable(0).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 98:
				if (testReturn.getClickable(0).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 99:
				result[i] = testReturn.getMatchfield(1).getDiceRepeatCount();
				break;
			case 100:
				result[i] = testReturn.getMatchfield(1).getDiceRepeatUsed();
				break;
			case 101:
				result[i] = testReturn.getMatchfield(1).getAdditionalDiceCount();
				break;
			case 102:
				result[i] = testReturn.getMatchfield(1).getAdditionalDiceUsed();
				break;
			case 103:
				if (testReturn.getMatchfield(1).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 104:
				if (testReturn.getMatchfield(1).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 105:
				if (testReturn.getMatchfield(1).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 106:
				if (testReturn.getMatchfield(1).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 107:
				if (testReturn.getMatchfield(1).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 108:
				if (testReturn.getMatchfield(1).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 109:
				if (testReturn.getMatchfield(1).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 110:
				if (testReturn.getMatchfield(1).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 111:
				if (testReturn.getMatchfield(1).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 112:
				if (testReturn.getMatchfield(1).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 113:
				if (testReturn.getMatchfield(1).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 114:
				if (testReturn.getMatchfield(1).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 115:
				if (testReturn.getMatchfield(1).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 116:
				if (testReturn.getMatchfield(1).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 117:
				if (testReturn.getMatchfield(1).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 118:
				if (testReturn.getMatchfield(1).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 119:
				if (testReturn.getMatchfield(1).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 120:
				if (testReturn.getMatchfield(1).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 121:
				if (testReturn.getMatchfield(1).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 122:
				if (testReturn.getMatchfield(1).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 123:
				if (testReturn.getMatchfield(1).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 124:
				if (testReturn.getMatchfield(1).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 125:
				if (testReturn.getMatchfield(1).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 126:
				result[i] = testReturn.getMatchfield(1).getGreen();
				break;
			case 127:
				result[i] = testReturn.getMatchfield(1).getOrange()[0];
				break;
			case 128:
				result[i] = testReturn.getMatchfield(1).getOrange()[1];
				break;
			case 129:
				result[i] = testReturn.getMatchfield(1).getOrange()[2];
				break;
			case 130:
				result[i] = testReturn.getMatchfield(1).getOrange()[3];
				break;
			case 131:
				result[i] = testReturn.getMatchfield(1).getOrange()[4];
				break;
			case 132:
				result[i] = testReturn.getMatchfield(1).getOrange()[5];
				break;
			case 133:
				result[i] = testReturn.getMatchfield(1).getOrange()[6];
				break;
			case 134:
				result[i] = testReturn.getMatchfield(1).getOrange()[7];
				break;
			case 135:
				result[i] = testReturn.getMatchfield(1).getOrange()[8];
				break;
			case 136:
				result[i] = testReturn.getMatchfield(1).getOrange()[9];
				break;
			case 137:
				result[i] = testReturn.getMatchfield(1).getOrange()[10];
				break;
			case 138:
				result[i] = testReturn.getMatchfield(1).getPurple()[0];
				break;
			case 139:
				result[i] = testReturn.getMatchfield(1).getPurple()[1];
				break;
			case 140:
				result[i] = testReturn.getMatchfield(1).getPurple()[2];
				break;
			case 141:
				result[i] = testReturn.getMatchfield(1).getPurple()[3];
				break;
			case 142:
				result[i] = testReturn.getMatchfield(1).getPurple()[4];
				break;
			case 143:
				result[i] = testReturn.getMatchfield(1).getPurple()[5];
				break;
			case 144:
				result[i] = testReturn.getMatchfield(1).getPurple()[6];
				break;
			case 145:
				result[i] = testReturn.getMatchfield(1).getPurple()[7];
				break;
			case 146:
				result[i] = testReturn.getMatchfield(1).getPurple()[8];
				break;
			case 147:
				result[i] = testReturn.getMatchfield(1).getPurple()[9];
				break;
			case 148:
				result[i] = testReturn.getMatchfield(1).getPurple()[10];
				break;
			case 149:
				result[i] = 0;
				if (testReturn.getClickable(1).isDiceRepeat()) {
					result[i] = 1;
				}
				break;
			case 150:
				result[i] = 0;
				if (testReturn.getClickable(1).isAdditionalDice()) {
					result[i] = 1;
				}
				break;
			case 151:
				result[i] = 0;
				if (testReturn.getClickable(1).isRollDices()) {
					result[i] = 1;
				}
				break;
				/*
			case 152:
				result[i] = 0;
				if (testReturn.getClickable(1).) {
					result[i] = 1;
				}
				break;
			case 153:
				result[i] = 0;
				if (testReturn.getClickable(1).) {
					result[i] = 1;
				}
				break;
			case 154:
				result[i] = 0;
				if (testReturn.getClickable(1).isRollDices()) {
					result[i] = 1;
				}
				break;
				*/
				//TODO

			case 155:
				if (testReturn.getClickable(1).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 156:
				if (testReturn.getClickable(1).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 157:
				if (testReturn.getClickable(1).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 158:
				if (testReturn.getClickable(1).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 159:
				if (testReturn.getClickable(1).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 160:
				if (testReturn.getClickable(1).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 161:
				if (testReturn.getClickable(1).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 162:
				if (testReturn.getClickable(1).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 163:
				if (testReturn.getClickable(1).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 164:
				if (testReturn.getClickable(1).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 165:
				if (testReturn.getClickable(1).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 166:
				if (testReturn.getClickable(1).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 167:
				if (testReturn.getClickable(1).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 168:
				if (testReturn.getClickable(1).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 169:
				if (testReturn.getClickable(1).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 170:
				if (testReturn.getClickable(1).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 171:
				if (testReturn.getClickable(1).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 172:
				if (testReturn.getClickable(1).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 173:
				if (testReturn.getClickable(1).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 174:
				if (testReturn.getClickable(1).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 175:
				if (testReturn.getClickable(1).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 176:
				if (testReturn.getClickable(1).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 177:
				if (testReturn.getClickable(1).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 178:
				if (testReturn.getClickable(1).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 179:
				if (testReturn.getClickable(1).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 180:
				if (testReturn.getClickable(1).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 181:
				result[i] = testReturn.getMatchfield(2).getDiceRepeatCount();
				break;
			case 182:
				result[i] = testReturn.getMatchfield(2).getDiceRepeatUsed();
				break;
			case 183:
				result[i] = testReturn.getMatchfield(2).getAdditionalDiceCount();
				break;
			case 184:
				result[i] = testReturn.getMatchfield(2).getAdditionalDiceUsed();
				break;
			case 185:
				if (testReturn.getMatchfield(2).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 186:
				if (testReturn.getMatchfield(2).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 187:
				if (testReturn.getMatchfield(2).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 188:
				if (testReturn.getMatchfield(2).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 189:
				if (testReturn.getMatchfield(2).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 190:
				if (testReturn.getMatchfield(2).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 191:
				if (testReturn.getMatchfield(2).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 192:
				if (testReturn.getMatchfield(2).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 193:
				if (testReturn.getMatchfield(2).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 194:
				if (testReturn.getMatchfield(2).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 195:
				if (testReturn.getMatchfield(2).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 196:
				if (testReturn.getMatchfield(2).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 197:
				if (testReturn.getMatchfield(2).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 198:
				if (testReturn.getMatchfield(2).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 199:
				if (testReturn.getMatchfield(2).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 200:
				if (testReturn.getMatchfield(2).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 201:
				if (testReturn.getMatchfield(2).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 202:
				if (testReturn.getMatchfield(2).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 203:
				if (testReturn.getMatchfield(2).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 204:
				if (testReturn.getMatchfield(2).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 205:
				if (testReturn.getMatchfield(2).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 206:
				if (testReturn.getMatchfield(2).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 207:
				if (testReturn.getMatchfield(2).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 208:
				result[i] = testReturn.getMatchfield(2).getGreen();
				break;
			case 209:
				result[i] = testReturn.getMatchfield(2).getOrange()[0];
				break;
			case 210:
				result[i] = testReturn.getMatchfield(2).getOrange()[1];
				break;
			case 211:
				result[i] = testReturn.getMatchfield(2).getOrange()[2];
				break;
			case 212:
				result[i] = testReturn.getMatchfield(2).getOrange()[3];
				break;
			case 213:
				result[i] = testReturn.getMatchfield(2).getOrange()[4];
				break;
			case 214:
				result[i] = testReturn.getMatchfield(2).getOrange()[5];
				break;
			case 215:
				result[i] = testReturn.getMatchfield(2).getOrange()[6];
				break;
			case 216:
				result[i] = testReturn.getMatchfield(2).getOrange()[7];
				break;
			case 217:
				result[i] = testReturn.getMatchfield(2).getOrange()[8];
				break;
			case 218:
				result[i] = testReturn.getMatchfield(2).getOrange()[9];
				break;
			case 219:
				result[i] = testReturn.getMatchfield(2).getOrange()[10];
				break;
			case 220:
				result[i] = testReturn.getMatchfield(2).getPurple()[0];
				break;
			case 221:
				result[i] = testReturn.getMatchfield(2).getPurple()[1];
				break;
			case 222:
				result[i] = testReturn.getMatchfield(2).getPurple()[2];
				break;
			case 223:
				result[i] = testReturn.getMatchfield(2).getPurple()[3];
				break;
			case 224:
				result[i] = testReturn.getMatchfield(2).getPurple()[4];
				break;
			case 225:
				result[i] = testReturn.getMatchfield(2).getPurple()[5];
				break;
			case 226:
				result[i] = testReturn.getMatchfield(2).getPurple()[6];
				break;
			case 227:
				result[i] = testReturn.getMatchfield(2).getPurple()[7];
				break;
			case 228:
				result[i] = testReturn.getMatchfield(2).getPurple()[8];
				break;
			case 229:
				result[i] = testReturn.getMatchfield(2).getPurple()[9];
				break;
			case 230:
				result[i] = testReturn.getMatchfield(2).getPurple()[10];
				break;
			case 231:
				result[i] = 0;
				if (testReturn.getClickable(2).isDiceRepeat()) {
					result[i] = 1;
				}
				break;
			case 232:
				result[i] = 0;
				if (testReturn.getClickable(2).isAdditionalDice()) {
					result[i] = 1;
				}
				break;
			case 233:
				result[i] = 0;
				if (testReturn.getClickable(2).isRollDices()) {
					result[i] = 1;
				}
				break;
				/*
			case 234:
				result[i] = 0;
				if (testReturn.getClickable(2).) {
					result[i] = 1;
				}
				break;
			case 235:
				result[i] = 0;
				if (testReturn.getClickable(2).) {
					result[i] = 1;
				}
				break;
			case 236:
				result[i] = 0;
				if (testReturn.getClickable(2).isRollDices()) {
					result[i] = 1;
				}
				break;
				*/
				//TODO
			case 237:
				if (testReturn.getClickable(2).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 238:
				if (testReturn.getClickable(2).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 239:
				if (testReturn.getClickable(2).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 240:
				if (testReturn.getClickable(2).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 241:
				if (testReturn.getClickable(2).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 242:
				if (testReturn.getClickable(2).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 243:
				if (testReturn.getClickable(2).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 244:
				if (testReturn.getClickable(2).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 245:
				if (testReturn.getClickable(2).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 246:
				if (testReturn.getClickable(2).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 247:
				if (testReturn.getClickable(2).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 248:
				if (testReturn.getClickable(2).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 249:
				if (testReturn.getClickable(2).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 250:
				if (testReturn.getClickable(2).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 251:
				if (testReturn.getClickable(2).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 252:
				if (testReturn.getClickable(2).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 253:
				if (testReturn.getClickable(2).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 254:
				if (testReturn.getClickable(2).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 255:
				if (testReturn.getClickable(2).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 256:
				if (testReturn.getClickable(2).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 257:
				if (testReturn.getClickable(2).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 258:
				if (testReturn.getClickable(2).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 259:
				if (testReturn.getClickable(2).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 260:
				if (testReturn.getClickable(2).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 261:
				if (testReturn.getClickable(2).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 262:
				if (testReturn.getClickable(2).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break; 
			case 263:
				result[i] = testReturn.getMatchfield(3).getDiceRepeatCount();
				break;
			case 264:
				result[i] = testReturn.getMatchfield(3).getDiceRepeatUsed();
				break;
			case 265:
				result[i] = testReturn.getMatchfield(3).getAdditionalDiceCount();
				break;
			case 266:
				result[i] = testReturn.getMatchfield(3).getAdditionalDiceUsed();
				break;
			case 267:
				if (testReturn.getMatchfield(3).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 268:
				if (testReturn.getMatchfield(3).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 269:
				if (testReturn.getMatchfield(3).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 270:
				if (testReturn.getMatchfield(3).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 271:
				if (testReturn.getMatchfield(3).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 272:
				if (testReturn.getMatchfield(3).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 273:
				if (testReturn.getMatchfield(3).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 274:
				if (testReturn.getMatchfield(3).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 275:
				if (testReturn.getMatchfield(3).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 276:
				if (testReturn.getMatchfield(3).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 277:
				if (testReturn.getMatchfield(3).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 278:
				if (testReturn.getMatchfield(3).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 279:
				if (testReturn.getMatchfield(3).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 280:
				if (testReturn.getMatchfield(3).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 281:
				if (testReturn.getMatchfield(3).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 282:
				if (testReturn.getMatchfield(3).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 283:
				if (testReturn.getMatchfield(3).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 284:
				if (testReturn.getMatchfield(3).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 285:
				if (testReturn.getMatchfield(3).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 286:
				if (testReturn.getMatchfield(3).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 287:
				if (testReturn.getMatchfield(3).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 288:
				if (testReturn.getMatchfield(3).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 289:
				if (testReturn.getMatchfield(3).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 290:
				result[i] = testReturn.getMatchfield(3).getGreen();
				break;
			case 291:
				result[i] = testReturn.getMatchfield(3).getOrange()[0];
				break;
			case 292:
				result[i] = testReturn.getMatchfield(3).getOrange()[1];
				break;
			case 293:
				result[i] = testReturn.getMatchfield(3).getOrange()[2];
				break;
			case 294:
				result[i] = testReturn.getMatchfield(3).getOrange()[3];
				break;
			case 295:
				result[i] = testReturn.getMatchfield(3).getOrange()[4];
				break;
			case 296:
				result[i] = testReturn.getMatchfield(3).getOrange()[5];
				break;
			case 297:
				result[i] = testReturn.getMatchfield(3).getOrange()[6];
				break;
			case 298:
				result[i] = testReturn.getMatchfield(3).getOrange()[7];
				break;
			case 299:
				result[i] = testReturn.getMatchfield(3).getOrange()[8];
				break;
			case 300:
				result[i] = testReturn.getMatchfield(3).getOrange()[8];
				break;
			case 301:
				result[i] = testReturn.getMatchfield(3).getOrange()[10];
				break;
			case 302:
				result[i] = testReturn.getMatchfield(3).getPurple()[0];
				break;
			case 303:
				result[i] = testReturn.getMatchfield(3).getPurple()[1];
				break;
			case 304:
				result[i] = testReturn.getMatchfield(3).getPurple()[2];
				break;
			case 305:
				result[i] = testReturn.getMatchfield(3).getPurple()[3];
				break;
			case 306:
				result[i] = testReturn.getMatchfield(3).getPurple()[4];
				break;
			case 307:
				result[i] = testReturn.getMatchfield(3).getPurple()[5];
				break;
			case 308:
				result[i] = testReturn.getMatchfield(3).getPurple()[6];
				break;
			case 309:
				result[i] = testReturn.getMatchfield(3).getPurple()[7];
				break;
			case 310:
				result[i] = testReturn.getMatchfield(3).getPurple()[8];
				break;
			case 311:
				result[i] = testReturn.getMatchfield(3).getPurple()[9];
				break;
			case 312:
				result[i] = testReturn.getMatchfield(3).getPurple()[10];
				break;
			case 313:
				result[i] = 0;
				if (testReturn.getClickable(3).isDiceRepeat()) {
					result[i] = 1;
				}
				break;
			case 314:
				result[i] = 0;
				if (testReturn.getClickable(3).isAdditionalDice()) {
					result[i] = 1;
				}
				break;
			case 315:
				result[i] = 0;
				if (testReturn.getClickable(3).isRollDices()) {
					result[i] = 1;
				}
				break;
				/*
			case 316:
				result[i] = 0;
				if (testReturn.getClickable(3).) {
					result[i] = 1;
				}
				break;
			case 317:
				result[i] = 0;
				if (testReturn.getClickable(3).) {
					result[i] = 1;
				}
				break;
			case 318:
				result[i] = 0;
				if (testReturn.getClickable(3).isRollDices()) {
					result[i] = 1;
				}
				break;
				*/
				//TODO
			case 319:
				if (testReturn.getClickable(3).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 320:
				if (testReturn.getClickable(3).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 321:
				if (testReturn.getClickable(2).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 322:
				if (testReturn.getClickable(3).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 323:
				if (testReturn.getClickable(3).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 324:
				if (testReturn.getClickable(3).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 325:
				if (testReturn.getClickable(3).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 326:
				if (testReturn.getClickable(3).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 327:
				if (testReturn.getClickable(3).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 328:
				if (testReturn.getClickable(3).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 329:
				if (testReturn.getClickable(3).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 330:
				if (testReturn.getClickable(3).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 331:
				if (testReturn.getClickable(3).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 332:
				if (testReturn.getClickable(3).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 333:
				if (testReturn.getClickable(3).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 334:
				if (testReturn.getClickable(3).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 335:
				if (testReturn.getClickable(3).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 336:
				if (testReturn.getClickable(3).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 337:
				if (testReturn.getClickable(3).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 338:
				if (testReturn.getClickable(3).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 339:
				if (testReturn.getClickable(3).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 340:
				if (testReturn.getClickable(3).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 341:
				if (testReturn.getClickable(3).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 342:
				if (testReturn.getClickable(0).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 343:
				if (testReturn.getClickable(0).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			case 344:
				if (testReturn.getClickable(0).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				break;
			}
		}

		setGameStatus(result);
		return gameStatus;
	}

	private void setGameStatus(int[] gameStatus) {
		this.gameStatus = gameStatus;
	}

	/*
	 * Verarbeitet die sendDataToServer() methoden aus JS
	 */
	@Override
	public void execute(User user, String gsonString) {
		if (this.gState == GameState.CLOSED)
			return;

		if (gsonString.equals("CLOSE")) {
			sendGameDataToClients("CLOSE");
			closeGame();
			return;
		}

		if (gsonString.equals("RESTART")) {
			if (playerList.size() == 0)
				return;
			setGameStatus(this.getNewGame());
			this.gState = GameState.RUNNING;
			sendGameDataToClients("standardEvent");
			return;
		}

		if (gsonString.equals("ADDKI")) {
			// TODO
		}

		if (gsonString.equals("STARTGAME")) {
			this.gState = GameState.RUNNING;
			currentGame = new MainLogic(this.getCurrentPlayerAmount());
			gameStatus = this.getNewGame();
			sendGameDataToClients("NEWGAME");
		}

		// Bei Bedarf:
		/*
		 * if (gState != GameState.RUNNING) return;
		 */

		// Koennen wir so wahrscheinlich nicht machen, je nachdem wie unsere
		// Logic Zug
		// definiert
		/*
		 * if (!user.equals(playerTurn)) { return; }
		 */

		// Die Folgen Methoden sind Templates, die k�nnen im COde auch weiter
		// runter
		// verschoben werden
		if (gsonString.equals("WUERFELN")) {
			sendGameDataToClients("TESTWUERFELN");
			return;
			// ACHTUNG: Wenn keine Game DATA Gebraucht wird, bitte nicht nach
			// unten springen lassen sondern returnen.
		}

		if (gsonString.equals("NACHWUERFELN")) {
			// TODO
		}

		if (gsonString.equals("ZUSATZWUERFELN")) {
			// TODO
		}

		if (gsonString.equals("SKIP")) {
			// TODO
		}

		String[] strArray = gsonString.split(",");
		int[] receivedArray = new int[345];
		for (int i = 0; i < 345; i++) {
			receivedArray[i] = Integer.parseInt(strArray[i]);
		}
		int[] gameStatus = getGameStatus();
		boolean changed = false;
		for (int i = 0; i < 345; i++) {
			if (gameStatus[i] == 0 && receivedArray[i] != 0) {
				changed = true;
				break;
			}
		}
		if (changed == true) {
			// TODO
		}

		// ist nur temporaer hier. Beendet die Methode wenn noch nicht
		// behandelte faelle
		// eintreten, sonst schmeisst der server einen Fehler
		return;
	}

	private int[] getNewGame() {
		int[] result = new int[345];
		for (int i = 0; i < 345; i++) {
			if (i == 0)
				result[i] = 1;

			else
				result[i] = 0;
		}
		result[69] = 1;
		return result;
	}

	@Override
	public ArrayList<User> getPlayerList() {
		return this.playerList;
	}

	@Override
	public ArrayList<User> getSpectatorList() {
		return this.spectatorList;
	}

	/*
	 * Hier senden wir die Daten an die Clients. Unter anderem das Array[212] wir
	 * koennen aber auch noch nachrichten dranhaengen
	 */
	@Override
	public String getGameData(String eventName, User user) {

		String gameData = "";
		if (eventName.equals("PLAYERLEFT")) {
			return playerLeft + " hat das Spiel verlassen!";
		}
		if (eventName.equals("CLOSE")) {
			return "CLOSE";
		}

		if (eventName.equals("TESTWUERFELN")) {
			String testwuerfel = "";
			testwuerfel += Integer.toString(ThreadLocalRandom.current().nextInt(1, 6 + 1));
			testwuerfel += ',';
			testwuerfel += Integer.toString(ThreadLocalRandom.current().nextInt(7, 12 + 1));
			testwuerfel += ',';
			testwuerfel += Integer.toString(ThreadLocalRandom.current().nextInt(13, 18 + 1));
			testwuerfel += ',';
			testwuerfel += Integer.toString(ThreadLocalRandom.current().nextInt(19, 24 + 1));
			testwuerfel += ',';
			testwuerfel += Integer.toString(ThreadLocalRandom.current().nextInt(25, 30 + 1));
			testwuerfel += ',';
			testwuerfel += Integer.toString(ThreadLocalRandom.current().nextInt(31, 36 + 1));
			return testwuerfel;
		}

		int[] grid = getGameStatus();

		for (int i = 0; i < 217; i++) {
			gameData += String.valueOf(grid[i]);
			gameData += ',';
		}

		// TODO (hier koenen wir jetzt anhaengen)

		gameData += isHost(user);

		return gameData;

	}

	@Override
	public void addUser(User user) {

		if (playerList.size() < 4 && !playerList.contains(user)) {
			playerList.add(user);

			if (playerTurn == null) {
				playerTurn = user;
			}
			sendGameDataToClients("START");
		}
		if (playerList.size() == 4) {
			this.gState = GameState.RUNNING;
			sendGameDataToClients("START");
		}
	}

	public void addKI(KI ki) {
		if (playerList.size() < 4 && !playerList.contains(ki)) {
			this.addUser(ki);
			;
		}
	}

	@Override
	public void addSpectator(User user) {
		this.spectatorList.add(user);
	}

	@Override
	public boolean isJoinable() {
		if (playerList.size() < 4) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void playerLeft(User user) {
		playerList.remove(user);
		playerLeft = user.getName();
		sendGameDataToClients("PLAYERLEFT");
	}

	private String isHost(User user) {
		if (user == creator)
			return ",HOST";
		else
			return ",NOTTHEHOST";
	}

	@Override
	public GameState getGameState() {
		return this.gState;
	}

}
