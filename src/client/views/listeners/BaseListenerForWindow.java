package client.views.listeners;

import client.views.MainWindow;

public abstract class BaseListenerForWindow extends BaseListenerWithMVCAccess {
	public MainWindow mainView;

	public void setMainView(MainWindow mainView) {
		this.mainView = mainView;
	}
}
