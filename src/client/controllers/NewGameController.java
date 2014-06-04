package client.controllers;

import client.views.NewGameDialog;

/**
 * Controller for the NewGame component (view with choosing a new game).
 */
public class NewGameController extends ControllerBase {

	private NewGameDialog newGameView;

	public NewGameController(NewGameDialog newGameView) {
		super(newGameView);
		this.newGameView = newGameView;
	}
}
