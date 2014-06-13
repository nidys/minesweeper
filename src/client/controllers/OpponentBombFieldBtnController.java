package client.controllers;

import java.awt.event.ActionEvent;

import client.controllers.base.BaseControllerForWindow;
import client.utils.ControllerGenerator;

public class OpponentBombFieldBtnController extends BaseControllerForWindow {

	public OpponentBombFieldBtnController(ControllerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
