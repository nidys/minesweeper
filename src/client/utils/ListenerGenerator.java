package client.utils;

import client.controllers.MainController;
import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.listeners.BaseListenerWithMVCAccess;

public class ListenerGenerator {

	private MainWindow mainView;
	private MainModel mainModel;
	private NetworkManager netManager;
	private GameState gameState;
	private MainController mainController;

	public ListenerGenerator(MainWindow mainView, MainModel mainModel, NetworkManager netManager, GameState gameState, MainController mainController) {
		super();
		this.mainView = mainView;
		this.mainModel = mainModel;
		this.netManager = netManager;
		this.gameState = gameState;
		this.mainController = mainController;
	}

	public void setFields(BaseListenerWithMVCAccess listener) {
		listener.setMainModel(mainModel);
		listener.setView(mainView);
		listener.setViewController(mainController);
		listener.setNetManager(netManager);
		listener.setGameState(gameState);
	}

}
