package client.controllers.newGame;

import java.awt.event.ActionEvent;

import common.enums.GameDifficulty;
import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;

public class HardDifficultyBtnController extends BaseControllerForDialog {
	
	public HardDifficultyBtnController(ControllerGenerator listenerGenerator) {
		listenerGenerator.setFieldsForDialog(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if (label == "Hard")
			gameState.setDifficulty(GameDifficulty.HARD);
	}
}
