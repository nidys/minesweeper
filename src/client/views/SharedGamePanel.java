package client.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import client.controllers.MyBombFielsBtnController;
import client.views.component.GameBoardPanel;
import common.model.DiscoveredField;

@SuppressWarnings("serial")
public class SharedGamePanel extends GamePanelBase {

	private GameBoardPanel gameBoard;
	
	public SharedGamePanel() {
		initUI();
	}
	
	private void initUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
	}
	
	@Override
	public void addNewPlayerGameBoardPanel(GameBoardPanel gameBoardPanel) {
		this.gameBoard = gameBoardPanel;

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(this.gameBoard, gbc_panel);

	}

	@Override
	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		gameBoard.addBombFieldBtnListener(listener);

	}

		@Override
	public void resetFields() {
		gameBoard.resetFields();

	}

	@Override
	public void setFieldFlagged(int position) {
		gameBoard.setFieldFlagged(position);

	}

	@Override
	public void setField(DiscoveredField field) {
		gameBoard.setField(field);

	}

	@Override
	public void setProgress(String opponentName, int progressValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addOpponentStatus(String opponentName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLifeLeft(int lifeLeft) {
		// TODO Auto-generated method stub
		
	}


}
