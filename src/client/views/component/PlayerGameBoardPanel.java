package client.views.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import client.controllers.MyBombFielsBtnController;
import client.utils.GraphicsFactory;
import common.model.GameDifficultyFactors;

@SuppressWarnings("serial")
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
		generatePlayeGameBoard();
	}

	private void createBasicComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
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
		gbl_statusPanel.columnWidths = new int[] { 0, 68, 111, 98, 179, 61, 0, 0 };
		gbl_statusPanel.rowHeights = new int[] { 26, 0 };
		gbl_statusPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_statusPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		statusPanel.setLayout(gbl_statusPanel);

		lblUserNick = new JLabel(this.userNick);
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
		gameBoardPanel.setLayout(new GridLayout(boardSizeX, boardSizeY, 0, 10));

	}

	private void generatePlayeGameBoard() {

		board = new FieldButton[boardSizeX * boardSizeY];
		for (int i = 0; i < boardSizeX; ++i) {
			for (int j = 0; j < boardSizeY; ++j) {
				board[i * boardSizeX + j] = createAndAddBombFieldBtn(i, j);
			}
		}

	}

	private FieldButton createAndAddBombFieldBtn(int i, int j) {
		FieldButton btn = new FieldButton(i * boardSizeX + j);
		btn.setPreferredSize(new Dimension(40, 40));
		btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		gameBoardPanel.add(btn);
		return btn;
	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		for (int i = 0; i < board.length; i++) {
			board[i].addMouseListener(listener);
		}
	}

	public void setFieldAsBomb(int pos) {
		board[pos].setBackground(Color.RED);
		board[pos].setIcon(GraphicsFactory.getBombIcon());
		board[pos].setPreferredSize(new Dimension(32, 32));
		board[pos].setText("");
		lblFace.setIcon(GraphicsFactory.getDeadFaceIcon());
		enableComponents(gameBoardPanel, false);
	}

	public void setFieldAsValued(int pos, int value) {
		board[pos].setText(Integer.toString(value));
	}

	public void setFieldAsEmpty(int pos) {
		board[pos].setBackground(Color.GRAY);
	}

	public void resetFields() {
		
		for (int i = 0; i < board.length; i++) {
			board[i].setBackground(null);
			board[i].setText("");
			board[i].setIcon(null);
		}
		enableComponents(gameBoardPanel, true);
	}

	public void setFieldAsFlagged(int pos) {
		if (!board[pos].isFlagged) { // so its not already discovered field
			board[pos].setBackground(UIManager.getColor("Button.shadow"));
			board[pos].setIcon(GraphicsFactory.getFlagIcon());
			board[pos].setPreferredSize(new Dimension(32, 32));

			lblBombs.setText((Integer.parseInt(lblBombs.getText()) - 1) + "");
			board[pos].isFlagged = true;

		} else if (board[pos].isFlagged) { // so we want to unset flag
			board[pos].setBackground(UIManager.getColor("Button.background"));
			board[pos].setIcon(null);
			
			lblBombs.setText((Integer.parseInt(lblBombs.getText()) + 1) + "");
			
			board[pos].isFlagged = false;
		} else {

		}
	}

	public void setFieldAsEmptyWithValue(int pos, int value) {
		switch (value)
		{
			case 1:
				board[pos].setForeground(Color.BLUE);
				break;
			case 2:
				board[pos].setForeground(Color.GREEN);
				break;
			case 3:
				board[pos].setForeground(Color.RED);
				break;
			case 4:
				board[pos].setForeground(Color.YELLOW);
				break;
			case 5:
				board[pos].setForeground(Color.PINK);
				break;
			default :
				break;
		}
		board[pos].setText("" + value);
	}

	public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
	
}
