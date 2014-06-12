package client.views.component;

import javax.swing.JButton;

public class FieldButton extends JButton {

	public FieldButton(int position) {
		super();
		this.position = position;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int position;
	

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	
}

