package client.controllers;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GameRoomDialog;
import client.views.MainWindow;
import client.views.component.GameBoardPanel;

import common.exceptions.gameManager.NotAllPlayersYetAreReady;
import common.exceptions.gameManager.UnknownGameId;
import common.exceptions.gameManager.UnknownUserId;

public class StartGameBtnController extends BaseControllerForDialog {
	private MainWindow mainView;
	private GameRoomDialog gameRoomView;

	public StartGameBtnController(ControllerGenerator controllerGenerator,
			MainWindow mainView, GameRoomDialog gameRoomView) {
		super();
		this.mainView = mainView;
		this.gameRoomView = gameRoomView;
		controllerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			
			netManager.start(gameState.getUserNick(), gameState.getGameId());
			
			mainView.initializeGameBoard(gameState.getMode());
			mainView.addNewPlayerGameBoardPanel(new GameBoardPanel(gameState.getDifficultyFactors(), gameState.getUserNick(), 3, 120)); //TODO Changed it.
			
			componentsFactory.initializeBoardListeners();

			gameRoomView.setVisible(false);
			
		} catch (RemoteException | UnknownGameId | NotAllPlayersYetAreReady
				| UnknownUserId ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
