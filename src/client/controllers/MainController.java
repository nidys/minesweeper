package client.controllers;

import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;
import client.utils.ComponentsFactory;
import client.utils.ListenerGenerator;
import client.views.MainWindow;
import client.views.NewGameDialog;
import client.views.listeners.GameModeBtnListener;
import client.views.listeners.NewGameButtonListener;

/**
 * Controller for the Main component (view with the game board).
 */
public class MainController extends ControllerBase {

	private MainWindow mainView;
	private MainModel mainModel;
	private NetworkManager netManager;
	private ComponentsFactory componentsFactory;
	private GameState gameState;
	private ListenerGenerator listenerGenerator;

	public MainController(MainWindow mainView, MainModel mainModel, NetworkManager netManager, GameState gameState,
			ComponentsFactory componentsFactory) {
		super(mainView);
		this.mainView = mainView;
		this.mainModel = mainModel;
		this.netManager = netManager;
		this.gameState = gameState;
		this.componentsFactory = componentsFactory;
		this.listenerGenerator = new ListenerGenerator(mainView, mainModel, netManager, gameState, this);

		initializeModels();
		initializeViewListeners();
	}

	public void createNewGameComponent() {
		mainModel.fakeModelChange(); // to fake a change in model for
		// Observer usage example
		NewGameController newGameController = componentsFactory.CreateNewGameComponent(listenerGenerator);
		newGameController.activate();
	}

	/**
	 * Example usage of an Observer pattern (not sure if we'll use it).
	 */
	private void initializeModels() {
		mainModel.addObserver(mainView);
	}

	private void initializeViewListeners() {
		mainView.addNewGameBtnListener(new NewGameButtonListener(listenerGenerator));
	}
}
