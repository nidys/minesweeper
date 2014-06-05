package server;

import java.rmi.RemoteException;

import common.network.GameManager;

public class GameManagerImpl implements GameManager {

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		System.out.println("User: " + msg + ", connected...");
		return msg + String.valueOf(msg.length() + 1);
	}

}
