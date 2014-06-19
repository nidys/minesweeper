package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GameRoomDialog;
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

	public HostBtnController(ControllerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = mainView.getUserNick();
			String gameId = newGameView.getGameId();
			GameMode gameMode = newGameView.getGameMode();
			
			info(log, "Sending create game for user[%s]", playerName);
			GameDifficultyFactors difficultyFactors = netManager.createGame(createGameConfig(
					playerName, gameMode, gameId));
			gameState.setDifficultyFactors(difficultyFactors);
			gameState.setMode(gameMode);
			newGameView.setVisible(false);
			
			GameRoomDialog gameRoomView = componentsFactory.createGameRoomComponent(playerName, gameId);
			gameRoomView.setVisible(true);
		} catch (RemoteException | InvalidGameNameException | MaximumRoomExceededException
				| MaxOpponentSizeIsTooLarge ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

	// TODO Add initialization of the missing Config fields
	private Config createGameConfig(String playerName, GameMode gameMode, String gameId) {
		Config config = new Config();
		config.setGameDifficulty(newGameView.getGameDifficulty());
		config.setGameId(gameId);
		config.setGameMode(gameMode);
		config.setUserNick(playerName);
		config.setPlayerHandler(gameState.getPlayerHandler());
		return config;
	}
}
