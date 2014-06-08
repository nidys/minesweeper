package client.controllers;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ListenerGenerator;
import client.views.MainWindow;

public class HostBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(HostBtnController.class);
	private MainWindow mainView;

	public HostBtnController(ListenerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		initializeGameBoard();
		newGameView.setVisible(false);
//		newGameController.disactivate();
		log.debug("Sending create game for user = " + gameState.getUserNick());
		netManager.createGame(gameState.getUserNick(), gameState.getMode(), gameState.getPlayerHandler());
	}
	
	public void initializeGameBoard() {
		mainView.drawGameBoard();
//		mainController.initializeGameBoard();
		componentsFactory.initializeBoardListeners();
	}
}
