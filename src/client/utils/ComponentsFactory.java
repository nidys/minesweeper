package client.utils;

import client.controllers.CancelBtnController;
import client.controllers.HostBtnController;
import client.controllers.JoinGamesBtnController;
import client.controllers.JoinRoomBtnController;
import client.controllers.BoardFieldBtnController;
import client.controllers.MessageDialogBtnController;
import client.controllers.NewGameBtnController;
import client.controllers.ReadyBtnController;
import client.controllers.RefreshGamesListBtnController;
import client.controllers.ResetBtnController;
import client.controllers.StartGameBtnController;
import client.gameRules.GameState;
import client.network.NetworkManager;
import client.views.GameRoomDialog;
import client.views.GamesListDialog;
import client.views.MainWindow;
import client.views.MessageDialog;
import client.views.NewGameDialog;

/**
 * Responsible for creating MVC components, and 'injecting' dependencies.
 * Component means a view, a controller, and a model(s).
 */
public class ComponentsFactory {
	private ControllerGenerator listenerGenerator;
	private MainWindow mainView;
	private NewGameDialog newGameView;
	private MessageDialog msgDialog;
	
	private GamesListDialog gamesListView;
	private GameRoomDialog gameRoomView;
	private NetworkManager netManager;
	private GameState gameState;



	/**
	 * Creates the Main component. This method cannot be called within the
	 * MainController! This method might be moved to the Locator.
	 * 
	 * @return Controller for Main component.
	 */
	public MainWindow createMainComponent() {
		if (mainView == null) {
			mainView = new MainWindow();
			msgDialog = new MessageDialog();
			netManager = new NetworkManager();
			gameState = new GameState();
			mainView.setMessageDialog(msgDialog);
			listenerGenerator = new ControllerGenerator(mainView, netManager, gameState, this);
			initializeStartViewListeners();
			initializeMessageDialogListeners();
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

	public GamesListDialog createGamesListComponent() {
		gamesListView = new GamesListDialog(mainView, true);
		initializeJoinGameListeners();
		return gamesListView;
	}
	
	public GameRoomDialog createGameRoomComponent(String playerName, String gameId, boolean isHost) {
		gameRoomView = new GameRoomDialog(gameId, playerName, isHost, mainView, true);
		initializeStartGameListeners();
		
		return gameRoomView;
	}
	
	// TODO MALY Temporarily here. Maybe move it to createMainComponent()?
	public void initializeBoardListeners() {
		
		mainView.addResetBtnListener(new ResetBtnController(listenerGenerator));
		mainView.addBombFieldBtnListener(new BoardFieldBtnController(listenerGenerator));
	}
	
	
	public void initializeMessageDialogListeners() {
		msgDialog.addMessageDialogBtnController(new MessageDialogBtnController(listenerGenerator, mainView, msgDialog));
		// TODO MALY Review if it's obsolete
		// mainView.addOponentFieldBtnListener(new
		// OpponentBombFieldBtnController(listenerGenerator));
	}

	private void initializeStartViewListeners() {
		mainView.addNewGameBtnListener(new NewGameBtnController(listenerGenerator));
	}

	private void initializeNewGameListeners() {
		newGameView.addHostBtnListener(new HostBtnController(listenerGenerator, mainView, msgDialog));
		newGameView.addJoinBtnListener(new JoinGamesBtnController(listenerGenerator, mainView));
	}
	
	private void initializeStartGameListeners() {
		gameRoomView.addStartGameBtnListener(new StartGameBtnController(listenerGenerator, mainView, gameRoomView, msgDialog));
		gameRoomView.addReadyBtnListener(new ReadyBtnController(listenerGenerator));
	}
	
	private void initializeJoinGameListeners() {
		gamesListView.addJoinGameBtnListener(new JoinRoomBtnController(listenerGenerator, mainView, gamesListView, msgDialog));
		gamesListView.addCanceltnListener(new CancelBtnController(listenerGenerator, mainView, gamesListView));	
		gamesListView.addRefreshGamesListBtnListener(new RefreshGamesListBtnController(listenerGenerator, mainView, gamesListView));	
	}
		
	
}
