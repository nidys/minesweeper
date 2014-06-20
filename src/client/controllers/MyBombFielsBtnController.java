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
import common.model.DiscoveredField;
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
				mainView.setFieldFlagged(position);
			} else {
				try {
					ShotResult shotResult = netManager.shot(gameState.getUserNick(), position);
					// TODO handle other fields, should game be continued,
					// update inside clock etc.
					List<DiscoveredField> results = shotResult.getUnrevealed();
					
					//TODO Maly ? Sometimes after reset results is null.
					if (results == null)
						return;
					
					for (DiscoveredField result : results)
						mainView.setField(result);

					DiscoveredField result = results.get(0);
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
}
