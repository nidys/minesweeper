package client.network;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import common.gameRules.GameMode;
import common.model.Result;
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
			remoteGameManager = (GameManager) Naming.lookup("rmi://" + serverAddr + ServerAddress.RMI_PLACE);
			remoteGameManager.tmpMsg(userNick);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createGame(String userNick, GameMode gm, PlayerHandler playerHandler) {
		try {
			log.debug(String.format("Sending gm=%s", gm));
			engine = remoteGameManager.createNewGame(userNick, gm, playerHandler);
			if (engine != null) {
				return true;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Boolean joinGame(String userNick, PlayerHandler playerHandler) {
		try {
			engine = remoteGameManager.joinGame(userNick, playerHandler);
			if (engine != null) {
				return true;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Result shot(String userNick, int position) {
		try {
			log.debug(String.format("Engine null=%b", engine == null));
			Result res = engine.shot(userNick, position);
			// engine.shot(userNick, position);
			log.debug("Got shot result = " + res);
			return res;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result.ERROR;
	}

	public void resetBoard(String userNick) {
		try {
			log.debug("Sending reset board");
			engine.resetBoard(userNick);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
