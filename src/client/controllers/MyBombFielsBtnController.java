package client.controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForField;
import client.utils.ControllerGenerator;
import client.views.component.FieldButton;

import common.exceptions.shot.PositionOutOfRange;
import common.model.Result;
import common.model.ShotResult;
import client.controllers.base.BaseControllerForWindow;
import client.utils.ControllerGenerator;


public class MyBombFielsBtnController extends BaseControllerForField {
	private static Logger log = Logger.getLogger(MyBombFielsBtnController.class);
	private boolean pressed;
	
	public MyBombFielsBtnController(ControllerGenerator listenerGenerator) {
		listenerGenerator.setFieldsForWindow(this);
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		pressed = true;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		pressed = false;

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		FieldButton button = (FieldButton)arg0.getComponent();
		button.getModel().setArmed(true);
		button.getModel().setPressed(true);
		pressed = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		FieldButton button = (FieldButton)arg0.getComponent();
		button.getModel().setArmed(false);
		button.getModel().setPressed(false);
		int position = button.getPosition();
		if (pressed) {
			if (SwingUtilities.isRightMouseButton(arg0)) {
				if (button.getText() != "F" && button.getBackground() != Color.GRAY){
					// TODO MALY COMMIT
					//mainView.setFieldAsFlagged(position);
				}
				else{
					// TODO MALY COMMIT
					//mainView.setFieldAsFlagged(position);
				}
			}
			else {
				
				log.debug("Clicked field, user=" + gameState.getUserNick());
				if (button.getText() == "F"){
					return;
				}
				log.debug("position=" + position);
//				try {
//					List<ShotResult> res = netManager.shot(gameState.getUserNick(), position);
//					
//				} catch (RemoteException | PositionOutOfRange e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
		pressed = false;

	}

}
