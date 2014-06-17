package client.controllers;

import static common.utils.LoggingHelper.debug;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.model.Config;
import common.model.GameDifficultyFactors;
import common.model.GameSettings;
import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.MainWindow;
import client.views.component.PlayerGameBoardPanel;

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
		debug(log, "Sending create game for user[%s]", gameState.getUserNick());
		try {
			// TODO pass here all required parameters
			GameDifficultyFactors gameDifficultyFactors 
				= netManager.createGame(createGameConfig());
			
			newGameView.setVisible(false);
			initializeGameBoard(gameDifficultyFactors);
		} catch (RemoteException | InvalidGameNameException |
				 MaximumRoomExceededException | MaxOpponentSizeIsTooLarge ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

	public void initializeGameBoard(GameDifficultyFactors gameDifficultyFactors) {
		mainView.drawGameBoard();
		mainView.initializeGameBoard(gameState.getMode());
		mainView.addNewPlayerToView(new PlayerGameBoardPanel(gameDifficultyFactors)); // TODO Przekazac dane z Config'a

		componentsFactory.initializeBoardListeners();
	}
	
	// TODO MALY 
	// Temporary filling Config with mock data. 
	// To be removed (replaced with data from the view).
	private Config createGameConfig()
	{
		Config config = new Config();
		config.setGameDifficulty(GameDifficulty.MEDIUM); // TODO Change it
		config.setGameId("MOCK_GAME_ID");
		config.setGameMode(GameMode.CLASSIC);
		config.setUserNick("MOCK_USER_NICK");
		config.setPlayerHandler(gameState.getPlayerHandler());
		return config;
	}
}
