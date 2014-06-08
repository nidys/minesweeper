package client.controllers.base;

import client.views.NewGameDialog;

public abstract class BaseControllerForDialog extends BaseController {
	protected NewGameDialog newGameView;

	public void setNewGameView(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}
}
