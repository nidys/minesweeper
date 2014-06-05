package client.network;

import java.rmi.Naming;

import common.GameManager;

public class NetworkManager {
	GameManager remoteGamemanager;

	public boolean connectToServer(String serverAddr) {
		try {
			remoteGamemanager = (GameManager) Naming.lookup("rmi://" + serverAddr + "/note");
			remoteGamemanager.tmpMsg("Some msg");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
