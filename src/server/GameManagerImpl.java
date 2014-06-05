package server;

import java.rmi.RemoteException;

import common.GameManager;

public class GameManagerImpl implements GameManager {

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		System.out.println("Got msg = " + msg);
		return msg + String.valueOf(msg.length() + 1);
	}

}
