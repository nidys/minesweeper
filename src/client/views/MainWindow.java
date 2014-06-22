package client.views;

import static client.internationalization.ButtonNames.NEW_GAME;
import static common.utils.LoggingHelper.info;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import client.controllers.BoardFieldBtnController;
import client.internationalization.ButtonNames;
import client.internationalization.DescriptionText;
import client.internationalization.DialogText;
import client.utils.GraphicsFactory;
import client.views.component.GameBoardPanel;
import common.enums.GameMode;
import common.enums.GameResult;
import common.model.DiscoveredField;
import common.model.GameSummary;
import common.model.LostReason;
import common.network.ServerAddress;

/**
 * View for the Main component (view with the game board). Standard window.
 */
@SuppressWarnings("serial")
public class MainWindow extends WindowBase {
	private static Logger log = Logger.getLogger(MainWindow.class);
	private JButton newGameBtn;
	private JButton resetBtn;
	private JTextField serverAddressTxtFld;
	private JTextField playerNameTxtFld;
	private GamePanelBase gamePanel;
	private GameRoomDialog gameRoomDialog;
	private boolean resetEnable = true;

	public MainWindow() {
		setTitle(DialogText.MAINWINDOW_TITLE);
		//TODO
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/images/bomb.png")));
		setResizable(false);
		drawEntryScreen();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

	public void initializeGameBoard(GameMode mode, int progressMaxValue) {
		clearWindow();
		drawComponents();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		drawGameBoard(mode, progressMaxValue);
	}

	private void clearWindow() {
		getContentPane().removeAll();
		getContentPane().repaint();
	}

	public void drawComponents() {
		setBounds(100, 100, 456, 356);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		resetBtn = new JButton(ButtonNames.RESET);
		menuBar.add(resetBtn);
		resetBtn.setEnabled(resetEnable);
		getContentPane().setBounds(0, 0, 450, 301);
		this.setResizable(true);
	}

	private void drawEntryScreen() {
		setBounds(100, 100, 463, 288);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 1, 55, 86, 31, 41, 0, 20,
				0 };
		gridBagLayout.rowHeights = new int[] { 10, 80, 10, 29, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 8;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 0;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);

		ImageIcon logo = GraphicsFactory.getLogoIcon();
		JLabel lblMinesweeper = new JLabel(logo);
		lblMinesweeper.setSize(logo.getIconWidth(), logo.getIconHeight());

		GridBagConstraints gbc_lblMinesweeper = new GridBagConstraints();
		gbc_lblMinesweeper.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinesweeper.gridwidth = 6;
		gbc_lblMinesweeper.fill = GridBagConstraints.BOTH;
		gbc_lblMinesweeper.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinesweeper.gridx = 1;
		gbc_lblMinesweeper.gridy = 1;
		getContentPane().add(lblMinesweeper, gbc_lblMinesweeper);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.gridwidth = 8;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 2;
		getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);

		JLabel lblServerAdress = new JLabel(DescriptionText.SERVER_ADDRESS);
		GridBagConstraints gbc_lblServerAdress = new GridBagConstraints();
		gbc_lblServerAdress.fill = GridBagConstraints.BOTH;
		gbc_lblServerAdress.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerAdress.gridx = 2;
		gbc_lblServerAdress.gridy = 3;
		getContentPane().add(lblServerAdress, gbc_lblServerAdress);

		serverAddressTxtFld = new JTextField();
		serverAddressTxtFld.setText(ServerAddress.LOCALHOST.getValue());
		serverAddressTxtFld.setColumns(10);
		GridBagConstraints gbc_serverAddress = new GridBagConstraints();
		gbc_serverAddress.fill = GridBagConstraints.BOTH;
		gbc_serverAddress.insets = new Insets(0, 0, 5, 5);
		gbc_serverAddress.gridx = 3;
		gbc_serverAddress.gridy = 3;
		getContentPane().add(serverAddressTxtFld, gbc_serverAddress);

		newGameBtn = new JButton(NEW_GAME);
		newGameBtn.setBackground(UIManager.getColor("Button.background"));
		GridBagConstraints gbc_newGameBtn = new GridBagConstraints();
		gbc_newGameBtn.anchor = GridBagConstraints.WEST;
		gbc_newGameBtn.fill = GridBagConstraints.VERTICAL;
		gbc_newGameBtn.gridwidth = 2;
		gbc_newGameBtn.gridheight = 2;
		gbc_newGameBtn.insets = new Insets(0, 0, 5, 5);
		gbc_newGameBtn.gridx = 5;
		gbc_newGameBtn.gridy = 3;
		getContentPane().add(newGameBtn, gbc_newGameBtn);

		JLabel lblUserName = new JLabel(DescriptionText.USER_NAME);
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 2;
		gbc_lblUserName.gridy = 4;
		getContentPane().add(lblUserName, gbc_lblUserName);

