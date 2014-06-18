package client.controllers.base;

import client.views.MainWindow;

public abstract class BaseControllerForWindow extends BaseActionController {
	public MainWindow mainView;

	public void setMainView(MainWindow mainView) {
		this.mainView = mainView;
	}
}
