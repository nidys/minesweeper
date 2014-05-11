package server;

import java.rmi.RemoteException;

import common.GameServer;

public class GameServerImpl implements GameServer {

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		System.out.println("Got msg = " + msg);
		return msg + String.valueOf(msg.length() + 1);
	}

}
