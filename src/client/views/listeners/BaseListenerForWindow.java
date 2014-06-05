package client.views.listeners;

import client.controllers.MainController;
import client.views.MainWindow;

public abstract class BaseListenerForWindow extends BaseListenerWithMVCAccess {
	protected MainWindow view;
	protected MainController viewController;

	public BaseListenerForWindow() {
		super();
	}

	public void setView(MainWindow view) {
		this.view = view;
	}

	public void setViewController(MainController viewController) {
		this.viewController = viewController;
	}
}
