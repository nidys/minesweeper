package client.utils;

import client.controllers.MainController;

/**
 * Root for the application initialization. Responsible for creation most of the
 * elements with a life-cycle close to application life-time.
 */
public class Locator {
	private ComponentsFactory componentsFactory;

	public Locator() {
		initialize();
	}

	private void initialize() {
		componentsFactory = new ComponentsFactory();
	}

	/**
	 * Entry point for an application.
	 * 
	 * @return Controller for a Main Component.
	 */
	public MainController getMain() {
		return componentsFactory.CreateMainComponent();
	}
}
