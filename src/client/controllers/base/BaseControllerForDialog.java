package client.controllers.base;

import java.awt.event.ActionListener;

import client.views.NewGameDialog;

public abstract class BaseControllerForDialog extends BaseActionController{
	protected NewGameDialog newGameView;

	public void setNewGameView(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}
}
