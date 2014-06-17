package client.views.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import common.model.GameDifficultyFactors;


import client.controllers.MyBombFielsBtnController;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class PlayerGameBoardPanel extends JPanel {
	
	private String userNick;

	private int boardSizeX = 5;
	private int boardSizeY = 5;
	private int bobmsNumber;
	private long duration;

	private JLabel lblUsernick;
	private JLabel lblBombs;
	private JPanel boardPanel;
	private FieldButton[] myBombField;
	
	

	public PlayerGameBoardPanel(GameDifficultyFactors gameDifficultyFactors) {
		this.boardSizeX = gameDifficultyFactors.getBoardSizeX(); // Currently we're assuming game board as a square.
		this.boardSizeY = gameDifficultyFactors.getBoardSizeY(); // Currently we're assuming game board as a square.
		this.bobmsNumber = gameDifficultyFactors.getBombsNumber();
		createBasicComponents();
		generateBoard();
	}
	


	private void createBasicComponents() {
		setLayout(null);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBounds(0, 0, 450, 32);
		add(statusPanel);
		statusPanel.setLayout(null);
		
		lblUsernick = new JLabel(userNick);
		lblUsernick.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsernick.setBounds(0, 0, 111, 32);
		statusPanel.add(lblUsernick);
		
		JLabel lblClock = new JLabel(""+duration);
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setForeground(Color.RED);
		lblClock.setBackground(Color.BLUE);
		lblClock.setBounds(339, 0, 111, 32);
		statusPanel.add(lblClock);
		
		lblBombs = new JLabel("Bombs");
		lblBombs.setHorizontalAlignment(SwingConstants.CENTER);
		lblBombs.setForeground(Color.BLUE);
		lblBombs.setBounds(218, 0, 111, 32);
		statusPanel.add(lblBombs);
		
		boardPanel = new JPanel();
		boardPanel.setBounds(0, 31, 450, 269);
		add(boardPanel);
		boardPanel.setLayout(new GridLayout(1, 0, 0, 0));
	}

	private void generateBoard(){
		myBombField = new FieldButton[boardSizeX*boardSizeY];
		for (int i = 0; i < boardSizeX; ++i){
			for (int j = 0; j < boardSizeY; ++j){
				myBombField[i*boardSizeX+j] = createBombFieldBtn(i*boardSizeX+j+1+"", 0, 0, 5, 5, j, i);
			}
		}
		
	}
	
	private FieldButton createBombFieldBtn(String name, int top, int left, int bottom, int right, int gridx, int gridy) {
		FieldButton btn = new FieldButton(gridy*boardSizeX+gridx + 1);
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

	public void setFieldAsEmptyWithValue(int pos, int value) {
		myBombField[pos-1].setText(""+value);
	}

	
	
	
}
