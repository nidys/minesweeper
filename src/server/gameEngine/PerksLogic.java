package server.gameEngine;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import common.model.Board;
import common.model.Result;
import common.network.PlayerHandler;

import server.BoardDispatcher;

public class PerksLogic {
	private static Logger log = Logger.getLogger(PerksLogic.class);
	private static final int MAX_PLAYERS = 4;
	Map<String, BoardDispatcher> players = new HashMap<String, BoardDispatcher>();
	private int boardSize;
	private int bombsNum;
	private final int BOARD_SIZE = 2; // Change to MAX board size?

	public boolean initGame(String userNick, PlayerHandler playerHandler) {
		log.debug("Create game for user = " + userNick);
		// receive boardSize, bombsNum? verify config?
		boardSize = 4;
		bombsNum = 1;
		players.put(userNick, new BoardDispatcher(new Board(bombsNum, boardSize, userNick), playerHandler));
		return false;
	}

	public boolean joinNewPlayer(String userNick, PlayerHandler playerHandler) {
		if (players.size() >= MAX_PLAYERS) {
			log.debug(String.format("joinNewPlayer = %s", userNick));
			players.put(userNick, new BoardDispatcher(new Board(bombsNum, boardSize, userNick), playerHandler));
			return true;
		}
		log.debug(String.format("Player[%s] not joined, too many players", userNick));
		return false;
	}

	public Result shot(String userNick, int position) {
		position--;
		log.debug(String.format("Got shor for user[%s], pos[%s]", userNick, position));
		if (isValueWithinBoardSize(position) == false) {
			log.info(String.format("Player[%s] shot in not valid field = %d", userNick, position));
			return Result.ERROR;
		}
		Result res = players.get(userNick).shot(position);
		log.debug(String.format("Player[%s], shot result = %s", userNick, res));
		for (String nick : players.keySet()) {
			if (!nick.equals(userNick)) {
				players.get(nick).informOpponent(position, res);
			}
		}
		return res;
	}

	public void resetBoard(String userNick) {
		log.debug("Reset from player: " + userNick);
		players.get(userNick).generateBombs(bombsNum);
	}

	private boolean isValueWithinBoardSize(int val) {
		return val >= 0 && val < boardSize;
	}
}
