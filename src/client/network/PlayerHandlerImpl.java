package client.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import static common.utils.LoggingHelper.info;
import client.controllers.BoardFieldBtnController;
import client.controllers.ResetBtnController;
import client.views.MainWindow;
import client.views.component.GameBoardPanel;
import common.enums.GameInterruptMessage;
import common.enums.GameMode;
import common.enums.LostReasonMessage;
import common.model.GameDifficultyFactors;
import common.model.GameSummary;
import common.model.LostReason;
import common.model.Result;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;

public class PlayerHandlerImpl extends UnicastRemoteObject implements PlayerHandler {
	private static Logger log = Logger.getLogger(PlayerHandlerImpl.class);
	private MainWindow view;
	private NetworkManager netManager;

	public PlayerHandlerImpl(MainWindow view, NetworkManager netManager) throws RemoteException {
		super();
		this.view = view;
		this.netManager = netManager;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2877955281942062670L;

	@Override
	public void setOpponents(String newOpponentNick) {
		info(log, "Setting newOpponent[%s]", newOpponentNick);
		// TODO For CLASSIC mode: set Labels for progress status
	}

	// TODO MALY Check if it's obsolete
//	public void opponentShot(int position, Result result) throws RemoteException {
//		log.debug(String.format("Got opponentShot. Posp[%d] result[%s]", position, result));
//		if (result == Result.BOMB) {
//			view.setFieldAsBomb(position);
//		} else {
//			view.setFieldAsEmpty(position);
//		}
//	}

	@Override
	public void endGame(GameSummary gameSummary) throws RemoteException {
		info(log, "Got end game with result[%s]", gameSummary.getGameResult());
		// TODO For CLASSIC send after one of the players win. From now,
		
		// GameLogic wont be available
		view.setResetEnable(false);
		view.displayEndGameSummary(gameSummary);

	}

	@Override
	public void setProgress(int progress, String playerNick) throws RemoteException {
		info(log, "Setting progress to[%d]", progress);
		// TODO Only For CLASSIC. See method description
		view.setProgress(playerNick, progress);

	}

	@Override
	public void reportError(GameInterruptMessage errorMsg) throws RemoteException {
		info(log, "Got game unexpectedly ended[%s]", errorMsg);
		// TODO send during game, before end, in case e.g. some players lost
		// connection

	}

	@Override
	public void setEngine(GameLogic engine) throws RemoteException {
		netManager.setEngine(engine);
		view.getGameRoomDialog().setVisible(false);
		
		// TODO invoke some window or set enable on board view?
	}

	@Override
	public void playerLost(LostReason reason) throws RemoteException {
		// TODO Look at 'ending scenarios' defined in gdoc for information what
		// should be done here

		if (reason.getReasonMsg() == LostReasonMessage.NO_BOARDS_LEFT)
			view.setResetEnable(false);
		System.out.println("Display reason of lost!!!!!!!!!!!!!!!!");
		view.displayLostReason(reason);

	}
	
	@Override
	public void addOpponent(String opponentName) throws RemoteException
	{
		view.getGameRoomDialog().addOpponent(opponentName);
		view.getGameRoomDialog().pack();
	}

	@Override
	public void changeState(String opponentName) throws RemoteException {
		
		if (opponentName == null)
			return;
		view.getGameRoomDialog().changeOpponentState(opponentName);
		
	}

	@Override
	public void startGame() throws RemoteException {
		view.getGameRoomDialog().simulateStartButtonClick(); // simulate start btn for other users
		
	}

	@Override
	public void informAboutLasBoard() throws RemoteException {
		view.setResetEnable(false);
		
	}

	// @Override
	// public void opponentShot(int position, Result result) throws
	// RemoteException {
	// log.debug(String.format("Got opponentShot. Posp[%d] result[%s]",
	// position, result));
	// if (result == Result.BOMB) {
	// view.setOpponentAsBomb(position);
	// } else {
	// view.setOpponentAsEmpty(position);
	// }
	// }

}
