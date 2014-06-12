package client.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import client.controllers.MyBombFielsBtnController;
import client.views.component.PlayerGameBoardPanel;

public class PerksGamePanel extends GamePanelBase {
	private GridLayout layout;
	ArrayList<PlayerGameBoardPanel> playerPanels = new ArrayList<PlayerGameBoardPanel>();
	public PerksGamePanel() {
		super();
	
		drawComponents();
	}

	private void drawComponents() {
		layout = new GridLayout(0, 2);
		setLayout(layout);
	}
	
	public void addPlayer(PlayerGameBoardPanel playerGameBoardPanel) {
		add(playerGameBoardPanel);
		playerPanels.add(playerGameBoardPanel);
		
	}
	
	public void addBombFieldBtnListener(MyBombFielsBtnController listener){
		for (PlayerGameBoardPanel board : playerPanels)
		{
			board.addBombFieldBtnListener(listener);
		}
	}

	public void setFieldAsBomb(int position) {
		playerPanels.get(0).setFieldAsBomb(position);
		
	}

	public void setFieldAsEmpty(int position) {
		playerPanels.get(0).setFieldAsEmpty(position);
		
	}

	public void resetFields() {
		playerPanels.get(0).resetFields();
		
	}

	public void setFieldAsFlagged(int position) {
		playerPanels.get(0).setFieldAsFlagged(position);
		
	}
	
}
