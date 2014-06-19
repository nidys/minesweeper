package server.gameEngine.model;

import common.utils.PositionConverter;

public class Board {
	public Field[][] mineField;
	int boardNum;
	int exposed;

	private int maxPosition;

	public Board(Field[][] mineField, int maxPosition) {
		this.maxPosition = maxPosition;
		setBoard(mineField, 0);
	}

	public void setBoard(Field[][] mineField, int boardNum) {
		this.mineField = mineField;
		this.boardNum = boardNum;
		exposed = 0;
	}

	public int getProgress() {
		return exposed;
	}

	public int getShotResult(int pos) {
		int x = PositionConverter.getXFromPosition(pos, mineField.length);
		int y = PositionConverter.getYFromPosition(pos, mineField.length);
		exposed++;
		return mineField[x][y].getValue();
	}

	public boolean isValueWithinBoardSize(int pos) {
		return pos >= 0 && pos < maxPosition;
	}

}
