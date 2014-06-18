package client.views.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import client.controllers.MyBombFielsBtnController;
import client.utils.GraphicsFactory;
import common.model.GameDifficultyFactors;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class PlayerGameBoardPanel extends JPanel {

	private String userNick;
	private int boardSizeX = 5;
	private int boardSizeY = 5;
	private int bombsNumber;
	private long duration;
	private FieldButton[] board;
	private JPanel statusPanel;
	private JPanel gameBoardPanel;
	private JLabel lblUserNick;
	private JLabel lblBombs;
	private JLabel lblDuration;
	private JLabel lblFace;

	public PlayerGameBoardPanel(GameDifficultyFactors gameDifficultyFactors, String userNick) {
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
		statusPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		GridBagConstraints gbc_statusPanel = new GridBagConstraints();
		gbc_statusPanel.insets = new Insets(0, 0, 5, 0);
		gbc_statusPanel.fill = GridBagConstraints.BOTH;
		gbc_statusPanel.gridx = 0;
		gbc_statusPanel.gridy = 0;
		add(statusPanel, gbc_statusPanel);
		GridBagLayout gbl_statusPanel = new GridBagLayout();
		gbl_statusPanel.columnWidths = new int[]{0, 68, 111, 98, 179, 61, 0, 0};
		gbl_statusPanel.rowHeights = new int[]{26, 0};
		gbl_statusPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE};
		gbl_statusPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		statusPanel.setLayout(gbl_statusPanel);

		lblUserNick = new JLabel("UserNick");
		lblUserNick.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblUserNick = new GridBagConstraints();
		gbc_lblUserNick.fill = GridBagConstraints.BOTH;
		gbc_lblUserNick.gridwidth = 2;
		gbc_lblUserNick.insets = new Insets(0, 0, 0, 5);
		gbc_lblUserNick.gridx = 0;
		gbc_lblUserNick.gridy = 0;
		statusPanel.add(lblUserNick, gbc_lblUserNick);
		
		lblFace = new JLabel("");
		lblFace.setIcon(GraphicsFactory.getHappyFaceIcon());
		GridBagConstraints gbc_lblFace = new GridBagConstraints();
		gbc_lblFace.insets = new Insets(0, 0, 0, 5);
		gbc_lblFace.gridx = 3;
		gbc_lblFace.gridy = 0;
		statusPanel.add(lblFace, gbc_lblFace);
		
		
		lblBombs = new JLabel("" + this.bombsNumber);
		lblBombs.setFont(new Font("Segoe WP", Font.BOLD, 11));
		lblBombs.setIcon(GraphicsFactory.getFlagIcon());
		GridBagConstraints gbc_lblBombs = new GridBagConstraints();
		gbc_lblBombs.fill = GridBagConstraints.BOTH;
		gbc_lblBombs.insets = new Insets(0, 0, 0, 5);
		gbc_lblBombs.gridx = 5;
		gbc_lblBombs.gridy = 0;
		statusPanel.add(lblBombs, gbc_lblBombs);

		lblDuration = new JLabel("Duration: " + this.duration);
		lblDuration.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.fill = GridBagConstraints.BOTH;
		gbc_lblDuration.gridx = 6;
		gbc_lblDuration.gridy = 0;
		statusPanel.add(lblDuration, gbc_lblDuration);

		gameBoardPanel = new JPanel();
		GridBagConstraints gbc_gameBoardPanel = new GridBagConstraints();
		gbc_gameBoardPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_gameBoardPanel.gridx = 0;
		gbc_gameBoardPanel.gridy = 1;
		add(gameBoardPanel, gbc_gameBoardPanel);
		gameBoardPanel.setLayout(new GridLayout(boardSizeX, boardSizeY));

	}

	private void generateBoard() {
		
		
		
		board = new FieldButton[boardSizeX * boardSizeY];
		for (int i = 0; i < boardSizeX; ++i) {
			for (int j = 0; j < boardSizeY; ++j) {
				board[i * boardSizeX + j] = createBombFieldBtn(i * boardSizeX
						+ j + 1 + "", i, j);
			}
		}

	}

	private FieldButton createBombFieldBtn(String name, int i, int j) {
		FieldButton btn = new FieldButton(i * boardSizeX + j + 1);
		btn.setPreferredSize(new Dimension(50, 50));
		gameBoardPanel.add(btn);
		btn.setText(name);


		return btn;
	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		for (int i = 0; i < board.length; i++) {
			board[i].addMouseListener(listener);
		}
	}

	public void setFieldAsBomb(int pos) {
		board[pos - 1].setBackground(Color.RED);
		board[pos - 1].setIcon(GraphicsFactory.getBombIcon());
		board[pos - 1].setPreferredSize(new Dimension(32,32));
		board[pos - 1].setText("");
		lblFace.setIcon(GraphicsFactory.getDeadFaceIcon());
	}

	public void setFieldAsValued(int pos, int value) {
		board[pos - 1].setText(Integer.toString(value));
	}

	public void setFieldAsEmpty(int pos) {
		board[pos - 1].setBackground(Color.GRAY);
	}

	public void resetFields() {
		for (int i = 0; i < board.length; i++) {
			board[i].setBackground(null);
		}
	}

	public void setFieldAsFlagged(int pos) {
		if (!board[pos - 1].isFlagged) { // so its not already discovered field
			//board[pos - 1].setText("F");
			board[pos - 1].setBackground(UIManager.getColor("Button.shadow"));
			board[pos - 1].setText("");
			board[pos - 1].setIcon(GraphicsFactory.getFlagIcon());
			board[pos - 1].setPreferredSize(new Dimension(32,32));
			
			
			lblBombs.setText((Integer.parseInt(lblBombs.getText())-1) + "");
			board[pos - 1].isFlagged = true;
			
		} else if (board[pos - 1].isFlagged){ // so we want to unset flag
			board[pos - 1].setText("" + pos);
			board[pos - 1].setBackground(UIManager.getColor("Button.background"));
			board[pos - 1].setIcon(null);
			lblBombs.setText((Integer.parseInt(lblBombs.getText())+1) + "");
			board[pos - 1].isFlagged = false;
		}else {
			
		}
	}

	public void setFieldAsEmptyWithValue(int pos, int value) {
		board[pos - 1].setText("" + value);
	}

}
