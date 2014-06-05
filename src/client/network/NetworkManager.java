package client.network;

import java.rmi.Naming;

import common.network.GameManager;

public class NetworkManager {
	GameManager remoteGamemanager;

	public boolean connectToServer(String serverAddr, String userNick) {
		try {
			remoteGamemanager = (GameManager) Naming.lookup("rmi://" + serverAddr + "/note");
			remoteGamemanager.tmpMsg(userNick);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
