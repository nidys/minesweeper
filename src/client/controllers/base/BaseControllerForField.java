package client.controllers.base;

import java.awt.event.MouseListener;

import client.views.MainWindow;

public abstract class BaseControllerForField extends BaseMouseController{
	public MainWindow mainView;

	public void setMainView(MainWindow mainView) {
		this.mainView = mainView;
	}
}
