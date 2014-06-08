package client.utils;

import client.controllers.MainController;
import client.controllers.NewGameController;
import client.gameRules.GameState;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.NewGameDialog;
import client.views.listeners.BaseListenerForDialog;
import client.views.listeners.BaseListenerForWindow;
import client.views.listeners.BaseListenerWithMVCAccess;

public class ListenerGenerator {
	private ComponentsFactory componentsFactory;
	
	// for window
	private MainWindow mainView;
	private NetworkManager netManager;
	private GameState gameState;
	private MainController mainController;

	// for dialog
	private NewGameDialog newGameView;
	private NewGameController newGameController;

	public ListenerGenerator(MainWindow mainView, NetworkManager netManager, GameState gameState, MainController mainController, ComponentsFactory componentsFactory) {
		this.mainView = mainView;
		this.netManager = netManager;
		this.gameState = gameState;
		this.mainController = mainController;
		this.componentsFactory = componentsFactory;
	}

	public void setDialogComponents(NewGameDialog newGameView, NewGameController newGameControlle) {
		this.newGameView = newGameView;
		this.newGameController = newGameControlle;
	}

	public void setFieldsForWindow(BaseListenerForWindow listener) {
		listener.setMainView(mainView);
		setBaseComponents(listener);
	}

	public void setFieldsForDialog(BaseListenerForDialog listener) {
		listener.setNewGameView(newGameView);
		listener.setNewGameControlle(newGameController);
		setBaseComponents(listener);
	}

	private void setBaseComponents(BaseListenerWithMVCAccess listener) {
		listener.setComponentsFactory(componentsFactory);
		listener.setNetManager(netManager);
		listener.setGameState(gameState);
		listener.setViewController(mainController);
	}
}
