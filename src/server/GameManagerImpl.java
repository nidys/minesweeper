package server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import server.gameEngine.BaseLogicImpl;
import server.gameEngine.PerksLogic;

import common.gameRules.GameMode;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;
import common.network.protocols.GameManager;

public class GameManagerImpl implements GameManager {
	private static Logger log = Logger.getLogger(GameManagerImpl.class);
	Map<String, BaseLogicImpl> games = new HashMap<String, BaseLogicImpl>();

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		log.info("User: " + msg + ", connected...");
		return msg + String.valueOf(msg.length() + 1);
	}

	@Override
	public GameLogic createNewGame(String userNick, GameMode gm, PlayerHandler playerHandler) throws RemoteException {
		if (gm == GameMode.PERKS) {
			BaseLogicImpl engine = new PerksLogic();
			if (createEngine(userNick, gm, playerHandler, engine)) {
				return engine;
			}
		}
		log.debug(String.format("Player[%s], create this game type is not yet implemented, gm=%s", userNick, gm));
		return null;
	}

	/**
	 * TODO: Completely wrong, but needed to design refactor first then
	 * interfaces spec update and then this will be repaired
	 */
	@Override
	public GameLogic joinGame(String userNick, PlayerHandler playerHandler) throws RemoteException {
		BaseLogicImpl engine = games.get(games.keySet().toArray()[0]);
		if (engine.joinNewPlayer(userNick, playerHandler)) {
			return engine;
		}
		log.debug(String.format("Players[%s] didnt join game", userNick));
		return null;
	}

	private Boolean createEngine(String userNick, GameMode gm, PlayerHandler playerHandler, BaseLogicImpl engine) {
		if (games.get(userNick) == null) {
			if (engine.initGame(userNick, playerHandler)) {
				games.put(userNick, engine);
				return true;
			}
			log.debug(String.format("Player[%s] couldnt init game[%s]", userNick, gm));
			return false;
		}
		log.debug(String.format("Player[%s] is already in game - %s", userNick, games.get(userNick).getClass().getName()));
		return false;
	}
}
