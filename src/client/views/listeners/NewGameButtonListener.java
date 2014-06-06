package client.views.listeners;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import client.network.PlayerHandlerImpl;
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
		gameState.setUserNick(userNick);
		try {
			gameState.setPlayerHandler(new PlayerHandlerImpl(view));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("New Game button user=" + gameState.getUserNick());
		viewController.createNewGameComponent();
	}

}
