package client.controllers.newGame;

import java.awt.event.ActionEvent;

import common.enums.GameDifficulty;
import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;


public class EasyDifficultyBtnController extends BaseControllerForDialog {

	public EasyDifficultyBtnController(ControllerGenerator listenerGenerator) {
		listenerGenerator.setFieldsForDialog(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if (label == "Easy")
			gameState.setDifficulty(GameDifficulty.EASY);
	}
}

