package client.controllers;

import java.awt.event.ActionEvent;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GameRoomDialog;
import client.views.MainWindow;
import client.views.component.PlayerGameBoardPanel;

public class StartGameBtnController extends BaseControllerForDialog {
	private MainWindow mainView;
	private GameRoomDialog gameRoomView;

	public StartGameBtnController(ControllerGenerator controllerGenerator, MainWindow mainView, GameRoomDialog gameRoomView) {
		super();
		this.mainView = mainView;
		this.gameRoomView = gameRoomView;
		controllerGenerator.setFieldsForDialog(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainView.drawGameBoard();
		mainView.initializeGameBoard(gameState.getMode());
		mainView.addPlayer(new PlayerGameBoardPanel(gameState.getDifficultyFactors(), gameState.getUserNick()));
		componentsFactory.initializeBoardListeners();
		
		gameRoomView.setVisible(false);
	}
}

