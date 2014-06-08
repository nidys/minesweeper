package client.views.listeners;

import java.awt.event.ActionEvent;

import client.utils.ListenerGenerator;

public class ResetBtnListener extends BaseListenerForWindow {

	public ResetBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		netManager.resetBoard(gameState.getUserNick());
		view.resetMyFields();
	}

}
