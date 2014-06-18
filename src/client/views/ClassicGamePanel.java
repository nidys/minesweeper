package client.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import client.controllers.MyBombFielsBtnController;
import client.views.GameResultDialog.GameResult;
import client.views.component.PlayerGameBoardPanel;

@SuppressWarnings("serial")
public class ClassicGamePanel extends GamePanelBase {
	private JPanel progressPanel;
	private int progressMaxValue;
	private Map<String, Opponent> opponentsMap = new HashMap<String, Opponent>();
	
	public PlayerGameBoardPanel gameBoard;

	private class Opponent {
		public JLabel nameLabel;
		public JLabel pointsLabel;
		public JProgressBar progressBar;
	}

	public ClassicGamePanel(int progressMaxValue) {
		initUI();
		
		this.progressMaxValue = progressMaxValue;		
	}

	private void initUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		progressPanel = new JPanel();
		GridBagConstraints gbc_progressPanel = new GridBagConstraints();
		gbc_progressPanel.fill = GridBagConstraints.BOTH;
		gbc_progressPanel.gridx = 1;
		gbc_progressPanel.gridy = 0;
		gbc_progressPanel.weightx = 0.5;
		progressPanel.setLayout(new GridBagLayout());
		add(progressPanel, gbc_progressPanel);
	}

	// public void setGameBoard(PlayerGameBoardPanel gameBoard) {
	//
	// }

	public void addOpponent(String opponentName) {
		Opponent opponent = new Opponent();

		opponent.nameLabel = new JLabel(opponentName);
		GridBagConstraints gbc_opponentNameLabel = new GridBagConstraints();
		gbc_opponentNameLabel.gridx = 0;
		gbc_opponentNameLabel.gridy = 2 * opponentsMap.size();
		progressPanel.add(opponent.nameLabel, gbc_opponentNameLabel);

		opponent.pointsLabel = new JLabel("0");
		GridBagConstraints gbc_opponentPointsLabel = new GridBagConstraints();
		gbc_opponentPointsLabel.gridx = 0;
		gbc_opponentPointsLabel.gridy = 2 * opponentsMap.size() + 1;
		progressPanel.add(opponent.pointsLabel, gbc_opponentPointsLabel);

		opponent.progressBar = new JProgressBar(0, progressMaxValue);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 2 * opponentsMap.size();
		gbc_progressBar.gridheight = 2;
		progressPanel.add(opponent.progressBar, gbc_progressBar);

		opponentsMap.put(opponentName, opponent);
	}

	public void removeOpponent(String opponentName) {
		Opponent opponent = opponentsMap.get(opponentName);

		opponentsMap.remove(opponentName);
		progressPanel.remove(opponent.nameLabel);
		progressPanel.remove(opponent.pointsLabel);
		progressPanel.remove(opponent.progressBar);
	}

	public void setProgress(String opponentName, int progressValue) {
		opponentsMap.get(opponentName).progressBar.setValue(progressValue);
		hasOpponentWon(opponentName, progressValue);
	}

	public void hasOpponentWon(String opponentName, int progress) {
		if (progress == progressMaxValue) {
			GameResultDialog gameResultDialog = new GameResultDialog(GameResult.FAIL, opponentName);
			gameResultDialog.setVisible(true);
		}
	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		gameBoard.addBombFieldBtnListener(listener);
	}

	public void setFieldAsBomb(int position) {
		gameBoard.setFieldAsBomb(position);

	}

	public void setFieldAsEmptyWithValue(int position, int value) {
		gameBoard.setFieldAsValued(position, value);

	}

	public void setFieldAsEmpty(int position) {
		gameBoard.setFieldAsEmpty(position);

	}

	public void resetFields() {
		gameBoard.resetFields();

	}

	public void setFieldAsFlagged(int position) {
		gameBoard.setFieldAsFlagged(position);

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

}
