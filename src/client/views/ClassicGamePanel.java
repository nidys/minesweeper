package client.views;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import client.controllers.MyBombFielsBtnController;
import client.views.component.PlayerGameBoardPanel;

import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

public class ClassicGamePanel extends GamePanelBase {
	private JPanel progressPanel;
	private int progressMaxValue;
	private Map<String, Opponent> opponentsMap;
	
	private class Opponent {
		public JLabel nameLabel;
		public JLabel pointsLabel;
		public JProgressBar progressBar;
	}
	
	public ClassicGamePanel(int progressMaxValue) {
		initUI();
		
		this.progressMaxValue = progressMaxValue;
		
		opponentsMap = new HashMap<String, Opponent>();
		
		//test
		JPanel gameBoard = new JPanel();
		addGameBoard(gameBoard);
		addOpponent("test");
		addOpponent("test2");
		setProgress("test", 15);
		removeOpponent("test2");
	}
	
	private void initUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
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
	
	public void addGameBoard(JPanel gameBoard) {
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(gameBoard, gbc_panel);
	}
	
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
		
		if (opponent != null) {
			opponentsMap.remove(opponentName);
			progressPanel.remove(opponent.nameLabel);
			progressPanel.remove(opponent.pointsLabel);
			progressPanel.remove(opponent.progressBar);
		} else {
			//TODO exception
		}
	}
	
	public void setProgress(String opponentName, int progressValue) {
		opponentsMap.get(opponentName).progressBar.setValue(progressValue);
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrame frame = new JFrame();
//					frame.setBounds(50, 50, 300, 300);
//					ClassicGamePanel gamePanel = new ClassicGamePanel(100);
//					frame.add(gamePanel);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	@Override
	public void addPlayer(PlayerGameBoardPanel playerGameBoardPanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFieldAsBomb(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFieldAsEmpty(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFieldAsFlagged(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFieldAsEmptyWithValue(int position, int value) {
		// TODO Auto-generated method stub
		
	}
}
