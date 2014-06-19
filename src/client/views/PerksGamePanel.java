package client.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import client.controllers.MyBombFielsBtnController;
import client.views.component.GameBoardPanel;

@SuppressWarnings("serial")
public class PerksGamePanel extends GamePanelBase {
	private GridLayout layout;
	ArrayList<GameBoardPanel> playerPanels = new ArrayList<GameBoardPanel>();

	public PerksGamePanel() {
		super();

		drawComponents();
	}

	private void drawComponents() {
		setBounds(0, 0, 450, 301);
		layout = new GridLayout(0, 1);
		setLayout(layout);
	}

	public void addPlayer(GameBoardPanel playerGameBoardPanel) {
		add(playerGameBoardPanel);
		playerPanels.add(playerGameBoardPanel);

	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		for (GameBoardPanel board : playerPanels) {
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
		playerPanels.get(0).setFieldFlagged(position);

	}

	public void setFieldAsEmptyWithValue(int position, int value) {
		playerPanels.get(0).setFieldAsEmptyWithValue(position, value);

	}

	@Override
	public void setProgress(String opponentName, int progressValue) {
		// TODO Auto-generated method stub

	}

}
