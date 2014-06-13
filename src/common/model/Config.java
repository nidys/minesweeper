package common.model;

import java.io.Serializable;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.network.callbacks.PlayerHandler;

public class Config implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4482950374250948406L;
	private String userNick;
	private GameMode gameMode;
	private PlayerHandler pplayerHandler;
	private GameDifficulty gameDifficulty;
	private String gameId;
	/**
	 * Default isNormal == true <br>
	 * isNormal == true => ignore isLifecount && isTimed <br>
	 * isLifecount && isLifecount - defines additional game rules
	 */
	private boolean isNormal;
	private boolean isLifecount;
	private boolean isTimed;
	private long gameDuration;
	private int lifeAmount;
	private int maxOpponentAmount;

	public Config(String userNick, GameMode gameMode, PlayerHandler pplayerHandler, GameDifficulty gameDifficulty, String gameId, boolean isNormal,
			boolean isLifecount, boolean isTimed, long gameDuration, int lifeAmount, int maxOpponentAmount) {
		super();
		this.userNick = userNick;
		this.gameMode = gameMode;
		this.pplayerHandler = pplayerHandler;
		this.gameDifficulty = gameDifficulty;
		this.gameId = gameId;
		this.isNormal = isNormal;
		this.isLifecount = isLifecount;
		this.isTimed = isTimed;
		this.gameDuration = gameDuration;
		this.lifeAmount = lifeAmount;
		this.maxOpponentAmount = maxOpponentAmount;
	}

	public Config() {

	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public PlayerHandler getPplayerHandler() {
		return pplayerHandler;
	}

	public void setPplayerHandler(PlayerHandler pplayerHandler) {
		this.pplayerHandler = pplayerHandler;
	}

	public GameDifficulty getGameDifficulty() {
		return gameDifficulty;
	}

	public void setGameDifficulty(GameDifficulty gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public boolean isNormal() {
		return isNormal;
	}

	public void setNormal(boolean isNormal) {
		this.isNormal = isNormal;
	}

	public boolean isLifecount() {
		return isLifecount;
	}

	public void setLifecount(boolean isLifecount) {
		this.isLifecount = isLifecount;
	}

	public boolean isTimed() {
		return isTimed;
	}

	public void setTimed(boolean isTimed) {
		this.isTimed = isTimed;
	}

	public long getGameDuration() {
		return gameDuration;
	}

	public void setGameDuration(long gameDuration) {
		this.gameDuration = gameDuration;
	}

	public int getLifeAmount() {
		return lifeAmount;
	}

	public void setLifeAmount(int lifeAmount) {
		this.lifeAmount = lifeAmount;
	}

	public int getMaxOpponentAmount() {
		return maxOpponentAmount;
	}

	public void setMaxOpponentAmount(int maxOpponentAmount) {
		this.maxOpponentAmount = maxOpponentAmount;
	}

}
