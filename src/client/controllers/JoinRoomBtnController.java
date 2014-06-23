package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.internationalization.DialogText;
import client.utils.ControllerGenerator;
import client.views.GamesListDialog;
import client.views.MainWindow;
import client.views.MessageDialog;
import client.views.component.GameBoardPanel;
import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.exceptions.join.UnableToJoinGame;
import common.model.GameDifficultyFactors;

public class JoinRoomBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(JoinRoomBtnController.class);
	private MainWindow mainView;
	private GamesListDialog gamesListView;
	private MessageDialog msgDialog;

	public JoinRoomBtnController(ControllerGenerator listenerGenerator, MainWindow mainView,
			GamesListDialog gamesListView, MessageDialog msgDialog) {
		super();
		this.mainView = mainView;
		this.gamesListView = gamesListView;
		this.msgDialog = msgDialog;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String playerName = gameState.getUserNick();
			GameMode gameMode = newGameView.getGameMode(); // TODO Shoudn't be
															// taken from
															// newGameView

			info(log, "Sending join game for user[%s]", playerName);
			String gameId = gamesListView.getSelectedGame();
			gameState.setGameId(gameId);
System.out.println("Joinuje do: " + gameId);


			newGameView.setVisible(false);
			
			
			gameRoomDialog = componentsFactory.createGameRoomComponent(playerName, gameId, false);
						
			mainView.setGameRoomDialog(gameRoomDialog); //PlayerHandlerImp - > ma dostep do GameRoomDialog

			
			
			gamesListView.setVisible(false);
			
			GameDifficultyFactors difficultyFactors = netManager.joinGame(playerName,
					gameState.getPlayerHandler(), gameId);
			gameState.setDifficultyFactors(difficultyFactors);
			gameState.setMode(gameMode);

			gameRoomDialog.setVisible(true);
			
			//initializeGameBoard(gameDifficultyFactors, playerName, gameMode);

		} catch (RemoteException  ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		} catch (InvalidGameNameException ex) {

			msgDialog.displayInfoMsg(null, true,
					DialogText.INVALID_GAME_NAME_EX_MSG,
					DialogText.PROBLEM_TITLE);

			ex.printStackTrace();
		} catch (MaximumPlayerExceededException ex) {
			msgDialog.displayInfoMsg(
					null,
					true,
					DialogText.MAX_PLAYER_EXCEEDE_EX_MSG,
					DialogText.PROBLEM_TITLE);

			ex.printStackTrace();
		} catch (PlayerWithIdenticalNickAlreadyInGame ex) {
			msgDialog.displayInfoMsg(
					null,
					true,
					DialogText.PLAYER_WITH_IDENTICAL_NICK_EX_MSG,
					DialogText.PROBLEM_TITLE);
			// TODO Handle exceptions
			ex.printStackTrace();
		} catch (UnableToJoinGame ex) {
			msgDialog.displayInfoMsg(
					null,
					true,
					DialogText.UNABLE_TO_JOIN_EX_MSG,
					DialogText.PROBLEM_TITLE);
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}

	private void initializeGameBoard(GameDifficultyFactors gameDifficultyFactors,
			String playerName, GameMode mode) {
		int progressMaxValue = ( gameDifficultyFactors.getBoardSizeX() * gameDifficultyFactors.getBoardSizeY() ) - gameDifficultyFactors.getBombsAmount();
		mainView.initializeGameBoard(mode, progressMaxValue);
		mainView.addNewPlayerGameBoardPanel(new GameBoardPanel(gameDifficultyFactors, playerName, 3, 120)); //TODO Changed it.
		componentsFactory.initializeBoardListeners();
	}
}
