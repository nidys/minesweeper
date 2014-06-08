package client.controllers;

import java.awt.event.ActionEvent;

import client.controllers.base.BaseControllerForWindow;
import client.utils.ListenerGenerator;

public class OpponentBombFieldBtnController extends BaseControllerForWindow {

	public OpponentBombFieldBtnController(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
