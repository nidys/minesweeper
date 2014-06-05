package client.gameRules;

import common.gameRules.GameMode;
import common.model.Board;

public class GameState {
	private GameMode currentMode;
	private Board mineBoard;
	private String userNick;

	public GameMode getCurrentMode() {
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

}
