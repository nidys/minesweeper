package common.model;

import java.io.Serializable;

public class ShotResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5529025823063141283L;
	private int position;
	/**
	 * -1 - bomb field<br>
	 * 0 - empty<br>
	 * 1-8 -bombs in neighbour
	 */
	private int value;

	public ShotResult(int position, int value) {
		super();
		this.position = position;
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
