package client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.models.MainModel;
import client.utils.ComponentsFactory;
import client.views.MainWindow;

/**
 * Controller for the Main component (view with the game board).
 */
public class MainController extends ControllerBase {

	private MainWindow mainView;
	private MainModel mainModel;
	private ComponentsFactory componentsFactory;

	public MainController(MainWindow mainView, MainModel mainModel,
			ComponentsFactory componentsFactory) {
		super(mainView);
		this.mainView = mainView;
		this.mainModel = mainModel;
		this.componentsFactory = componentsFactory;

		initializeModels();
		initializeViewListeners();
	}

	/**
	 * Example usage of an Observer pattern (not sure if we'll use it).
	 */
	private void initializeModels() {
		mainModel.addObserver(mainView);
	}

	private void initializeViewListeners() {

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainModel.fakeModelChange(); // to fake a change in model for
											 // Observer usage example

				NewGameController newGameController = componentsFactory.CreateNewGameComponent();
				newGameController.activate();
			}
		};
		mainView.addNewGameBtnListener(listener);
	}
}
