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
	 * genutzt)+2x Zusatzwuerfel+12Gelb+11Blau+1Gruen+11Orange+11Lila) Wuerfel:
	 * 0-36 ([Blau, Gelb, Gruen, Lila, Orange, Weiss] 1: 1blau 7: 1Gelb 30:
	 * 6Weiss
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

			// TODO

			case 8:
				result[i] = 0;
				for (Dice dice : testReturn.getDices()) {
					if (dice.getField() == 0) {
						result[i] = dice.getColor().ordinal() + 1;
						break;
					}
				}
				break;

			// TODO

			case 11:
				if (testReturn.getDices()[Color.blue.ordinal()].isOnTray()) {
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

			case 21:
				if (testReturn.getMatchfield(0).getYellow()[0]) {
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

			case 73:
				if (testReturn.getClickable(0).getYellow()[0]) {
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
		result[69]=1;
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
	 * Hier senden wir die Daten an die Clients. Unter anderem das Array[212]
	 * wir koennen aber auch noch nachrichten dranhaengen
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
