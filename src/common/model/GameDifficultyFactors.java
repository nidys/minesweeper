package common.model;

import java.io.Serializable;

public class GameDifficultyFactors implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3660076982397855767L;
	private int boardSizeX;
	private int boardSizeY;
	private int bombsNumber;

	public GameDifficultyFactors(int boardSizeX, int boardSizeY, int bombsNumber) {
		super();
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
		this.bombsNumber = bombsNumber;
	}

	public int getBoardSizeX() {
		return boardSizeX;
	}

	public void setBoardSizeX(int boardSizeX) {
		this.boardSizeX = boardSizeX;
	}

	public int getBoardSizeY() {
		return boardSizeY;
	}

	public void setBoardSizeY(int boardSizeY) {
		this.boardSizeY = boardSizeY;
	}

	public int getBombsAmount() {
		return bombsNumber;
	}

	public void setBombsNumber(int bombsNumber) {
		this.bombsNumber = bombsNumber;
	}

}
