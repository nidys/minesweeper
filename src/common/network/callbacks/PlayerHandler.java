package common.network.callbacks;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import common.enums.GameInterruptMessage;
import common.model.GameSummary;

public interface PlayerHandler extends Remote, Serializable {
	// TODO for SHARED/PERKS mode?
	// public void opponentShot(int position, Result result) throws
	// RemoteException;

	public void setOpponents(String newOpponentNick) throws RemoteException;

	public void endGame(GameSummary gameSummary) throws RemoteException;

	/**
	 * 100% = boardSize - bombAmount
	 * 
	 * @param progress
	 * @param playerNick
	 *            TODO
	 */
	public void setProgress(int progress, String playerNick) throws RemoteException;

	/**
	 * Used during game
	 * 
	 * @param errorMsg
	 */
	public void reportError(GameInterruptMessage errorMsg) throws RemoteException;
}
