package client.network;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import common.gameRules.GameMode;
import common.model.Result;
import common.network.GameManager;
import common.network.PlayerHandler;

public class NetworkManager {
	private static Logger log = Logger.getLogger(NetworkManager.class);
	GameManager remoteGameManager;

	public boolean connectToServer(String serverAddr, String userNick) {
		try {
			remoteGameManager = (GameManager) Naming.lookup("rmi://" + serverAddr + "/note");
			remoteGameManager.tmpMsg(userNick);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createGame(String userNick, GameMode gm, PlayerHandler playerHandler) {
		try {
			return remoteGameManager.createNewGame(userNick, gm, playerHandler);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Boolean joinGame(String userNick, PlayerHandler playerHandler) {
		try {
			return remoteGameManager.joinGame(userNick, playerHandler);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Result shot(String userNick, int position) {
		try {
			Result res = remoteGameManager.shot(userNick, position);
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
			remoteGameManager.resetBoard(userNick);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
