package server.gameEngine.utils;

import static common.utils.LoggingHelper.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import server.gameEngine.model.Field;

<<<<<<< HEAD
=======
import common.model.DiscoveredField;
>>>>>>> a74bc6108e4ef992814dd34d8fd91de0faa2a6e2
import common.utils.PositionConverter;

public class Generator {

<<<<<<< HEAD
=======
	public static void main(String... args) {
		List<Field[][]> tab = generate(1, null, 3, 6, 6);
		@SuppressWarnings("unused")
		List<DiscoveredField> resu = shot(tab.get(0), 17);
		debugFieldSett(String.format("after shot\n"), tab.get(0));
	}

	public static List<DiscoveredField> shot(Field[][] mineField, int pos) {
		List<DiscoveredField> arr = new ArrayList<DiscoveredField>();
		int x = PositionConverter.getXFromPosition(pos, mineField.length);
		int y = PositionConverter.getYFromPosition(pos, mineField.length);
		if (mineField[x][y] == Field.BOMB) {
			arr.add(new DiscoveredField(pos, Field.BOMB.getValue()));
			return arr;
		}
		// TODO check if shot is on already shot place
		if (mineField[x][y].shouldVisit() == false) {
			error(log, "This field was already clicked");
			return null;
		}
		new Solver(mineField).shot(arr, x, y);
		return arr;
	}

>>>>>>> a74bc6108e4ef992814dd34d8fd91de0faa2a6e2
	public static List<Field[][]> generate(int amount, List<Field[][]> old, int bombsNumber,
			int boardSizeX, int boardSizeY) {
		List<Field[][]> result = new ArrayList<Field[][]>();
		if (old != null) {
			result.addAll(old);
		}
		for (int i = 0; i < amount; i++) {
			result.add(generateField(bombsNumber, boardSizeX, boardSizeY));
		}
		return result;
	}

	public static Field[][] generateField(int bombsNumber, int boardSizeX, int boardSizeY) {
		Field[][] mineField = new Field[boardSizeX][boardSizeY];
		initEmpty(mineField);
		generateBombs(bombsNumber, mineField);
		generateNumbers(mineField);
		return mineField;
	}

	public static void generateBombs(int bombsNumber, Field[][] mineField) {
		Random r = new Random(System.currentTimeMillis());
		int i = 0;
		while (i < bombsNumber) {
			int val = r.nextInt(mineField.length * mineField[0].length);
			int x = PositionConverter.getXFromPosition(val, mineField.length);
			int y = PositionConverter.getYFromPosition(val, mineField.length);
			if (mineField[x][y] == Field.EMPTY) {
				mineField[x][y] = Field.BOMB;
				i++;
			}
		}
		debugFieldSett(String.format("generateBombs[%d], done\n", bombsNumber), mineField);
	}

	public static void generateNumbers(Field[][] mineField) {
		int[] steps = new int[] { -1, 0, 1 };
		for (int i = 0; i < mineField.length; i++) {
			for (int j = 0; j < mineField[i].length; j++) {
				if (mineField[i][j] != Field.BOMB) {
					countBombsInNeighbour(mineField, steps, i, j);
				}
			}
		}
		debugFieldSett(String.format("generateNeighbours\n"), mineField);
	}

	public static void countBombsInNeighbour(Field[][] mineField, int[] steps, int i, int j) {
		int bombsInNeighbourhood = 0;
		for (int y = 0; y < steps.length; y++) {
			int yy = j + steps[y];
			for (int x = 0; x < steps.length; x++) {
				int xx = i + steps[x];
				if (isInRange(xx, yy, mineField.length, mineField[i].length)
						&& (mineField[xx][yy] == Field.BOMB)) {
					bombsInNeighbourhood++;
				}
			}
		}
		mineField[i][j] = Field.getFromValue(bombsInNeighbourhood);
	}

	public static boolean isInRange(int x, int y, int maxX, int maxY) {
		return (x >= 0 && y >= 0 && x < maxX && y < maxY);
	}

	private static void debugFieldSett(String msg, Field[][] mineField) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < mineField[0].length; i++) {
			s.append(String.format("i[%d] - ", i));
			for (int j = 0; j < mineField.length; j++) {
				s.append(String.format("%s | ", mineField[j][i] == Field.BOMB ? "X"
						: mineField[j][i].getValue()));
			}
			s.append("\n");
		}
		debug(log, msg + s.toString());
	}

	public static void initEmpty(Field[][] mineField) {

		for (int i = 0; i < mineField.length; i++) {
			for (int j = 0; j < mineField[i].length; j++) {
				mineField[i][j] = Field.EMPTY;
			}
		}
	}

	static Logger log = Logger.getLogger(Generator.class);
}
