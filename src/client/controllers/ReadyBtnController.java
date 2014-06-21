package client.controllers;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import common.exceptions.gameManager.UnknownGameId;
import common.exceptions.gameManager.UnknownUserId;

public class ReadyBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(ReadyBtnController.class);

	public ReadyBtnController(ControllerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
	
			netManager.ready(gameState.getUserNick(), gameState.getGameId());

		} catch (RemoteException | UnknownGameId | UnknownUserId ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}
}
