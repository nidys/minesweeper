package client.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import client.controllers.MyBombFielsBtnController;
import client.views.component.PlayerGameBoardPanel;

@SuppressWarnings("serial")
public class PerksGamePanel extends GamePanelBase {
	private GridLayout layout;
	ArrayList<PlayerGameBoardPanel> playerGameBoardPanels = new ArrayList<PlayerGameBoardPanel>();

	public PerksGamePanel() {
		super();

		drawComponents();
	}

	private void drawComponents() {
		setBounds(0, 0, 450, 301);
		layout = new GridLayout(0, 1);
		setLayout(layout);
	}

	public void addPlayer(PlayerGameBoardPanel playerGameBoardPanel) {
		add(playerGameBoardPanel);
		playerGameBoardPanels.add(playerGameBoardPanel);

	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		playerGameBoardPanels.get(playerGameBoardPanels.size() - 1).addBombFieldBtnListener(listener);
		
	}

	public void setFieldAsBomb(int position) {
		playerGameBoardPanels.get(0).setFieldAsBomb(position);

	}

	public void setFieldAsEmpty(int position) {
		playerGameBoardPanels.get(0).setFieldAsEmpty(position);

	}

	public void resetFields() {
		playerGameBoardPanels.get(0).resetFields();

	}

	public void setFieldAsFlagged(int position) {
		playerGameBoardPanels.get(0).setFieldAsFlagged(position);

	}

	public void setFieldAsEmptyWithValue(int position, int value) {
		playerGameBoardPanels.get(0).setFieldAsEmptyWithValue(position, value);

	}

	@Override
	public void setProgress(String opponentName, int progressValue) { 
		// TODO Auto-generated method stub

	}

}
