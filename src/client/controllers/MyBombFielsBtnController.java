package client.controllers;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import common.model.Result;
import client.controllers.base.BaseControllerForWindow;
import client.utils.ListenerGenerator;

public class MyBombFielsBtnController extends BaseControllerForWindow {
	private static Logger log = Logger.getLogger(MyBombFielsBtnController.class);

	public MyBombFielsBtnController(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug("Clicked my bomb field, user=" + gameState.getUserNick());
		int position = Integer.valueOf(e.getActionCommand());
		Result res = netManager.shot(gameState.getUserNick(), position);
		if (res == Result.BOMB) {
			mainView.setMyFieldAsBomb(position);
		} else {
			mainView.setMyFieldAsEmpty(position);
		}
	}

}
