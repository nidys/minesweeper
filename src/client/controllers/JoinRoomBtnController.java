package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.DebugGraphics;

import org.apache.log4j.Logger;

import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.AvailableGameInfo;
import common.model.GameDifficultyFactors;
import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GameRoomDialog;
import client.views.GamesListDialog;
import client.views.MainWindow;
import client.views.component.GameBoardPanel;

public class JoinRoomBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(JoinRoomBtnController.class);
	private MainWindow mainView;
	private GamesListDialog gamesListView;
	
	public JoinRoomBtnController(ControllerGenerator listenerGenerator, MainWindow mainView, GamesListDialog gamesListView) {
		super();
		this.mainView = mainView;
		this.gamesListView = gamesListView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = gameState.getUserNick();
			GameMode gameMode = newGameView.getGameMode(); // TODO Shoudn't be taken from newGameView
					
			info(log, "Sending join game for user[%s]", playerName);
			String gameId = gamesListView.getSelectedGame();
			gameState.setGameId(gameId);
			System.out.println("Joinuje do: " + gameId);
			
			newGameView.setVisible(false);
			GameDifficultyFactors difficultyFactors = netManager.joinGame(playerName,
					gameState.getPlayerHandler(), gameId);
			gameState.setDifficultyFactors(difficultyFactors);
			gameState.setMode(gameMode);
			
			GameRoomDialog gameRoomView = componentsFactory.createGameRoomComponent(playerName, gameId);
			gameRoomView.setVisible(true);
			gamesListView.setVisible(false);
			//initializeGameBoard(gameDifficultyFactors, playerName, gameMode);
		} catch (RemoteException | MaximumPlayerExceededException | InvalidGameNameException
				| PlayerWithIdenticalNickAlreadyInGame ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

	private void initializeGameBoard(GameDifficultyFactors gameDifficultyFactors,
		mainView.addPlayer(new PlayerGameBoardPanel(gameDifficultyFactors, playerName));
			String playerName, GameMode mode) {
		mainView.initializeGameBoard(mode);
		componentsFactory.initializeBoardListeners();
	}
}