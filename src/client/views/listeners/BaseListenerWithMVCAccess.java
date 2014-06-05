package client.views.listeners;

import java.awt.event.ActionListener;

import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;

public abstract class BaseListenerWithMVCAccess implements ActionListener {

	protected MainModel mainModel;
	protected NetworkManager netManager;
	protected GameState gameState;

	public BaseListenerWithMVCAccess() {
		super();
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