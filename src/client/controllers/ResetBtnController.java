package client.controllers;

import static common.utils.LoggingHelper.info;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import org.apache.log4j.Logger;
import client.controllers.base.BaseControllerForWindow;
import client.utils.ControllerGenerator;

public class ResetBtnController extends BaseControllerForWindow {
	private static Logger log = Logger.getLogger(ResetBtnController.class);

	public ResetBtnController(ControllerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		info(log, "Sending reset board");
		try {
			netManager.resetBoard(gameState.getUserNick());
			mainView.resetFields();
		} catch (RemoteException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
