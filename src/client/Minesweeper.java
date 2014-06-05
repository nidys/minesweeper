package client;

import java.awt.EventQueue;

import client.controllers.MainController;
import client.utils.Locator;

public class Minesweeper {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Client started...");
		init();
	}

	private static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Locator locator = new Locator();

					MainController mainController = locator.getMain();
					mainController.activate();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}