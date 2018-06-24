package games.gsClever;

import java.io.IOException;
import java.util.ArrayList;
import gameClasses.Game;
import gameClasses.GameState;
import global.FileHelper;
import userManagement.User;

/**
 * Game Class which get accessed by server
 * @author Nico Rychlik
 *
 */

public class gsClever extends Game {

	/*
	 * game Status
	 *  1x Spielerzahl + 1x rundenzaehler + 1x aktueller Spieler + 5x Wuefelflaechen+3x genommener wuerfel 
	 *  +4xSpielfeld:(2xNachwuerfeln(Freigeschaltet + genutzt)+2xZusatzwuerfel+12Gelb+11Blau+1Gruen+1Orange+1Lila)
	 *	Wuerfel: 1-30 ([Blau, Gelb, Gruen, Lila, Orange, Weiss] 1: 1blau 7: 1Gelb 30: 6Weiss
	 *
	 *
	 */
	private int[] gameStatus = new int[131];
	private User playerTurn = null;
	private ArrayList<User> playerList = new ArrayList<User>();
	private ArrayList<User> spectatorList = new ArrayList<User>();
	private String playerLeft = null;
	
	
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
			System.err
					.println("Loading of file gsClever/css/gsClever.css failed");
		}
		return null;
	}

	@Override
	public String getJavaScript() {
		return "<link rel=\"stylesheet\"\r\n" + 
				"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
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
	
	private int[] getGameStatus() {
		return gameStatus;
	}

	private void setGameStatus(int[] gameStatus) {
		this.gameStatus = gameStatus;
	}

	@Override
	public void execute(User user, String gsonString) {
		if(this.gState==GameState.CLOSED) return;
		
		if(gsonString.equals("CLOSE")){
			sendGameDataToClients("CLOSE");
			closeGame();
			return;
		}
		
		if (gsonString.equals("RESTART")) {
			if (playerList.size() == 0 || playerList.size() > 4) return;
			setGameStatus(new int[131]);
			this.gState = GameState.RUNNING;
			sendGameDataToClients("standardEvent");
			return;
		}
		if (gState != GameState.RUNNING)
			return;
		if (!user.equals(playerTurn)) {
			return;
		}
		
		String[] strArray = gsonString.split(",");
		int[] receivedArray = new int[131];
		for (int i = 0; i < 131; i++) {
			receivedArray[i] = Integer.parseInt(strArray[i]);
		}
		int[] gameStatus = getGameStatus();
		boolean changed = false;
		for (int i = 0; i < 132; i++) {
			if (gameStatus[i] == 0 && receivedArray[i] != 0) {
				changed = true;
				break;
			}
		}
		if (changed == true) {
			//TODO
			
			/*
			 * gameStatus[2] gibt den aktuellen Spieler an. Der nächste Spieler ist dran.
			 */
			if(gameStatus[2]<getCurrentPlayerAmount()) gameStatus[2]++;
			else gameStatus[2]=1;
			setGameStatus(gameStatus);
			
			//TODO
			sendGameDataToClients("standardEvent");
		}
		
	}

	@Override
	public ArrayList<User> getPlayerList() {
		return this.playerList;
	}

	@Override
	public ArrayList<User> getSpectatorList() {
		return this.spectatorList;
	}

	@Override
	public String getGameData(String eventName, User user) {
		
		//TODO
		String gameData = "";
		if(eventName.equals("PLAYERLEFT")){
			return playerLeft + " hat das Spiel verlassen!";
		}
		if(eventName.equals("CLOSE")){
			return "CLOSE";
		}
		
		int[] grid = getGameStatus();

		for (int i = 0; i < grid.length; i++) {
			gameData += String.valueOf(grid[i]);
			gameData += ',';
		}
		
		if(playerList.size()<2){
			gameData += "Warte Auf 2ten Spieler...";
			gameData += isHost(user);
			return gameData;
		}

		if (this.gState == GameState.FINISHED) {
			//TODO
		}

		else if (playerTurn.equals(user)) {
			gameData += "Du bist dran!";
		} else
			gameData += playerTurn.getName() + " ist dran!";

		if (playerList.indexOf(user) == 0)
			gameData += " (x)";
		else
			gameData += " (o)";
		
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
		if(user==creator) return ",HOST";
		else return ",NOTTHEHOST";
	}

	@Override
	public GameState getGameState() {
		// TODO Auto-generated method stub
		return null;
	}

}
