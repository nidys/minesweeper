package client.controllers.base;

import client.views.GameRoomDialog;
import client.views.NewGameDialog;

public abstract class BaseControllerForDialog extends BaseActionController {
	protected NewGameDialog newGameView;
	protected GameRoomDialog gameRoomDialog;
	
	public void setNewGameView(NewGameDialog newGameView) {
		this.newGameView = newGameView;
	}
	
	
}
