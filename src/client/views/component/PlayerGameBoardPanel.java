package client.views.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import common.model.GameDifficultyFactors;


import client.controllers.MyBombFielsBtnController;
import client.utils.Locator;
import client.views.MainWindow;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PlayerGameBoardPanel extends JPanel {

	private String userNick;

	private int boardSizeX = 5;
	private int boardSizeY = 5;
	private int bombsNumber;
	private long duration;
	private FieldButton[] myBombField;
	private JPanel statusPanel;
	private int statsuPanelSize;
	private JPanel gameBoardPanel;
	private JLabel lblUserNick;
	private JLabel lblBombs;
	private JLabel lblDuration;
	private JButton button;


	public PlayerGameBoardPanel(GameDifficultyFactors gameDifficultyFactors, String userNick) {
		
//		this.userNick = "user";
//		this.boardSizeX = 16;
//		this.boardSizeY = 16;
//		this.bombsNumber = 40;
//		this.duration = 100;
		this.userNick = userNick;
		this.boardSizeX = gameDifficultyFactors.getBoardSizeX();
		this.boardSizeY = gameDifficultyFactors.getBoardSizeY();
		this.bombsNumber = gameDifficultyFactors.getBombsNumber();
		this.duration = 100;
		
		createBasicComponents();
		generateBoard();
	}



	private void createBasicComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_statusPanel = new GridBagConstraints();
		gbc_statusPanel.insets = new Insets(0, 0, 5, 0);
		gbc_statusPanel.fill = GridBagConstraints.BOTH;
		gbc_statusPanel.gridx = 0;
		gbc_statusPanel.gridy = 0;
		add(statusPanel, gbc_statusPanel);
		GridBagLayout gbl_statusPanel = new GridBagLayout();
		gbl_statusPanel.columnWidths = new int[]{0, 68, 179, 61, 0, 0};
		gbl_statusPanel.rowHeights = new int[]{26, 0};
		gbl_statusPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_statusPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		statusPanel.setLayout(gbl_statusPanel);

		
		lblUserNick = new JLabel("UserNick");
		lblUserNick.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblUserNick = new GridBagConstraints();
		gbc_lblUserNick.fill = GridBagConstraints.VERTICAL;
		gbc_lblUserNick.gridwidth = 2;
		gbc_lblUserNick.insets = new Insets(0, 0, 0, 5);
		gbc_lblUserNick.gridx = 0;
		gbc_lblUserNick.gridy = 0;
		statusPanel.add(lblUserNick, gbc_lblUserNick);
		
		lblBombs = new JLabel("" + this.bombsNumber);
		lblBombs.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblBombs = new GridBagConstraints();
		gbc_lblBombs.fill = GridBagConstraints.BOTH;
		gbc_lblBombs.insets = new Insets(0, 0, 0, 5);
		gbc_lblBombs.gridx = 3;
		gbc_lblBombs.gridy = 0;
		statusPanel.add(lblBombs, gbc_lblBombs);
		
		lblDuration = new JLabel("Duration: " + this.duration);
		lblDuration.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.fill = GridBagConstraints.BOTH;
		gbc_lblDuration.gridx = 4;
		gbc_lblDuration.gridy = 0;
		statusPanel.add(lblDuration, gbc_lblDuration);
		
		gameBoardPanel = new JPanel();
		GridBagConstraints gbc_gameBoardPanel = new GridBagConstraints();
		gbc_gameBoardPanel.gridx = 0;
		gbc_gameBoardPanel.gridy = 1;
		add(gameBoardPanel, gbc_gameBoardPanel);
		GridBagLayout gbl_gameBoardPanel = new GridBagLayout();
//		gbl_gameBoardPanel.columnWidths = new int[]{0};
//		gbl_gameBoardPanel.rowHeights = new int[]{0};
//		gbl_gameBoardPanel.columnWeights = new double[]{Double.MIN_VALUE};
//		gbl_gameBoardPanel.rowWeights = new double[]{Double.MIN_VALUE};
		gameBoardPanel.setLayout(gbl_gameBoardPanel);
		

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
		gbc_btn.fill = GridBagConstraints.BOTH;
		gbc_btn.gridx = gridx;
		gbc_btn.gridy = gridy;
		gameBoardPanel.add(btn, gbc_btn);
		
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
