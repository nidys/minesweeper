package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GamesListDialog;
import client.views.MainWindow;
import client.views.component.GameBoardPanel;
import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.AvailableGameInfo;
import common.model.GameDifficultyFactors;

public class JoinGamesBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(JoinGamesBtnController.class);
	private MainWindow mainView;

	public JoinGamesBtnController(ControllerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = gameState.getUserNick();
			GameMode gameMode = newGameView.getGameMode();
			
			List<AvailableGameInfo> games = netManager.getGameList();
			GamesListDialog gamesListView = componentsFactory.createGamesListComponent();
			gamesListView.setGames(games);
			gamesListView.setVisible(true);
			
			
//			info(log, "Sending join game for user[%s]", playerName);
//			GameDifficultyFactors gameDifficultyFactors = netManager.joinGame(playerName,
//					gameState.getPlayerHandler(), newGameView.getGameId());

			newGameView.setVisible(false);
			//initializeGameBoard(gameDifficultyFactors, playerName, gameMode);
		} catch (RemoteException ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

//	private void initializeGameBoard(GameDifficultyFactors gameDifficultyFactors,
//			String playerName, GameMode gameMode) {
//		mainView.drawGameBoard();
//		mainView.initializeGameBoard(gameMode);
//		mainView.addPlayer(new PlayerGameBoardPanel(gameDifficultyFactors, playerName));
//		componentsFactory.initializeBoardListeners();
//	}
}
