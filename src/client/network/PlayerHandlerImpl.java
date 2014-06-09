package client.network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import client.views.MainWindow;

import common.model.Result;
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
	public void opponentShot(int position, Result result) throws RemoteException {
		log.debug(String.format("Got opponentShot. Posp[%d] result[%s]", position, result));
		if (result == Result.BOMB) {
			view.setOpponentAsBomb(position);
		} else {
			view.setOpponentAsEmpty(position);
		}
	}

}
