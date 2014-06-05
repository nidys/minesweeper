package common.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameManager extends Remote {
	public String tmpMsg(String msg) throws RemoteException;
}
