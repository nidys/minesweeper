package client.views.listeners;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.NewGameController;
import client.network.PlayerHandlerImpl;
import client.utils.ListenerGenerator;

public class NewGameButtonListener extends BaseListenerForWindow {
	private static Logger log = Logger.getLogger(NewGameButtonListener.class);

	public NewGameButtonListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String serverAddr = mainView.getServerAddress();
		String userNick = mainView.getUserNick();
		netManager.connectToServer(serverAddr, userNick);
		gameState.setUserNick(userNick);
		try {
			gameState.setPlayerHandler(new PlayerHandlerImpl(mainView));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.debug("New Game button user=" + gameState.getUserNick());
		NewGameController newGameController = componentsFactory.createNewGameComponent();
		newGameController.activate();
	}

}
