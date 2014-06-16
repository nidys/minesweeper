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
	private String hostUser;
	private int maxPlayers;
	private int currentlyConnectedPlayers;

	public AvailableGameInfo(String gameId, GameMode gameMode, String hostUser, int maxPlayers,
			int currentlyAttachedPlayers) {
		super();
		this.gameId = gameId;
		this.gameMode = gameMode;
		this.hostUser = hostUser;
		this.maxPlayers = maxPlayers;
		this.currentlyConnectedPlayers = currentlyAttachedPlayers;
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

	public String getHostUser() {
		return hostUser;
	}

	public void setHostUser(String hostUser) {
		this.hostUser = hostUser;
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

}
