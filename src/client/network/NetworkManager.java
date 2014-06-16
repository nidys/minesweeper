package client.network;

import static common.utils.LoggingHelper.debug;
import static common.utils.LoggingHelper.error;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.AvailableGameInfo;
import common.model.Config;
import common.model.GameSettings;
import common.model.Result;
import common.model.ShotResult;
import common.network.ServerAddress;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;
import common.network.protocols.GameManager;

public class NetworkManager {
	private static Logger log = Logger.getLogger(NetworkManager.class);
	GameManager remoteGameManager;
	GameSettings gameSettings;
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

	public boolean createGame(Config config) 
			throws RemoteException, InvalidGameNameException, 
				   MaximumRoomExceededException, MaxOpponentSizeIsTooLarge {
		// TODO used in HostBtnController, fill with appropriate parameters
		// log.debug(String.format("Sending gm=%s", gm));
		
		// engine = remoteGameManager.createNewGame(userNick, gm, playerHandler);
		gameSettings = remoteGameManager.createNewGame(config);
		
		return gameSettings != null;
	}

	public List<AvailableGameInfo> getGameList() {
		// TODO implement!!!
		error(log, "implement!!!");
		// gm.getGameList();
		return null;
	}

	public Boolean joinGame(String userNick, PlayerHandler playerHandler) 
			throws RemoteException, MaximumPlayerExceededException, 
				   InvalidGameNameException, PlayerWithIdenticalNickAlreadyInGame {
		// TODO used in JoinBtnController
		// TODO must pass gameId too. Is engine deprecated? 
		error(log, "Finish implementation!!!");
//		engine = remoteGameManager.joinGame(userNick, playerHandler);
		gameSettings = remoteGameManager.joinGame(userNick, "MOCK_GAME_ID", playerHandler);
		
		return gameSettings != null;
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
			// If engine is deprecated, NullPointer will occur here, replace with new logic
			engine.resetBoard(userNick);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
