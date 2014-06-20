package common.network.callbacks;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import common.enums.GameInterruptMessage;
import common.enums.GameMode;
import common.model.GameDifficultyFactors;
import common.model.GameSummary;
import common.model.LostReason;
import common.network.protocols.GameLogic;

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

	public void playerLost(LostReason reason) throws RemoteException;

	/**
	 * Used during game
	 * 
	 * @param errorMsg
	 */
	public void reportError(GameInterruptMessage errorMsg) throws RemoteException;

	public void setEngine(GameLogic engine) throws RemoteException;
	
	public void addOpponent(String opponentName) throws RemoteException;

	void changeStateToReady(String opponentName) throws RemoteException;
	
	void startGame() throws RemoteException;
	
}
