package client.views;

import static client.internationalization.ButtonNames.NEW_GAME;

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import client.controllers.MyBombFielsBtnController;
import client.internationalization.ButtonNames;
import client.internationalization.DialogText;
import client.utils.GraphicsFactory;
import client.views.component.PlayerGameBoardPanel;

import common.enums.GameMode;
import common.network.ServerAddress;

/**
 * View for the Main component (view with the game board). Standard window.
 */
@SuppressWarnings("serial")
public class MainWindow extends WindowBase {
	private static GraphicsFactory graphicsFactory = new GraphicsFactory();

	private JButton newGameBtn;
	private JButton btnReset;
	private JTextField serverAddress;
	private JTextField userNick;

	private GamePanelBase gamePanel;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Minesweeper");
		// TODO extract to constant!!!
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainWindow.class.getResource("/resources/images/bomb.png")));
		setResizable(false);
		drawEntryScreen();
		// drawGameBoard();
	}

	public void drawGameBoard() {
		clearWindow();
		drawComponents();
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

		btnReset = new JButton(ButtonNames.RESET);
		menuBar.add(btnReset);
		getContentPane().setBounds(0, 0, 450, 301);
		this.setResizable(true);
	}

	private void drawEntryScreen() {
		setBounds(100, 100, 463, 288);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 1, 55, 86, 31, 41, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 80, 10, 29, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 8;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 0;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);

		ImageIcon logo = graphicsFactory.getLogoIcon();
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

		JLabel lblServerAdress = new JLabel("Server adress:");
		GridBagConstraints gbc_lblServerAdress = new GridBagConstraints();
		gbc_lblServerAdress.fill = GridBagConstraints.BOTH;
		gbc_lblServerAdress.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerAdress.gridx = 2;
		gbc_lblServerAdress.gridy = 3;
		getContentPane().add(lblServerAdress, gbc_lblServerAdress);

		serverAddress = new JTextField();
		serverAddress.setText(ServerAddress.LOCALHOST.getValue());
		serverAddress.setColumns(10);
		GridBagConstraints gbc_serverAddress = new GridBagConstraints();
		gbc_serverAddress.fill = GridBagConstraints.BOTH;
		gbc_serverAddress.insets = new Insets(0, 0, 5, 5);
		gbc_serverAddress.gridx = 3;
		gbc_serverAddress.gridy = 3;
		getContentPane().add(serverAddress, gbc_serverAddress);

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

		JLabel lblUserName = new JLabel("User name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 2;
		gbc_lblUserName.gridy = 4;
		getContentPane().add(lblUserName, gbc_lblUserName);

		userNick = new JTextField();
		// generate random user name so that user don't need to write it all the
		// time
		userNick.setText("user" + String.valueOf(new Random().nextInt(100)));
		userNick.setColumns(10);
		GridBagConstraints gbc_userNick = new GridBagConstraints();
		gbc_userNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_userNick.anchor = GridBagConstraints.NORTH;
		gbc_userNick.insets = new Insets(0, 0, 5, 5);
		gbc_userNick.gridx = 3;
		gbc_userNick.gridy = 4;
		getContentPane().add(userNick, gbc_userNick);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.gridwidth = 8;
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 5;
		getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);

		Dimension d = this.getLayout().preferredLayoutSize(this);
		this.setSize(d);
		this.setResizable(false);
	}

	/**
	 * @param listener
	 *            Listener for an action when the user clicks the "New Game"
	 *            button.
	 */
	public void addNewGameBtnListener(ActionListener listener) {
		newGameBtn.addActionListener(listener);
	}

	public void addResetBtnListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}

	public String getServerAddress() {
		return serverAddress.getText();
	}

	public String getUserNick() {
		return userNick.getText();
	}

	// TODO MALY COMMIT
	public void setMyFieldAsBomb(int pos) {
		// TODO from now shot return list of fields to discover
		// myBombField[pos - 1].setBackground(Color.RED);
	}

	public void initializeGameBoard(GameMode mode) {
		if (mode == GameMode.PERKS) {
			gamePanel = new PerksGamePanel();
			getContentPane().add(gamePanel);
			this.setTitle(DialogText.MAINWINDOW_PERKS_TITLE);
		} else if (mode == GameMode.CLASSIC) {
			// TODO AGA, for testing here
			gamePanel = new ClassicGamePanel(100);
			getContentPane().add(gamePanel);
			this.setTitle(DialogText.MAINWINDOW_CLASSIC_TITLE);
		} else if (mode == GameMode.SHARED) {
			this.setTitle(DialogText.MAINWINDOW_SHARED_TITLE);
		} else {

		}
	}

	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		gamePanel.addBombFieldBtnListener(listener);
	}

	// TODO Aga -> change this method to sth like
	// create/addNewPlayerGameBoardPanel
	public void addPlayer(PlayerGameBoardPanel playerGameBoardPanel) {
		gamePanel.addPlayer(playerGameBoardPanel);
		Dimension d = this.getLayout().preferredLayoutSize(this);
		this.setSize(d);
	}

	// TODO -> change it to sth more "changeable" so it will be showing
	private void displayEndGameMessage() {
		int option = JOptionPane.showConfirmDialog(this, DialogText.END_GAME_TITLE,
				DialogText.END_GAME_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (option == 0) { // YES

		} else if (option == 1) { // NO

		}

	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY COMMIT
	public void setOpponentAsBomb(int pos) {
		// oponentBombField[pos - 1].setBackground(Color.RED);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY COMMIT
	public void setOpponentAsEmpty(int pos) {
		// oponentBombField[pos - 1].setBackground(Color.GRAY);
	}

	public void resetFields() {
		gamePanel.resetFields();
	}

	public void setFieldAsFlagged(int position) {
		gamePanel.setFieldAsFlagged(position);
	}

	public void setFieldAsEmpty(int position) {
		gamePanel.setFieldAsEmpty(position);
	}

	public void setFieldAsEmptyWithValue(int position, int value) {
		gamePanel.setFieldAsEmptyWithValue(position, value);
	}

	public void setFieldAsBomb(int position) {
		gamePanel.setFieldAsBomb(position);

		// TODO Aga Delete magic strings (use internationalization)
		displayEndGameMessage();
	}

	public void setProgress(String userNick, int progressValue) {
		gamePanel.setProgress(userNick, progressValue);
	}
}
