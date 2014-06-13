package client.controllers;

import static client.utils.LoggingHelper.debug;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.MainWindow;

public class JoinBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(JoinBtnController.class);
	private MainWindow mainView;

	public JoinBtnController(ControllerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainView.drawGameBoard();
		componentsFactory.initializeBoardListeners();
		newGameView.setVisible(false);
		// newGameController.disactivate();
		debug(log, "Sending join game for user[%s]", gameState.getUserNick());
		// TODO must specify gameId too, and pass
		// netManager.joinGame(gameState.getUserNick(),
		// gameState.getPlayerHandler());
	}

}
