package client.views.listeners;

import java.awt.event.ActionEvent;

import client.utils.ListenerGenerator;

public class NewGameButtonListener extends BaseListenerWithMVCAccess {

	public NewGameButtonListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFields(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String serverAddr = view.getServerAddress();
		String userNick = view.getUserNick();
		netManager.connectToServer(serverAddr, userNick);
		viewController.createNewGameComponents();
	}

}
