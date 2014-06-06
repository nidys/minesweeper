package common.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.gameRules.GameMode;
import common.model.Result;

public interface GameManager extends Remote {
	public String tmpMsg(String msg) throws RemoteException;

	public Boolean createNewGame(String userNick, GameMode gm, PlayerHandler playerHandler) throws RemoteException;

	public Boolean joinGame(String userNick, PlayerHandler playerHandler) throws RemoteException;

	public Result shot(String userNick, int position) throws RemoteException;

	public void resetBoard(String userNick) throws RemoteException;
}
