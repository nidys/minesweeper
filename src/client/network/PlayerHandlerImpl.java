package client.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import static common.utils.LoggingHelper.info;
import client.views.MainWindow;
import common.enums.GameInterruptMessage;
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

	public void opponentShot(int position, Result result) throws RemoteException {
		log.debug(String.format("Got opponentShot. Posp[%d] result[%s]", position, result));
		if (result == Result.BOMB) {
			view.setFieldAsBomb(position);
		} else {
			view.setFieldAsEmpty(position);
		}
	}

	@Override
	public void endGame(GameSummary gameSummary) throws RemoteException {
		info(log, "Got end game with result[%s]", gameSummary.getGameResult());
		// TODO For CLASSIC send after one of the players win. From now,
		// GameLogic wont be available

	}

	@Override
	public void setProgress(int progress, String playerNick) throws RemoteException {
		info(log, "Setting progress to[%d]", progress);
		// TODO Only For CLASSIC. See method description

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
		// TODO invoke some window or set enable on board view?
	}

	@Override
	public void playerLost(LostReason reason) throws RemoteException {
		// TODO Look at 'ending scenarios' defined in gdoc for information what
		// should be done here

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
