package client.controllers;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GamesListDialog;
import client.views.MainWindow;

public class RefreshGamesListBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(HostBtnController.class);
	private MainWindow mainView;
	private GamesListDialog gamesListView;
	
	public RefreshGamesListBtnController(ControllerGenerator listenerGenerator,
			MainWindow mainView, GamesListDialog gamesListView) {
		super();
		this.mainView = mainView;
		this.gamesListView = gamesListView;
		listenerGenerator.setFieldsForDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
