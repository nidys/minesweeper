package server;

import server.gameEngine.model.Board;
import common.network.callbacks.PlayerHandler;

public class PlayerData {
	public Board board;
	public PlayerHandler playerHandler;

	public PlayerData(Board board, PlayerHandler playerHandler) {
		super();
		this.board = board;
		this.playerHandler = playerHandler;
	}

	// TODO change those methods
	// public Result shot(int pos) {
	// return board.shot(pos);
	//
	// }
	//
	// public void generateBombs(int bombsNum) {
	// board.initEmpty();
	// board.generateBombs(bombsNum);
	// }
	//
	// public void informOpponent(int position, Result res) {
	// // try {
	// // playerHandler.opponentShot(position, res);
	// // } catch (RemoteException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// }
}
