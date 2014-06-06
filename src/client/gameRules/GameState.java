package client.gameRules;

import common.gameRules.GameMode;
import common.model.Board;
import common.network.PlayerHandler;

public class GameState {
	private GameMode currentMode;
	private Board mineBoard;
	private String userNick;
	private PlayerHandler playerHandler;

	public GameMode getMode() {
		return currentMode;
	}

	public void setCurrentMode(GameMode currentMode) {
		this.currentMode = currentMode;
	}

	public void setMineBoard(Board mineBoard) {
		this.mineBoard = mineBoard;
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

}
