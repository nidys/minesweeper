package client.controllers;

import java.awt.event.ActionEvent;

import client.controllers.base.BaseControllerForWindow;
import client.utils.ListenerGenerator;

public class ResetBtnController extends BaseControllerForWindow {

	public ResetBtnController(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		netManager.resetBoard(gameState.getUserNick());
		mainView.resetMyFields();
	}
}
