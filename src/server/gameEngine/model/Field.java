package server.gameEngine.model;

public enum Field {
	VONE(-3), VTWO(-4), VTHREE(-5), VFOUR(-6), VFIVE(-7), VSIX(-8), VSEVEN(-9), VEIGHT(-10), // <br>
	VEMPTY(-2), // <br>
	BOMB(-1), EMPTY(0), // <br>
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);
	private int val;

	private Field(int val) {
		this.val = val;
	}

	public int getValue() {
		return val;
	}

	public static Field getFromValue(int i) {
		switch (i) {
		case -10:
			return VEIGHT;
		case -9:
			return VSEVEN;
		case -8:
			return VSIX;
		case -7:
			return VFIVE;
		case -6:
			return VFOUR;
		case -5:
			return VTHREE;
		case -4:
			return VTWO;
		case -3:
			return VONE;
		case -2:
			return VEMPTY;
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

	public boolean isNumber() {
		return getValue() >= Field.ONE.getValue();
	}

	public boolean shouldVisit() {
		return getValue() >= Field.EMPTY.getValue();
	}

	public Field getMarked() {
		int factor = (getValue() + 1) * 2;
		return Field.getFromValue(val - factor);
	}
}