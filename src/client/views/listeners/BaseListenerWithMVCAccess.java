package client.views.listeners;

import java.awt.event.ActionListener;

import client.controllers.MainController;
import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;
import client.views.MainWindow;

public abstract class BaseListenerWithMVCAccess implements ActionListener {
	private MainModel mainModel;
	protected MainWindow view;
	protected MainController viewController;
	protected NetworkManager netManager;
	private GameState gameState;

	public BaseListenerWithMVCAccess() {
		super();
	}

	public void setView(MainWindow view) {
		this.view = view;
	}

	public void setViewController(MainController viewController) {
		this.viewController = viewController;
	}

	public void setNetManager(NetworkManager netManager) {
		this.netManager = netManager;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setMainModel(MainModel mainModel) {
		this.mainModel = mainModel;
	}
}
