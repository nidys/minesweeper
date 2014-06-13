package common.model;

import java.io.Serializable;
import java.util.List;

import common.network.protocols.GameLogic;

public class GameSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1084967509635332417L;
	private GameLogic engine;
	private GameDifficultyFactors factors;
	private List<String> currentlyLoggedUsers; // null for createNewGame

	public GameSettings(GameLogic engine, GameDifficultyFactors factors, List<String> currentlyLoggedUsers) {
		super();
		this.engine = engine;
		this.factors = factors;
		this.currentlyLoggedUsers = currentlyLoggedUsers;
	}

	public GameLogic getEngine() {
		return engine;
	}

	public void setEngine(GameLogic engine) {
		this.engine = engine;
	}

	public GameDifficultyFactors getFactors() {
		return factors;
	}

	public void setFactors(GameDifficultyFactors factors) {
		this.factors = factors;
	}

	public List<String> getCurrentlyLoggedUsers() {
		return currentlyLoggedUsers;
	}

	public void setCurrentlyLoggedUsers(List<String> currentlyLoggedUsers) {
		this.currentlyLoggedUsers = currentlyLoggedUsers;
	}

}
