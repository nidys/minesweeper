package server.gameEngine.utils;

import static common.utils.LoggingHelper.error;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.gameEngine.model.Field;

import common.model.DiscoveredFields;
import common.utils.PositionConverter;

public class Solver {
	Field[][] mineField;
	int[] steps = new int[] { -1, 0, 1 };

	public Solver(Field[][] mineField) {
		this.mineField = mineField;
	}

	// assuming shot is not on bomb
	public void resolveShot(List<DiscoveredFields> arr, int x, int y) {
		arr.add(new DiscoveredFields(PositionConverter.getPositionFromXY(x, y, mineField.length),
				mineField[x][y].getValue()));
		if (mineField[x][y] == Field.EMPTY) {
			mineField[x][y] = mineField[x][y].getMarked();
			iterateNeighbours(arr, x, y);
		} else {
			mineField[x][y] = mineField[x][y].getMarked();
		}
	}

	public void iterateNeighbours(List<DiscoveredFields> arr, int currX, int currY) {
		for (int y = 0; y < steps.length; y++) {
			int yy = currY + steps[y];
			for (int x = 0; x < steps.length; x++) {
				int xx = currX + steps[x];
				if (Generator.isInRange(xx, yy, mineField.length, mineField[currX].length)
						&& (mineField[xx][yy].shouldVisit())) {
					resolveShot(arr, xx, yy);
				}
			}
		}
	}

	public static List<DiscoveredFields> shot(Field[][] mineField, int pos) {
		List<DiscoveredFields> arr = new ArrayList<DiscoveredFields>();
		int x = PositionConverter.getXFromPosition(pos, mineField.length);
		int y = PositionConverter.getYFromPosition(pos, mineField.length);
		if (mineField[x][y] == Field.BOMB) {
			arr.add(new DiscoveredFields(pos, Field.BOMB.getValue()));
			return arr;
		}
		// TODO check if shot is on already shot place
		if (mineField[x][y].shouldVisit() == false) {
			error(log, "This field was already clicked");
			return null;
		}
		new Solver(mineField).resolveShot(arr, x, y);
		return arr;
	}

	private static Logger log = Logger.getLogger(Solver.class);
}
