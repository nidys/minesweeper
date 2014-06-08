package client.controllers;

import client.utils.ListenerGenerator;
import client.views.NewGameDialog;

/**
 * Controller for the NewGame component (view with choosing a new game).
 * DEPRECATED.
 */
public class NewGameController extends ControllerBase {
	public NewGameController(NewGameDialog newGameView, ListenerGenerator listenerGenerator) {
		super(newGameView);
		listenerGenerator.setDialogComponents(newGameView, this);
	}
}
