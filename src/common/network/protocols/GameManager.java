package common.network.protocols;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.gameRules.GameMode;
import common.network.callbacks.PlayerHandler;

public interface GameManager extends Remote {
	public String tmpMsg(String msg) throws RemoteException;

	// change userNick to soth more general? pass board size, bombs num, nick,..
	// ?
	public GameLogic createNewGame(String userNick, GameMode gm, PlayerHandler playerHandler) throws RemoteException;

	public GameLogic joinGame(String userNick, PlayerHandler playerHandler) throws RemoteException;
}
