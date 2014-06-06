package client.views.listeners;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.utils.ListenerGenerator;

public class JoinBtnListener extends BaseListenerForDialog {
	private static Logger log = Logger.getLogger(JoinBtnListener.class);

	public JoinBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewController.initializeGameBoard();
		newGameController.disactivate();
		log.debug("Sending join game for user = " + gameState.getUserNick());
		netManager.joinGame(gameState.getUserNick(), gameState.getPlayerHandler());
	}

}
