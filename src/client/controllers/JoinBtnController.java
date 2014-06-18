package client.controllers;

import static common.utils.LoggingHelper.debug;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.MainWindow;
import client.views.component.PlayerGameBoardPanel;

import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.GameDifficultyFactors;

public class JoinBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(JoinBtnController.class);
	private MainWindow mainView;

	public JoinBtnController(ControllerGenerator listenerGenerator,
			MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = gameState.getUserNick();
			GameMode gameMode = newGameView.getGameMode();

			debug(log, "Sending join game for user[%s]", playerName);
			GameDifficultyFactors gameDifficultyFactors = netManager.joinGame(
					playerName, gameState.getPlayerHandler(),
					newGameView.getGameId());

			newGameView.setVisible(false);
			initializeGameBoard(gameDifficultyFactors, playerName, gameMode);
		} catch (RemoteException | MaximumPlayerExceededException
				| InvalidGameNameException
				| PlayerWithIdenticalNickAlreadyInGame ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

	private void initializeGameBoard(
			GameDifficultyFactors gameDifficultyFactors, String playerName,
			GameMode gameMode) {
		mainView.drawGameBoard();
		mainView.initializeGameBoard(gameMode);
		mainView.addNewPlayerToView(new PlayerGameBoardPanel(
				gameDifficultyFactors, playerName));
		componentsFactory.initializeBoardListeners();
	}
}
