package client.utils;

import client.controllers.MyBombFielsBtnController;
import client.controllers.OpponentBombFieldBtnController;
import client.controllers.base.BaseController;
import client.controllers.base.BaseControllerForDialog;
import client.controllers.base.BaseControllerForWindow;
import client.gameRules.GameState;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.NewGameDialog;

public class ControllerGenerator {
	private ComponentsFactory componentsFactory;

	// for window
	private MainWindow mainView;
	private NetworkManager netManager;
	private GameState gameState;

	// for dialog
	private NewGameDialog newGameView;

	public ControllerGenerator(MainWindow mainView, NetworkManager netManager, GameState gameState, ComponentsFactory componentsFactory) {
		this.mainView = mainView;
		this.netManager = netManager;
		this.gameState = gameState;
		this.componentsFactory = componentsFactory;
	}

	public void setDialogComponents(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}

	public void setFieldsForWindow(BaseControllerForWindow listener) {
		listener.setMainView(mainView);
		setBaseComponents(listener);
	}

	public void setFieldsForDialog(BaseControllerForDialog listener) {
		listener.setNewGameView(newGameView);
		setBaseComponents(listener);
	}

	private void setBaseComponents(BaseController listener) {
		listener.setComponentsFactory(componentsFactory);
		listener.setNetManager(netManager);
		listener.setGameState(gameState);
	}

	public void setFieldsForWindow(MyBombFielsBtnController listener) {
		listener.setMainView(mainView);
		setBaseComponents(listener);
		
	}

	public void setFieldsForWindow(OpponentBombFieldBtnController listener) {
		listener.setMainView(mainView);
		setBaseComponents(listener);
		
	}
}
