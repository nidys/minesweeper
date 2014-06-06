package client.views.listeners;

import client.views.MainWindow;

public abstract class BaseListenerForWindow extends BaseListenerWithMVCAccess {
	protected MainWindow view;

	public BaseListenerForWindow() {
		super();
	}

	public void setView(MainWindow view) {
		this.view = view;
	}
}
