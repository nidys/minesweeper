package client.views.listeners;

import java.awt.event.ActionEvent;

import client.utils.ListenerGenerator;

public class HostBtnListener extends BaseListenerForDialog {

	public HostBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewController.initializeGameBoard();
		newGameController.disactivate();
		System.out.println("Sending create game for user = " + gameState.getUserNick());
		netManager.createGame(gameState.getUserNick(), gameState.getMode(), gameState.getPlayerHandler());
	}

}
