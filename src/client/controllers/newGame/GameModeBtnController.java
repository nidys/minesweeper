package client.controllers.newGame;

import java.awt.event.ActionEvent;

import common.enums.GameMode;
import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;

public class GameModeBtnController extends BaseControllerForDialog {

	public GameModeBtnController(ControllerGenerator listenerGenerator) {
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameState.setCurrentMode(GameMode.valueOf(e.getActionCommand().toUpperCase()));
	}

}
