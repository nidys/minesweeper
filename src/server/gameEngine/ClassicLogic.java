package server.gameEngine;

import static common.utils.LoggingHelper.debug;
import static common.utils.LoggingHelper.error;
import static common.utils.LoggingHelper.info;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.PlayerData;
import server.gameEngine.model.Board;

import common.enums.GameMode;
import common.exceptions.shot.PositionOutOfRange;
import common.model.ShotResult;
import common.network.callbacks.PlayerHandler;

public class ClassicLogic extends BaseLogicImpl {

	@Override
	public GameMode getGameMode() {
		return GameMode.CLASSIC;
	}

	@Override
	public List<ShotResult> shot(String userNick, int position) throws RemoteException,
			PositionOutOfRange {
		debug(log, "Got shot for user[%s], pos[%d]", userNick, position);
		PlayerData pData = players.get(userNick);
		Board board = pData.board;
		PlayerHandler handler = pData.playerHandler;

		if (board.isValueWithinBoardSize(position) == false) {
			info(log, "Player[%s] shot in not valid field, position[%d]", userNick, position);
			throw new PositionOutOfRange();
		}

		List<ShotResult> arr = new ArrayList<ShotResult>();
		arr.add(new ShotResult(position, board.getShotResult(position)));
		// TODO report progress to other players?
		error(log, "implement progress?");
		return arr;
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		// TODO implement logic
		debug(log, "Reset from player[%s]", userNick);
		// players.get(userNick).generateBombs(bombsNum);
	}

	@Override
	public void ready(String userNick) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");
	}

	@Override
	public void start(String userNick) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");

	}

	@Override
	public void leaveBeforeEnd(String userNick) throws RemoteException {
		// TODO Auto-generated method stub
		error(log, "implement!!!");

	}

	// #############################################################
	private static final long serialVersionUID = 7863313309124525563L;
	private static Logger log = Logger.getLogger(ClassicLogic.class);

	public ClassicLogic() throws RemoteException {
		super();
	}
}
