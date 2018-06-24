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
		return "<script src=\"javascript/gsClever.js\"></script>";
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
			if (playerList.size() != 2) return;
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
		int[] receivedArray = new int[9];
		for (int i = 0; i < 9; i++) {
			receivedArray[i] = Integer.parseInt(strArray[i]);
		}
		int[] gameStatus = getGameStatus();
		boolean changed = false;
		for (int i = 0; i < 9; i++) {
			if (gameStatus[i] == 0 && receivedArray[i] != 0) {
				gameStatus[i] = playerList.indexOf(user) + 1;
				changed = true;
				break;
			}
		}
		if (changed == true) {
			for (User u : playerList) {
				if (!u.equals(playerTurn)) {
					playerTurn = u;
					break;
				}
			}
			setGameStatus(gameStatus);
			sendGameDataToClients("standardEvent");
		}
		
	}

	@Override
	public ArrayList<User> getPlayerList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getSpectatorList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameData(String eventName, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSpectator(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isJoinable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void playerLeft(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameState getGameState() {
		// TODO Auto-generated method stub
		return null;
	}

}
