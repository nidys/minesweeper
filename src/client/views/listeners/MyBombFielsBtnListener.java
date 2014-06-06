package client.views.listeners;

import java.awt.event.ActionEvent;

import client.utils.ListenerGenerator;

public class MyBombFielsBtnListener extends BaseListenerForWindow {

	public MyBombFielsBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Clicked my bomb field");
	}

}
