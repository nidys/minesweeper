package client.views.listeners;

import java.awt.event.ActionListener;

import client.controllers.MainController;
import client.network.NetworkManager;
import client.views.MainWindow;

public abstract class BaseListenerWithMVCAccess implements ActionListener {
	protected MainWindow view;
	protected MainController viewController;
	protected NetworkManager netManager;

	public BaseListenerWithMVCAccess(MainWindow view, MainController controller, NetworkManager netManager) {
		super();
		this.view = view;
		this.viewController = controller;
		this.netManager = netManager;
	}
}
