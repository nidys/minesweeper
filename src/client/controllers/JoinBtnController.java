package client.controllers;

import static common.utils.LoggingHelper.debug;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import common.exceptions.create.InvalidGameNameException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.model.Config;
import common.model.GameDifficultyFactors;
import client.controllers.base.BaseControllerForDialog;
import client.network.PlayerHandlerImpl;
import client.utils.ControllerGenerator;
import client.views.MainWindow;
import client.views.component.PlayerGameBoardPanel;

public class JoinBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(JoinBtnController.class);
	private MainWindow mainView;

	public JoinBtnController(ControllerGenerator listenerGenerator, MainWindow mainView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO must specify gameId too, and pass
		try {
			debug(log, "Sending join game for user[%s]", gameState.getUserNick());
			GameDifficultyFactors gameDifficultyFactors 
			= netManager.joinGame(gameState.getUserNick(), gameState.getPlayerHandler());
			
			newGameView.setVisible(false);
			initializeGameBoard(gameDifficultyFactors);
		} catch (RemoteException | MaximumPlayerExceededException | 
				 InvalidGameNameException | PlayerWithIdenticalNickAlreadyInGame ex) {
			// TODO Handle exceptions
			ex.printStackTrace();
		}
	}
	
	private void initializeGameBoard(GameDifficultyFactors gameDifficultyFactors) {
		mainView.drawGameBoard();
		mainView.initializeGameBoard(gameState.getMode());
		mainView.addNewPlayerToView(new PlayerGameBoardPanel(gameDifficultyFactors, gameState.getUserNick()));
		componentsFactory.initializeBoardListeners();
	}
}
