package client.utils;

import client.controllers.MainController;
import client.controllers.NewGameController;
import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.NewGameDialog;
import client.views.listeners.BaseListenerForDialog;
import client.views.listeners.BaseListenerForWindow;
import client.views.listeners.BaseListenerWithMVCAccess;

public class ListenerGenerator {

	// for window
	private MainWindow mainView;
	private MainModel mainModel;
	private NetworkManager netManager;
	private GameState gameState;
	private MainController mainController;

	// for dialog
	private NewGameDialog newGameView;
	private NewGameController newGameController;

	public ListenerGenerator(MainWindow mainView, MainModel mainModel, NetworkManager netManager, GameState gameState, MainController mainController) {
		super();
		this.mainView = mainView;
		this.mainModel = mainModel;
		this.netManager = netManager;
		this.gameState = gameState;
		this.mainController = mainController;
	}

	public void setDialogComponents(NewGameDialog newGameView, NewGameController newGameControlle) {
		this.newGameView = newGameView;
		this.newGameController = newGameControlle;
	}

	public void setFieldsForWindow(BaseListenerForWindow listener) {
		listener.setView(mainView);
		setBaseComponents(listener);
	}

	public void setFieldsForDialog(BaseListenerForDialog listener) {
		listener.setNewGameView(newGameView);
		listener.setNewGameControlle(newGameController);
		setBaseComponents(listener);
	}

	private void setBaseComponents(BaseListenerWithMVCAccess listener) {
		listener.setMainModel(mainModel);
		listener.setNetManager(netManager);
		listener.setGameState(gameState);
		listener.setViewController(mainController);
	}

}
