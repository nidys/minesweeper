package client.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import static common.utils.LoggingHelper.debug;
import client.views.MainWindow;

import common.enums.GameInterruptMessage;
import common.model.GameSummary;
import common.network.callbacks.PlayerHandler;

public class PlayerHandlerImpl extends UnicastRemoteObject implements PlayerHandler {
	private static Logger log = Logger.getLogger(PlayerHandlerImpl.class);
	private MainWindow view;

	public PlayerHandlerImpl(MainWindow view) throws RemoteException {
		super();
		this.view = view;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2877955281942062670L;

	@Override
	public void setOpponents(String newOpponentNick) {
		debug(log, "Setting newOpponent[%s]", newOpponentNick);
		// TODO For CLASSIC mode: set Labels for progress status
	}

	@Override
	public void endGame(GameSummary gameSummary) throws RemoteException {
		debug(log, "Got end game with result[%s]", gameSummary.getGameResult());
		// TODO For CLASSIC send after one of the players win. From now,
		// GameLogic wont be available

	}

	@Override
	public void setProgress(int progress) throws RemoteException {
		debug(log, "Setting progress to[%d]", progress);
		// TODO Only For CLASSIC. See method description

	}

	@Override
	public void reportError(GameInterruptMessage errorMsg) throws RemoteException {
		debug(log, "Got game unexpectedly ended[%s]", errorMsg);
		// TODO send during game, before end, in case e.g. some players lost
		// connection

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
