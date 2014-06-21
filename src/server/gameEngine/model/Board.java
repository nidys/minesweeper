package server.gameEngine.model;

import common.utils.PositionConverter;

public class Board {
	public Field[][] mineField;
	int boardNum;
	int exposed;

	private int maxPosition;

	public Board(Field[][] mineField, int maxPosition) {
		this.maxPosition = maxPosition;
		this.boardNum = 1;
		setBoard(mineField);
	}

	public void setBoard(Field[][] mineField) {
		this.mineField = mineField;
		exposed = 0;
	}

	public int getProgress() {
		return exposed;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void incBoardNum() {
		this.boardNum++;
	}

	public int getExposed() {
		return exposed;
	}

	public void setExposed(int exposed) {
		this.exposed = exposed;
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
