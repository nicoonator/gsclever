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
import games.gsClever.Logic.Player;
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
	private ArrayList<Player> userList = new ArrayList<Player>();
	private ArrayList<User> spectatorList = new ArrayList<User>();
	private String playerLeft = null;
	private String users = "";
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
		return "<script src=\"javascript/gsClever.js\"></script>"
				+ " <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>";
	}

	@Override
	public int getMaxPlayerAmount() {
		return 4;
	}

	@Override
	public int getCurrentPlayerAmount() {
		return playerList.size();
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

		if (gsonString.equals("STARTGAME") && userList.size() >= 2) {
			this.gState = GameState.RUNNING;
			currentGame = new MainLogic(this.userList);
			gameStatus = this.getNewGame();
			sendGameDataToClients("NEWGAME");
			sendGameDataToClients("STARTARRAY");
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
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}

		if (gsonString.equals("NACHWUERFELN")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}

		if (gsonString.equals("ZUSATZWUERFELN")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}

		if (gsonString.equals("SKIP")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("COLORDECIDER")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("WHITEDECIDER")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW0")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW1")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW2")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW3")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW4")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW5")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW6")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW7")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW8")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW9")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW10")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKYELLOW11")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE0")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE1")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE2")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE3")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE4")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE5")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE6")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE7")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE8")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE9")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKBLUE10")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKGREEN")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKORANGE")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
		if (gsonString.equals("CLICKPURPLE")) {
			sendGameDataToClients("SUBMITGAME");
			// TODO
			return;
		}
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
		result[1] = 1;
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
	 * Hier senden wir die Daten an die Clients. Unter anderem das Array[345] wir
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

		if (eventName.equals("USERJOINED")) {
			gameData = users;
			return gameData;
		}
		if (eventName.equals("NEWGAME")) {
			gameData = Integer.toString(userList.size());
			for (int i = 0; i < playerList.size(); i++) {
				if (playerList.get(i).getName() == user.getName()) {
					gameData += "," + Integer.toString(i + 1);
				}
			}
			return gameData;
		}
		if (eventName.equals("STARTARRAY")) {
			for (int i = 0; i < 345; i++) {
				gameData += getNewGame()[i];
				if (i < 344) {
					gameData += ",";
				}
			}
			return gameData;
		}

		if (eventName.equals("SUBMITGAME")) {
			int[] temp = this.getGameStatus();
			for (int i = 0; i < 345; i++) {
				gameData += temp[i];
				if (i < 344) {
					gameData += ",";
				}
			}
			return gameData;
		}
		return gameData;

	}

	@Override
	public void addUser(User user) {

		if (playerList.size() < 4 && !playerList.contains(user)) {

			playerList.add(user);
			Player p = new Player(user);
			userList.add(p);
			if (users.equals("")) {
				users = user.getName();
			} else {
				users = users + "," + user.getName();
			}
			sendGameDataToClients("USERJOINED");
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

			case 70:
				result[i] = 0;
				if (testReturn.getDecisionMaker(0).isColorDiceActive()) {
					result[i] = 1;
				}
				break;
			case 71:
				result[i] = 0;
				if (testReturn.getDecisionMaker(0).isWhiteDiceActive()) {
					result[i] = 1;
				}
				break;
			case 72:
				result[i] = 0;
				if (result[i - 2] == 1) {
					result[i] = testReturn.getDecisionMaker(0).getColorOfDice().ordinal() + 1;
				}
				break;
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
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
					result[i] = testReturn.getMatchfield(1).getDiceRepeatCount();
				}
				break;
			case 100:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
					result[i] = testReturn.getMatchfield(1).getDiceRepeatUsed();
				}
				break;
			case 101:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
					result[i] = testReturn.getMatchfield(1).getAdditionalDiceCount();
				}
				break;
			case 102:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
					result[i] = testReturn.getMatchfield(1).getAdditionalDiceUsed();
				}
				break;
			case 103:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
					if (testReturn.getMatchfield(1).getYellow()[0]) {
						result[i] = 1;
					} else
						result[i] = 0;
				}
				break;
			case 104:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
					if (testReturn.getMatchfield(1).getYellow()[1]) {
						result[i] = 1;
					} else
						result[i] = 0;
				}
				break;
			case 105:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 106:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 107:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 108:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 109:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 110:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 111:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 112:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 113:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 114:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 115:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 116:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 117:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 118:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 119:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 120:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 121:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 122:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 123:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 124:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 125:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getMatchfield(1).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 126:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getGreen();
				}
				break;
			case 127:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[0];
				}
				break;
			case 128:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[1];
				
				}
				break;
			case 129:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[2];
				}
				break;
			case 130:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[3];
				}
				break;
			case 131:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[4];
				}
				break;
			case 132:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[5];
				}
				break;
			case 133:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[6];
				}
				break;
			case 134:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[7];
				}
				break;
			case 135:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[8];
				}
				break;
			case 136:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[9];
				}
				break;
			case 137:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getOrange()[10];
				}
				break;
			case 138:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[0];
				}
				break;
			case 139:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[1];
				}
				break;
			case 140:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[2];
				}
				break;
			case 141:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[3];
				}
				break;
			case 142:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[4];
				}
				break;
			case 143:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[5];
				}
				break;
			case 144:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[6];
				}
				break;
			case 145:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[7];
				}
				break;
			case 146:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[8];
				}
				break;
			case 147:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[9];
				}
				break;
			case 148:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				result[i] = testReturn.getMatchfield(1).getPurple()[10];
				}
				break;
			case 149:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).isDiceRepeat()) {
					result[i] = 1;
				}
				}
				break;
			case 150:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).isAdditionalDice()) {
					result[i] = 1;
				}
				}
				break;
			case 151:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).isRollDices()) {
					result[i] = 1;
				}
				}
				break;

			// TODO

			case 152:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1)) {
					result[i] = 1;
				}
				}
				break;
			case 153:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1)) {
					result[i] = 1;
				}
				}
				break;
			case 154:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).isRollDices()) {
					result[i] = 1;
				}
				}
				break;

			// TODO

			case 155:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 156:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 157:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 158:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 159:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 160:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 161:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 162:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 163:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 164:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 165:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 166:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 167:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 168:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 169:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 170:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 171:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 172:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 173:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 174:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 175:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 176:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 177:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 178:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 179:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 180:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 1) {
				if (testReturn.getClickable(1).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			// BEGIN SPIELER 3
			case 181:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
					result[i] = testReturn.getMatchfield(2).getDiceRepeatCount();
				}
				break;
			case 182:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getDiceRepeatUsed();
				}
				break;
			case 183:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getAdditionalDiceCount();
				}
				break;
			case 184:result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getAdditionalDiceUsed();
				}
				break;
			case 185:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 186:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 187:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 188:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 189:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 190:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 191:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 192:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 193:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 194:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 195:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 196:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 197:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 198:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 199:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 200:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 201:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 202:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 203:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 204:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 205:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 206:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 207:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getMatchfield(2).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 208:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getGreen();
				}
				break;
			case 209:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[0];
				}
				break;
			case 210:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[1];
				}
				break;
			case 211:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[2];
				
				}break;
			case 212:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[3];
				}
				break;
			case 213:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[4];
				}
				break;
			case 214:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[5];
				}
				break;
			case 215:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[6];
				}
				break;
			case 216:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[7];
				}
				break;
			case 217:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[8];
				}
				break;
			case 218:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[9];
				}
				break;
			case 219:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getOrange()[10];
				}
				break;
			case 220:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[0];
				}
				break;
			case 221:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[1];
				}
				break;
			case 222:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[2];
				}
				break;
			case 223:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[3];
				}
				break;
			case 224:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[4];
				}
				break;
			case 225:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[5];
				}
				break;
			case 226:result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[6];
				}
				break;
			case 227:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[7];
				}
				break;
			case 228:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[8];
				}
				break;
			case 229:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[9];
				}
				break;
			case 230:result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				result[i] = testReturn.getMatchfield(2).getPurple()[10];
				}
				break;
			case 231:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).isDiceRepeat()) {
					result[i] = 1;
				}
				}
				break;
			case 232:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).isAdditionalDice()) {
					result[i] = 1;
				}
				}
				break;
			case 233:
				
					result[i] = 0;
					if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).isRollDices()) {
					result[i] = 1;
				}
					}
				break;
			// TODO
			case 234:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
					if (testReturn.getClickable(2)) {
				
					result[i] = 1;
				}
				}
				break;
			case 235:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
					if (testReturn.getClickable(2)) {
				
					result[i] = 1;
					}
					}
				break;
			case 236:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
					if (testReturn.getClickable(2).isRollDices()) {
				
					result[i] = 1;
				}
				}
				break;

			// TODO
			case 237:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 238:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 239:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 240:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 241:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
					if (testReturn.getClickable(2).getYellow()[4]) {
				
					result[i] = 1;
					}else
					result[i] = 0;
				}
				break;
			case 242:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 243:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
					if (testReturn.getClickable(2).getYellow()[6]) {
				
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 244:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 245:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 246:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 247:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 248:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 249:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 250:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 251:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 252:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 253:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 254:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 255:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 256:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 257:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 258:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 259:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 260:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 261:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 262:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 2) {
				if (testReturn.getClickable(2).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			// BEGIN SPIELER 4
			case 263:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				result[i] = testReturn.getMatchfield(3).getDiceRepeatCount();
				}
				break;
			case 264:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				result[i] = testReturn.getMatchfield(3).getDiceRepeatUsed();
				}
				break;
			case 265:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getAdditionalDiceCount();
				}
				break;
			case 266:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getAdditionalDiceUsed();
				}
				break;
			case 267:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 268:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 269:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 270:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 271:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 272:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 273:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 274:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 275:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 276:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 277:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 278:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 279:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 280:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 281:
				result[i] = 0;
			if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
			}
			break;
			case 282:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 283:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 284:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 285:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 286:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 287:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 288:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 289:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getMatchfield(3).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 290:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				result[i] = testReturn.getMatchfield(3).getGreen();
				}
				break;
			case 291:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[0];
				}
				break;
			case 292:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[1];
				}
				break;
			case 293:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[2];
				}
				break;
			case 294:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[3];
				}
				break;
			case 295:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[4];
				}
				break;
			case 296:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[5];
				}
				break;
			case 297:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[6];
				}
				break;
			case 298:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[7];
				}
				break;
			case 299:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[8];
				}
				break;
			case 300:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[8];
				}
				break;
			case 301:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getOrange()[10];
				}
				break;
			case 302:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[0];
				}
				break;
			case 303:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[1];
				}
				break;
			case 304:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[2];
				}
				break;
			case 305:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[3];
				}
				break;
			case 306:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[4];
				}
				break;
			case 307:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[5];
				}
				break;
			case 308:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[6];
				}
				break;
			case 309:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[7];
				}
				break;
			case 310:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[8];
				}
				break;
			case 311:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[9];
				}
				break;
			case 312:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					result[i] = testReturn.getMatchfield(3).getPurple()[10];
				}
				break;
			case 313:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).isDiceRepeat()) {
					result[i] = 1;
				}
				}
				break;
			case 314:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).isAdditionalDice()) {
					result[i] = 1;
				}
				}
				break;
			case 315:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).isRollDices()) {
					result[i] = 1;
				}
				}
				break;
			// TODO
			case 316:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3)) {
					result[i] = 1;
				}
				}
				break;
			case 317:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3)) {
					result[i] = 1;
				}
				}
				break;
			case 318:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).isRollDices()) {
					result[i] = 1;
				}
				}
				break;

			// TODO
			case 319:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 320:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
					if (testReturn.getClickable(3).getYellow()[1]) {
				
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 321:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(2).getYellow()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 322:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 323:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 324:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 325:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 326:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 327:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 328:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 329:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 330:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getYellow()[11]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 331:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[0]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 332:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[1]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 333:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[2]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 334:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[3]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 335:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[4]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 336:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[5]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 337:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[6]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 338:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[7]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 339:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[8]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 340:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[9]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 341:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(3).getBlue()[10]) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 342:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(0).getGreen()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 343:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(0).getOrange()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			case 344:
				result[i] = 0;
				if (this.getCurrentPlayerAmount() > 3) {
				if (testReturn.getClickable(0).getPurple()) {
					result[i] = 1;
				} else
					result[i] = 0;
				}
				break;
			}
		}

		setGameStatus(result);
		return gameStatus;
	}

}
