package common.network.protocols;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.exceptions.shot.PositionOutOfRange;
import common.model.ShotResult;

public interface GameLogic extends Remote {

	/**
	 * 
	 * @param userNick
	 * @param position
	 *            [0, X*Y - 1]
	 * @return
	 * @throws RemoteException
	 */
	public ShotResult shot(String userNick, int position) throws RemoteException,
			PositionOutOfRange;

	public void resetBoard(String userNick) throws RemoteException;

	public void leaveBeforeEnd(String userNick) throws RemoteException;
}