		playerNameTxtFld = new JTextField();
		// generate random user name so that user don't need to write it all the
		// time
		playerNameTxtFld.setText("user"	+ String.valueOf(new Random().nextInt(100)));
		playerNameTxtFld.setColumns(10);
		GridBagConstraints gbc_userNick = new GridBagConstraints();
		gbc_userNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_userNick.anchor = GridBagConstraints.NORTH;
		gbc_userNick.insets = new Insets(0, 0, 5, 5);
		gbc_userNick.gridx = 3;
		gbc_userNick.gridy = 4;
		getContentPane().add(playerNameTxtFld, gbc_userNick);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.gridwidth = 8;
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 5;
		getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);

		Dimension d = this.getLayout().preferredLayoutSize(this);
		setSize(d);
		setResizable(false);
	}

	public void addNewGameBtnListener(ActionListener listener) {
		newGameBtn.addActionListener(listener);
	}

	public void addResetBtnListener(ActionListener listener) {
		resetBtn.addActionListener(listener);
	}

	public void addBombFieldBtnListener(BoardFieldBtnController listener) {
		gamePanel.addBombFieldBtnListener(listener);
	}

	public String getServerAddress() {
		return serverAddressTxtFld.getText();
	}

	public String getPlayerName() {
		return playerNameTxtFld.getText();
	}

	public void addNewPlayerGameBoardPanel(GameBoardPanel gameBoardPanel) {
		gamePanel.addNewPlayerGameBoardPanel(gameBoardPanel);
		Dimension d = this.getLayout().preferredLayoutSize(this);
		setSize(d);
		setMinimumSize(d);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY Check if it's obsolete
	public void setOpponentAsBomb(int pos) {
		// oponentBombField[pos - 1].setBackground(Color.RED);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY Check if it's obsolete
	public void setOpponentAsEmpty(int pos) {
		// oponentBombField[pos - 1].setBackground(Color.GRAY);
	}

	public void setProgress(String userNick, int progressValue) {
		gamePanel.setProgress(userNick, progressValue);
	}

	public void resetFields() {
		gamePanel.resetFields();
	}

	public void setFieldFlagged(int position) {
		gamePanel.setFieldFlagged(position);
	}

	public void setField(DiscoveredField field) {
		gamePanel.setField(field);
	}

	private void drawGameBoard(GameMode mode, int progressMaxValue) {
		switch (mode) {
		case CLASSIC:	
			gamePanel = new ClassicGamePanel(progressMaxValue);
			setTitle(DialogText.MAINWINDOW_CLASSIC_TITLE);
			break;
		case PERKS:
			gamePanel = new PerksGamePanel();
			setTitle(DialogText.MAINWINDOW_PERKS_TITLE);
			break;
		case SHARED:
			gamePanel = new SharedGamePanel();
			setTitle(DialogText.MAINWINDOW_SHARED_TITLE);
		}
		getContentPane().add(gamePanel);
	}

	public GameRoomDialog getGameRoomDialog() {
		return gameRoomDialog;
	}
	
	public void setGameRoomDialog(GameRoomDialog gameRoomDialog) {
		this.gameRoomDialog = gameRoomDialog;
	}


	public void addOpponentStatus(String playerName) {
		gamePanel.addOpponentStatus(playerName);
		pack();
	}

	public void setLifeLeft(int lifeLeft) {
		gamePanel.setLifeLeft(lifeLeft);

	}

	public void setResetEnable(boolean enable) {
		info(log, "setResetEnabled");
		resetEnable = enable;

		if (resetBtn != null)
			resetBtn.setEnabled(resetEnable);
	}

	public void displayLostReason(LostReason reason) {
		System.out.println("Display lost reason in GameResultDialog");
		
		
		MessageDialog gameResultDialog = null;
		switch (reason.getReasonMsg()) {
			case NO_BOARDS_LEFT :
				gameResultDialog = new MessageDialog(this, true, GameResult.LOSE, reason.getPlayerNick(), "No boards left, you loose and gain XXX points",
						"No boards left");
				break;
			case NO_LIFES :
				gameResultDialog = new MessageDialog(this, true, common.enums.GameResult.LOSE, reason.getPlayerNick(), "No lifes left, you loose and gain XXX points",
						"No lifes left");
				break;
			case NORMAL_MODE_LOST :
				gameResultDialog = new MessageDialog(this, true, common.enums.GameResult.LOSE, reason.getPlayerNick(), "You loose and gain XXX points", "You loose.");
				break;
			case PLAYER_LEFT_BEFORE_END :
				// gameResultDialog = new GameResultDialog(this, true,
				// GameResult.FAIL, reason.getPlayerNick(),
				// "No boards left, you loose and gain XXX points",
				// "No boards left");
				break;
			default :
				break;
		}
		if (gameResultDialog != null)
			gameResultDialog.setVisible(true);

	}

	public void displayEndGameSummary(GameSummary gameSummary) {
		System.out.println("Display end game summary in GameResultDialog");
		MessageDialog gameResultDialog = new MessageDialog(this, true, gameSummary.getGameResult(), "playerName", "message", "title");
		gameResultDialog.setVisible(true);
	}

}
