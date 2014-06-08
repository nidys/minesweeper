package client;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import client.utils.Locator;
import client.views.MainWindow;

public class Minesweeper {
	private static Logger log = Logger.getLogger(Minesweeper.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		log.debug("Client started...");
		init();
	}

	private static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Locator locator = new Locator();

					MainWindow mainView = locator.getMain();
					mainView.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}