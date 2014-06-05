package client.gameRules;

import common.gameRules.GameMode;

public class GameState {
	private GameMode currentMode;

	public GameMode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(GameMode currentMode) {
		this.currentMode = currentMode;
	}

}
