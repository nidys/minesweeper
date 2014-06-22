package client.views;

import javax.swing.JPanel;

import common.model.DiscoveredField;

import client.controllers.BoardFieldBtnController;
import client.views.component.GameBoardPanel;

// TODO when all will be working -> change it to interface and change method names so anybody will now whats going on inside them
@SuppressWarnings("serial")
public abstract class GamePanelBase extends JPanel {

	public abstract void addNewPlayerGameBoardPanel(GameBoardPanel playerGameBoardPanel);

	public abstract void addBombFieldBtnListener(BoardFieldBtnController listener);

	public abstract void resetFields();

	public abstract void setProgress(String opponentName, int progressValue);

	public abstract void setField(DiscoveredField field);

	public abstract void setFieldFlagged(int position);
	
	public abstract void addOpponentStatus(String opponentName);

	public abstract void setLifeLeft(int lifeLeft);

}
