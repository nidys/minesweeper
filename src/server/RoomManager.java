package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import common.network.ServerAddress;
import common.network.ServerAddress.Port;
import common.network.protocols.GameManager;

public class RoomManager {
	private static Logger log = Logger.getLogger(RoomManager.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initServer();
	}

	private static void initServer() {
		try {
			LocateRegistry.createRegistry(Port.DEFAULT.getInt());
			GameManager gs = new GameManagerImpl();
			UnicastRemoteObject.exportObject(gs, 0);
			String urlName = "rmi://" + ServerAddress.LOCALHOST.getValue() + ServerAddress.RMI_PLACE;
			log.debug("Connecting: " + urlName);
			Naming.rebind(urlName, gs);
			log.debug("Server started...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
