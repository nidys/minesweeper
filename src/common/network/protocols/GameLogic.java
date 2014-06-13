package common.network.protocols;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.model.ShotResult;

public interface GameLogic extends Remote {

	public List<ShotResult> shot(String userNick, int position) throws RemoteException;

	public void resetBoard(String userNick) throws RemoteException;

	public void ready(String userNick) throws RemoteException;

	/**
	 * Host uses this method when all players click 'ready' button
	 * 
	 * @param userNick
	 * @throws RemoteException
	 */
	public void start(String userNick) throws RemoteException;

	public void leaveBeforeEnd(String userNick) throws RemoteException;

}
