package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.utils.ControllerGenerator;
import client.views.GamesListDialog;
import client.views.MainWindow;

public class CancelBtnController extends BaseControllerForDialog {
	private static Logger log = Logger.getLogger(CancelBtnController.class);
	private MainWindow mainView;
	private GamesListDialog gamesListView;
	
	public CancelBtnController(ControllerGenerator listenerGenerator,	MainWindow mainView, GamesListDialog gamesListView) {
		super();
		this.mainView = mainView;
		listenerGenerator.setFieldsForDialog(this);
		this.gamesListView = gamesListView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		info(log, "Cancel-> back to NewGame window");
		gamesListView.setVisible(false);
		newGameView.setVisible(true);
		
	}

}
