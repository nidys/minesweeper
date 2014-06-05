package client.utils;

import client.controllers.MainController;
import client.controllers.NewGameController;
import client.models.MainModel;
import client.network.NetworkManager;
import client.views.MainWindow;
import client.views.NewGameDialog;

/**
 * Responsible for creating MVC components, and 'injecting' dependencies.
 * Component means a view, a controller, and a model(s).
 */
public class ComponentsFactory {
	private MainWindow mainView;
	private MainController mainController;
	private NetworkManager netManager;

	/**
	 * Creates the NewGame component.
	 * 
	 * @return Controller for NewGame component.
	 */
	public NewGameController CreateNewGameComponent() {
		NewGameDialog newGameView = new NewGameDialog(mainView, true);
		NewGameController newGameController = new NewGameController(newGameView);

		return newGameController;
	}

	/**
	 * Creates the Main component. This method cannot be called within the
	 * MainController! This method might be moved to the Locator.
	 * 
	 * @return Controller for Main component.
	 */
	public MainController CreateMainComponent() {
		if (mainView == null) {
			mainView = new MainWindow();
			MainModel mainModel = new MainModel();
			netManager = new NetworkManager();
			mainController = new MainController(mainView, mainModel, netManager, this);
		}
		return mainController;
	}
}
