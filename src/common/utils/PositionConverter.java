package common.utils;

import static common.utils.LoggingHelper.debug;

import org.apache.log4j.Logger;

public class PositionConverter {
	/**
	 * @param x
	 * @param y
	 * @param columns
	 *            == boardSizeX
	 * @return
	 */
	public static int getPositionFromXY(int x, int y, int columns) {
		int retVal = x + y * columns;
		if (DEBUG) {
			debug(log, "getPositionFromXY x[%d],  y[%d], retVal[%d]", x, y, retVal);
		}
		return retVal;
	}

	public static int getXFromPosition(int pos, int columns) {
		int retVal = pos - getYFromPosition(pos, columns) * columns;
		if (DEBUG) {
			debug(log, "get_X_fromPosition pos[%d],  retVal[%d]", pos, retVal);
		}
		return retVal;
	}

	public static int getYFromPosition(int pos, int columns) {
		int retVal = pos / columns;
		if (DEBUG) {
			debug(log, "get_Y_frommPosition pos[%d],  retVal[%d]", pos, retVal);
		}
		return retVal;
	}

	private static Logger log = Logger.getLogger(PositionConverter.class);
	private static boolean DEBUG = false;
}
