package server.gameEngine;

import java.rmi.RemoteException;

import common.enums.GameMode;

public class ClassicLogic extends BaseLogicImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7863313309124525563L;

	public ClassicLogic() throws RemoteException {
		super();
	}

	@Override
	public GameMode getGameMode() {
		return GameMode.CLASSIC;
	}
}
