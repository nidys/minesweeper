package common.model;

import java.io.Serializable;

public class DiscoveredField implements Serializable {
	private int position;
	/**
	 * -1 - bomb field<br>
	 * 0 - empty<br>
	 * 1-8 -bombs in neighbour
	 */
	private int value;

	public DiscoveredField(int position, int value) {
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 5529025823063141283L;
}
