package client.controllers;

import java.awt.event.ActionListener;

import client.gameRules.GameState;
import client.models.MainModel;
import client.network.NetworkManager;
import client.utils.ComponentsFactory;
import client.utils.ListenerGenerator;
import client.views.MainWindow;
import client.views.listeners.MyBombFielsBtnListener;
import client.views.listeners.NewGameButtonListener;
import client.views.listeners.OpponentBombFieldBtnListener;
import client.views.listeners.ResetBtnListener;

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
		initializeStartViewListeners();
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

	private void initializeStartViewListeners() {
		mainView.addNewGameBtnListener(new NewGameButtonListener(listenerGenerator));
	}

	public void initializeGameBoard() {
		mainView.drawGameBoard();
		mainView.addResetBtnListener(new ResetBtnListener(listenerGenerator));
		mainView.addBombFieldBtnListener(new MyBombFielsBtnListener(listenerGenerator));
		mainView.addOponentFieldBtnListener(new OpponentBombFieldBtnListener(listenerGenerator));
	}
}
