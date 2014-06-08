package client.utils;

import client.controllers.MainController;
import client.controllers.NewGameController;
import client.gameRules.GameState;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.NewGameDialog;
import client.views.listeners.GameModeBtnListener;
import client.views.listeners.HostBtnListener;
import client.views.listeners.JoinBtnListener;
import client.views.listeners.MyBombFielsBtnListener;
import client.views.listeners.NewGameButtonListener;
import client.views.listeners.OpponentBombFieldBtnListener;
import client.views.listeners.ResetBtnListener;

/**
 * Responsible for creating MVC components, and 'injecting' dependencies.
 * Component means a view, a controller, and a model(s).
 */
public class ComponentsFactory {
	private MainWindow mainView;
	private MainController mainController;
	private NetworkManager netManager;
	private GameState gameState;
	private ListenerGenerator listenerGenerator;
	private NewGameDialog newGameView;
	private NewGameController newGameController;
	
	/**
	 * Creates the Main component. This method cannot be called within the
	 * MainController! This method might be moved to the Locator.
	 * 
	 * @return Controller for Main component.
	 */
	public MainController createMainComponent() {
		if (mainView == null) {
			mainView = new MainWindow();
			netManager = new NetworkManager();
			gameState = new GameState();
			
			mainController = new MainController(mainView, this);
			listenerGenerator = new ListenerGenerator(mainView, netManager, gameState, mainController, this);
			initializeStartViewListeners();
		}
		return mainController;
	}
	
	/**
	 * Creates the NewGame component.
	 * 
	 * @return Controller for NewGame component.
	 */
	public NewGameController createNewGameComponent() {
		newGameView = new NewGameDialog(mainView, true);
		newGameController = new NewGameController(newGameView, listenerGenerator);
		listenerGenerator.setDialogComponents(newGameView, newGameController);
		
		initializeNewGameListeners();
		return newGameController;
	}
	
	// Temporarily here
	public void initializeBoardListeners() {
		mainView.addResetBtnListener(new ResetBtnListener(listenerGenerator));
		mainView.addBombFieldBtnListener(new MyBombFielsBtnListener(listenerGenerator));
		mainView.addOponentFieldBtnListener(new OpponentBombFieldBtnListener(listenerGenerator));
	}
	
	private void initializeStartViewListeners() {
		mainView.addNewGameBtnListener(new NewGameButtonListener(listenerGenerator));
	}
	
	private void initializeNewGameListeners() {
		newGameView.addGameModeBtnListener(new GameModeBtnListener(listenerGenerator));
		newGameView.addHostBtnListener(new HostBtnListener(listenerGenerator, mainView));
		newGameView.addJoinBtnListener(new JoinBtnListener(listenerGenerator, mainView));
	}
}
