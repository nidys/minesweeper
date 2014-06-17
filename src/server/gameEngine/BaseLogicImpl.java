package server.gameEngine;

import static common.utils.LoggingHelper.debug;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import server.PlayerData;
import server.gameEngine.model.Board;
import server.gameEngine.model.Field;
import server.gameEngine.utils.Generator;

import common.enums.GameMode;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.exceptions.shot.PositionOutOfRange;
import common.model.Config;
import common.model.GameDifficultyFactors;
import common.model.GameSettings;
import common.model.ShotResult;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;

public abstract class BaseLogicImpl extends UnicastRemoteObject implements GameLogic {

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
	protected List<Field[][]> generatedBoards;

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
			throws PlayerWithIdenticalNickAlreadyInGame, MaximumPlayerExceededException,
			RemoteException {
		handleJoinPlayerConditions(userNick);

		debug(log, "Player[%s] setting boardSizeX[%d], boardSizeY[%d], bombsNumber[%d]", userNick,
				boardSizeX, boardSizeY, bombsNumber);

		players.put(userNick, new PlayerData(new Board(getCopyOfFirstBoard(), boardSizeX
				* boardSizeY), playerHandler));

		setOpponentInOtherPlayers(userNick);
	}

	// Helpers

	private void setOpponentInOtherPlayers(String userNick) throws RemoteException {
		for (String player : getOtherPlayers(userNick)) {
			debug(log, "Sending setOpponent to [%s]", player);
			players.get(player).playerHandler.setOpponents(userNick);
		}
	}

	private void handleJoinPlayerConditions(String userNick)
			throws PlayerWithIdenticalNickAlreadyInGame, MaximumPlayerExceededException {
		if (players.containsKey(userNick)) {
			throw new PlayerWithIdenticalNickAlreadyInGame();
		} else if (players.size() == maxOpponentAmount) {
			throw new MaximumPlayerExceededException();
		}
	}

	private void setBoardSize(Config gameConfig) {
		switch (gameConfig.getGameDifficulty()) {
		default:
		case EASY:
			boardSizeX = 9;
			boardSizeY = 9;
			bombsNumber = 10;
			break;
		case MEDIUM:
			boardSizeX = 16;
			boardSizeY = 16;
			bombsNumber = 40;
			break;
		case HARD:
			boardSizeX = 30;
			boardSizeY = 16;
			bombsNumber = 99;
			break;
		}
		debug(log, "setting boardSizeX[%d], boardSizeY[%d], bombsNumber[%d]", boardSizeX,
				boardSizeY, bombsNumber);
		generatedBoards = Generator.generate(10, null, bombsNumber, boardSizeX, boardSizeY);
		players.put(hostUserId, new PlayerData(new Board(getCopyOfFirstBoard(), boardSizeX
				* boardSizeY), gameConfig.getPlayerHandler()));
	}

	private Field[][] getCopyOfFirstBoard() {
		Field[][] templBoard = generatedBoards.get(0);
		Field[][] userBoard = new Field[boardSizeX][boardSizeY];
		for (int i = 0; i < templBoard.length; i++) {
			System.arraycopy(templBoard[i], 0, userBoard[i], 0, templBoard[0].length);
		}
		return userBoard;
	}

	public String getHostUserId() {
		return hostUserId;
	}

	public GameSettings getGameSettings(String userNick) {
		// PlayerData pData = players.get(userNick);
		GameDifficultyFactors factors = new GameDifficultyFactors(boardSizeX, boardSizeY,
				bombsNumber);
		List<String> opponents = getOtherPlayers(userNick);
		return new GameSettings((GameLogic) this, factors, opponents);
	}

	private List<String> getOtherPlayers(String userNick) {
		List<String> opponents = new ArrayList<String>(players.keySet());
		opponents.remove(userNick);
		return opponents;
	}

	public abstract GameMode getGameMode();

	public int getMaxOpponentAmount() {
		return maxOpponentAmount;
	}

	public int getCurrentlyConnectedPlayers() {
		return players.size();
	}

	// GameLogic Interface
	@Override
	public abstract List<ShotResult> shot(String userNick, int position) throws RemoteException,
			PositionOutOfRange;

	@Override
	public abstract void resetBoard(String userNick) throws RemoteException;

	@Override
	public abstract void ready(String userNick) throws RemoteException;

	@Override
	public abstract void start(String userNick) throws RemoteException;

	@Override
	public abstract void leaveBeforeEnd(String userNick) throws RemoteException;

	// #############################################################
	private static final long serialVersionUID = 1320613550415878733L;
	private static Logger log = Logger.getLogger(BaseLogicImpl.class);

	public BaseLogicImpl() throws RemoteException {
		super();
	}
}
