package client.controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForField;
import client.utils.ListenerGenerator;
import client.views.component.FieldButton;
import common.model.Result;

public class MyBombFielsBtnController extends BaseControllerForField {
	private static Logger log = Logger.getLogger(MyBombFielsBtnController.class);

	private boolean pressed;
	public MyBombFielsBtnController(ListenerGenerator listenerGenerator) {
		super();
		listenerGenerator.setFieldsForWindow(this);
	}
	//
	//	@Override
	//	public void actionPerformed(ActionEvent e) {
	//		log.debug("Clicked my bomb field, user=" + gameState.getUserNick());
	//		int position = Integer.valueOf(e.getActionCommand());
	//		Result res = netManager.shot(gameState.getUserNick(), position);
	//		if (res == Result.BOMB) {
	//			mainView.setMyFieldAsBomb(position);
	//		} else {
	//			mainView.setMyFieldAsEmpty(position);
	//		}
	//	}

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
					mainView.setFieldAsFlagged(position);
				}
				else{
					mainView.setFieldAsFlagged(position);
				}
			}
			else {
				
				log.debug("Clicked field, user=" + gameState.getUserNick());
				if (button.getText() == "F"){
					return;
				}
				log.debug("position=" + position);
				Result res = netManager.shot(gameState.getUserNick(), position);
				if (res == Result.BOMB) {
					mainView.setFieldAsBomb(position);
				}
				else{
					mainView.setFieldAsEmpty(position);
				}
			}
		}
		pressed = false;

	}

}
