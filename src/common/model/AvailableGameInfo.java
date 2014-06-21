package common.model;

import java.io.Serializable;

import common.enums.GameMode;

public class AvailableGameInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8806991692072237822L;
	private String gameId;
	private GameMode gameMode;
	private String hostPlayerName;
	private int maxPlayers;
	private int currentlyConnectedPlayers;

	public AvailableGameInfo(String gameId, GameMode gameMode, String hostPlayerName,
			int maxPlayers, int currentlyConnectedPlayers) {
		super();
		this.gameId = gameId;
		this.gameMode = gameMode;
		this.hostPlayerName = hostPlayerName;
		this.maxPlayers = maxPlayers;
		this.currentlyConnectedPlayers = currentlyConnectedPlayers;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public String getHostPlayerName() {
		return hostPlayerName;
	}

	public void setHostPlayerName(String hostPlayerName) {
		this.hostPlayerName = hostPlayerName;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getCurrentlyConnectedPlayers() {
		return currentlyConnectedPlayers;
	}

	public void setCurrentlyConnectedPlayers(int currentlyConnectedPlayers) {
		this.currentlyConnectedPlayers = currentlyConnectedPlayers;
	}

	public String toString() {
		String result = gameId + " (" + gameMode + ") Host: " + hostPlayerName + " Players: "
				+ currentlyConnectedPlayers;
		if (maxPlayers == 0)
			return result;
		else
			return result + "/" + maxPlayers;
	}
}
