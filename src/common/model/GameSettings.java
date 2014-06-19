package common.model;

import java.io.Serializable;
import java.util.List;

import common.network.protocols.GameLogic;

public class GameSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1084967509635332417L;
	private GameDifficultyFactors factors;
	private List<String> currentlyLoggedUsers; // null for createNewGame
	private String hostUserId;

	public GameSettings(GameLogic engine, GameDifficultyFactors factors,
			List<String> currentlyLoggedUsers, String hostUserId) {
		super();
		this.factors = factors;
		this.currentlyLoggedUsers = currentlyLoggedUsers;
		this.hostUserId = hostUserId;
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

	public String getHostUserId() {
		return hostUserId;
	}

	public void setHostUserId(String hostUserId) {
		this.hostUserId = hostUserId;
	}

}
