package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import common.GameServer;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			GameServer gs = new GameServerImpl();
			UnicastRemoteObject.exportObject(gs, 0);

			Naming.rebind("rmi://localhost:1099/note", gs);
			System.out.println("Server started...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
