package client.controllers;

import java.awt.Window;

/**
 * Base class for all MVC controllers.
 */
public abstract class ControllerBase {
	private Window view;

	public ControllerBase(Window view) {
		this.view = view;
	}

	/**
	 * Activates the view that corresponds to the controller (makes it visible
	 * on the screen).
	 */
	public void activate() {
		System.out.println("BBBB");
		view.setVisible(true);
	}
}
