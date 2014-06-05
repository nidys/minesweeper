package client.controllers;

import client.models.MainModel;
import client.network.NetworkManager;
import client.utils.ComponentsFactory;
import client.views.MainWindow;
import client.views.listeners.NewGameButtonListener;

/**
 * Controller for the Main component (view with the game board).
 */
public class MainController extends ControllerBase {

	private MainWindow mainView;
	private MainModel mainModel;
	private NetworkManager netManager;
	private ComponentsFactory componentsFactory;

	public MainController(MainWindow mainView, MainModel mainModel, NetworkManager netManager, ComponentsFactory componentsFactory) {
		super(mainView);
		this.mainView = mainView;
		this.mainModel = mainModel;
		this.netManager = netManager;
		this.componentsFactory = componentsFactory;

		initializeModels();
		initializeViewListeners();
	}

	public void createNewGameComponents() {
		mainModel.fakeModelChange(); // to fake a change in model for
		// Observer usage example

		NewGameController newGameController = componentsFactory.CreateNewGameComponent();
		newGameController.activate();
	}

	/**
	 * Example usage of an Observer pattern (not sure if we'll use it).
	 */
	private void initializeModels() {
		mainModel.addObserver(mainView);
	}

	private void initializeViewListeners() {
		mainView.addNewGameBtnListener(new NewGameButtonListener(mainView, this, netManager));
	}
}
