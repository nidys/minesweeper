package client.utils;

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

	// for dialog
	private NewGameDialog newGameView;

	public ListenerGenerator(MainWindow mainView, NetworkManager netManager, GameState gameState, ComponentsFactory componentsFactory) {
		this.mainView = mainView;
		this.netManager = netManager;
		this.gameState = gameState;
		this.componentsFactory = componentsFactory;
	}

	public void setDialogComponents(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}

	public void setFieldsForWindow(BaseListenerForWindow listener) {
		listener.setMainView(mainView);
		setBaseComponents(listener);
	}

	public void setFieldsForDialog(BaseListenerForDialog listener) {
		listener.setNewGameView(newGameView);
		setBaseComponents(listener);
	}

	private void setBaseComponents(BaseListenerWithMVCAccess listener) {
		listener.setComponentsFactory(componentsFactory);
		listener.setNetManager(netManager);
		listener.setGameState(gameState);
	}
}
