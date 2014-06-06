package client.views.listeners;

import java.awt.event.ActionEvent;

import client.utils.ListenerGenerator;

public class NewGameButtonListener extends BaseListenerForWindow {

	public NewGameButtonListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String serverAddr = view.getServerAddress();
		String userNick = view.getUserNick();
		netManager.connectToServer(serverAddr, userNick);
		viewController.createNewGameComponent();
		gameState.setUserNick(userNick);
	}

}
