package server.gameEngine;

import java.rmi.RemoteException;

import common.enums.GameMode;

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

}
