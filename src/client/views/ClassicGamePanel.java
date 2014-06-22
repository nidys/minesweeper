package client.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import client.controllers.BoardFieldBtnController;
import client.views.component.GameBoardPanel;

import common.model.DiscoveredField;

@SuppressWarnings("serial")
public class ClassicGamePanel extends GamePanelBase {
	private JPanel progressPanel;
	private int progressMaxValue;
	private Map<String, Opponent> opponentsMap = new HashMap<String, Opponent>();
	
	private int playerPoints = 0;
	
	public GameBoardPanel gameBoard;

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
		gbc_progressPanel.anchor = GridBagConstraints.NORTH;
		gbc_progressPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressPanel.gridx = 1;
		gbc_progressPanel.gridy = 0;
		gbc_progressPanel.weightx = 0.5;
		progressPanel.setLayout(new GridBagLayout());
		add(progressPanel, gbc_progressPanel);
	}


	public void addOpponentStatus(String opponentName) {
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
		
		if (progressValue == 0){
			playerPoints += Integer.parseInt(opponentsMap.get(opponentName).pointsLabel.getText());
		}
		
		opponentsMap.get(opponentName).pointsLabel.setText(progressValue + playerPoints + "");
		
		hasOpponentWon(opponentName, progressValue);
	}

	public void hasOpponentWon(String opponentName, int progress) {
		if (progress == progressMaxValue) {
			MessageDialog gameResultDialog = new MessageDialog(null, false, common.enums.GameResult.LOSE, opponentName, "You loose and gain XXX points", "You loose.");
			gameResultDialog.setVisible(true);
		}
	}

	public void addBombFieldBtnListener(BoardFieldBtnController listener) {
		gameBoard.addBombFieldBtnListener(listener);
	}

	

	@Override
	public void setField(DiscoveredField field) {
		gameBoard.setField(field);

	}

	public void resetFields() {
		gameBoard.resetFields();

	}

	@Override
	public void setFieldFlagged(int position) {
		gameBoard.setFieldFlagged(position);

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
	public void setLifeLeft(int lifeLeft) {
		gameBoard.setLifeLeft(lifeLeft);
		
	}

}
