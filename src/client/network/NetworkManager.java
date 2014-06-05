package client.network;

import java.rmi.Naming;

import common.network.GameManager;

public class NetworkManager {
	GameManager remoteGameManager;

	public boolean connectToServer(String serverAddr, String userNick) {
		try {
			remoteGameManager = (GameManager) Naming.lookup("rmi://" + serverAddr + "/note");
			remoteGameManager.tmpMsg(userNick);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
