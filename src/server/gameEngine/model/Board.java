package server.gameEngine.model;

import static common.utils.LoggingHelper.debug;

import java.util.Random;

import org.apache.log4j.Logger;

import common.utils.PositionConverter;

public class Board {
	private Field[][] mineField;

	private int maxPosition;
	private int bombsNumber;

	public Board(int bombsNum, int boardSizeX, int boardSizeY) {
		this.bombsNumber = bombsNum;
		mineField = new Field[boardSizeX][boardSizeY];
		maxPosition = boardSizeX * boardSizeY;
		initEmpty();
		generateBombs();
	}

	public int getShotResult(int pos) {
		int x = PositionConverter.getXFromPosition(pos, mineField.length);
		int y = PositionConverter.getYFromPosition(pos, mineField.length);
		return mineField[x][y].getValue();
	}

	public boolean isValueWithinBoardSize(int pos) {
		return pos >= 0 && pos < maxPosition;
	}

	public void generateBombs() {
		Random r = new Random(System.currentTimeMillis());
		int i = 0;
		while (i < bombsNumber) {
			int val = r.nextInt(mineField.length * mineField[0].length);
			int x = PositionConverter.getXFromPosition(val, mineField.length);
			int y = PositionConverter.getYFromPosition(val, mineField.length);
			if (mineField[x][y] != Field.BOMB) {
				mineField[x][y] = Field.BOMB;
				i++;
			}
		}
		debugFieldSett(String.format("generateBombs[%d], done\n", bombsNumber));
	}

	public void initEmpty() {
		for (int i = 0; i < mineField.length; i++) {
			for (int j = 0; j < mineField[i].length; j++) {
				mineField[i][j] = Field.EMPTY;
			}
		}
	}

	private void debugFieldSett(String msg) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < mineField[0].length; i++) {
			s.append(String.format("i[%d] - ", i));
			for (int j = 0; j < mineField.length; j++) {
				s.append(String.format("%s | ", mineField[j][i]));
			}
			s.append("\n");
		}
		debug(log, msg + s.toString());
	}

	public enum Field {
		EMPTY(0), BOMB(-1), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);
		private int val;

		private Field(int val) {
			this.val = val;
		}

		public int getValue() {
			return val;
		}
	}

	private static Logger log = Logger.getLogger(Board.class);
}
