package server.gameEngine.model;

public enum Field {
	EMPTY(0), BOMB(-1), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);
	private int val;

	private Field(int val) {
		this.val = val;
	}

	public int getValue() {
		return val;
	}

	public static Field getFromValue(int i) {
		switch (i) {
		case -1:
			return BOMB;
		default:
		case 0:
			return EMPTY;
		case 1:
			return ONE;
		case 2:
			return TWO;
		case 3:
			return THREE;
		case 4:
			return FOUR;
		case 5:
			return FIVE;
		case 6:
			return SIX;
		case 7:
			return SEVEN;
		case 8:
			return EIGHT;
		}
	}
}