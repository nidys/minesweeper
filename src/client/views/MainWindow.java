package client.views;

import static client.internationalization.ButtonNames.NEW_GAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import client.controllers.MyBombFielsBtnController;
import client.internationalization.ButtonNames;

import client.internationalization.DialogText;

import client.utils.GraphicsFactory;

import client.views.component.PlayerGameBoardPanel;

import common.enums.GameMode;
import common.network.ServerAddress;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.UIManager;
import java.awt.Toolkit;

/**
 * View for the Main component (view with the game board). Standard window.
 */
@SuppressWarnings("serial")
public class MainWindow extends WindowBase {
	private static Logger log = Logger.getLogger(MainWindow.class);
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/images/bomb.png")));
		setResizable(false);
		drawEntryScreen();
		//drawGameBoard();
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
						getContentPane().setBounds(0,0,450,301);
	}


	private void drawEntryScreen() {
		setBounds(100, 100, 463, 288);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{43, 1, 55, 86, 31, 41, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{34, 1, 36, 29, 20, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblMinesweeper = new JLabel(graphicsFactory.getLogoIcon());
		lblMinesweeper.setText("");
		lblMinesweeper.setFont(new Font("Tahoma", Font.PLAIN, 56));
		GridBagConstraints gbc_lblMinesweeper = new GridBagConstraints();
		gbc_lblMinesweeper.gridwidth = 7;
		gbc_lblMinesweeper.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMinesweeper.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinesweeper.gridx = 1;
		gbc_lblMinesweeper.gridy = 1;
		getContentPane().add(lblMinesweeper, gbc_lblMinesweeper);
		
		JLabel lblServerAdress = new JLabel("Server adress:");
		GridBagConstraints gbc_lblServerAdress = new GridBagConstraints();
		gbc_lblServerAdress.fill = GridBagConstraints.VERTICAL;
		gbc_lblServerAdress.anchor = GridBagConstraints.WEST;
		gbc_lblServerAdress.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerAdress.gridwidth = 2;
		gbc_lblServerAdress.gridx = 1;
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
				gbc_newGameBtn.fill = GridBagConstraints.VERTICAL;
				gbc_newGameBtn.gridwidth = 2;
				gbc_newGameBtn.gridheight = 2;
				gbc_newGameBtn.anchor = GridBagConstraints.WEST;
				gbc_newGameBtn.insets = new Insets(0, 0, 5, 5);
				gbc_newGameBtn.gridx = 6;
				gbc_newGameBtn.gridy = 3;
				getContentPane().add(newGameBtn, gbc_newGameBtn);
		
		JLabel lblUserName = new JLabel("User name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.gridwidth = 2;
		gbc_lblUserName.anchor = GridBagConstraints.WEST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 1;
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
		getContentPane().validate();
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
	
	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		gamePanel.addBombFieldBtnListener(listener);
	}


	public void addNewPlayerToView(PlayerGameBoardPanel playerGameBoardPanel) {
		gamePanel.addPlayer(playerGameBoardPanel);
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
		//myBombField[pos - 1].setBackground(Color.RED);
	}
	
	public void initializeGameBoard(GameMode mode) {
		if (mode == GameMode.PERKS){
			gamePanel = new PerksGamePanel();
			getContentPane().add(gamePanel);
		}else if (mode == GameMode.CLASSIC){
			// TODO AGA, for testing here
			gamePanel = new ClassicGamePanel(100);
			getContentPane().add(gamePanel);
		}else if (mode == GameMode.SHARED){
			
		}else{
			
		}
	}
	

	public void setFieldAsBomb(int position) {
		gamePanel.setFieldAsBomb(position);
	
		// TODO Aga Delete magic strings (use internationalization)
		displayEndGameMessage();
		
	}
	

	private void displayEndGameMessage()
	{
		int option = JOptionPane.showConfirmDialog(this,
			    DialogText.END_GAME_TITLE,
			    DialogText.END_GAME_MESSAGE,
			    JOptionPane.YES_NO_OPTION);
		
		if (option == 0){ //YES
			
		}else if (option == 1){ //NO
			
		}
		
	}
	
	
	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY COMMIT
	public void setOpponentAsBomb(int pos) {
		//oponentBombField[pos - 1].setBackground(Color.RED);
	}

	public void setFieldAsEmpty(int position) {
		gamePanel.setFieldAsEmpty(position);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY COMMIT
	public void setOpponentAsEmpty(int pos) {
		//oponentBombField[pos - 1].setBackground(Color.GRAY);
	}

	public void resetFields() {
		gamePanel.resetFields();
	}
	
	public void setFieldAsFlagged(int position) {
		gamePanel.setFieldAsFlagged(position);
	}
	
	public void setFieldAsEmptyWithValue(int position, int value){
		gamePanel.setFieldAsEmptyWithValue(position, value);
	}
}
