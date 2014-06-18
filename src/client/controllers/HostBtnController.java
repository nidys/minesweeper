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
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.model.Config;
import common.model.GameDifficultyFactors;

public class HostBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(HostBtnController.class);
	private MainWindow mainView;

	public HostBtnController(ControllerGenerator listenerGenerator,
			MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = mainView.getUserNick();
			GameMode gameMode = newGameView.getGameMode();
			
			debug(log, "Sending create game for user[%s]", playerName);
			GameDifficultyFactors gameDifficultyFactors = netManager
					.createGame(createGameConfig(playerName, gameMode));

			newGameView.setVisible(false);
			initializeGameBoard(gameDifficultyFactors, playerName, gameMode);
		} catch (RemoteException | InvalidGameNameException
				| MaximumRoomExceededException | MaxOpponentSizeIsTooLarge ex) {
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

	// TODO Add initialization of the missing Config fields
	private Config createGameConfig(String playerName, GameMode gameMode) {
		Config config = new Config();
		config.setGameDifficulty(newGameView.getGameDifficulty());
		config.setGameId(newGameView.getGameId());
		config.setGameMode(gameMode);
		config.setUserNick(playerName);
		config.setPlayerHandler(gameState.getPlayerHandler());
		return config;
	}
}
