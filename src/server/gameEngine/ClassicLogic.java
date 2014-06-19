package server.gameEngine;

import static common.utils.LoggingHelper.error;
import static common.utils.LoggingHelper.info;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import server.PlayerData;
import server.gameEngine.model.Board;
import server.gameEngine.utils.Generator;

import common.enums.GameMode;
import common.exceptions.shot.PositionOutOfRange;
import common.model.DiscoveredField;
import common.model.ShotResult;

public class ClassicLogic extends BaseLogicImpl {

	@Override
	public GameMode getGameMode() {
		return GameMode.CLASSIC;
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
		List<DiscoveredField> unrevealed = Generator.shot(board.mineField, position);

		for (String otherPlayer : players.keySet()) {
			if (otherPlayer.equals(userNick) == false) {
				// TODO whos progress?
				players.get(otherPlayer).playerHandler.setProgress(board.getProgress(), userNick);
			}
		}
		error(log, "implement correct shot result response");

		// TODO return Shotresutl
		return new ShotResult(unrevealed, 10, 10, true);
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		// TODO implement logic
		info(log, "Reset from player[%s]", userNick);
		// players.get(userNick).generateBombs(bombsNum);
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
