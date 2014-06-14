package client.network;

import static client.utils.LoggingHelper.debug;
import static client.utils.LoggingHelper.error;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.model.AvailableGameInfo;
import common.model.Config;
import common.model.Result;
import common.model.ShotResult;
import common.network.ServerAddress;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;
import common.network.protocols.GameManager;

public class NetworkManager {
	private static Logger log = Logger.getLogger(NetworkManager.class);
	GameManager remoteGameManager;
	GameLogic engine;

	public boolean connectToServer(String serverAddr, String userNick) {
		try {
			remoteGameManager = (GameManager) Naming.lookup("rmi://" + serverAddr
					+ ServerAddress.RMI_PLACE);
			remoteGameManager.tmpMsg(userNick);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createGame(String userNick, GameMode gm, PlayerHandler playerHandler) {
		// TODO used in HostBtnController, fill with appropriate parameters
		// try {
		// log.debug(String.format("Sending gm=%s", gm));
		// engine = remoteGameManager.createNewGame(userNick, gm,
		// playerHandler);
		// if (engine != null) {
		// return true;
		// }
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return false;
	}

	public List<AvailableGameInfo> getGameList() {
		// TODO implement!!!
		error(log, "implement!!!");
		// gm.getGameList();
		return null;
	}

	public Boolean joinGame(String userNick, PlayerHandler playerHandler) {
		// TODO used in JoinBtnController
		// TODO must pass gameId too
		error(log, "implement!!!");
		// try {
		// engine = remoteGameManager.joinGame(userNick, playerHandler);
		// if (engine != null) {
		// return true;
		// }
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return false;
	}

	public Result shot(String userNick, int position) {
		// TODO used in MyBombFielsBtnController
		// TODO from now shot from engine return list of fields to discover
		// try {
		// log.debug(String.format("Engine null=%b", engine == null));
		// Result res = null;//engine.shot(userNick, position);
		// log.debug("Got shot result = " + res);
		// return res;
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return Result.ERROR;
	}

	public void resetBoard(String userNick) {
		try {
			debug(log, "Sending reset board");
			engine.resetBoard(userNick);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
