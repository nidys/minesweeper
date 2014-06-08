package client.views.listeners;

import client.views.NewGameDialog;

public abstract class BaseListenerForDialog extends BaseListenerWithMVCAccess {
	protected NewGameDialog newGameView;

	public void setNewGameView(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}
}
