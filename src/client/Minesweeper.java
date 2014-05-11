package client;

import java.rmi.Naming;

import common.GameServer;

public class Minesweeper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Client started...");
		tmpNetworkInit();
	}

	private static void tmpNetworkInit() {
		try {
			GameServer nb = (GameServer) Naming.lookup("rmi://127.0.0.1:1099/note");
			nb.tmpMsg("Some msg");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
