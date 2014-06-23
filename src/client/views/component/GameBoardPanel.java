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
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import client.controllers.BoardFieldBtnController;
import client.internationalization.DescriptionText;
import client.utils.GraphicsFactory;
import common.model.DiscoveredField;
import common.model.GameDifficultyFactors;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {

	private String playerName;
	private int boardSizeX;
	private int boardSizeY;
	private int bombsAmount;
	private int lifeAmount;
	private long gameDuration;
	
	private FieldButton[] board;
	private JPanel statusPanel;
	private JPanel gameBoardPanel;
	private JLabel playerNameLbl;
	private JLabel bombsLbl;
	private JLabel durationLbl;
	private JLabel lifeCountLbl;

	public GameBoardPanel(GameDifficultyFactors gameDifficultyFactors, String playerName, int lifeAmount, long gameDuration) {
		this.playerName = playerName;
		this.boardSizeX = gameDifficultyFactors.getBoardSizeX();
		this.boardSizeY = gameDifficultyFactors.getBoardSizeY();
		this.bombsAmount = gameDifficultyFactors.getBombsAmount();
		this.lifeAmount = lifeAmount;
		this.gameDuration = gameDuration;

		createBasicComponents();
		generateBoard();
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
		gbl_statusPanel.columnWidths = new int[] { 68, 179, 0, 61, 0, 0 };
		gbl_statusPanel.rowHeights = new int[] { 26, 0 };
		gbl_statusPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_statusPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		statusPanel.setLayout(gbl_statusPanel);

		playerNameLbl = new JLabel(this.playerName);
		playerNameLbl.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblUserNick = new GridBagConstraints();
		gbc_lblUserNick.fill = GridBagConstraints.VERTICAL;
		gbc_lblUserNick.insets = new Insets(0, 0, 0, 5);
		gbc_lblUserNick.gridx = 0;
		gbc_lblUserNick.gridy = 0;
		statusPanel.add(playerNameLbl, gbc_lblUserNick);
		
		lifeCountLbl = new JLabel(DescriptionText.LIFES_COUNT + lifeAmount);
		lifeCountLbl.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblLifeCount = new GridBagConstraints();
		gbc_lblLifeCount.fill = GridBagConstraints.VERTICAL;
		gbc_lblLifeCount.insets = new Insets(0, 0, 0, 5);
		gbc_lblLifeCount.gridx = 2;
		gbc_lblLifeCount.gridy = 0;
		statusPanel.add(lifeCountLbl, gbc_lblLifeCount);
		
		if (lifeAmount <= 0)
			lifeCountLbl.setVisible(false);
		else
			lifeCountLbl.setVisible(true);

		bombsLbl = new JLabel("" + this.bombsAmount);
		bombsLbl.setFont(new Font("Segoe WP", Font.BOLD, 11));
		bombsLbl.setIcon(GraphicsFactory.getFlagIcon());
		GridBagConstraints gbc_lblBombs = new GridBagConstraints();
		gbc_lblBombs.fill = GridBagConstraints.VERTICAL;
		gbc_lblBombs.insets = new Insets(0, 0, 0, 5);
		gbc_lblBombs.gridx = 3;
		gbc_lblBombs.gridy = 0;
		statusPanel.add(bombsLbl, gbc_lblBombs);

		
		durationLbl = new JLabel(DescriptionText.DURATION + gameDuration);
		durationLbl.setFont(new Font("Segoe WP", Font.BOLD, 11));
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.fill = GridBagConstraints.VERTICAL;
		gbc_lblDuration.gridx = 4;
		gbc_lblDuration.gridy = 0;
		statusPanel.add(durationLbl, gbc_lblDuration);
		
		if (gameDuration <= 0)
			durationLbl.setVisible(false);
		else
			durationLbl.setVisible(true);

		gameBoardPanel = new JPanel();
		GridBagConstraints gbc_gameBoardPanel = new GridBagConstraints();
		gbc_gameBoardPanel.gridx = 0;
		gbc_gameBoardPanel.gridy = 1;
		add(gameBoardPanel, gbc_gameBoardPanel);
		gameBoardPanel.setLayout(new GridLayout(boardSizeX, boardSizeY));
//		gameBoardPanel.setLayout(new GridLayout(5, 5));

	}

	public void addBombFieldBtnListener(MouseListener listener) {
		for (int i = 0; i < board.length; i++) {
			board[i].addMouseListener(listener);
		}
	}

	public void resetFields() {
		for (int i = 0; i < board.length; i++) {
			board[i].setBackground(null);
			board[i].setText("");
			board[i].setIcon(null);
		}
		enableComponents(gameBoardPanel, true);

	}

	private void generateBoard() {
		board = new FieldButton[boardSizeX * boardSizeY];
		for (int i = 0; i < boardSizeX; ++i) {
			for (int j = 0; j < boardSizeY; ++j) {
				board[i * boardSizeX + j] = createAndAddBombFieldBtn(i, j);
			}
		}
	}

	private FieldButton createAndAddBombFieldBtn(int i, int j) {
		FieldButton btn = new FieldButton(i * boardSizeX + j);
		btn.setPreferredSize(new Dimension(50, 50));
		btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		gameBoardPanel.add(btn);
		return btn;
	}

	public void setField(DiscoveredField field) {
		int fieldValue = field.getValue();
		int position = field.getPosition();
		switch (fieldValue) {
		case -1:
			board[position].setBackground(Color.RED);
			board[position].setIcon(GraphicsFactory.getBombIcon());
			board[position].setPreferredSize(new Dimension(32, 32));
			enableComponents(gameBoardPanel, false);
			break;
		case 0:
			board[position].setBackground(Color.GRAY);
			break;
		case 1:
			board[position].setForeground(Color.BLUE);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 2:
			board[position].setForeground(Color.GREEN);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 3:
			board[position].setForeground(Color.RED);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 4:
			board[position].setForeground(Color.YELLOW);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 5:
			board[position].setForeground(Color.PINK);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 6:
			board[position].setForeground(Color.CYAN);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 7:
			board[position].setForeground(Color.MAGENTA);
			board[position].setText(Integer.toString(fieldValue));
			break;
		case 8:
			board[position].setForeground(Color.ORANGE);
			board[position].setText(Integer.toString(fieldValue));
			break;
		}
	}

	public void setFieldFlagged(int pos) {
		if (!board[pos].isFlagged) { // so its not already discovered field
			board[pos].setBackground(UIManager.getColor("Button.shadow"));
			board[pos].setIcon(GraphicsFactory.getFlagIcon());
			board[pos].setPreferredSize(new Dimension(32, 32));
			bombsLbl.setText((Integer.parseInt(bombsLbl.getText()) - 1) + "");
			board[pos].isFlagged = true;

		} else if (board[pos].isFlagged) { // so we want to unset flag
			board[pos].setBackground(UIManager.getColor("Button.background"));
			board[pos].setIcon(null);
			bombsLbl.setText((Integer.parseInt(bombsLbl.getText()) + 1) + "");
			board[pos].isFlagged = false;
		}
	}

	public void enableComponents(Container container, boolean enable) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			component.setEnabled(enable);
			if (component instanceof Container) {
				enableComponents((Container) component, enable);
			}
		}
	}

	public void setLifeLeft(int lifeLeft) {
		lifeCountLbl.setText(DescriptionText.LIFES_COUNT + lifeLeft + "");
	}

}
