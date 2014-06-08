package client.views.listeners;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.utils.ListenerGenerator;
import client.views.MainWindow;

public class JoinBtnListener extends BaseListenerForDialog {
	private static Logger log = Logger.getLogger(JoinBtnListener.class);
	private MainWindow mainView;

	public JoinBtnListener(ListenerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainView.drawGameBoard();
		componentsFactory.initializeBoardListeners();
		newGameView.setVisible(false);
//		newGameController.disactivate();
		log.debug("Sending join game for user = " + gameState.getUserNick());
		netManager.joinGame(gameState.getUserNick(), gameState.getPlayerHandler());
	}

}
