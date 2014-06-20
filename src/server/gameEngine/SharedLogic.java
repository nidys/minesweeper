package server.gameEngine;

import static common.utils.LoggingHelper.error;
import static common.utils.LoggingHelper.info;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import server.PlayerData;
import server.gameEngine.model.Board;
import server.gameEngine.utils.Solver;

import common.enums.GameMode;
import common.exceptions.shot.PositionOutOfRange;
import common.model.DiscoveredField;
import common.model.ShotResult;

@SuppressWarnings("serial")
public class SharedLogic extends BaseLogicImpl {

	private static Logger log = Logger.getLogger(ClassicLogic.class);

	public SharedLogic() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public GameMode getGameMode() {

		return GameMode.SHARED;
	}

	@Override
	public ShotResult shot(String userNick, int position) throws RemoteException,
			PositionOutOfRange {
		info(log, "Got shot for user[%s], pos[%d]", userNick, position);
		PlayerData pData = players.get(userNick);
		Board board = pData.board;
		// PlayerHandler handler = pData.playerHandler;

		if (board.isValueWithinBoardSize(position) == false) {
			info(log, "Player[%s] shot in not valid field, position[%d]", userNick, position);
			throw new PositionOutOfRange();
		}

		// TODO implement full shot logic
		List<DiscoveredField> unrevealed = Solver.shot(board.mineField, position);

		// TODO return Shotresutl
		return new ShotResult(unrevealed, 10, 10, true);
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		// TODO Auto-generated method stub

		info(log, "Reset from player[%s]", userNick);
		error(log, "!!!implement");
		// players.get(userNick).resetBoard();
	}

	@Override
	public void leaveBeforeEnd(String userNick) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
