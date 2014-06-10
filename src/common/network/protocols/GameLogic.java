package common.network.protocols;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.model.Result;

public interface GameLogic extends Remote {

	public Result shot(String userNick, int position) throws RemoteException;

	public void resetBoard(String userNick) throws RemoteException;

}
