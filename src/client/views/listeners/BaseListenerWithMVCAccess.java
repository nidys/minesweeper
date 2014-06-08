package client.views.listeners;

import java.awt.event.ActionListener;

import client.controllers.MainController;
import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;
import client.utils.ComponentsFactory;

public abstract class BaseListenerWithMVCAccess implements ActionListener {
	protected ComponentsFactory componentsFactory;
	protected MainController mainController;
	protected NetworkManager netManager;
	protected GameState gameState;
	
	public void setNetManager(NetworkManager netManager) {
		this.netManager = netManager;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setViewController(MainController mainController) {
		this.mainController = mainController;
	}

	public void setComponentsFactory(ComponentsFactory componentsFactory) {
		this.componentsFactory = componentsFactory;
	}
}