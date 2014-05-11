package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameServer extends Remote {
	public String tmpMsg(String msg) throws RemoteException;
}
