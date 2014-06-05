package common.model;

import java.util.Random;

public class Board {
	private final int BOARD_SIZE = 5;
	private Field[][] mineField = new Field[BOARD_SIZE][BOARD_SIZE];
	private int bombsNum;

	public Board(int bombsNum) {
		this.bombsNum = bombsNum;
		initEmpty();
		generateBombs(bombsNum);
	}

	private void generateBombs(int bombsNum) {
		int tmpBombsNum = bombsNum;
		Random r = new Random();
		while (tmpBombsNum > 0) {
			int pos = r.nextInt(BOARD_SIZE * BOARD_SIZE);
			if (mineField[pos / BOARD_SIZE][pos % BOARD_SIZE] != Field.BOMB) {
				mineField[pos / BOARD_SIZE][pos % BOARD_SIZE] = Field.BOMB;
				tmpBombsNum--;
			}
		}
	}

	private void initEmpty() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				mineField[i][j] = Field.EMPTY;
			}
		}
	}

	public enum Field {
		EMPTY, MARKED, BOMB;
	}
}
