package client.views;

import javax.swing.JPanel;

import client.controllers.MyBombFielsBtnController;
import client.views.component.PlayerGameBoardPanel;

// TODO when all will be working -> change it to interface and change method names so anybody will now whats going on inside them
@SuppressWarnings("serial")
public abstract class GamePanelBase extends JPanel {

	public abstract void addPlayer(PlayerGameBoardPanel playerGameBoardPanel);

	public abstract void addBombFieldBtnListener(MyBombFielsBtnController listener);

	public abstract void setFieldAsBomb(int position);

	public abstract void setFieldAsEmpty(int position);

	public abstract void resetFields();

	public abstract void setFieldAsFlagged(int position);

	public abstract void setFieldAsEmptyWithValue(int position, int value);

	public abstract void setProgress(String opponentName, int progressValue);

}
