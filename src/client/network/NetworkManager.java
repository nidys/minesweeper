package client.network;

import static common.utils.LoggingHelper.error;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.exceptions.shot.PositionOutOfRange;
import common.exceptions.start.UnknownGameHost;
import common.model.AvailableGameInfo;
import common.model.Config;
import common.model.GameDifficultyFactors;
import common.model.GameSettings;
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

	public GameDifficultyFactors createGame(Config config) throws RemoteException,
			InvalidGameNameException, MaximumRoomExceededException, MaxOpponentSizeIsTooLarge {
		// TODO used in HostBtnController, fill with appropriate parameters
		// engine = remoteGameManager.createNewGame(userNick, gm,
		// playerHandler);
		gameSettings = remoteGameManager.createNewGame(config);

		// --TODO DELETE THIS, only temporarly added here, ready gdoc and method
		// comments-
		try {
			remoteGameManager.start(config.getGameId(), config.getGameId());
		} catch (UnknownGameHost e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ------ @up ------------------------

		return gameSettings.getFactors();
	}

	public List<AvailableGameInfo> getGameList() {
		// TODO implement!!!
		error(log, "implement!!!");
		// gm.getGameList();
		return null;
	}

	public GameDifficultyFactors joinGame(String userNick, PlayerHandler playerHandler,
			String gameId) throws RemoteException, MaximumPlayerExceededException,
			InvalidGameNameException, PlayerWithIdenticalNickAlreadyInGame {
		// TODO used in JoinBtnController
		// TODO must pass gameId too.
		error(log, "Finish implementation!!!");
		gameSettings = remoteGameManager.joinGame(userNick, gameId, playerHandler);

		return gameSettings.getFactors();
	}

	public ShotResult shot(String userNick, int position) throws RemoteException,
			PositionOutOfRange {
		return engine.shot(userNick, position);
	}

	public void resetBoard(String userNick) throws RemoteException {
		engine.resetBoard(userNick);
	}

	public void start(String userNick, String gameId) throws RemoteException, UnknownGameHost {
		// TODO after that server will invoke setEngine on PlayerHandler which
		// should set engine field in networkmanager class
		remoteGameManager.start(userNick, gameId);
	}

	public void ready(String userNick, String gameId) throws RemoteException {
		// TODO when host click start, server will invoke setEngine on
		// PlayerHandler which should set engine field in networkmanager class
		remoteGameManager.ready(userNick, gameId);
	}

	public void setEngine(GameLogic engine) {
		this.engine = engine;
	}
}
