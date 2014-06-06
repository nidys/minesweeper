package client.views.listeners;

import java.awt.event.ActionEvent;

import common.model.Result;

import client.utils.ListenerGenerator;

public class MyBombFielsBtnListener extends BaseListenerForWindow {

	public MyBombFielsBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clicked my bomb field, user=" + gameState.getUserNick());
		int position = Integer.valueOf(e.getActionCommand());
		Result res = netManager.shot(gameState.getUserNick(), position);
		if (res == Result.BOMB) {
			view.setMyFieldAsBomb(position);
		} else {
			view.setMyFieldAsEmpty(position);
		}
	}

}
