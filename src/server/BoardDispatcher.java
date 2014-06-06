package server;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import common.model.Board;
import common.model.Result;
import common.network.PlayerHandler;

public class BoardDispatcher {
	private static Logger log = Logger.getLogger(BoardDispatcher.class);
	private Board board;
	private PlayerHandler playerHandler;

	public BoardDispatcher(Board board, PlayerHandler playerHandler) {
		super();
		this.board = board;
		this.playerHandler = playerHandler;
	}

	public Result shot(int pos) {
		return board.shot(pos);

	}

	public void generateBombs(int bombsNum) {
		board.initEmpty();
		board.generateBombs(bombsNum);
	}

	public void informOpponent(int position, Result res) {
		try {
			playerHandler.opponentShot(position, res);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
