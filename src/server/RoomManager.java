package server;

import static common.utils.LoggingHelper.info;

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
			String urlName = "rmi://" + ServerAddress.LOCALHOST.getValue()
					+ ServerAddress.RMI_PLACE;
			info(log, "Connecting[%s]", urlName);
			Naming.rebind(urlName, gs);
			info(log, "Server started...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
