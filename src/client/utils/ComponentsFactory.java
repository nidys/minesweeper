package client.utils;

import client.controllers.HostBtnController;
import client.controllers.JoinBtnController;
import client.controllers.MyBombFielsBtnController;
import client.controllers.NewGameBtnController;
import client.controllers.ResetBtnController;
import client.gameRules.GameState;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.NewGameDialog;

/**
 * Responsible for creating MVC components, and 'injecting' dependencies.
 * Component means a view, a controller, and a model(s).
 */
public class ComponentsFactory {
	private MainWindow mainView;
	private NetworkManager netManager;
	private GameState gameState;
	private ControllerGenerator listenerGenerator;
	private NewGameDialog newGameView;

	/**
	 * Creates the Main component. This method cannot be called within the
	 * MainController! This method might be moved to the Locator.
	 * 
	 * @return Controller for Main component.
	 */
	public MainWindow createMainComponent() {
		if (mainView == null) {
			mainView = new MainWindow();
			netManager = new NetworkManager();
			gameState = new GameState();

			listenerGenerator = new ControllerGenerator(mainView, netManager, gameState, this);
			initializeStartViewListeners();
		}
		return mainView;
	}

	/**
	 * Creates the NewGame component.
	 * 
	 * @return Controller for NewGame component.
	 */
	public NewGameDialog createNewGameComponent() {
		newGameView = new NewGameDialog(mainView, true);
		listenerGenerator.setDialogComponents(newGameView);

		initializeNewGameListeners();
		return newGameView;
	}

	// Temporarily here
	public void initializeBoardListeners() {
		mainView.addResetBtnListener(new ResetBtnController(listenerGenerator));
		mainView.addBombFieldBtnListener(new MyBombFielsBtnController(listenerGenerator));
		// TODO MALY Review if it's obsolete
		// mainView.addOponentFieldBtnListener(new
		// OpponentBombFieldBtnController(listenerGenerator));
	}

	private void initializeStartViewListeners() {
		mainView.addNewGameBtnListener(new NewGameBtnController(listenerGenerator));
	}

	private void initializeNewGameListeners() {
		newGameView.addHostBtnListener(new HostBtnController(listenerGenerator, mainView));
		newGameView.addJoinBtnListener(new JoinBtnController(listenerGenerator, mainView));
	}
}
