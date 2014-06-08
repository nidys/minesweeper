package common.model;

import java.util.Random;

import org.apache.log4j.Logger;

public class Board {
	private static Logger log = Logger.getLogger(Board.class);
	private Field[] mineField;

	public Board(int bombsNum, int boardSize, String userNick) {
		mineField = new Field[boardSize];
		initEmpty();
		generateBombs(bombsNum);
	}

	public Result shot(int pos) {
		if (mineField[pos] == Field.BOMB) {
			return Result.BOMB;
		} else {
			return Result.EMPTY;
		}
		// TODO consider field with number

	}

	public void generateBombs(int bombsNum) {
		Random r = new Random(System.currentTimeMillis());
		int i = 0;
		while (i < bombsNum) {
			int val = r.nextInt(mineField.length);
			if (mineField[val] != Field.BOMB) {
				mineField[val] = Field.BOMB;
				i++;
			}
		}
		debugFieldSett(String.format("generateBombs[%d], done\n", bombsNum));
	}

	public void initEmpty() {
		for (int i = 0; i < mineField.length; i++) {
			mineField[i] = Field.EMPTY;
		}
	}

	private void debugFieldSett(String msg) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < mineField.length; i++) {
			s.append(String.format("\t[i=%d] = %s\n", i, mineField[i]));
		}
		log.debug(msg + s.toString());
	}

	public enum Field {
		EMPTY, MARKED, BOMB;
	}
}
