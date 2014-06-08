package client.views.listeners;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.controllers.NewGameController;
import client.utils.ListenerGenerator;
import client.views.MainWindow;

public class HostBtnListener extends BaseListenerForDialog {
	private static Logger log = Logger.getLogger(HostBtnListener.class);
	private MainWindow mainView;

	public HostBtnListener(ListenerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		initializeGameBoard();
		newGameController.disactivate();
		log.debug("Sending create game for user = " + gameState.getUserNick());
		netManager.createGame(gameState.getUserNick(), gameState.getMode(), gameState.getPlayerHandler());
	}
	
	public void initializeGameBoard() {
		mainView.drawGameBoard();
//		mainController.initializeGameBoard();
		componentsFactory.initializeBoardListeners();
	}
}
