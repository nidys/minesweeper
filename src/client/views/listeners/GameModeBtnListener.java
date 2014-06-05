package client.views.listeners;

import java.awt.event.ActionEvent;

import common.gameRules.GameMode;

import client.utils.ListenerGenerator;

public class GameModeBtnListener extends BaseListenerForDialog {

	public GameModeBtnListener(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameState.setCurrentMode(GameMode.valueOf(e.getActionCommand()));
	}

}
