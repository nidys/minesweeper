package client.views.listeners;

import java.awt.event.ActionEvent;

import client.utils.ListenerGenerator;

public class JoinBtnListener extends BaseListenerForDialog {

	public JoinBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewController.initializeGameBoard();
		newGameController.disactivate();
		System.out.println("Sending join game for user = " + gameState.getUserNick());
		netManager.joinGame(gameState.getUserNick(), gameState.getPlayerHandler());
	}

}
