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
import common.exceptions.shot.PositionOutOfRange;
import common.model.AvailableGameInfo;
import common.model.Config;
import common.model.GameDifficultyFactors;
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

	public GameDifficultyFactors createGame(Config config) 
			throws RemoteException, InvalidGameNameException, 
				   MaximumRoomExceededException, MaxOpponentSizeIsTooLarge {
		// TODO used in HostBtnController, fill with appropriate parameters
		// log.debug(String.format("Sending gm=%s", gm));
		
		// engine = remoteGameManager.createNewGame(userNick, gm, playerHandler);
		gameSettings = remoteGameManager.createNewGame(config);
		engine = gameSettings.getEngine();
		
		return gameSettings.getFactors();
	}

	public List<AvailableGameInfo> getGameList() {
		// TODO implement!!!
		error(log, "implement!!!");
		// gm.getGameList();
		return null;
	}

	public GameDifficultyFactors joinGame(String userNick, PlayerHandler playerHandler, String gameId) 
			throws RemoteException, MaximumPlayerExceededException, 
				   InvalidGameNameException, PlayerWithIdenticalNickAlreadyInGame {
		// TODO used in JoinBtnController
		// TODO must pass gameId too.
		error(log, "Finish implementation!!!");
		gameSettings = remoteGameManager.joinGame(userNick, gameId, playerHandler);
		engine = gameSettings.getEngine();
		
		return gameSettings.getFactors();
	}

	public List<ShotResult> shot(String userNick, int position) throws RemoteException, PositionOutOfRange {
		// log.debug(String.format("Engine null=%b", engine == null));	
		return engine.shot(userNick, position);
	}

	public void resetBoard(String userNick) throws RemoteException {
		engine.resetBoard(userNick);
	}
}
