package common.model;

import java.util.Random;

public class Board {
	private final int BOARD_SIZE = 2;
	private Field[][] mineField = new Field[BOARD_SIZE][BOARD_SIZE];
	private int bombsNum;

	public Board(int bombsNum) {
		this.bombsNum = bombsNum;
		initEmpty();
		generateBombs(bombsNum);
	}

	public Result shot(int pos) {
		int x = pos / BOARD_SIZE;
		int y = pos % BOARD_SIZE;
		x = x == BOARD_SIZE ? 0 : x;
		y = y == BOARD_SIZE ? 0 : y;
		System.out.println("trying to hit x=" + x + ",y=" + y + ", pos = " + pos);
		if (mineField[x][y] == Field.BOMB) {
			return Result.BOMB;
		} else {
			return Result.EMPTY;
		}
		// TODO consider field with number

	}

	public void generateBombs(int bombsNum) {
		Random r = new Random(System.currentTimeMillis());
		int x = r.nextInt(BOARD_SIZE - 1);
		int y = r.nextInt(BOARD_SIZE - 1);
		mineField[x][y] = Field.BOMB;
		System.out.println("Bomb is at =" + x + ", =" + y);
		// int tmpBombsNum = bombsNum;
		// Random r = new Random();
		// while (tmpBombsNum > 0) {
		// int tmpBoardSize = BOARD_SIZE - 1;
		// int pos = r.nextInt(BOARD_SIZE * BOARD_SIZE);
		// int x = 1;// pos / BOARD_SIZE - 1;
		// int y = 0;// pos % BOARD_SIZE - 1;
		// x = x < 0 ? 0 : x;
		// y = y < 0 ? 0 : y;
		// if (mineField[x][y] != Field.BOMB) {
		// mineField[x][y] = Field.BOMB;
		// System.out.println("Bomb is at =" + x + ", =" + y);
		// tmpBombsNum--;
		// }
		// }
	}

	public void initEmpty() {
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
