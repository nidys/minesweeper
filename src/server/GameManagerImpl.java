package server;

import static common.utils.LoggingHelper.debug;
import static common.utils.LoggingHelper.error;
import static common.utils.LoggingHelper.info;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import server.gameEngine.BaseLogicImpl;
import server.gameEngine.ClassicLogic;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.exceptions.start.UnknownGameHost;
import common.model.AvailableGameInfo;
import common.model.Config;
import common.model.GameSettings;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameManager;

public class GameManagerImpl implements GameManager {
	private static Logger log = Logger.getLogger(GameManagerImpl.class);
	// <gameId, engine>
	Map<String, BaseLogicImpl> games = new HashMap<String, BaseLogicImpl>();
	private final int MAX_GAME_AMOUNT = 5;

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		info(log, "User[%s], connected...", msg);
		return msg + String.valueOf(msg.length() + 1);
	}

	@Override
	public synchronized GameSettings createNewGame(Config gameConfig) throws RemoteException,
			InvalidGameNameException, MaximumRoomExceededException, MaxOpponentSizeIsTooLarge {
		handleServerCapabilitiesRestriction(gameConfig);
		BaseLogicImpl engine = createEngine(gameConfig);
		if (engine == null) {
			return null;
		}
		return engine.getGameSettings(gameConfig.getUserNick());
	}

	@Override
	public synchronized List<AvailableGameInfo> getGameList() {
		List<AvailableGameInfo> gameList = new ArrayList<AvailableGameInfo>();
		for (Entry<String, BaseLogicImpl> pair : games.entrySet()) {
			BaseLogicImpl engine = pair.getValue();
			gameList.add(new AvailableGameInfo(pair.getKey(), engine.getGameMode(), engine
					.getHostUserId(), engine.getMaxOpponentAmount(), engine
					.getCurrentlyConnectedPlayers()));
		}
		printGameList(gameList);
		return gameList;
	}

	@Override
	public synchronized GameSettings joinGame(String userNick, String gameId,
			PlayerHandler playerHandler) throws RemoteException, MaximumPlayerExceededException,
			InvalidGameNameException, PlayerWithIdenticalNickAlreadyInGame {
		if (games.containsKey(gameId) == false) {
			info(log, "Player[%s] tried to join game[%s] but doesn't exist", userNick, gameId);
			throw new InvalidGameNameException();
		}
		BaseLogicImpl engine = games.get(gameId);
		engine.addPlayer(userNick, playerHandler);
		return engine.getGameSettings(userNick);
	}

	// Helpers
	private BaseLogicImpl createEngine(Config gameConfig) throws RemoteException,
			MaxOpponentSizeIsTooLarge {
		BaseLogicImpl engine;
		switch (gameConfig.getGameMode()) {
		case CLASSIC:
			engine = new ClassicLogic();
			engine.setGameConfiguration(gameConfig);
			games.put(gameConfig.getGameId(), engine);
			break;
		case SHARED:
		case PERKS:
		default:
			error(log, "Mode[%s] not yet implemented!", gameConfig.getGameMode());
			engine = null;
		}
		return engine;
	}

	private void handleServerCapabilitiesRestriction(Config gameConfig)
			throws MaximumRoomExceededException, InvalidGameNameException {
		if (games.size() == MAX_GAME_AMOUNT) {
			info(log, "Player[%s] tried to create game but server is full",
					gameConfig.getUserNick());
			throw new MaximumRoomExceededException();
		}
		if (games.containsKey(gameConfig.getGameId())) {
			info(log,
					"Player[%s] tried to create game[%s] but already exists, created by player[%s]",
					gameConfig.getUserNick(), gameConfig.getGameId(),
					games.get(gameConfig.getGameId()).getHostUserId());
			throw new InvalidGameNameException();
		}
	}

	/**
	 * For debug purposes
	 * 
	 * @param gameList
	 */
	private void printGameList(List<AvailableGameInfo> gameList) {
		StringBuffer sb = new StringBuffer();
		for (AvailableGameInfo info : gameList) {
			sb.append(String
					.format("\n\tgameId[%s] gameMode[%s],hostUser[%s],currentlyConnected[%d], maxPlayers[%d]",
							info.getGameId(), info.getGameMode(), info.getHostUser(),
							info.getCurrentlyConnectedPlayers(), info.getMaxPlayers()));

		}
		debug(log, sb.toString());
	}

	@Override
	public void ready(String userNick, String gameId) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");
	}

	@Override
	public void start(String userNick, String gameId) throws RemoteException, UnknownGameHost {
		if (games.get(gameId) == null) {
			throw new UnknownGameHost();
		}
		games.get(gameId).setEngine();

	}
}
