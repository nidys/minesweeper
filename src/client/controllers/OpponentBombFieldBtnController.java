package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForField;
import client.utils.ControllerGenerator;
import client.views.component.FieldButton;

public class OpponentBombFieldBtnController extends BaseControllerForField {
	private static Logger log = Logger.getLogger(OpponentBombFieldBtnController.class);

	private boolean pressed;

	public OpponentBombFieldBtnController(ControllerGenerator listenerGenerator) {
		super();
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
		FieldButton button = (FieldButton) arg0.getComponent();
		button.getModel().setArmed(true);
		button.getModel().setPressed(true);
		pressed = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		FieldButton button = (FieldButton) arg0.getComponent();
		button.getModel().setArmed(false);
		button.getModel().setPressed(false);
		int position = button.getPosition();
		if (pressed) {
			if (SwingUtilities.isRightMouseButton(arg0)) {
				if (button.getText() != "F") {
					// TODO COMMIT
					// mainView.setFieldAsFlagged(position);
					info(log, "mainView.setFieldAsFlagged(position);");
				} else {
					// TODO COMMIT
					// mainView.setFieldAsFlagged(position);
					info(log, "mainView.setFieldAsFlagged(position);");
				}
			} else {
				// TODO COMMIT
				// log.debug("Clicked field, user=" + gameState.getUserNick());

				// Result res = netManager.shot(gameState.getUserNick(),
				// position);
				// if (res == Result.BOMB) {
				// // TODO COMMIT
				// //mainView.setFieldAsBomb(position);
				// System.out.println("mainView.setFieldAsBomb(position);");
				// } else {
				// // TODO COMMIT
				// //mainView.setFieldAsEmpty(position);
				// System.out.println("mainView.setFieldAsEmpty(position);");
				// }
			}
		}
		pressed = false;

	}
}
