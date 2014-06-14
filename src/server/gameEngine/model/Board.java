package server.gameEngine.model;

import static client.utils.LoggingHelper.debug;

import java.util.Random;

import org.apache.log4j.Logger;

public class Board {
	private static Logger log = Logger.getLogger(Board.class);
	private Field[][] mineField;

	private int boardSizeX;
	private int boardSizeY;
	private int bombsNumber;

	public Board(int bombsNum, int boardSizeX, int boardSizeY) {
		this.bombsNumber = bombsNum;
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
		mineField = new Field[boardSizeX][boardSizeY];
		initEmpty();
		// generateBombs(bombsNum);
	}

	// public Result shot(int pos) {
	// if (mineField[pos] == Field.BOMB) {
	// return Result.BOMB;
	// } else {
	// return Result.EMPTY;
	// }
	// // TODO consider field with number
	//
	// }

	// public void generateBombs(int bombsNum) {
	// Random r = new Random(System.currentTimeMillis());
	// int i = 0;
	// while (i < bombsNum) {
	// int val = r.nextInt(mineField.length);
	// if (mineField[val] != Field.BOMB) {
	// mineField[val] = Field.BOMB;
	// i++;
	// }
	// }
	// debugFieldSett(String.format("generateBombs[%d], done\n", bombsNum));
	// }

	public int getBoardSizeX() {
		return boardSizeX;
	}

	public int getBoardSizeY() {
		return boardSizeY;
	}

	public int getBombsNumber() {
		return bombsNumber;
	}

	public void initEmpty() {
		for (int i = 0; i < mineField.length; i++) {
			// mineField[i] = Field.EMPTY;
		}
	}

	private void debugFieldSett(String msg) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < mineField.length; i++) {
			s.append(String.format("\t[i=%d] = %s\n", i, mineField[i]));
		}
		debug(log, msg, s.toString());
	}

	public enum Field {
		EMPTY, MARKED, BOMB;
	}
}
