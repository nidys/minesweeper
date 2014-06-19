package client.gameRules;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.model.GameDifficultyFactors;
import common.network.callbacks.PlayerHandler;

public class GameState {
	private GameMode currentMode;
	private String userNick;
	private PlayerHandler playerHandler;
	private GameDifficulty difficulty;
	private GameDifficultyFactors difficultyFactors;

	public GameMode getMode() {
		return currentMode;
	}

	public void setMode(GameMode currentMode) {
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

	public GameDifficultyFactors getDifficultyFactors() {
		return difficultyFactors;
	}

	public void setDifficultyFactors(GameDifficultyFactors difficultyFactors) {
		this.difficultyFactors = difficultyFactors;
	}
}
