package server.gameEngine;

import static common.utils.LoggingHelper.debug;
import static common.utils.LoggingHelper.error;
import static common.utils.LoggingHelper.info;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import server.PlayerData;
import server.gameEngine.model.Board;
import server.gameEngine.model.Field;
import server.gameEngine.utils.Solver;
import common.enums.GameMode;
import common.enums.LostReasonMessage;
import common.exceptions.shot.PositionOutOfRange;
import common.model.DiscoveredField;
import common.model.ShotResult;

public class ClassicLogic extends BaseLogicImpl {

	private static final int IGNORE_VAL = 0;
	private static final boolean GAME_CAN_BE_CONTINUED = true;

	@Override
	public GameMode getGameMode() {
		return GameMode.CLASSIC;
	}

	@Override
	public synchronized ShotResult shot(String userNick, int position) throws RemoteException,
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
		if (unrevealed == null) {
			debug(log, "This shoudlnt happened error in client");
			return null; // TODO add malicious try to shot already shot
							// position?
		}
		
		
		
		// Progress should be changed even though the normal is not set
		if (unrevealed.get(0).getValue() == Field.BOMB.getValue())
		{
			setProgress(userNick, board, 0);
		}
		else
		{
			setProgress(userNick, board, unrevealed.size());
		}
		
		if (isNormal && unrevealed.get(0).getValue() == Field.BOMB.getValue()) {
			debug(log, "Normal mode, game lost");
			informOthersAboutPlayerLost(userNick, LostReasonMessage.NORMAL_MODE_LOST);
			removePlayerAndFinishIfLast(userNick);
			
			return new ShotResult(unrevealed, IGNORE_VAL, IGNORE_VAL, !GAME_CAN_BE_CONTINUED);
		} else if (isNormal) {
			debug(log, "Normal mode, successful shot");

			return new ShotResult(unrevealed, IGNORE_VAL, IGNORE_VAL, GAME_CAN_BE_CONTINUED);
		} else if (isLifecount) {
			if (unrevealed.get(0).getValue() == Field.BOMB.getValue()) {
				pData.lifeAmount--;
				// TODO implement
			}
		}	

		error(log, "implement correct shot result response");

		// TODO return Shotresutl
		return new ShotResult(unrevealed, 10, 10, true);
	}

	private void setProgress(String userNick, Board board,	int i) throws RemoteException {
		
		if (i != 0)
			board.setExposed(board.getExposed() + i);
		else
			board.setExposed(0);
		
		for (String otherPlayer : players.keySet()) {
			info(log, "For other player[%s] than [%s] set progress[%s]", otherPlayer, userNick, board.getProgress());
			
			if (otherPlayer.equals(userNick) == false) {
				players.get(otherPlayer).playerHandler.setProgress(board.getProgress(),
						userNick);
			}
		}
	}

	@Override
	public synchronized void resetBoard(String userNick) throws RemoteException {

//TODO isNormal shouldn't affect boardReset
		
//		if (isNormal) {
//			return;
//		}
		
		info(log, "Reset from player[%s]", userNick);
		
		Board board = players.get(userNick).board;
		
		
		if (board.getBoardNum() < boardAmount) {
			info(log, "Reset from player[%s]", userNick);
			board.incBoardNum();
			
			board.setBoard(getCopyOfGeneratedBoard(board.getBoardNum()));
			for (String otherPlayer : players.keySet()) {
				if (otherPlayer.equals(userNick) == false) {
					players.get(otherPlayer).playerHandler.setProgress(board.getProgress(),
							userNick);
				}
			}
			
			if (board.getBoardNum() == boardAmount)
				players.get(userNick).playerHandler.informAboutLasBoard();
			
		} else if (board.getBoardNum() == boardAmount) {
			info(log, "Player[%s] used all bards", userNick);
			informOthersAboutPlayerLost(userNick, LostReasonMessage.NO_BOARDS_LEFT);
			informPlayerAboutLost(userNick, LostReasonMessage.NO_BOARDS_LEFT);
			removePlayerAndFinishIfLast(userNick);
		} else {
			error(log, "client should not be able to click reset after he lost");
			// TODO handle malicious ?
		}
	}

	

	@Override
	public synchronized void leaveBeforeEnd(String userNick) throws RemoteException {
		debug(log, "Player[%s] leaving game", userNick);
		informOthersAboutPlayerLost(userNick, LostReasonMessage.PLAYER_LEFT_BEFORE_END);
		removePlayerAndFinishIfLast(userNick);
	}

	// #############################################################
	private static final long serialVersionUID = 7863313309124525563L;
	private static Logger log = Logger.getLogger(ClassicLogic.class);

	public ClassicLogic() throws RemoteException {
		super();
	}
}
