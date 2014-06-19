package server.gameEngine.utils;

import java.util.List;

import org.apache.log4j.Logger;

import server.gameEngine.model.Field;

import common.model.ShotResult;
import common.utils.PositionConverter;

public class Solver {
	Field[][] mineField;
	int[] steps = new int[] { -1, 0, 1 };

	public Solver(Field[][] mineField) {
		this.mineField = mineField;
	}

	// assuming shot is not on bomb
	public void shot(List<ShotResult> arr, int x, int y) {
		arr.add(new ShotResult(PositionConverter.getPositionFromXY(x, y, mineField.length),
				mineField[x][y].getValue()));
		if (mineField[x][y] == Field.EMPTY) {
			mineField[x][y] = mineField[x][y].getMarked();
			f(arr, x, y);
		} else {
			mineField[x][y] = mineField[x][y].getMarked();
		}
	}

	public void f(List<ShotResult> arr, int currX, int currY) {
		for (int y = 0; y < steps.length; y++) {
			int yy = currY + steps[y];
			for (int x = 0; x < steps.length; x++) {
				int xx = currX + steps[x];
				if (Generator.isInRange(xx, yy, mineField.length, mineField[currX].length)
						&& (mineField[xx][yy].shouldVisit())) {
					shot(arr, xx, yy);
				}
			}
		}
	}

	private static Logger log = Logger.getLogger(Solver.class);
}
