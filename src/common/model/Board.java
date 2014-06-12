package common.model;

import java.util.Random;

import org.apache.log4j.Logger;

public class Board {
	final static int BOMB = -1;
	static final int EMPTY = 0;
	private static Logger log = Logger.getLogger(Board.class);
	private int[] mineField; // 0 - nothing, 1-5 - values -1 - bomb
	private int boardSize;

	public Board(int bombsNum, int boardSize, String userNick) {
		this.boardSize = boardSize;
		mineField = new int[this.boardSize*this.boardSize];
		System.out.println(bombsNum + ";" + boardSize);
		initEmpty();
		generateBombs(bombsNum);
		fillWithNumbers();
	}

	public Result shot(int pos) {
		if (mineField[pos] == BOMB) {
			return Result.BOMB;
		} else {
			return Result.EMPTY;
		}
		// TODO consider field with number

	}
	
	public void fillWithNumbers(){
		for (int i = 0 ; i < this.boardSize; ++ i){
			for (int j = 0; j < this.boardSize; ++j){
				
			}
		}
			
	}

	public void generateBombs(int bombsNum) {
		Random r = new Random(System.currentTimeMillis());
		int i = 0;
		while (i < bombsNum) {
			int val = r.nextInt(mineField.length);
			if (mineField[val] != BOMB) {
				mineField[val] = BOMB;
				i++;
			}
		}
		debugFieldSett(String.format("generateBombs[%d], done\n", bombsNum));
	}

	public void initEmpty() {
		for (int i = 0; i < mineField.length; i++) {
			mineField[i] = EMPTY;
		}
	}

	private void debugFieldSett(String msg) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < mineField.length; i++) {
			s.append(String.format("\t[i=%d] = %s\n", i, mineField[i]));
		}
		log.debug(msg + s.toString());
	}
}
