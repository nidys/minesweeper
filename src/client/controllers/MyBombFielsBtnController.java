package client.controllers;

import static client.utils.LoggingHelper.debug;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import common.model.Result;
import client.controllers.base.BaseControllerForWindow;
import client.utils.ControllerGenerator;

public class MyBombFielsBtnController extends BaseControllerForWindow {
	private static Logger log = Logger.getLogger(MyBombFielsBtnController.class);

	public MyBombFielsBtnController(ControllerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		debug(log, "Clicked my bomb field, user[%s]", gameState.getUserNick());
		int position = Integer.valueOf(e.getActionCommand());
		// TODO from now shot return list of fields to discover
		// Result res = netManager.shot(gameState.getUserNick(), position);
		// if (res == Result.BOMB) {
		// mainView.setMyFieldAsBomb(position);
		// } else {
		// mainView.setMyFieldAsEmpty(position);
		// }
	}

}
