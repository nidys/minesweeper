package client.controllers;

import java.awt.event.ActionEvent;

import client.controllers.base.BaseControllerForWindow;
import client.utils.ControllerGenerator;

public class ResetBtnController extends BaseControllerForWindow {

	public ResetBtnController(ControllerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		netManager.resetBoard(gameState.getUserNick());
		// TODO MALY COMMIT That or mainView.resetMyFields();
		//mainView.resetFields();
	}
}
