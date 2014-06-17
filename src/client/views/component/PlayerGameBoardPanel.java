package client.views.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import common.model.GameDifficultyFactors;

import client.controllers.MyBombFielsBtnController;

public class PlayerGameBoardPanel extends JPanel {
	
	private String userNick;
	private int boardSize;
	private int bombsAmount;
	private int time;
	private JLabel lblUsernick;
	private JLabel lblBombs;
	private JPanel boardPanel;
	private FieldButton[] myBombField;
	
	
	public PlayerGameBoardPanel(GameDifficultyFactors gameDifficultyFactors) {
		this.boardSize = gameDifficultyFactors.getBoardSizeX(); // Currently we're assuming game board as a square.
		this.bombsAmount = gameDifficultyFactors.getBombsNumber();
		createBasicComponents();
		generateBoard();
	}
	
	private void createBasicComponents() {
		setLayout(null);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBounds(0, 0, 450, 32);
		add(statusPanel);
		statusPanel.setLayout(null);
		
		lblUsernick = new JLabel("Usernick");
		lblUsernick.setBounds(0, 11, 46, 14);
		statusPanel.add(lblUsernick);
		
		JLabel lblClock = new JLabel("Clocks");
		lblClock.setForeground(Color.RED);
		lblClock.setBackground(Color.BLUE);
		lblClock.setBounds(362, 0, 88, 25);
		statusPanel.add(lblClock);
		
		lblBombs = new JLabel("Bombs");
		lblBombs.setForeground(Color.BLUE);
		lblBombs.setBounds(274, 0, 78, 25);
		statusPanel.add(lblBombs);
		
		boardPanel = new JPanel();
		boardPanel.setBounds(0, 31, 450, 269);
		add(boardPanel);
		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[] { 0, 0, 0};
//		gridBagLayout.rowHeights = new int[] { 0, 0, 0};
//		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
//		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		boardPanel.setLayout(gridBagLayout);
	}

	private void generateBoard(){
		myBombField = new FieldButton[boardSize*boardSize];
		for (int i = 0; i < boardSize; ++i){
			for (int j = 0; j < boardSize; ++j){
				myBombField[i*boardSize+j] = createBombFieldBtn(i*boardSize+j+1+"", 0, 0, 5, 5, j, i);
			}
		}
		
	}
	
	private FieldButton createBombFieldBtn(String name, int top, int left, int bottom, int right, int gridx, int gridy) {
		FieldButton btn = new FieldButton(gridy*boardSize+gridx + 1);
		btn.setText(name);
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.gridx = gridx;
		gbc_btn.gridy = gridy;
		boardPanel.add(btn, gbc_btn);
		return btn;
	}
	
	
	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		for (int i = 0; i < myBombField.length; i++) {
			myBombField[i].addMouseListener(listener);
		}
	}
	

	public void setFieldAsBomb(int pos) {
		myBombField[pos-1].setBackground(Color.RED);
	}

	public void setFieldAsValued(int pos, int value) {
		myBombField[pos-1].setText(Integer.toString(value));
	}
	
	public void setFieldAsEmpty(int pos) {
		myBombField[pos-1].setBackground(Color.GRAY);
	}


	public void resetFields() {
		for (int i = 0; i < myBombField.length; i++) {
			myBombField[i].setBackground(null);
		}
	}

	public void setFieldAsFlagged(int pos) {
		if (myBombField[pos-1].getText() != "F" && myBombField[pos-1].getBackground() != Color.GRAY){
			myBombField[pos-1].setText("F");
			myBombField[pos-1].setBackground(Color.BLUE);
		}
		else{
			myBombField[pos-1].setText(""+pos);
			myBombField[pos-1].setBackground(Color.GRAY);
		}
	}

	
	
	
}
