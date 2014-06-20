package common.network.protocols;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.gameManager.NotAllPlayersYetAreReady;
import common.exceptions.gameManager.UnknownGameId;
import common.exceptions.gameManager.UnknownUserId;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.exceptions.join.UnableToJoinGame;
import common.model.AvailableGameInfo;
import common.model.Config;
import common.model.GameSettings;
import common.network.callbacks.PlayerHandler;

public interface GameManager extends Remote {
	public String tmpMsg(String msg) throws RemoteException;

	public GameSettings createNewGame(Config gameConfig) throws RemoteException,
			InvalidGameNameException, MaximumRoomExceededException, MaxOpponentSizeIsTooLarge;

	public List<AvailableGameInfo> getGameList() throws RemoteException;

	public GameSettings joinGame(String userNick, String gameId, PlayerHandler playerHandler)
			throws RemoteException, MaximumPlayerExceededException, InvalidGameNameException,
			PlayerWithIdenticalNickAlreadyInGame, UnableToJoinGame;

	public void ready(String userNick, String gameId) throws RemoteException, UnknownGameId,
			UnknownUserId;

	/**
	 * Host uses this method when all players click 'ready' button
	 * 
	 * @param userNick
	 * @throws RemoteException
	 */
	public void start(String userNick, String gameId) throws RemoteException, UnknownGameId,
			NotAllPlayersYetAreReady;
}
