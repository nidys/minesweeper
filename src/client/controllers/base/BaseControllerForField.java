package client.controllers.base;

import client.views.MainWindow;

public abstract class BaseControllerForField extends BaseMouseController {
	public MainWindow mainView;

	public void setMainView(MainWindow mainView) {
		this.mainView = mainView;
	}
}
