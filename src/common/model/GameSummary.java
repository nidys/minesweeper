package common.model;

import java.io.Serializable;

import common.enums.GameResult;

public class GameSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6405299736256074459L;
	private GameResult gameResult;

	public GameSummary(GameResult gameResult) {
		super();
		this.gameResult = gameResult;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}

}
