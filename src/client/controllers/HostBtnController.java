package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.internationalization.DialogText;
import client.utils.ControllerGenerator;
import client.views.MainWindow;
import client.views.MessageDialog;
import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.model.Config;
import common.model.GameDifficultyFactors;

public class HostBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(HostBtnController.class);
	private MainWindow mainView;
	private MessageDialog msgDialog;

	public HostBtnController(ControllerGenerator listenerGenerator, MainWindow mainView, MessageDialog msgDialog) {
		super();
		this.mainView = mainView;
		this.msgDialog = msgDialog;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = mainView.getPlayerName();
			String gameId = newGameView.getGameId();
			GameMode gameMode = newGameView.getGameMode();
			int lifesAmount = newGameView.getLifesAmount();
			long timeAmount = newGameView.getTimeAmount();
			int boardAmount = newGameView.getBoardAmount();

			boolean isWinWhenSolvedSelected = newGameView.isWinWhenSolvedSelected();
			boolean isLivesCountSelected = newGameView.isLivesCountSelected();
			boolean isBoardLimitSelected = newGameView.isBoardLimitSelected();
			boolean isTimedSelected = newGameView.isTimedSelected();

			info(log, "Sending create game for user[%s]", playerName);
			GameDifficultyFactors difficultyFactors = netManager.createGame(createGameConfig(
					playerName, gameMode, gameId, lifesAmount, timeAmount, boardAmount,
					isWinWhenSolvedSelected, isLivesCountSelected, isBoardLimitSelected,
					isTimedSelected));
			gameState.setDifficultyFactors(difficultyFactors);
			gameState.setMode(gameMode);
			gameState.setGameId(gameId);
			newGameView.setVisible(false);

			gameRoomDialog = componentsFactory.createGameRoomComponent(playerName, gameId, true);
			mainView.setGameRoomDialog(gameRoomDialog);
			gameRoomDialog.setVisible(true);

		} catch (RemoteException ex) {

			ex.printStackTrace();
		} catch (InvalidGameNameException ex) {

			msgDialog.displayInfoMsg(null, true,
					DialogText.INVALID_GAME_NAME_EX_MSG,
					DialogText.PROBLEM_TITLE);

			ex.printStackTrace();
		} catch (MaximumRoomExceededException ex) {
			msgDialog.displayInfoMsg(
					null,
					true,
					DialogText.MAX_ROOM_EXCEEDE_EX_MSG,
					DialogText.PROBLEM_TITLE);

			ex.printStackTrace();
		} catch (MaxOpponentSizeIsTooLarge ex) {
			msgDialog.displayInfoMsg(
					null,
					true,
					DialogText.MAX_OPPONENT_TOO_LARGE_EX_MSG,
					DialogText.PROBLEM_TITLE);
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

	// TODO Add initialization of the missing Config fields !!!
	// isNormal;isLifecount;isTimed;gameDuration;lifeAmount; maxOpponentAmount;
	private Config createGameConfig(String playerName, GameMode gameMode, String gameId,
			int lifesAmount, long timeAmount, int boardAmount, boolean isWinWhenSolvedSelected,
			boolean isLivesCountSelected, boolean isBoardLimitSelected, boolean isTimedSelected) {

		Config config = new Config();
		config.setGameDifficulty(newGameView.getGameDifficulty());
		config.setGameId(gameId);
		config.setGameMode(gameMode);
		config.setUserNick(playerName);
		config.setPlayerHandler(gameState.getPlayerHandler());
		config.setBoardAmount(boardAmount);
		config.setGameDuration(timeAmount);
		config.setLifeAmount(lifesAmount);
		config.setNormal(isWinWhenSolvedSelected);
		config.setLifecount(isLivesCountSelected);
		config.setTimed(isTimedSelected);

		return config;
	}
}
