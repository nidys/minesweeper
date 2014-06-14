package common.network.protocols;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.Config;
import common.model.GameSettings;
import common.network.callbacks.PlayerHandler;
import common.model.AvailableGameInfo;

public interface GameManager extends Remote {
	public String tmpMsg(String msg) throws RemoteException;

	public GameSettings createNewGame(Config gameConfig) throws RemoteException,
			InvalidGameNameException, MaximumRoomExceededException, MaxOpponentSizeIsTooLarge;

	public List<AvailableGameInfo> getGameList() throws RemoteException;

	public GameSettings joinGame(String userNick, String gameId, PlayerHandler playerHandler)
			throws RemoteException, MaximumPlayerExceededException, InvalidGameNameException,
			PlayerWithIdenticalNickAlreadyInGame;
}
