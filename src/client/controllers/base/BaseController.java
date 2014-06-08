package client.controllers.base;

import java.awt.event.ActionListener;

import client.gameRules.GameState;
import client.network.NetworkManager;
import client.utils.ComponentsFactory;

public abstract class BaseController implements ActionListener {
	protected ComponentsFactory componentsFactory;
	protected NetworkManager netManager;
	protected GameState gameState;
	
	public void setNetManager(NetworkManager netManager) {
		this.netManager = netManager;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setComponentsFactory(ComponentsFactory componentsFactory) {
		this.componentsFactory = componentsFactory;
	}
}