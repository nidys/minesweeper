package server;

import server.gameEngine.model.Board;
import common.network.callbacks.PlayerHandler;

public class PlayerData {
	public Board board;
	public PlayerHandler playerHandler;
	public boolean selectedReady = false;
	public int lifeAmount;

	public PlayerData(Board board, PlayerHandler playerHandler) {
		super();
		this.board = board;
		this.playerHandler = playerHandler;
	}

}
