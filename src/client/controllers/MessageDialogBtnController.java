package client.controllers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForDialog;
import client.internationalization.ButtonNames;
import client.utils.ControllerGenerator;
import client.views.MainWindow;
import client.views.MessageDialog;

public class MessageDialogBtnController extends BaseControllerForDialog{
	
	private static Logger log = Logger.getLogger(MessageDialogBtnController.class);
	private MainWindow mainView;
	private MessageDialog msgDialog;
	
	public MessageDialogBtnController(ControllerGenerator listenerGenerator, MainWindow mainView, MessageDialog msgDialog) {
		super();
		this.mainView = mainView;
		this.msgDialog = msgDialog;
		listenerGenerator.setFieldsForDialog(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String btnTxt = arg0.getActionCommand();
		
		if (btnTxt.equals(ButtonNames.OK)){
			msgDialog.setVisible(false);
			msgDialog.clearUi();
		}else if (btnTxt.equals(ButtonNames.GO_TO_MENU)){
			msgDialog.setVisible(false);
			msgDialog.clearUi();
			mainView.initializeEntryScreen();
			mainView.setVisible(true);
		}
		
	}
	

}
