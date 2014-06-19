package server.gameEngine;

import java.rmi.RemoteException;

import common.enums.GameMode;
import common.model.ShotResult;

public class PerksLogic extends BaseLogicImpl {

	public PerksLogic() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8003462797558330437L;

	// private static Logger log = Logger.getLogger(PerksLogic.class);

	@Override
	public GameMode getGameMode() {
		return GameMode.PERKS;
	}

	@Override
	public ShotResult shot(String userNick, int position) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void leaveBeforeEnd(String userNick) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
