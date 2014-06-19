package client.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import client.controllers.MyBombFielsBtnController;
import client.views.component.PlayerGameBoardPanel;

public class SharedGamePanel extends GamePanelBase {

	private PlayerGameBoardPanel gameBoard;
	
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
	public void addPlayer(PlayerGameBoardPanel playerGameBoardPanel) {
		this.gameBoard = playerGameBoardPanel;

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
	public void setFieldAsBomb(int position) {
		gameBoard.setFieldAsBomb(position);

	}

	@Override
	public void setFieldAsEmpty(int position) {
		gameBoard.setFieldAsEmpty(position);


	}

	@Override
	public void resetFields() {
		gameBoard.resetFields();

	}

	@Override
	public void setFieldAsFlagged(int position) {
		gameBoard.setFieldAsFlagged(position);

	}

	@Override
	public void setFieldAsEmptyWithValue(int position, int value) {
		gameBoard.setFieldAsValued(position, value);

	}

	@Override
	public void setProgress(String opponentName, int progressValue) {
		// TODO Auto-generated method stub

	}

}
