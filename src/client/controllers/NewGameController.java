package client.controllers;

import client.utils.ListenerGenerator;
import client.views.NewGameDialog;
import client.views.listeners.GameModeBtnListener;
import client.views.listeners.HostBtnListener;
import client.views.listeners.JoinBtnListener;

/**
 * Controller for the NewGame component (view with choosing a new game).
 */
public class NewGameController extends ControllerBase {

	private NewGameDialog newGameView;
	private ListenerGenerator listenerGenerator;

	public NewGameController(NewGameDialog newGameView, ListenerGenerator listenerGenerator) {
		super(newGameView);
		this.newGameView = newGameView;
		this.listenerGenerator = listenerGenerator;
		listenerGenerator.setDialogComponents(newGameView, this);
		initializeViewListeners();
	}

	private void initializeViewListeners() {
		newGameView.addGameModeBtnListener(new GameModeBtnListener(listenerGenerator));
		newGameView.addHostBtnListener(new HostBtnListener(listenerGenerator));
		newGameView.addJoinBtnListener(new JoinBtnListener(listenerGenerator));
	}
}
