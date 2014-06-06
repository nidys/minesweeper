package client.views.listeners;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.utils.ListenerGenerator;

public class HostBtnListener extends BaseListenerForDialog {
	private static Logger log = Logger.getLogger(HostBtnListener.class);

	public HostBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewController.initializeGameBoard();
		newGameController.disactivate();
		log.debug("Sending create game for user = " + gameState.getUserNick());
		netManager.createGame(gameState.getUserNick(), gameState.getMode(), gameState.getPlayerHandler());
	}

}
