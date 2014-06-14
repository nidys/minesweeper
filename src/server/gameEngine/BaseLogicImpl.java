package server.gameEngine;

import static client.utils.LoggingHelper.debug;
import static client.utils.LoggingHelper.error;
import static client.utils.LoggingHelper.info;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import server.PlayerData;
import server.gameEngine.model.Board;

import common.enums.GameMode;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.Config;
import common.model.GameDifficultyFactors;
import common.model.GameSettings;
import common.model.ShotResult;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;

//Temporarly acts as classic mode, to be moved to ClassicLogic
public abstract class BaseLogicImpl extends UnicastRemoteObject implements GameLogic {
	public BaseLogicImpl() throws RemoteException {
		super();
	}

	protected static final int MAX_PLAYERS = 4;
	// <userId, (board,callback)>
	Map<String, PlayerData> players = new HashMap<String, PlayerData>();
	protected String hostUserId;

	protected boolean isNormal;
	protected boolean isLifecount = false;
	protected boolean isTimed = false;
	protected long gameDuration;
	protected int lifeAmount;
	protected int maxOpponentAmount;

	private int boardSizeX, boardSizeY, bombsNumber;

	public void setGameConfiguration(Config gameConfig) throws MaxOpponentSizeIsTooLarge {
		hostUserId = gameConfig.getUserNick();
		setBoardSize(gameConfig);
		isNormal = gameConfig.isNormal();
		if (isNormal == false) {
			isLifecount = gameConfig.isLifecount();
			isTimed = gameConfig.isTimed();
		}
		gameDuration = gameConfig.getGameDuration();
		lifeAmount = gameConfig.getLifeAmount();
		maxOpponentAmount = gameConfig.getMaxOpponentAmount();
		if (maxOpponentAmount > MAX_PLAYERS) {
			debug(log,
					"sending MaxOpponentSizeIsTooLarge exception. maxOpponentAmount[%d], while MAX_PLAYERS[%d]",
					maxOpponentAmount, MAX_PLAYERS);
			throw new MaxOpponentSizeIsTooLarge();
		}
		debug(log, "creating game for player[%s], maxPlayers[%d]", hostUserId, maxOpponentAmount);
	}

	public void addPlayer(String userNick, PlayerHandler playerHandler)
			throws PlayerWithIdenticalNickAlreadyInGame, MaximumPlayerExceededException {
		handleJoinPlayerConditions(userNick);
		debug(log, "Player[%s] setting boardSizeX[%d], boardSizeY[%d], bombsNumber[%d]", userNick,
				boardSizeX, boardSizeY, bombsNumber);
		players.put(userNick, new PlayerData(new Board(bombsNumber, boardSizeX, boardSizeY),
				playerHandler));
	}

	private void handleJoinPlayerConditions(String userNick)
			throws PlayerWithIdenticalNickAlreadyInGame, MaximumPlayerExceededException {
		if (players.containsKey(userNick)) {
			throw new PlayerWithIdenticalNickAlreadyInGame();
		} else if (players.size() == maxOpponentAmount) {
			throw new MaximumPlayerExceededException();
		}
	}

	// TODO: verify sizes are OK
	private void setBoardSize(Config gameConfig) {
		switch (gameConfig.getGameDifficulty()) {
		default:
		case EASY:
			boardSizeX = 5;
			boardSizeY = 10;
			bombsNumber = 15;
			break;
		case MEDIUM:
			boardSizeX = 10;
			boardSizeY = 20;
			bombsNumber = 70;
			break;
		case HARD:
			boardSizeX = 20;
			boardSizeY = 40;
			bombsNumber = 270;
			break;
		}
		debug(log, "setting boardSizeX[%d], boardSizeY[%d], bombsNumber[%d]", boardSizeX,
				boardSizeY, bombsNumber);
		players.put(hostUserId, new PlayerData(new Board(bombsNumber, boardSizeX, boardSizeY),
				gameConfig.getPlayerHandler()));
	}

	public String getHostUserId() {
		return hostUserId;
	}

	public GameSettings getGameSettings(String userNick) {
		PlayerData pData = players.get(userNick);
		GameDifficultyFactors factors = new GameDifficultyFactors(pData.board.getBoardSizeX(),
				pData.board.getBoardSizeY(), pData.board.getBombsNumber());
		List<String> opponents = new ArrayList<String>(players.keySet());
		opponents.remove(userNick);
		return new GameSettings((GameLogic) this, factors, opponents);
	}

	public abstract GameMode getGameMode();

	public int getMaxOpponentAmount() {
		return maxOpponentAmount;
	}

	public int getCurrentlyConnectedPlayers() {
		return players.size();
	}

	// #############################################################

	@Override
	public List<ShotResult> shot(String userNick, int position) throws RemoteException {
		position--;
		debug(log, "Got shor for user[%s], pos[%d]", userNick, position);
		if (isValueWithinBoardSize(position) == false) {
			info(log, "Player[%s] shot in not valid field = %d", userNick, position);

			error(log, "returning null");
			// TODO return list
			return null;
		}
		// TODO return list
		// Result res = players.get(userNick).shot(position);
		// log.debug(String.format("Player[%s], shot result = %s, players[%d]",
		// userNick, res, players.size()));
		for (String nick : players.keySet()) {
			if (!nick.equals(userNick)) {
				debug(log, "Sending to player[%s]", nick);
				// TODO in classic mode send progress
				// use for SHARED/PERKS mode
				// players.get(nick).informOpponent(position + 1, res);
			}
		}
		// TODO return list
		error(log, "returning null");
		return null;
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		// TODO implement logic
		debug(log, "Reset from player[%s]", userNick);
		// players.get(userNick).generateBombs(bombsNum);
	}

	private boolean isValueWithinBoardSize(int val) {
		// return val >= 0 && val < board;
		return false;
	}

	@Override
	public void ready(String userNick) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");
	}

	@Override
	public void start(String userNick) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");

	}

	@Override
	public void leaveBeforeEnd(String userNick) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320613550415878733L;
	private static Logger log = Logger.getLogger(BaseLogicImpl.class);
}
