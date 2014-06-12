package client.views.component;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import client.controllers.MyBombFielsBtnController;
import client.controllers.OpponentBombFieldBtnController;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Color;

public class PlayerGameBoardPanel extends JPanel {
	
	private String userNick;
	private int boardSize = 4;
	private int bobmsNum;
	private int time;
	private JLabel lblUsernick;
	private JLabel lblBombs;
	private JPanel boardPanel;
	private FieldButton[] myBombField;
	
	
	public PlayerGameBoardPanel() {
		createBasicComponents();
		generateBoard();
	}

	private void generateBoard(){
		myBombField = new FieldButton[boardSize*boardSize];
		for (int i = 0; i < boardSize; ++i){
			for (int j = 0; j < boardSize; ++j){
				createBombFieldBtn(""+i*boardSize+j, 0, 0, 5, 5, i, j);
			}
		}
		
	}
	
	private FieldButton createBombFieldBtn(String name, int top, int left, int bottom, int right, int gridx, int gridy) {
		FieldButton btn = new FieldButton(gridx*boardSize+gridy + 1);
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.insets = new Insets(top, left, bottom, right);
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
	
	public void addOponentFieldBtnListener(OpponentBombFieldBtnController opponentBombFieldBtnController) {
//		for (int i = 0; i < oponentBombField.length; i++) {
//			oponentBombField[i].addMouseListener(opponentBombFieldBtnController);
//		}
	}


	public void setFieldAsBomb(int pos) {
		myBombField[pos - 1].setBackground(Color.RED);
	}

	public void setFieldAsEmpty(int pos) {
		myBombField[pos - 1].setBackground(Color.GRAY);
	}


	public void resetFields() {
		for (int i = 0; i < myBombField.length; i++) {
			myBombField[i].setBackground(null);
		}
	}

	public void setFieldAsFlagged(int pos) {
		if (myBombField[pos - 1].getText() != "F")
		{
			myBombField[pos - 1].setText("F");
			myBombField[pos - 1].setBackground(Color.BLUE);
		}
		else{
			myBombField[pos - 1].setText(""+pos);
			myBombField[pos - 1].setBackground(Color.GRAY);
		}
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
		GridBagLayout gbl_boardPanel = new GridBagLayout();
		gbl_boardPanel.columnWidths = new int[]{0};
		gbl_boardPanel.rowHeights = new int[]{0};
		gbl_boardPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_boardPanel.rowWeights = new double[]{Double.MIN_VALUE};
		boardPanel.setLayout(gbl_boardPanel);
	}
}
