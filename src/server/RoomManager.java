package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import common.network.GameManager;
import common.network.ServerAddress;
import common.network.ServerAddress.Port;

public class RoomManager {
	private static Logger log = Logger.getLogger(RoomManager.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(Port.DEFAULT.getInt());
			GameManager gs = new GameManagerImpl();
			UnicastRemoteObject.exportObject(gs, 0);

			Naming.rebind("rmi://" + ServerAddress.LOCALHOST.getValue() + "/note", gs);
			log.debug("Server started...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
