package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import common.GameManager;
import common.ServerAddress;

public class RoomManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			GameManager gs = new GameManagerImpl();
			UnicastRemoteObject.exportObject(gs, 0);

			Naming.rebind("rmi://" + ServerAddress.LOCALHOST.getValue() + "/note", gs);
			System.out.println("Server started...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
