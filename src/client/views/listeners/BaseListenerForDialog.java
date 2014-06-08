package client.views.listeners;

import client.controllers.NewGameController;
import client.views.NewGameDialog;

public abstract class BaseListenerForDialog extends BaseListenerWithMVCAccess {
	protected NewGameDialog newGameView;
	protected NewGameController newGameController;

	public void setNewGameView(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}

	public void setNewGameControlle(NewGameController newGameControlle) {
		this.newGameController = newGameControlle;
	}
}
