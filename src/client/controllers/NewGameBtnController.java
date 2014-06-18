package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForWindow;
import client.network.PlayerHandlerImpl;
import client.utils.ControllerGenerator;
import client.views.NewGameDialog;

public class NewGameBtnController extends BaseControllerForWindow {
	private static Logger log = Logger.getLogger(NewGameBtnController.class);

	public NewGameBtnController(ControllerGenerator listenerGenerator) {
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
		info(log, "New Game button user[%s]", gameState.getUserNick());
		NewGameDialog newGameView = componentsFactory.createNewGameComponent();
		newGameView.setVisible(true);
	}

}
