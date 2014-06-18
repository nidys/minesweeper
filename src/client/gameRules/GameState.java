package client.gameRules;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.network.callbacks.PlayerHandler;

public class GameState {
	private GameMode currentMode;
	private String userNick;
	private PlayerHandler playerHandler;
	private GameDifficulty difficulty;

	public GameMode getMode() {
		return currentMode;
	}

	public void setCurrentMode(GameMode currentMode) {
		this.currentMode = currentMode;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserNick() {
		return userNick;
	}

	public PlayerHandler getPlayerHandler() {
		return playerHandler;
	}

	public void setPlayerHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler;
	}

	public GameDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(GameDifficulty difficulty) {
		this.difficulty = difficulty;
	}
}
