package client.controllers;

import static common.utils.LoggingHelper.info;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import client.controllers.base.BaseControllerForField;
import client.utils.ControllerGenerator;
import client.views.component.FieldButton;

import common.exceptions.shot.PositionOutOfRange;
import common.model.DiscoveredFields;
import common.model.ShotResult;

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
				// TODO Check if the button is to able to be flagged
				mainView.setFieldAsFlagged(position);
			} else {
				try {
					ShotResult shotResult = netManager.shot(gameState.getUserNick(), position);
					// TODO handle other fields, should game be continued,
					// update inside clock etc.
					List<DiscoveredFields> results = shotResult.getUnrevealed();
					for (DiscoveredFields result : results)
						setField(result);

					DiscoveredFields result = results.get(0);
					info(log, "Clicked field, user[%s], pos[%d], val[%d]", gameState.getUserNick(),
							result.getPosition(), result.getValue());
				} catch (RemoteException | PositionOutOfRange e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		pressed = false;
	}

	private void setField(DiscoveredFields result) {
		int fieldValue = result.getValue();
		switch (fieldValue) {
		case -1:
			mainView.setFieldAsBomb(result.getPosition());
			break;
		case 0:
			mainView.setFieldAsEmpty(result.getPosition());
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			mainView.setFieldAsEmptyWithValue(result.getPosition(), fieldValue);
			break;
		}
	}
}
