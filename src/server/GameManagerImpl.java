package server;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import server.gameEngine.PerksLogic;

import common.gameRules.GameMode;
import common.model.Result;
import common.network.GameManager;
import common.network.PlayerHandler;

public class GameManagerImpl implements GameManager {
	private static Logger log = Logger.getLogger(GameManagerImpl.class);
	PerksLogic engine = new PerksLogic();

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		log.info("User: " + msg + ", connected...");
		return msg + String.valueOf(msg.length() + 1);
	}

	@Override
	public Boolean createNewGame(String userNick, GameMode gm, PlayerHandler playerHandler) throws RemoteException {
		if (gm == GameMode.PERKS) {
			return engine.initGame(userNick, playerHandler);
		}
		log.debug(String.format("Player[%s], create this game type is not yet implemented, gm=%s", userNick, gm));
		return false;
	}

	@Override
	public Boolean joinGame(String userNick, PlayerHandler playerHandler) throws RemoteException {
		if (engine.joinNewPlayer(userNick, playerHandler)) {
			return true;
		}
		return false;
	}

	@Override
	public Result shot(String userNick, int position) throws RemoteException {
		return engine.shot(userNick, position);
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		engine.resetBoard(userNick);
	}

}
