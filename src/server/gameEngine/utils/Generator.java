package server.gameEngine.utils;

import static common.utils.LoggingHelper.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import server.gameEngine.model.Field;

import common.utils.PositionConverter;

public class Generator {

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
		return mineField;
	}

	public static void generateBombs(int bombsNumber, Field[][] mineField) {
		temporaryInitFirst8Fields(mineField);

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

	private static void temporaryInitFirst8Fields(Field[][] mineField) {
		int MAX_NEIGHBOURS = 8, it = 0;
		for (int i = 0; i < mineField.length; i++) {
			for (int j = 0; j < mineField[i].length; j++) {
				if (it > MAX_NEIGHBOURS) {
					break;
				}
				mineField[i][j] = Field.getFromValue(++it);
			}
			if (it > MAX_NEIGHBOURS) {
				break;
			}
		}
	}

	private static void debugFieldSett(String msg, Field[][] mineField) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < mineField[0].length; i++) {
			s.append(String.format("i[%d] - ", i));
			for (int j = 0; j < mineField.length; j++) {
				s.append(String.format("%s | ", mineField[j][i]));
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

	private static Logger log = Logger.getLogger(Generator.class);
}
