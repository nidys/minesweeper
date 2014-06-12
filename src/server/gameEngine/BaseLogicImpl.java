package server.gameEngine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import server.BoardDispatcher;

import common.model.Board;
import common.model.Result;
import common.network.callbacks.PlayerHandler;
import common.network.protocols.GameLogic;

public abstract class BaseLogicImpl extends UnicastRemoteObject implements GameLogic {
	protected BaseLogicImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320613550415878733L;
	private static Logger log = Logger.getLogger(BaseLogicImpl.class);
	protected static final int MAX_PLAYERS = 4;
	Map<String, BoardDispatcher> players = new HashMap<String, BoardDispatcher>();
	protected int boardSize;
	protected int bombsNum;
	protected final int BOARD_SIZE = 2; // Change to MAX board size?

	public boolean initGame(String userNick, PlayerHandler playerHandler) {
		log.debug("Create game for user = " + userNick);
		// receive boardSize, bombsNum? verify config?
		boardSize = 4;
		bombsNum = 1;
		players.put(userNick, new BoardDispatcher(new Board(bombsNum, boardSize, userNick), playerHandler));
		return true;
	}

	public boolean joinNewPlayer(String userNick, PlayerHandler playerHandler) {
		if (players.size() <= MAX_PLAYERS) {
			log.debug(String.format("joinNewPlayer = %s", userNick));
			players.put(userNick, new BoardDispatcher(new Board(bombsNum, boardSize, userNick), playerHandler));
			return true;
		}
		log.debug(String.format("Player[%s] not joined, too many players", userNick));
		return false;
	}

	@Override
	public Result shot(String userNick, int position) throws RemoteException {
		position--;
		log.debug(String.format("Got shor for user[%s], pos[%s]", userNick, position));
		if (isValueWithinBoardSize(position) == false) {
			log.info(String.format("Player[%s] shot in not valid field = %d", userNick, position));
			return Result.ERROR;
		}
		Result res = players.get(userNick).shot(position);
		log.debug(String.format("Player[%s], shot result = %s, players[%d]", userNick, res, players.size()));
		for (String nick : players.keySet()) {
			if (!nick.equals(userNick)) {
				log.debug(String.format("Sending to player[%s]", nick));
				players.get(nick).informOpponent(position + 1, res);
			}
		}
		return res;
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		log.debug("Reset from player: " + userNick);
		players.get(userNick).generateBombs(bombsNum);
	}

	private boolean isValueWithinBoardSize(int val) {
		return val >= 0 && val < boardSize;
	}

}