package client.views.listeners;

import java.awt.event.ActionEvent;

import client.controllers.MainController;
import client.network.NetworkManager;
import client.views.MainWindow;

public class NewGameButtonListener extends BaseListenerWithMVCAccess {

	public NewGameButtonListener(MainWindow view, MainController controller, NetworkManager netManager) {
		super(view, controller, netManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String serverAddr = view.getServerAddress();
		netManager.connectToServer(serverAddr);
		viewController.createNewGameComponents();
	}

}
