package common.network;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import common.model.Result;

public interface PlayerHandler extends Remote, Serializable {
	public void opponentShot(int position, Result result) throws RemoteException;
}
