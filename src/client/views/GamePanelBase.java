package client.views;

import javax.swing.JPanel;

import client.controllers.MyBombFielsBtnController;
import client.views.component.PlayerGameBoardPanel;

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
