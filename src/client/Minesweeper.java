package client;

import static common.utils.LoggingHelper.debug;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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
		debug(log, "Client started...");
		init();
	}

	private static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
					
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